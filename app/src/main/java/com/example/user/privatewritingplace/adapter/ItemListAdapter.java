package com.example.user.privatewritingplace.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.privatewritingplace.R;
import com.example.user.privatewritingplace.item.Item;


import java.util.ArrayList;


public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {
    //맴버변수 선언
    private Context             context;
    private int                 resource;
    private ArrayList<Item>     itemList;

    //생성자 구현
    public ItemListAdapter(Context context, int resource, ArrayList<Item> itemList){
        this.context = context;
        this.resource = resource;
        this.itemList = itemList;
    }

    //사용자 정의 메소드 1
    public void addItem(Item item){
        this.itemList.add(0, item);
        notifyDataSetChanged();
    }//end addItem()
    //사용자 정의 메소드 2
    public void addItemList(ArrayList<Item> itemList){
        this.itemList.addAll(itemList);
        notifyDataSetChanged();
    }//end addItemList()
    //사용자 정의 메소드 3
    public void delItem(int position){
        this.itemList.remove(position);
        notifyDataSetChanged();
    }//end delItem()

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }//end getItemCount()

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(resource, parent, false);
        return new ViewHolder(view);
    }//end onCreateViewHolder()

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = itemList.get(position);

        holder.categoryText.setText(item.getCategory());
        holder.titleText.setText(item.getTitle());
        holder.contentText.setText(item.getContent());
        holder.dateText.setText(item.getRegDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show();
            }
        });
    }//end onBindViewHolder()

    //내부 클래스 구현 : 뷰홀더 상속 및 구현
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryText;
        TextView titleText;
        TextView contentText;
        TextView dateText;

        public ViewHolder(View itemView){
            super(itemView);
            categoryText = itemView.findViewById(R.id.category);
            titleText = itemView.findViewById(R.id.title);
            contentText = itemView.findViewById(R.id.content);
            dateText = itemView.findViewById(R.id.regdate);
        }
    }//end ViewHolder 내부 클래스

}
