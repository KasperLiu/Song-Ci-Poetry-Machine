package scnu.student.songcimachine.songcipoetrymachine.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kasper on 2016/7/8.
 */
/**
 * 两字词语数据库Helper
 *
 * Created by 伟龙 on 2016/7/8.
 */
public class OneDBHelper extends SQLiteOpenHelper {

    /**
     * 数据库名称
     */
    private static final String DB_NAME = "oneText.db";

    /**
     * 数据库版本
     */
    private static final int DB_VERSION = 1;

    /**
     * 建表语句
     */
    private static final String CREATE_TABLE = "create table if not exists oneText" +
            "( _id integer primary key autoincrement,idx integer, word text,weight integer )";

    /**
     * 删除表语句
     */
    private static final String DROP_TABLE = "drop table if exists oneText";

    /**
     * 单例
     */
    private static OneDBHelper mInstance;


    private OneDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public static OneDBHelper getInstance(Context context) {

        if (mInstance == null) {
            synchronized (OneDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new OneDBHelper(context);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        db.execSQL(CREATE_TABLE);

    }

}
