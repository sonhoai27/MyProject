package com.it.sonh.sqllite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;
    ArrayList<ThoiKhoaBieu> thoiKhoaBieu = new ArrayList<>();
    ListView lvKTB;
    Button btnAdd, btnEdit;
    EditText edtTKB;
    private int position;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getTable("ThoiKhoaBieu");
        listView();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("ThoiKhoaBieu");
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inset("ThoiKhoaBieu");
            }
        });
    }
    private void init(){
        lvKTB = (ListView)findViewById(R.id.lvTKB);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        edtTKB = (EditText)findViewById(R.id.edtTKB);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        database = new Database(getApplicationContext(),"GhiChu.sqlite",null,1);
        database.queryData("create table if not exists " +
                        "GhiChu(Id integer primary key autoincrement, Content varchar)");
    }
    private void getTable(String tblName){
        Cursor tkb = database.getData("select * from "+tblName);
        //dieu kien dung la tru, là vẫn còn
        while (tkb.moveToNext()){
//            Log.i("ROW",tkb.getString(0)+"-"+tkb.getString(1));
            thoiKhoaBieu.add(new ThoiKhoaBieu(tkb.getString(0), tkb.getString(1)));
        }
    }
    private void listView(){
        adapter = new Adapter(
                getApplicationContext(),
                thoiKhoaBieu
        );
        lvKTB.setAdapter(adapter);

        lvKTB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edtTKB.setText(thoiKhoaBieu.get(i).getName());
                position = Integer.parseInt(thoiKhoaBieu.get(i).getId());
            }
        });
        lvKTB.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                delete("ThoiKhoaBieu", String.valueOf(thoiKhoaBieu.get(i).getId()));
                return false;
            }
        });
    }
    private void update(String tblName){
        database.queryData("update "+tblName+" set Name ='"+edtTKB.getText()+"' where Id ="+position);
        position = 0;
        thoiKhoaBieu.clear();
        getTable("ThoiKhoaBieu");
        listView();
    }
    private void inset(String tblName){
        database.queryData("insert into "+tblName+" values (null, '"+edtTKB.getText()+"')");
//        position = 0;
//        thoiKhoaBieu.clear();
//        getTable("ThoiKhoaBieu");
//        listView();
    }
    private void delete(String tblName, String id){
        database.queryData("delete from "+tblName+ " where id = " + id);
        position = 0;
        thoiKhoaBieu.clear();
        getTable("ThoiKhoaBieu");
        listView();
    }
}
