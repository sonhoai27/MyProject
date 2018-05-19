package com.sonhoai.sonho.btvnsqlite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Bai2Activity extends AppCompatActivity {
    Button btnAdd;
    TextView tvCountStudents;
    ListView lvStudent;
    MySQLite mySQL;
    ArrayList<Student> students = new ArrayList<Student>();
    private StudentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        init();
        lvStudent.setAdapter(studentAdapter);
        registerForContextMenu(lvStudent);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Bai2Activity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_addnewstudent1, null);

                builder.setView(dialogView);
                final EditText edtFName=(EditText)dialogView.findViewById(R.id.edtFNameStudent);
                final EditText edtEmail=(EditText)dialogView.findViewById(R.id.edtEmailStudent);
                builder.setTitle("Create Student");
                builder.setNegativeButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //init
                        String fName=edtFName.getText().toString();
                        String email=edtEmail.getText().toString();

                        if(fName.length()!=0&&email.length()!=0){
                            mySQL.insertStudent(fName,email);
                            Student student=new Student();
                            student.setfName(fName);
                            student.setEmail(email);
                            students.add(student);
                            updateDataView();
                            Toast.makeText(getApplicationContext(),"Add Student is successful",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"No enough information- Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater=getMenuInflater() ;
        menuInflater.inflate(R.menu.menu_context,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.action_edit:
                edit_dialog(info.position);
                updateDataView();
                return true;

            case R.id.action_delete:
                int position=info.position;
                mySQL.deleteStudentById(students.get(position).getId_student());
                students.remove(position);
                lvStudent.setAdapter(studentAdapter);
                Toast.makeText(this,"Delete is successful", Toast.LENGTH_SHORT).show();
                updateDataView();
                return true;
        }
        return super.onContextItemSelected(item);
    }
    public void updateDataView(){
        studentAdapter.notifyDataSetChanged();
        tvCountStudents.setText(students.size() +" records found");
    }
    public void init(){
        btnAdd=(Button)findViewById(R.id.btnAddStudent);
        tvCountStudents=(TextView)findViewById(R.id.tvCount);
        lvStudent=(ListView)findViewById(R.id.listViewStudents);

        mySQL=new MySQLite(getApplicationContext());
        students=mySQL.getAllStudents();
        studentAdapter=new StudentAdapter(this,students);
        tvCountStudents.setText(students.size()+" records found");
    }
    void edit_dialog(final int position){

        AlertDialog.Builder builder = new AlertDialog.Builder(Bai2Activity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_addnewstudent1, null);
        builder.setView(dialogView);

        final EditText edtFName=(EditText)dialogView.findViewById(R.id.edtFNameStudent);
        final EditText edtEmail=(EditText)dialogView.findViewById(R.id.edtEmailStudent);
        builder.setTitle("Edit Student");

        edtEmail.setText(students.get(position).getEmail());
        edtFName.setText(students.get(position).getfName());

        builder.setNegativeButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String fname=edtFName.getText().toString();
                String email=edtEmail.getText().toString();
                try{
                    mySQL.updateStudent(students.get(position).getId_student(),fname,email);
                    students.get(position).setEmail(email);
                    students.get(position).setfName(fname);
                    studentAdapter.notifyDataSetChanged();

                }catch (Exception e){

                };
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
