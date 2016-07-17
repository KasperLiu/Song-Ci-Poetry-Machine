package scnu.student.songcimachine.songcipoetrymachine.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import scnu.student.songcimachine.songcipoetrymachine.bean.SongCi;
import scnu.student.songcimachine.songcipoetrymachine.database.SongCiDBHelper;

/**
 * Created by Kasper on 2016/7/17.
 */
public class SongCiDBManager {

    private SQLiteOpenHelper mHelper;
    private SQLiteDatabase mDataBase;
    private Context mContext;

    private String selectAll = "select * from SongCi";
    private String selectAllByDate = "select * from SongCi order by editTime desc";
    // 插入id时应该设置为null
    private String insertSentence = "insert into SongCi(_id,ciPai,title,content,firstSentence," +
            "editTime)" + " values(?, ?, ?, ?, ?, ?)";
    private String dropTabe = "DROP TABLE SongCi";

    public SongCiDBManager(Context context) {
        mContext = context;
        mHelper = SongCiDBHelper.getInstance(mContext);
            }

    /**
     * 向数据库添加宋词
     *
     * @param info
     */
    public void add(SongCi info) {
        mDataBase = mHelper.getWritableDatabase();
        mDataBase.execSQL(insertSentence,
                    new Object[]{null,info.getCiPai(),info.getTitle(),info.getContent(),
                            info.getFirstSentence(),info.getEditTime()});
        mDataBase.close();
    }

    /**
     * 更新数据库，按ID更新
     * @param info
     */
    public void update(SongCi info){
        mDataBase = mHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", info.getTitle());
        cv.put("content", info.getContent());
        cv.put("firstSentence", info.getFirstSentence());
        cv.put("editTime", info.getEditTime());
        mDataBase.update("SongCi", cv, "_id = ?", new String[]{String.valueOf(info.getId())});
        mDataBase.close();
    }

    /**
     * 删除宋词，根据宋词ID删除
     * @param info
     */
    public void delete(SongCi info){
        mDataBase = mHelper.getWritableDatabase();
        mDataBase.delete("SongCi", "_id = ?", new String[]{String.valueOf(info.getId())});
        mDataBase.close();
    }

    /**
     * 查询所有的数据
     * @return List<Person>
     */
    public List<SongCi> queryAll() {
        ArrayList<SongCi> songCis = new ArrayList<SongCi>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            SongCi info = new SongCi();
            info.setId(c.getInt(c.getColumnIndex("_id")));
            info.setCiPai(c.getString(c.getColumnIndex("ciPai")));
            info.setTitle(c.getString(c.getColumnIndex("title")));
            info.setContent(c.getString(c.getColumnIndex("content")));
            info.setFirstSentence(c.getString(c.getColumnIndex("firstSentence")));
            info.setEditTime(c.getLong(c.getColumnIndex("editTime")));
            songCis.add(info);
        }
        c.close();
        return songCis;
    }

    public List<SongCi> queryAllByDate(){
        ArrayList<SongCi> songCis = new ArrayList<SongCi>();
        Cursor c = queryTheCursorByDate();
        while (c.moveToNext()) {
            SongCi info = new SongCi();
            info.setId(c.getInt(c.getColumnIndex("_id")));
            info.setCiPai(c.getString(c.getColumnIndex("ciPai")));
            info.setTitle(c.getString(c.getColumnIndex("title")));
            info.setContent(c.getString(c.getColumnIndex("content")));
            info.setFirstSentence(c.getString(c.getColumnIndex("firstSentence")));
            info.setEditTime(c.getLong(c.getColumnIndex("editTime")));
            songCis.add(info);
        }
        c.close();
        return songCis;
    }

    /**
     * 查询所有的宋词
     * 按ID主键排序
     * @return  Cursor
     */
    public Cursor queryTheCursor() {
        mDataBase = mHelper.getReadableDatabase();
        return mDataBase.rawQuery(selectAll, null);
    }

    /**
     * 查询所有的宋词
     * 根据时间降序排序
     * @return  Cursor
     */
    public Cursor queryTheCursorByDate() {
        mDataBase = mHelper.getReadableDatabase();
        return mDataBase.rawQuery(selectAllByDate, null);
    }

    /**
     * 关闭数据库
     */
    public void closeDB(){
        mDataBase.close();
    }

    public void DropTable(){
        mDataBase = mHelper.getWritableDatabase();
        mDataBase.execSQL(dropTabe);
        mDataBase.close();
    }

    public void deleteDatabase(){
        mContext.deleteDatabase("SongCi.db");
    }

}
