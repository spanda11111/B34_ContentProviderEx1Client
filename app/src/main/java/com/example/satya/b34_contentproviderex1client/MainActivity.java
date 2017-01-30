package com.example.satya.b34_contentproviderex1client;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    Button bt1,bt2;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.et1);
        et2= (EditText) findViewById(R.id.et2);
        bt1= (Button) findViewById(R.id.bt1);
        bt2= (Button) findViewById(R.id.bt2);
        tv1= (TextView) findViewById(R.id.tv1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //client one to INSERT STUDENT DETAILS INTO FIRST URI
                ContentResolver contentResolver = getContentResolver();
                String name= et1.getText().toString();
                String sub= et2.getText().toString();
                ContentValues contentValues= new ContentValues() ;
                contentValues.put(UriProvider.NAME,name);
                contentValues.put(UriProvider.SUB,sub);
                //now insert
                contentResolver.insert(UriProvider.STUDENT_URI,contentValues);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //want to read data
                //prepare resolver
                ContentResolver contentResolver = getContentResolver();
                Cursor c = contentResolver.query(UriProvider.STUDENT_URI,null,null,null,null);
                StringBuilder stringBuilder= new StringBuilder();
                if (c!=null)
                {
                    while (c.moveToNext())
                    {
                        int no= c.getInt(0);
                        String name=c.getString(1);
                        String sub=c.getString(2);
                        stringBuilder.append("no:"+no+"\n"+"name:"+name+"\n"+"sub:"+sub);
                    }
                    tv1.setText(stringBuilder.toString());
                }
            }
        });
    }
}
