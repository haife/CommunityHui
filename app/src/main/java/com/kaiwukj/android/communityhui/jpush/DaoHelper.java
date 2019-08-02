package com.kaiwukj.android.communityhui.jpush;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kaiwukj.android.communityhui.greendao.gen.DaoMaster;
import com.kaiwukj.android.communityhui.greendao.gen.DaoSession;
import com.kaiwukj.android.communityhui.greendao.gen.MessageEntityDao;


/**
 * @author Charles
 * @date 2019/8/2.
 * description：Kaiwu
 */
public class DaoHelper {

    public static MessageEntityDao getMessageDao(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "recluse-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        return daoSession.getMessageEntityDao();
    }
}
