package com.sonhoai.sonho.ttsqlite;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvGhiChu;
    private EditText edtInput;
    private Button btnAdd, btnUpdate;
    private Database db;
    private ArrayList<GhiChu> ghiChus = new ArrayList<>();
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getData();
        add();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(position,edtInput.getText().toString());
            }
        });
        lvGhiChu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = ghiChus.get(i).getID();
                edtInput.setText(ghiChus.get(i).getContent());
            }
        });
        lvGhiChu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                db.query("delete from GhiChu where Id ="+ghiChus.get(i).getID());
                ghiChus.clear();
                getData();
                return false;
            }
        });
//        db.query("create table if not exists " +
//                "GhiChu(Id integer primary key autoincrement, Content varchar)");
//        //db.query("insert into GhiChu values(null, 'HAHHA')");
//
//        Cursor note = db.getData("select * from GhiChu");
//        while (note.moveToNext()){
//            Log.i("DATA", note.getString(1));
//        }
    }

    public void getData(){
        Cursor note = db.getData("select * from GhiChu");
        while (note.moveToNext()){
            ghiChus.add(new GhiChu(note.getInt(0), note.getString(1)));
        }

        ListViewAdapter adapter = new ListViewAdapter(
                getApplicationContext(),
                ghiChus
        );
        lvGhiChu.setAdapter(adapter);
    }
    public void add(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = edtInput.getText().toString();
                db.query("insert into GhiChu values(null, '"+content+"')");
                ghiChus.clear();
                getData();
            }
        });
    }
    public void update(int id, String content){
        db.query("update GhiChu set Content = '"+content+"' where Id = "+id);
        ghiChus.clear();
        getData();
    }
    public void init() {
       lvGhiChu = findViewById(R.id.lvNotes);
       btnAdd = findViewById(R.id.btnAdd);
       btnUpdate = findViewById(R.id.btnUpdate);
       edtInput = findViewById(R.id.edtInput);

       //khoi tao db

        db = new Database(
                getApplicationContext(),
                "GHICHU.sqlite",
                null,
                1
        );
        db.query("create table if not exists " +
        "GhiChu(Id integer primary key autoincrement, Content varchar)");
    }
}
