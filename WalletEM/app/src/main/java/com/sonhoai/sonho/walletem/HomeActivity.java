package com.sonhoai.sonho.walletem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private Button btnScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, 123);
            }
        });
    }

    private void init(){
        btnScan = findViewById(R.id.btnScan);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 123 && resultCode == RESULT_OK && data != null){
            String status = data.getStringExtra("REQUEST");
            if(status.equals("THANH_CONG")){
                final AlertDialog.Builder notifyNetwork = new AlertDialog.Builder(this);
                notifyNetwork.setTitle("Thanh toán thành công!");
                notifyNetwork.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                notifyNetwork.show();
            }else{

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
