package scnu.student.songcimachine.songcipoetrymachine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 52347 on 2016/7/6.
 */
public class PoemContent extends Activity {

    private TextView tv_back;
    private TextView tv_title;
    private TextView tv_content;

    private Button btn_share;
    private Button btn_delete;
    private Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_content);
    }

    private void findViewById() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);
        btn_share = (Button) findViewById(R.id.btn_share);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        setListener();
    }

    private void setListener() {
        btn_share.setOnClickListener(clickListener);
        btn_delete.setOnClickListener(clickListener);
        btn_save.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_share:

                    break;
                case R.id.btn_save:

                    break;
                case R.id.btn_delete:

                    break;
            }
        }
    };
}
