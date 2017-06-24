package com.example.hassan.azkarandad3ia;

import java.io.Serializable;

/**
 * Created by hassan on 12/04/2017.
 */

public class Story implements Serializable{
    private  int id ;
    private String title ,desc;
    private int bookMark ,cat_id ;

    public Story(int id, String title, String desc ,int bookMark ,int cat_id) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.bookMark=bookMark;
        this.cat_id=cat_id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getBookMark() {
        return bookMark;
    }

    public void setBookMark(int bookMark) {
        this.bookMark = bookMark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
