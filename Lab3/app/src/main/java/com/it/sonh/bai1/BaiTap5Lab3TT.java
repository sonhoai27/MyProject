package com.it.sonh.bai1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BaiTap5Lab3TT extends AppCompatActivity {
    private EditText edtC, edtF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_tap5_lab3_tt);
        edtC = (EditText) findViewById(R.id.edtDoC);
        edtF = (EditText) findViewById(R.id.edtDoF);
    }

    public void toF(View view){
        Float c = Float.valueOf(edtC.getText().toString());
        edtF.setText((c*(9/5)+32)+"");
    }
    public void toC(View view){
        Float f = Float.valueOf(edtF.getText().toString());
        edtC.setText(((f-32)*5/9)+"");
    }
    public void Clear(View view) {
        edtC.setText("");
        edtF.setText("");
    }
}
