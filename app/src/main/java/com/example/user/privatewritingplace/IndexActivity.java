package com.example.user.privatewritingplace;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IndexActivity extends AppCompatActivity {
    //맴버변수 선언
    EditText login_pw;
    Button   login_btn;

    String collect_pw = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        login_pw = (EditText)findViewById(R.id.password);
        login_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pw = s.toString();
                if(pw.length() == 4){
                    login_btn.setTextColor(Color.parseColor("#A0ffffff"));
                    login_btn.setBackgroundColor(Color.parseColor("#A0ffffff"));
                }else{
                    login_btn.setTextColor(Color.parseColor("#00000000"));
                    login_btn.setBackgroundColor(Color.parseColor("#1Affffff"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        login_btn = (Button)findViewById(R.id.loginBtn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pw = login_pw.getText().toString();

                if(pw.equals(collect_pw)){//비밀번호가 일치할때
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else{//비밀번호가 일치하지 않을 때
                    Toast.makeText(getBaseContext(), "비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                    //UX
                    login_pw.setText("");
                    login_pw.requestFocus();
                }
            }
        });
    }
}
