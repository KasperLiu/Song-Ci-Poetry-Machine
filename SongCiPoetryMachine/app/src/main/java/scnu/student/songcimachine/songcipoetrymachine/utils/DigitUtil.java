package scnu.student.songcimachine.songcipoetrymachine.utils;

import java.util.Map;


/**
 * Created by 延霖 on 2016/7/10.
 * 拆分数字工具类
 */
public class DigitUtil {

    private DBManager oneDBManager;
    private DBManager twoDBManager;
    private Map<String, Integer> map;

    private String data;
    private int dataLength;
    private int flag;//扫描标志位
    private boolean isRandom;//开始随机生成的标志位
    private int[] values;//存放每次选择的数字
    private int valuesIndex;
    private int sentenceIndex;
    private int beginRandomIndex;

    private String[] result;

    /**
     *
     * @param data:用户输入的数字
     * @param map:词牌格式
     * @param oneDBManager:二字词库
     * @param twoDBManager:三字词库
     */
    public DigitUtil(String data, Map<String, Integer> map, DBManager oneDBManager, DBManager twoDBManager){
        this.data = data;
        this.map = map;
        this.oneDBManager = oneDBManager;
        this.twoDBManager = twoDBManager;

        this.dataLength = data.length();
        this.flag = 0;
        this.valuesIndex = -1;
        this.isRandom = false;
    }

    /**
     *
     * @return sentences:输出宋词
     */
    public String getSentences(){
        if (data.length() > 4){
            for (int i = 0;i<data.length();i++){
                if (data.charAt(0) != data.charAt(i))
                    break;
                if (i == data.length() - 1)
                    return "您输入的数字重复，请重新输入";
            }
        } else {
            return "亲，您输入的数字也忒少了吧";
        }

        values = new int[(map.get("numOfWords")/2)+1];
        result = new String[map.get("numOfSentences")];
        String sentences = "";
        for (sentenceIndex = 0;sentenceIndex < result.length;sentenceIndex++){
            String sentence = null;
            switch (map.get(String.valueOf(sentenceIndex+1))){
                case 2:
                    sentence = getWords(2);
                    break;
                case 3:
                    sentence = getWords(3);
                    break;
                case 4:
                    sentence = getWords(2) + getWords(2);
                    break;
                case 5:
                    sentence = getWords(2) + getWords(3);
                    break;
                case 6:
                    sentence = getWords(2) + getWords(2) + getWords(2);
                    break;
                case 7:
                    sentence = getWords(2) + getWords(2) +getWords(3);
                    break;
                case 8:
                    sentence = getWords(2) + getWords(2) + getWords(2) + getWords(2);
                    break;
                case 9:
                    sentence = getWords(3) + getWords(2) + getWords(2) + getWords(2);
                    break;
                case 10:
                    sentence = getWords(3) + getWords(2) + getWords(2) + getWords(3);
                    break;
                case 11:
                    sentence = getWords(2) + getWords(2) + getWords(2) + getWords(2) + getWords(3);
                    break;
                default:
                    break;
            }
            result[sentenceIndex] = sentence;
            if (sentenceIndex == result.length - 1){
                sentences += result[sentenceIndex] + "。\n";
                break;
            }
            sentences += result[sentenceIndex] + "，\n";
        }
        return sentences;
    }

    /**
     *
     * @return r:根据用户输入数字生成的句子
     */
    public String getUserResult(){
        String r = "";
        if (dataLength < 4 || result == null){
            return "";
        }
        if (beginRandomIndex == 0){
            r = result[0];
            return r;
        }
        for (int i = 0;i < beginRandomIndex - 1;i++){
            r += result[i] + ",\n";
        }
        r += result[beginRandomIndex - 1];
        return r;
    }


    private String getWords(int countOfWords) {
        if (flag > dataLength - 1){
            return getRandomWords(countOfWords);
        }
        if (countOfWords == 2){
            return oneDBManager.getText(scan());
        } else if (countOfWords == 3){
            return twoDBManager.getText(scan());
        }
        return null;
    }

    private String getRandomWords(int countOfWords) {
        String word;
        valuesIndex++;
        values[valuesIndex] = random();
        if (countOfWords == 2) {
            word = oneDBManager.getText(values[valuesIndex]);
        } else {
            word = twoDBManager.getText(values[valuesIndex]);
        }
        return word;
    }

    private int scan() {
        int single_digit = Integer.parseInt(data.substring(flag, flag+1));
        int result;

        if (isRepeat(single_digit)){
            if (flag + 2 > dataLength){
                result = random();
                flag += 2;
            } else {
                int double_digit = Integer.parseInt(data.substring(flag,flag+2));
                if (isRepeat(double_digit)){
                    result = random();
                    flag += 2;
                } else {
                    result = double_digit;
                    flag += 2;
                }
            }
        } else {
            result = single_digit;
            flag += 1;
        }

        valuesIndex++;
        values[valuesIndex] = result;
        return result;
    }

    private int random(){
        if (!isRandom){
            beginRandomIndex = sentenceIndex;
            isRandom = true;
        }
        int x = (int)(Math.random()*100);
        while (x == 0 || isRepeat(x)){
            x = (int)(Math.random()*100);
        }
        return x;
    }

    private boolean isRepeat(int number) {
        for (int x : values) {
            if (number == x)
                return true;
        }
        return false;
    }

}
