package com.sonhoai.sonho.btlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BT1Activity extends AppCompatActivity {
    ListView listViewPlaces;
    TextView tvItem;
    final ArrayList<String> places=new ArrayList<>();
    final PlaceAdapter placeAdapter=new PlaceAdapter(this,places);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt1);
        setTitle("Exercise 1");
        listViewPlaces=(ListView)findViewById(R.id.lisviewPlaces);
        tvItem=(TextView)findViewById(R.id.tvItemName);
        init();

        listViewPlaces.setAdapter(placeAdapter);
        listViewPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvItem.setText(places.get(i));
            }
        });
    }
    public void init(){
        places.add("Hồ Chí Minh");
        places.add("Hà Nội");
        places.add("Spa");
        places.add("Huế");
        places.add("Long An");
    }
}
