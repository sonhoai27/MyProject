package com.it.sonh.sqllite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sonho on 3/7/2018.
 */

public class Database extends SQLiteOpenHelper {

    //con trỏ trỏ tới duyệt dữ liệu, và lấy ra
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //ham ko tra vem tao, them, cap nhat, xoa
    public void queryData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);

    }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();//co thể doc ve ghi nếu get write
        return database.rawQuery(sql, null);
    }
    //ham co tra ve, truy van, trả dữ liệu dạng con tro2, trả về từng dòng
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
