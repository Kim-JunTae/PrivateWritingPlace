package com.example.user.privatewritingplace;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.user.privatewritingplace.adapter.ItemListAdapter;
import com.example.user.privatewritingplace.item.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //맴버변수 선언
    Spinner     categorySpinner;
    Button      writeBtn;

    //RecyclerView 변수 선언
    Context             context;
    RecyclerView        itemList;
    ItemListAdapter     itemListAdapter;
    LinearLayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeBtn = (Button)findViewById(R.id.writeBtn);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent);
            }
        });

        context=this;
        setView();
    }//onCreate()

    //view 설정
    private void setView(){
        categorySpinner = findViewById(R.id.category);
        itemList = findViewById(R.id.recyclerView);

        setRecyclerView();  //사용자 정의 메소드 : recyclerView 설정
        setListItem();  //사용자 정의 메소드 :
    }//end setView()

    private void setRecyclerView(){
        layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        itemList.setLayoutManager(layoutManager);
        itemListAdapter
                = new ItemListAdapter(context, R.layout.row_item, new ArrayList<Item>());

        itemList.setAdapter(itemListAdapter);
    }//end setRecyclerView()

    private void setListItem(){
        ArrayList<Item> list = getDummyList();
        itemListAdapter.addItemList(list);
    }//end setListItem()

    private ArrayList<Item> getDummyList(){
        ArrayList<Item> list = new ArrayList<Item>();

        Item item1 = new Item("제목1", "일상", "안드로이드 최종프로젝트");
        Item item2 = new Item("제목2", "글", "웹 최종프로젝트");

        list.add(item1); list.add(item2);

        return list;
    }//end getDummyList()
}
