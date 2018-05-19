package com.sonhoai.sonho.btlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class BT2Activity extends AppCompatActivity {
    ArrayList<Category> categorys=new ArrayList<Category>();
    ArrayList<String> categoryNames=new ArrayList<String>();
    ArrayList<Device> fullDevices=new ArrayList<Device>();
    ArrayList<Device> devices=new ArrayList<>();
    Spinner spinnerCategory;
    ListView lisviewDevice;
    final DeviceAdapter deviceAdapter=new DeviceAdapter(this,devices);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt2);
        spinnerCategory=(Spinner)findViewById(R.id.spinerCategory);
        lisviewDevice=(ListView)findViewById(R.id.lisviewDevice);
        init();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerCategory.setAdapter(adapter);

        setTitle("Exercise 2");
        lisviewDevice.setAdapter(deviceAdapter);
        spinnerCategory.setOnItemSelectedListener(new MyProcessEvent());
    }

    void init(){
        categorys.add(new Category(0,"Điện thoại"));
        categorys.add(new Category(1,"Máy tính"));
        categorys.add(new Category(2,"Đồng hồ"));
        //-----
        fullDevices.add(new Device("Nokia",0));
        fullDevices.add(new Device("HTC",0));
        fullDevices.add(new Device("Assus",1));
        fullDevices.add(new Device("Casio",2));
        fullDevices.add(new Device("Orient",2));
        fullDevices.add(new Device("OP",2));
        fullDevices.add(new Device("Citizen",2));
        for (int i=0;i<categorys.size();i++){
            categoryNames.add(categorys.get(i).getCategoryName());
        }
    }
    private class MyProcessEvent implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            loadListDeviceByCategory(categorys.get(i).getIdCategory());
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
        private void loadListDeviceByCategory(int idCategory )
        {
            devices.clear();
            for(int i=0;i<fullDevices.size();i++){
                if(fullDevices.get(i).getIdCategory()==idCategory) devices.add(fullDevices.get(i));
            }
            deviceAdapter.notifyDataSetChanged();
        }

    }
}
