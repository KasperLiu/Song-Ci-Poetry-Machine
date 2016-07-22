package scnu.student.songcimachine.songcipoetrymachine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import scnu.student.songcimachine.songcipoetrymachine.R;
import scnu.student.songcimachine.songcipoetrymachine.bean.SongCi;
import scnu.student.songcimachine.songcipoetrymachine.database.SongCiDBHelper;
import scnu.student.songcimachine.songcipoetrymachine.utils.DigitUtil;
import scnu.student.songcimachine.songcipoetrymachine.MainActivity;
import scnu.student.songcimachine.songcipoetrymachine.utils.SongCiDBManager;

/**
 * Created by 52347 on 2016/7/3.
 */
public class WriteFragment extends Fragment
{
    private Spinner mSpinner;
    private ArrayList<String> mList;
    private ArrayAdapter mAdapter;
    private EditText editText;
    // 换一首
    private TextView btn_change;
    // 收藏
    private ImageButton btn_collect;
    // 分享
    private ImageButton btn_share;

    // 生成结果
    private TextView tv_result;
    // 词牌名
    private TextView tv_cipai;
    // 题目
    private TextView tv_title;
    // 宋词
    private TextView tv_content;

    private Button goButton;
    // 保存词牌名
    private String selectedCiPaiName = null;

    // 补充结果
    private String finalResult = null;
    // 生成结果
    private String curResult = null;
    // 宋词数据库
    private SongCiDBManager mManager;

    private final String TITLE = "随机";

    private FragmentStatePagerAdapter mainAdapter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_write_poems, container, false);
        mManager = new SongCiDBManager(rootView.getContext());
        init(rootView);

        return rootView;
    }

    private void init(View view)
    {
        editText = (EditText)view.findViewById(R.id.et_input);
        goButton = (Button)view.findViewById(R.id.btn_go);
        btn_change = (TextView)view.findViewById(R.id.btn_change);
        btn_collect = (ImageButton)view.findViewById(R.id.btn_collect);
        btn_share = (ImageButton)view.findViewById(R.id.btn_share);
        tv_result = (TextView)view.findViewById(R.id.tv_result);
        tv_cipai = (TextView)view.findViewById(R.id.tv_poem_title);
        tv_title = (TextView)view.findViewById(R.id.tv_content1);
        tv_content = (TextView)view.findViewById(R.id.tv_content2);

        goButton.setOnClickListener(onClickListener);
        btn_change.setOnClickListener(onClickListener);
        btn_collect.setOnClickListener(onClickListener);
        btn_share.setOnClickListener(onClickListener);

        mSpinner = (Spinner) view.findViewById(R.id.sn_poems);
        mList = new ArrayList<String>();
        mList.add("清平乐");
        mList.add("浣溪沙");
        mList.add("菩萨蛮");
        mAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, mList);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCiPaiName = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if ( finalResult != null && curResult != null){
            // 显示宋词
            tv_content.setText(finalResult);
            tv_result.setText(curResult);
            tv_cipai.setText(selectedCiPaiName);
            tv_title.setText(TITLE);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(final View v) {
            switch(v.getId()){
                case R.id.btn_go:
                case R.id.btn_change:
                    DigitUtil digitUtil = new DigitUtil(editText.getText().toString(),
                            getFormatWithCiPaiName(selectedCiPaiName),
                            ((MainActivity) getActivity()).oneDBManager,
                            ((MainActivity) getActivity()).twoDBManager);
                    finalResult = digitUtil.getSentences();//算法生成结果
                    curResult = digitUtil.getUserResult();//生成结果

                    tv_content.setText(finalResult);
                    //Log.d("Test","========== "+ finalResult.length() + " =========");

                    if (finalResult.length() > 20){
                        // 显示宋词
                        tv_result.setText(curResult);
                        tv_cipai.setText(selectedCiPaiName);
                        tv_title.setText(TITLE);
                    }
                    else {
                        // 显示空白
                        tv_result.setText("");
                        tv_cipai.setText("");
                        tv_title.setText("");
                    }

//                    Log.d("digitutil", finalResult);
//                    Log.d("digitutil", "===============");
//                    Log.d("digitutil", curResult);
                    break;
                case R.id.btn_collect:
                    // 字数够才存入数据库中
                    if (finalResult != null && !finalResult.equals("")){
                        if ( finalResult.length() > 20){
                            SongCi info = new SongCi();
                            info.setCiPai(selectedCiPaiName);
                            info.setTitle(TITLE);
                            info.setContent(finalResult);
                            info.setFirstSentence(curResult);
                            Date date = new Date();
                            long datetime = date.getTime();
                            info.setEditTime(datetime);
                            // 保存到数据库中
                            mManager.add(info);
                            if (mainAdapter != null){
                                mainAdapter.notifyDataSetChanged();
                            }
                            Toast.makeText(v.getContext(),"已保存成功！",
                                    Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(v.getContext(),"亲，没有生产宋词语句喔~请再输入试试",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(v.getContext(),"亲，没有生产宋词语句喔~请再输入试试",
                                Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.btn_share:
                    // 字数够才允许分享
                    // 字数够才存入数据库中
                    if (finalResult != null && !finalResult.equals("")) {
                        if ( finalResult.length() > 20){
                            // 分享功能
                            sendShare();
                        }
                        else {
                            Toast.makeText(v.getContext(),"亲，没有生产宋词语句喔~请再输入试试",
                                    Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(v.getContext(),"亲，没有生产宋词语句喔~请再输入试试",
                                Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

    // 获取ciPaiName对应的词的格式（返回Map，其中numOfWords键对应总字数，numOfSentences键对应总句数，"1","2"..."numOfSentences"键分别
    // 对应每一句的字数）
    private Map getFormatWithCiPaiName(String ciPaiName)
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        switch (ciPaiName) {
            case "清平乐":
                map.put("numOfWords", 46);
                map.put("numOfSentences", 8);
                map.put("1", 4);
                map.put("2", 5);
                map.put("3", 7);
                map.put("4", 6);
                map.put("5", 6);
                map.put("6", 6);
                map.put("7", 6);
                map.put("8", 6);
                break;
            case "浣溪沙":
                map.put("numOfWords", 42);
                map.put("numOfSentences", 6);
                map.put("1", 7);
                map.put("2", 7);
                map.put("3", 7);
                map.put("4", 7);
                map.put("5", 7);
                map.put("6", 7);
                break;
            case "菩萨蛮":
                map.put("numOfWords", 44);
                map.put("numOfSentences", 8);
                map.put("1", 7);
                map.put("2", 7);
                map.put("3", 5);
                map.put("4", 5);
                map.put("5", 5);
                map.put("6", 5);
                map.put("7", 5);
                map.put("8", 5);
                break;
        }
        return map;
    }

    public void setMainAdapter(FragmentStatePagerAdapter adapter){
        mainAdapter = adapter;
    }

    public void sendShare(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        String send = selectedCiPaiName + "\n" + TITLE + "\n" + finalResult;
        sendIntent.putExtra(Intent.EXTRA_TEXT, send);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "宋词"); // 分享的主题
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "将宋词分享到"));
    }
}
