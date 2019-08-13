package com.kaiwukj.android.communityhui.app.impl;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.kaiwukj.android.communityhui.BuildConfig;
import com.kaiwukj.android.communityhui.hx.DemoHelper;
import com.kaiwukj.android.communityhui.utils.ImagePickerLoad;
import com.kaiwukj.android.communityhui.utils.NineGridImageLoader;
import com.kaiwukj.android.mcas.base.delegate.AppLifecycles;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.ninegrid.NineGridView;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.header.FalsifyHeader;

import java.lang.reflect.Field;

import androidx.multidex.MultiDex;
import cn.jpush.android.api.JPushInterface;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;
import timber.log.Timber;

import static me.jessyan.autosize.utils.LogUtils.isDebug;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc
 */
public class AppLifecyclesImpl implements AppLifecycles {

    @Override
    public void attachBaseContext(Context base) {
        MultiDex.install(base);
    }

    @Override
    public void onCreate(Application application) {
        initTimber();
        initTextFaceType(application);
        //qmui arch 初始化
        QMUISwipeBackActivityManager.init(application);
        //AutoSize
        AutoSizeConfig.getInstance().getUnitsManager().setSupportDP(true).setSupportSubunits(Subunits.PT);
        /*-----ARouterSDK初始化Start-----*/
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application); // 尽可能早，推荐在Application中初始化
        /*---------ARouterSDK初始化End-----------*/

//        Fragmentation.builder()
//                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
//                .stackViewMode(Fragmentation.BUBBLE)
//                .debug(BuildConfig.DEBUG)
//                // 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
//                .handleException(e -> {
//                    // 建议在该回调处上传至我们的Crash监测服务器
//                    // 以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
//                    // Bugtags.sendException(e);
//                })
//                .install();
        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(application);

        DemoHelper.getInstance().init(application);
        //图片选择器
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImagePickerLoad());
        imagePicker.setShowCamera(true);
        imagePicker.setMultiMode(true);
        imagePicker.setCrop(false);
        imagePicker.setSaveRectangle(true);
        imagePicker.setOutPutX(1000);
        imagePicker.setOutPutY(1000);
        //九宫格图片控件
        NineGridView.setImageLoader(new NineGridImageLoader());

        /*环信*/
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
        //初始化
        EMClient.getInstance().init(application, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);

        EaseUI.getInstance().init(application.getApplicationContext(), options);
    }

    private void initTimber() {
        if (BuildConfig.LOG_DEBUG) {
            //Timber日志打印
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    public void onTerminate(Application application) {
    }

    /**
     * 配置全局字体
     */
    private void initTextFaceType(Application application) {
        Typeface typeface = Typeface.createFromAsset(application.getAssets(), "PingFangSC-Regular.ttf");
        try {
            Field field = Typeface.class.getDeclaredField("MONOSPACE");
            field.setAccessible(true);
            field.set(null, typeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> new FalsifyHeader(context));
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new FalsifyFooter(context);
        });
    }


}
