package scnu.student.songcimachine.songcipoetrymachine;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import scnu.student.songcimachine.songcipoetrymachine.bean.SongCi;
import scnu.student.songcimachine.songcipoetrymachine.utils.SongCiDBManager;

/**
 * Created by 52347 on 2016/7/6.
 */
public class PoemContent extends Activity {

    private TextView tv_back;
    private TextView tv_cipai;
    private EditText et_title;
    private EditText et_content;

    private ImageButton btn_share;
    private ImageButton btn_delete;
    private ImageButton btn_save;

    private SongCi accept = null;

    private SongCiDBManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_content);
        mManager = new SongCiDBManager(this);
        Intent intent = getIntent();
        accept = (SongCi)intent.getSerializableExtra("msg");
        //Log.d("PoemContent", "======= " + accept.getId() + " =======");
        init();
    }

    private void init() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_cipai =(TextView) findViewById(R.id.tv_cipai);
        et_title = (EditText) findViewById(R.id.tv_title);
        et_content = (EditText) findViewById(R.id.tv_content);
        btn_share = (ImageButton) findViewById(R.id.btn_share);
        btn_save = (ImageButton) findViewById(R.id.btn_save);
        btn_delete = (ImageButton) findViewById(R.id.btn_delete);
        setListener();
        if ( accept != null ){
            tv_cipai.setText(accept.getCiPai());
            et_title.setText(accept.getTitle());
            et_content.setText(accept.getContent());
        }
    }

    private void setListener() {
        tv_back.setOnClickListener(clickListener);
        btn_share.setOnClickListener(clickListener);
        btn_delete.setOnClickListener(clickListener);
        btn_save.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_back:
                    finish();
                    break;
                case R.id.btn_share:
                    // 需要判空操作
                    if (et_title.getText().toString().equals("") || et_content.getText().toString().equals("")){
                        Toast.makeText(PoemContent.this,"宋词信息不全，请补充完整",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        sendShare();
                    }
                    break;
                case R.id.btn_save:
                    // 保存好数据，然后销毁此activity，然后返回bookFragment需要通知其刷新，
                    // 或者直接跳回MainActivity？
                    // 需要判空操作
                    if (et_title.getText().toString().equals("") || et_content.getText().toString().equals("")){
                        Toast.makeText(PoemContent.this,"宋词信息不全，请补充完整",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (accept != null){
                            accept.setTitle(et_title.getText().toString());
                            accept.setContent(et_content.getText().toString());
                            // 还要设置第一句话,切到第一个句号那里
                            String[] temp = et_content.getText().toString().split("。");
                            accept.setFirstSentence(temp[0]+"。");
                            //Log.d("PoemContent","======== "+ temp[0] + "。" + " ========");
                            // 还要重新设置编辑时间
                            Date date = new Date();
                            long datetime = date.getTime();
                            accept.setEditTime(datetime);
                            mManager.update(accept);
                            Toast.makeText(PoemContent.this,"保存成功",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            // 传输的数据发生了错误
                            Toast.makeText(PoemContent.this,"发生错误，保存失败",Toast.LENGTH_SHORT).show();
                        }
                        // 销毁操作
                        PoemContent.this.finish();
                    }
                    break;
                case R.id.btn_delete:
                    // 保存好数据，然后销毁此activity，然后返回bookFragment需要通知其刷新，
                    // 或者直接跳回MainActivity？
                    // 弹出一个对话框询问，然后执行删除
                    dialog();
                    break;
            }
        }
    };

    public void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(PoemContent.this);
        builder.setTitle("删除");
        builder.setMessage("是否删除该首宋词？");

        //监听下方button点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mManager.delete(accept);
                Toast.makeText(PoemContent.this, "已删除", Toast.LENGTH_SHORT).show();
                PoemContent.this.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        //设置对话框是可取消的
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    public void sendShare(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        String send = accept.getCiPai() + "\n" + et_title.getText().toString() +
                "\n" + et_content.getText().toString();
        sendIntent.putExtra(Intent.EXTRA_TEXT, send);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "宋词"); // 分享的主题
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "将宋词分享到"));
    }

}
