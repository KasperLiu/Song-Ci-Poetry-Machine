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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import scnu.student.songcimachine.songcipoetrymachine.MainActivity;
import scnu.student.songcimachine.songcipoetrymachine.PoemContent;
import scnu.student.songcimachine.songcipoetrymachine.R;
import scnu.student.songcimachine.songcipoetrymachine.adapter.PoemsListAdapter;
import scnu.student.songcimachine.songcipoetrymachine.bean.BeanPoemCollect;
import scnu.student.songcimachine.songcipoetrymachine.bean.SongCi;
import scnu.student.songcimachine.songcipoetrymachine.utils.SongCiDBManager;

/**
 * Created by 52347 on 2016/7/3.
 */
public class BookFragment extends Fragment {
    private ListView mListView;
    private PoemsListAdapter mAdapter;
    private List<SongCi> mList;
    private SongCiDBManager mManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_poems_book, container, false);
        mListView = (ListView) rootView.findViewById(R.id.lv_collect);
        mManager = new SongCiDBManager(rootView.getContext());
        setList();
        mAdapter = new PoemsListAdapter(rootView.getContext(), mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView temp_listView = (ListView)adapterView;
                SongCi temp = (SongCi)temp_listView.getItemAtPosition(i);
                Intent intent = new Intent(getActivity(), PoemContent.class);
                intent.putExtra("msg",temp);
                startActivity(intent);
            }
        });
        return rootView;
    }

    private void setList() {
        // 查询 以时间降序排序
        mList = mManager.queryAllByDate();
//        if (mList == null ){
//            Log.d("BookFragment","====== mList == null ========");
//        }
//        else {
//            Log.d("BookFragment","====== mList != null ========");
//        }
    }

}
