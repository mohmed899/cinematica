package com.example.myapplication.UI;

import java.io.Serializable;

public class Cinema implements Serializable {
    String id, name, address, movie_count, imgURL, phone, is3D, timeMoneyInfo, distanceToUser;

    public Cinema(String id, String name, String address, String movie_count, String imgURL, String phone, String is3D, String timeMoneyInfo, String dis) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.movie_count = movie_count;
        this.imgURL = imgURL;
        this.phone = phone;
        this.is3D = is3D;
        this.timeMoneyInfo = timeMoneyInfo;
        this.distanceToUser = dis;
    }

//    public static List<Cinema> showCinemaWithFillter(String mod, String filter, DataBase db) {
//        List<Cinema> cinemaList = new ArrayList<>();
//        Cursor cr = db.show(mod, filter);
//        cinemaList.clear();
//        cr.moveToFirst();
//        while (!cr.isAfterLast()) {
//            cinemaList.add(new Cinema(cr.getString(0), cr.getString(1), cr.getString(2), cr.getString(3), cr.getString(4), cr.getString(5), cr.getString(6), cr.getString(7)));
//            cr.moveToNext();
//        }
//        cr.close();
//        return cinemaList;
//    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMovie_count() {
        return movie_count;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getPhone() {
        return phone;
    }

    public String getIs3D() {
        return is3D;
    }

    public String getDistanceToUser() {
        return distanceToUser;
    }

    public String getTimeMoneyInfo() {
        return timeMoneyInfo;
    }

}
