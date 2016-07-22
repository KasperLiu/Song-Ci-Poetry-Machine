package scnu.student.songcimachine.songcipoetrymachine.bean;

/**
 * Created by 52347 on 2016/7/6.
 */
public class BeanPoemCollect {
    private String date;
    private String name;
    private String content;

    public BeanPoemCollect() {
    }

    public BeanPoemCollect(String date, String name, String content) {
        this.date = date;
        this.name = name;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
