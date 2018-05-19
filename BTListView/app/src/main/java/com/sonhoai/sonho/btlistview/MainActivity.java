package com.sonhoai.sonho.btlistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnEx1,btnEx2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEx1=(Button)findViewById(R.id.btnEx1);
        btnEx2=(Button)findViewById(R.id.btnEx2);
        btnEx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),BT1Activity.class);
                startActivity(intent);
            }
        });
        btnEx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),BT2Activity.class);
                startActivity(intent);
            }
        });
    }
}
