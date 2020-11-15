package com.example.myapplication.Models;

import android.database.Cursor;

import com.example.myapplication.database.DataBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {
   private String id,name,type,imgURL,SecondImgUrl,Length,Language,category,rating,trile,relaDate;

    public String getRating() {
        return rating;
    }

    //id,name,type,ur1,ur2,len,lang,cat,rate,tril,redat


    public Movie(String id, String name, String type, String imgURL, String secondImgUrl, String length, String language, String category, String rating, String trile, String relaDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imgURL = imgURL;
        SecondImgUrl = secondImgUrl;
        Length = length;
        Language = language;
        this.category = category;
        this.rating = rating;
        this.trile = trile;
        this.relaDate = relaDate;
    }

    public String getSecondImgUrl() {
        return SecondImgUrl;
    }

    public String getLength() {
        return Length;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getImgURL() {
        return imgURL;
    }

    public static List<Movie> showMovieWithFillter(String mod, String filter, DataBase db)
    {
       List<Movie> movieList = new ArrayList<>();
        Cursor cr = db.show(mod, filter);
        movieList.clear();
        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            movieList.add(new Movie(cr.getString(0), cr.getString(1), cr.getString(2), cr.getString(3), cr.getString(4), cr.getString(5), cr.getString(6), cr.getString(7),cr.getString(8),cr.getString(9),cr.getString(10)));
            cr.moveToNext();
        }
        cr.close();
        return movieList;
    }

    public String getTrile() {
        return trile;
    }

    public String getRelaDate() {
        return relaDate;
    }
}
