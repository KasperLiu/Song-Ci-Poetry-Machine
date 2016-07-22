package scnu.student.songcimachine.songcipoetrymachine.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.Date;
import java.util.List;

import scnu.student.songcimachine.songcipoetrymachine.R;
import scnu.student.songcimachine.songcipoetrymachine.bean.BeanPoemCollect;
import scnu.student.songcimachine.songcipoetrymachine.bean.SongCi;

/**
 * Created by 52347 on 2016/7/6.
 */
public class PoemsListAdapter extends BaseAdapter {

    private List<SongCi> mList = null;
    private ViewHolder mViewHolder;
    private Context mContext = null;

    public PoemsListAdapter(Context context, List<SongCi> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            mViewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            view = mInflater.inflate(R.layout.book_lsit_item, null);
            mViewHolder.tv_time = (TextView) view.findViewById(R.id.tv_time);
            mViewHolder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            mViewHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            mViewHolder.tv_content = (TextView) view.findViewById(R.id.tv_content);
            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }
        if ( mList != null){
            SongCi mBean = mList.get(i);
            String timeStr = DateFormat.
                    getDateFormat(view.getContext()).format(new Date(mBean.getEditTime())) + " ";
            timeStr += android.text.format.DateFormat.
                    getTimeFormat(view.getContext()).format(new Date(mBean.getEditTime()));

            mViewHolder.tv_time.setText(timeStr);
            mViewHolder.tv_name.setText(mBean.getCiPai());
            mViewHolder.tv_title.setText(mBean.getTitle());
            mViewHolder.tv_content.setText(mBean.getFirstSentence());
        }

        return view;
    }

    class ViewHolder {
        private TextView tv_time;
        private TextView tv_name;
        private TextView tv_title;
        private TextView tv_content;
        private LinearLayout ll_poem;
    }
}
