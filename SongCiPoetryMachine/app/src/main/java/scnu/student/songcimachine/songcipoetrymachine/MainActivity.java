package scnu.student.songcimachine.songcipoetrymachine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

import scnu.student.songcimachine.songcipoetrymachine.fragment.BookFragment;
import scnu.student.songcimachine.songcipoetrymachine.fragment.TableFragment;
import scnu.student.songcimachine.songcipoetrymachine.fragment.WriteFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdpter;
    private List<Fragment> mTabs;

    private ImageView iv_write;
    private ImageView iv_book;
    private ImageView iv_table;

    private TextView tv_write;
    private TextView tv_book;
    private TextView tv_table;

    private ImageView mTabLine;

    private int mScreen1_3;
    private int mCurrentPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiView();
    }

    private void intiView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        iv_write = (ImageView) findViewById(R.id.iv_write_poems);
        iv_book = (ImageView) findViewById(R.id.iv_poems_book);
        iv_table = (ImageView) findViewById(R.id.iv_poems_table);

        tv_write = (TextView) findViewById(R.id.tv_write_poems);
        tv_book = (TextView) findViewById(R.id.tv_poems_book);
        tv_table = (TextView) findViewById(R.id.tv_poems_table);

        mTabLine = (ImageView) findViewById(R.id.iv_tabline);
        initTabLine();

        WriteFragment writeFragment = new WriteFragment();
        BookFragment bookFragment = new BookFragment();
        TableFragment tableFragment = new TableFragment();

        mTabs = new ArrayList<Fragment>();
        mTabs.add(writeFragment);
        mTabs.add(bookFragment);
        mTabs.add(tableFragment);

        mAdpter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position);
            }
        };

        mViewPager.setAdapter(mAdpter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLine.getLayoutParams();
                if (mCurrentPageIndex == 0 && position == 0) {              // 0->1
                    lp.leftMargin = (int) (mScreen1_3 * positionOffset);
                }
                if (mCurrentPageIndex == 1 && position == 1) {              // 1->2
                    lp.leftMargin = (int) (mScreen1_3 + mScreen1_3 * positionOffset);
                }
                if (mCurrentPageIndex == 2 && position == 1) {              // 2->1
                    lp.leftMargin = (int) (mScreen1_3 * mCurrentPageIndex + mScreen1_3 * (positionOffset - 1));
                }
                if (mCurrentPageIndex == 1 && position == 0) {              // 1->0
                    lp.leftMargin = (int) (mScreen1_3 * mCurrentPageIndex + mScreen1_3 * (positionOffset - 1));
                }
                mTabLine.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                //根据所选择的列表转变top栏目的字体颜色
                switch (position) {
                    case 0:
                        tv_write.setSelected(true);
                        iv_write.setSelected(true);

                        iv_book.setSelected(false);
                        tv_book.setSelected(false);

                        iv_table.setSelected(false);
                        tv_table.setSelected(false);
                        break;
                    case 1:
                        tv_write.setSelected(false);
                        iv_write.setSelected(false);

                        iv_book.setSelected(true);
                        tv_book.setSelected(true);

                        iv_table.setSelected(false);
                        tv_table.setSelected(false);
                        break;
                    case 2:
                        tv_write.setSelected(false);
                        iv_write.setSelected(false);

                        iv_book.setSelected(false);
                        tv_book.setSelected(false);

                        iv_table.setSelected(true);
                        tv_table.setSelected(true);
                }

                mCurrentPageIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTabLine() {
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3 = outMetrics.widthPixels / 3;
        ViewGroup.LayoutParams lp = mTabLine.getLayoutParams();
        lp.width = mScreen1_3;
        mTabLine.setLayoutParams(lp);
    }
}
