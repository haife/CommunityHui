package com.kaiwukj.android.communityhui.utils;


import com.kaiwukj.android.communityhui.mvp.http.entity.bean.SubImageBean;
import com.kaiwukj.android.communityhui.mvp.listener.OnSubDataUpdateListener;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QiNiuUtil {

    private OnSubDataUpdateListener listener;

    public QiNiuUtil(OnSubDataUpdateListener listener) {
        this.listener = listener;
    }

    private List<SubImageBean> urls = new ArrayList<>();
    private List<String> filePaths;
    private int i = 0;

    public void uploadImagesToQiniu(List<String> filePaths, String token) {
        this.filePaths = filePaths;
        uploadImage(filePaths.get(0), token);
    }

    public void uploadImageToQiniu(String filePath, String token) {
        uploadImage(filePath, token);
    }

    /**
     * 上传图片到七牛
     *
     * @param filePath 要上传的图片路径
     * @param token    在七牛官网上注册的token
     */
    private synchronized void uploadImage(final String filePath, final String token) {
        UploadManager uploadManager = new UploadManager();
        // 设置图片名字
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String key = "icon_" + sdf.format(new Date()) + ".jpg";

//        LogUtils.i("key ---> " + key);
//        LogUtils.i("filePath ---> " + filePath);

        uploadManager.put(filePath, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                // info.error中包含了错误信息，可打印调试
                // 上传成功后将key值上传到自己的服务器
//                LogUtils.i("key ---> " + key);
//                LogUtils.i("info ---> " + info);
//                LogUtils.i("res ---> " + res);
                String url =  key;

//                LogUtils.i("url ---> " + url);

                SubImageBean subImageBean = new SubImageBean();
                subImageBean.setType(i + 1 +"");
                subImageBean.setImgUrl(url);
                urls.add(subImageBean);
                i++;
                if(filePaths.size()>urls.size()){
                    uploadImageToQiniu(filePaths.get(i), token);
                }
                if (filePaths.size() == urls.size()) {
                    listener.onUpdateUrls(urls);
                }
            }
        }, new UploadOptions(null, null, false,
                new UpProgressHandler() {
                    public void progress(String key, double percent) {
//                        Log.i("qiniu", key + ": " + percent);
//                        LogUtils.i(key + ": " + percent);
                    }
                }, null));
    }
}
