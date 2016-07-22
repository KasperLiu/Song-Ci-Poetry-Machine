package scnu.student.songcimachine.songcipoetrymachine.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kasper on 2016/7/16.
 */
public class SongCiDBHelper extends SQLiteOpenHelper {

    /**
     * 数据库名称
     */
    private static final String DB_NAME = "SongCi.db";

    /**
     * 数据库版本
     */
    private static final int DB_VERSION = 1;

    /**
     * 建表语句
     */
    private static final String CREATE_TABLE = "create table if not exists SongCi" +
            "( _id integer primary key autoincrement, ciPai text, title text, content text, " +
            "firstSentence text, editTime integer)";

    /**
     * 删除表语句
     */
    private static final String DROP_TABLE = "drop table if exists SongCi";

    /**
     * 单例
     */
    private static SongCiDBHelper mInstance;

    /**
     * 构造方法，调用父类SQLiteOpenHelper的构造函数
     * 参1：上下文环境；参2:数据库名称(以.db结尾) ; 参3：游标工厂(默认为null) ;
     * 参4：代表使用数据库模型版本的证书
     * @param context
     */
    private SongCiDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 单例模式
     * @param context
     * @return
     */
    public static SongCiDBHelper getInstance(Context context) {

        if (mInstance == null) {
            synchronized (SongCiDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new SongCiDBHelper(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 更新数据库
     * 将数据库从旧的模型转换为新的模型  参1：对象   ； 参2：旧版本号 ； 参3：新版本号
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO 更改数据库版本的操作
        db.execSQL(DROP_TABLE);
        // 重新新建
        db.execSQL(CREATE_TABLE);
    }

    /**
     * 创建数据库
     * 根据需要对SQLiteDatabase 的对象填充表和数据初始化
     * 该方法时在第一次创建的时候执行，实际上时第一次得到SQLiteDatabase对象的时侯才会调用这个方法
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO 创建数据库后，对数据库的操作
        db.execSQL(CREATE_TABLE);
    }

    /**
     * 打开数据库
     * 打开数据库执行的函数
     * @param db
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        // TODO 每次成功打开数据库后首先被执行
        super.onOpen(db);
    }

}
