package cn.edu.cqu.godutch;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class paylayout extends AppCompatActivity {
    private TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置当前的Activity的界面布局
        setContentView(R.layout.paylayout);
        //获得Intent
        Intent intent = this.getIntent();
        tv = (TextView)findViewById(R.id.payEach);
        //从Intent获得额外信息，设置为TextView的文本
        tv.setText(intent.getStringExtra("payEach"));
    }
}