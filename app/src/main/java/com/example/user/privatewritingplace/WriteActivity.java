package com.example.user.privatewritingplace;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.privatewritingplace.db.DBManager;
import com.example.user.privatewritingplace.item.Item;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WriteActivity extends AppCompatActivity {
    //맴버변수
    Spinner  spinner;
    EditText titleText, contentText;
    Button   saveBtn;

    //DB 관련 변수
    DBManager       dbManager;
    SQLiteDatabase  sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        spinner = (Spinner)findViewById(R.id.spinner);
        titleText = (EditText)findViewById(R.id.title);
        contentText = (EditText)findViewById(R.id.content);
        saveBtn = (Button)findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(titleText != null && contentText != null){
                    register(v);
                }else{
                    Toast.makeText(getBaseContext(), "제목 또는 내용을 입력해 주세요", Toast.LENGTH_SHORT).show();
                }
            }//end onClick()
        });
    }//end onCreate()

    public void register(View v){
        spinner = (Spinner)findViewById(R.id.spinner);
        titleText = (EditText)findViewById(R.id.title);
        contentText = (EditText)findViewById(R.id.content);

        String category = spinner.toString();
        String title = titleText.getText().toString();
        String content = contentText.getText().toString();

        try{
            dbManager = new DBManager(this);
            sqLiteDatabase = dbManager.getWritableDatabase();

            Item item = new Item(title, category, content);

            ContentValues values = new ContentValues();
            values.put("category", item.getCategory());
            values.put("title", item.getTitle());
            values.put("content", item.getContent());
            values.put("regDate", item.getRegDate());

            long newRowID = sqLiteDatabase.insert("items", null, values);
            sqLiteDatabase.close();
            dbManager.close();

            if(newRowID != -1){ //insert 성공 시 양수
                Toast.makeText(this, "글 등록 성공", Toast.LENGTH_SHORT).show();
            }else{              //insert 실패 시 음수(-1)
                Toast.makeText(this, "글 등록 실패", Toast.LENGTH_SHORT).show();
            }
        }catch(SQLiteException e){
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }//end register()

    //Back 버튼을 눌렀을 때 MainActivity로 이동하게 하자!
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK :
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return false;   //false를 돌려주면 어떻게 되는거지?

            default:
                return false;
        }
    }//end onKeyDown


}
