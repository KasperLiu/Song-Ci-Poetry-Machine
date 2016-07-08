package scnu.student.songcimachine.songcipoetrymachine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;



import java.util.List;

import scnu.student.songcimachine.songcipoetrymachine.R;
import scnu.student.songcimachine.songcipoetrymachine.bean.BeanPoemCollect;

/**
 * Created by 52347 on 2016/7/6.
 */
public class PoemsListAdapter extends BaseAdapter {

    private List<BeanPoemCollect> mList = null;
    private ViewHolder mViewHolder;
    private Context mContext = null;

    public PoemsListAdapter(Context context, List<BeanPoemCollect> mList) {
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
            mViewHolder.tv_title = (TextView) view.findViewById(R.id.tv_name);
            mViewHolder.tv_content = (TextView) view.findViewById(R.id.tv_content);
            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }
        BeanPoemCollect mBean = mList.get(i);
        mViewHolder.tv_time.setText(mBean.getDate());
        mViewHolder.tv_title.setText(mBean.getName());
        mViewHolder.tv_content.setText(mBean.getContent());
        return view;
    }

    class ViewHolder {
        private TextView tv_time;
        private TextView tv_title;
        private TextView tv_content;
        private LinearLayout ll_poem;
    }
}
