package com.sonhoai.sonho.contextmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView txtShowContextMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        registerForContextMenu(txtShowContextMenu);
    }

    private void init(){
        txtShowContextMenu = findViewById(R.id.txtShowContext);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.option1:doOptionOne();break;
            case R.id.option2:doOptionTwo();break;
            default: return false;
        }
        return super.onContextItemSelected(item);
    }

    private void doOptionTwo() {
        Toast.makeText(getApplicationContext(), "AA", Toast.LENGTH_SHORT).show();
    }
    private void doOptionOne() {
        Toast.makeText(getApplicationContext(), "AA", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option1:
                doOptionOne();
                return true;
            case R.id.option2:
                doOptionTwo();
                return true;
            default:
                return false;
        }
    }
}
