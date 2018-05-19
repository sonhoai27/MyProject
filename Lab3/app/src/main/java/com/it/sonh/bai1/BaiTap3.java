package com.it.sonh.bai1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BaiTap3 extends AppCompatActivity {
    private ArrayList<String> monHoc = new ArrayList<>();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai_tap_3);
        init();
        monHoc.add("Lập trình Android");
        monHoc.add("Lập trình Nhúng");
        monHoc.add("Lập trình Web");
        monHoc.add("Lập trình iOS");
        monHoc.add("Nodejs");

        ArrayAdapter arrayAdapter = new ArrayAdapter(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                monHoc
        );
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), monHoc.get(i)+"", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void init(){
        listView = (ListView)findViewById(R.id.listView);
    }
}
