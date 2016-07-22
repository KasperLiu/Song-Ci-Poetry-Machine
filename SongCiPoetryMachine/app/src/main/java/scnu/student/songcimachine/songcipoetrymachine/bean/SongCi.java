package scnu.student.songcimachine.songcipoetrymachine.bean;

import java.io.Serializable;

/**
 * Created by Kasper on 2016/7/16.
 */
public class SongCi implements Serializable {
    private static final long serialVersionUID = 1L;
    // id唯一标识
    private int _id;
    // 词牌名
    private String ciPai;
    // 标题
    private String title;
    // 宋词文本
    private String content;
    // 宋词首句（前2句话）
    private String firstSentence;
    // 编辑时间，以此降序排序
    private long editTime;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getCiPai() {
        return ciPai;
    }

    public void setCiPai(String ciPai) {
        this.ciPai = ciPai;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstSentence() {
        return firstSentence;
    }

    public void setFirstSentence(String firstSentence) {
        this.firstSentence = firstSentence;
    }

    public long getEditTime() {
        return editTime;
    }

    public void setEditTime(long editTime) {
        this.editTime = editTime;
    }
}
