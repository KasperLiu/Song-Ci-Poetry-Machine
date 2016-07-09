package scnu.student.songcimachine.songcipoetrymachine.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import scnu.student.songcimachine.songcipoetrymachine.bean.WordInfo;
import scnu.student.songcimachine.songcipoetrymachine.database.OneDBHelper;
import scnu.student.songcimachine.songcipoetrymachine.database.TwoDBHelper;

/**
 * Created by Kasper on 2016/7/8.
 */
/**
 * 数据库操作类
 * <p/>
 * Created by 伟龙 on 2016/7/8.
 */
public class DBManager {


    /**
     * one 为两字词语
     * two 为三字词语
     */
    public static enum Type {
        one, two
    }

    private Type mType;
    private SQLiteOpenHelper mHelper;
    private SQLiteDatabase mDataBase;
    private Context mContext;

    public DBManager(Context context, Type type) {
        mContext = context;

        if (type == Type.one) {
            mHelper = OneDBHelper.getInstance(context);
            mType = Type.one;
        } else if (type == Type.two) {
            mHelper = TwoDBHelper.getInstance(context);
            mType = Type.two;
        }
    }


    /**
     * 判断数据库是否为空
     *
     * @return
     */
    public boolean isDbEmpty() {
        mDataBase = mHelper.getReadableDatabase();
        Cursor cursor = null;
        if (mType == Type.one) {
            cursor = mDataBase.rawQuery("select * from oneText", new String[]{});
        } else if (mType == Type.two) {
            cursor = mDataBase.rawQuery("select * from twoText", new String[]{});
        }
        if (cursor.moveToNext()) {
            return false;
        }
        return true;
    }


    /**
     * 插入词语
     *
     * @param info
     */
    public void insertWord(WordInfo info) {
        mDataBase = mHelper.getWritableDatabase();
        if (mType == Type.one) {
            mDataBase.execSQL("insert into oneText(idx,word,weight) values(? ,?, ?)",
                    new Object[]{info.getIndex(), info.getText(), info.getWeight()});
        } else if (mType == Type.two) {
            mDataBase.execSQL("insert into twoText(idx,word,weight) values(? ,?, ?)",
                    new Object[]{info.getIndex(), info.getText(), info.getWeight()});
        }
        mDataBase.close();
    }


    /**
     * 根据唯一数字标志获取词语
     *
     * @param index
     * @return
     */
    public String getText(int index) {

        String text = null;

        mDataBase = mHelper.getReadableDatabase();
        Cursor cursor = null;
        if (mType == Type.one) {
            cursor = mDataBase.rawQuery("select * from oneText where idx = ?", new String[]{index + ""});
        } else if (mType == Type.two) {
            cursor = mDataBase.rawQuery("select * from twoText where idx = ?", new String[]{index + ""});
        }
        if (cursor.moveToNext()) {
            text = cursor.getString(cursor.getColumnIndex("word"));
        }
        return text;
    }

    /**
     * 根据唯一数字标志获取词语的权重
     *
     * @param index
     * @return
     */
    public int getWeight(int index) {
        int weight;

        mDataBase = mHelper.getReadableDatabase();

        Cursor cursor = null;
        if (mType == Type.one) {
            cursor = mDataBase.rawQuery("select * from oneText where idx = ?", new String[]{index + ""});
        } else if (mType == Type.two) {
            cursor = mDataBase.rawQuery("select * from twoText where idx = ?", new String[]{index + ""});
        }

        if (cursor.moveToNext()) {
            weight = cursor.getInt(cursor.getColumnIndex("weight"));
            return weight;
        }
        return -1;
    }


    public boolean deleteDatabase(){
            return mContext.deleteDatabase(mHelper.getDatabaseName());
    }

    /**
     * 获取数据库类型
     * @return
     */
    public Type getType() {
        return mType;
    }
}