package com.it.sonh.bai1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BaiTap4Lab3tt extends AppCompatActivity {
    private EditText edtNum1, edtNum2;
    private Button btnTich,btnHieu,btnThuong,btnUC,btnExit;
    private TextView txtKQ;
    private Float a, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai_tap_4_lab3tt);
        init();
        btnUC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = Float.parseFloat(edtNum1.getText().toString());
                b = Float.parseFloat(edtNum2.getText().toString());
                txtKQ.setText(String.valueOf(USCLN(a, b)));
            }
        });
        btnHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = Float.parseFloat(edtNum1.getText().toString());
                b = Float.parseFloat(edtNum2.getText().toString());
                txtKQ.setText(String.valueOf(a-b));
            }
        });
        btnTich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = Float.parseFloat(edtNum1.getText().toString());
                b = Float.parseFloat(edtNum2.getText().toString());
                txtKQ.setText(String.valueOf(a*b));
            }
        });
        btnThuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = Float.parseFloat(edtNum1.getText().toString());
                b = Float.parseFloat(edtNum2.getText().toString());
                txtKQ.setText(String.valueOf(a/b));
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }

    public void tong(View view) {
        a = Float.parseFloat(edtNum1.getText().toString());
        b = Float.parseFloat(edtNum2.getText().toString());
        txtKQ.setText(String.valueOf(a+b));
    }
    private void init(){
        edtNum1 = (EditText)findViewById(R.id.edtNum1);
        edtNum2 = (EditText)findViewById(R.id.edtNum2);
        txtKQ = (TextView)findViewById(R.id.txtKQ);
        btnHieu = (Button)findViewById(R.id.btnHieu);
        btnTich = (Button)findViewById(R.id.btnTich);
        btnThuong = (Button)findViewById(R.id.btnThuong);
        btnUC = (Button)findViewById(R.id.btnUC);
        btnExit = (Button)findViewById(R.id.btnExit);
    }
    private Float USCLN(Float a, Float b) {
        if (b == 0) return a;
        return USCLN(b, a % b);
    }

}
