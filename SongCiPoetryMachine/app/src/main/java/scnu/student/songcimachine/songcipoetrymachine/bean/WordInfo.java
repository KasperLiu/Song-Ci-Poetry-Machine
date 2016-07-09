package scnu.student.songcimachine.songcipoetrymachine.bean;

/**
 * Created by Kasper on 2016/7/8.
 */
/**
 * 词语实体类
 *
 * Created by 伟龙 on 2016/7/8.
 */
public class WordInfo {

    /**
     * 数字标识
     */
    private int index;

    /**
     * 词语
     */
    private String text;

    /**
     * 权重
     */
    private int weight;


    public WordInfo() {
    }

    public WordInfo(int index, String text, int weight) {
        this.index = index;
        this.text = text;
        this.weight = weight;
    }


    public int getIndex() {

        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}