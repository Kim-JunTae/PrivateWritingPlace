package com.example.user.privatewritingplace.item;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by user on 2020-07-27.
 */

public class Item {
    public String title,
                  category,
                  content,
                  regDate;

    public Item(String title, String category, String content){
        SimpleDateFormat dateFormat
                = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        Date date = new Date();

        regDate = dateFormat.format(date);

        this.title = title;
        this.category = category;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}
