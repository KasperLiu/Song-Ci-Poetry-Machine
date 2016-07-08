package scnu.student.songcimachine.songcipoetrymachine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import java.util.ArrayList;

import scnu.student.songcimachine.songcipoetrymachine.R;

/**
 * Created by 52347 on 2016/7/3.
 */
public class WriteFragment extends Fragment {

    private Spinner mSpinner;
    private ArrayList<String> mList;
    private ArrayAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_write_poems, container, false);
        init(rootView);

        return rootView;
    }

    private void init(View view) {
        mSpinner = (Spinner) view.findViewById(R.id.sn_poems);
        mList = new ArrayList<String>();
        mList.add("清平乐");
        mList.add("涴溪沙");
        mList.add("菩萨蛮");
        mAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, mList);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}
