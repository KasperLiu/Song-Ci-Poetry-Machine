package scnu.student.songcimachine.songcipoetrymachine.utils;

import scnu.student.songcimachine.songcipoetrymachine.bean.WordInfo;

/**
 * Created by Kasper on 2016/7/8.
 */
/**
 * 截取文本文件每行的数据
 *
 * Created by 伟龙 on 2016/7/8.
 */
public class TextUtil {

    public static WordInfo cutText(String t) {

        WordInfo info = new WordInfo();

        int idx = t.indexOf("\"");
        idx++;
        int idx1 = t.indexOf("\"", idx);
        int index = Integer.parseInt(t.substring(idx, idx1));
        idx1++;
        int idx2 = t.indexOf("\"", idx1);
        idx2++;
        int idx3 = t.indexOf("\"", idx2);
        String text = t.substring(idx2, idx3);
        idx3++;
        int weight = Integer.parseInt(t.substring(idx3).trim());

        // 2016.7.9 将序号-1剔除掉乱码字符，重新从1开始排列
        info.setIndex(index - 1);
        info.setText(text);
        info.setWeight(weight);

        return info;
    }
}