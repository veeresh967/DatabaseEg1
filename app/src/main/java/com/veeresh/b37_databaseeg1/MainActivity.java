package com.veeresh.b37_databaseeg1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2;
    Button b1, b2;
    TextView tv;

    //1. declare mydatabase variable
    MyDatabase m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //2. create database object
        m = new MyDatabase(this);
        //3. open the database
        m.open();

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        tv = (TextView) findViewById(R.id.textView1);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cursor is used to read the data from table
                Cursor c = m.queryStudent();
                //now cursor contains data coming from table, let us read row by row.
                StringBuilder sb = new StringBuilder();
                if(c != null){
                    while(c.moveToNext() == true){
                        String sno = c.getString(0);
                        String name = c.getString(1);
                        String sub = c.getString(2);
                        sb.append(sno+"-"+name+"-"+sub+"\n");
                    }
                }
                tv.setText(sb.toString());
            }
        });
        //insert button click listener
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //5. insert row into student table
                String name = et1.getText().toString();
                String sub = et2.getText().toString();
                m.insertStudent(name, sub);
                Toast.makeText(MainActivity.this, "INSERTED 1 ROW", Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");
                et1.requestFocus();
                //end
            }
        });
    }

    //4. close database in ondestroy - activity life cycle method
    @Override
    protected void onDestroy() {
        super.onDestroy();
        m.close();
    }
}
