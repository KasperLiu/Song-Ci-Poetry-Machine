package scnu.student.songcimachine.songcipoetrymachine.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import scnu.student.songcimachine.songcipoetrymachine.R;
import scnu.student.songcimachine.songcipoetrymachine.adapter.PoemsListAdapter;
import scnu.student.songcimachine.songcipoetrymachine.bean.BeanPoemCollect;

/**
 * Created by 52347 on 2016/7/3.
 */
public class BookFragment extends Fragment {
    private ListView mListView;
    private PoemsListAdapter mAdapter;
    private List<BeanPoemCollect> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_poems_book, container, false);
        mListView = (ListView) rootView.findViewById(R.id.lv_collect);
        setList();
        mAdapter = new PoemsListAdapter(rootView.getContext(), mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setList() {
//        Cursor cursor = "数据库取值"
        mList = new ArrayList<BeanPoemCollect>();
        BeanPoemCollect mBean = new BeanPoemCollect();
        for (int i = 0; i < 10; i++) {
            mBean.setDate("数据库取时间");
            mBean.setName("数据库取标题");
            mBean.setContent("数据库取内容");
            mList.add(mBean);
        }
    }
}
