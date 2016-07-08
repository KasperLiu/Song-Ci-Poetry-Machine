package scnu.student.songcimachine.songcipoetrymachine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.util.List;

import scnu.student.songcimachine.songcipoetrymachine.R;
import scnu.student.songcimachine.songcipoetrymachine.bean.BeanPoemCollect;

/**
 * Created by 52347 on 2016/7/3.
 */
public class TableFragment extends Fragment {

    private ListView mListView;
    private List<BeanPoemCollect> mList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_poems_book, container, false);
        mListView = (ListView) rootView.findViewById(R.id.lv_collect);
        return rootView;
    }
}
