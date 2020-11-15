package com.example.myapplication.database;

public class DataBeasInfo {
    public static final String DATABASE_NAME = "my_biggest_fear";
    public static  class MovieTable{
        public static final String TABLE_NAME = "movies";
        public static final String ID = "m_id";
        public static final String NAME = "Movie_name";
        public static final String img_url = "MovieImg_url1";
        public static final String SecondImgUrl = "SecMovieImg_url";
        public static final String Type = "Movie_type";
        public static final String  trailer= "MovieTrailer";
        public static final String releaseDate = "releaseDate";
        public static final String Length = "MovieLength";
        public static final String Language = "MovieLanguage"; //100 min
        public static final String category= "MovieCategory"; // +13   +18
        public static final String Rating= "rating";

    }
    public static  class CinemaTable{
        public static final String TABLE_NAME = "Cinemas";
        public static final String ID = "c_id";
        public static final String NAME = "Cinema_name";
        public static final String ADDRESS = "Cinema_address";
        public static final String MOVIEcount = "MovieCount";
        public static final String img_url = "MovieImg_url";
        public static final String PHONE = "phone";
        public static final String price_time = "price_time";
        public static final String IS3D = "is3D";
    }
    public static  class Cinema_movie{
        public static final String TABLE_NAME = "Cinema_Movie";
        public static final String M_ID = "Movie_id";
        public static final String  c_ID= "Cinema_id";
        public static final String  movie_time_money= "movie_time_money";
    }

    public static class Users{
        public static final String TABLE_NAME = "Users";
        public static final String Ufirstname = "FirstName";
        public static final String uLastname = "Lastname";
        public static final String uEmail = "Email";
        public static final String uPassword = "Password";
        public static final String U_ID = "User_id";
        public static final String ImgUri = "URI";

    }
    public static  class FavoriteList{
        public static final String TABLE_NAME = "User_favorite";
        public static final String Cinema_id = "Cinema_id";
        public static final String User_id = "user_id";

    }
}
