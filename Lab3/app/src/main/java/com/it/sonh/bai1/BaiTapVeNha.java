package com.it.sonh.bai1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BaiTapVeNha extends AppCompatActivity {
    private TextView kq;
    private int numKQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai_tap_ve_nha);
        kq = (TextView)findViewById(R.id.txtKq);
    }

    public void onClickMe(View view) {
        Button btn = (Button)view;
        kq.setText(kq.getText().toString()+btn.getText());

    }

    public void onDelete(View view) {
        kq.setText("");
    }

    public void onResult(View view) {

    }
}
