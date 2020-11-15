package com.example.myapplication.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.Models.User;

import java.util.concurrent.atomic.AtomicReference;

public class DataBase extends SQLiteOpenHelper {
    public static String dataBaseNAme = "dataBase";

    SQLiteDatabase db;

    public DataBase(@Nullable Context context) {
        super(context, dataBaseNAme, null, 1);
    }

    @Override
    //user table
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + DataBeasInfo.Users.TABLE_NAME + "("
                + DataBeasInfo.Users.U_ID + " INTEGER PRIMARY KEY,"
                + DataBeasInfo.Users.Ufirstname + " text,"
                + DataBeasInfo.Users.uLastname + " text,"
                + DataBeasInfo.Users.ImgUri + " text,"
                + DataBeasInfo.Users.uEmail + " text,"
                + DataBeasInfo.Users.uPassword + " text);");
        //favorite table
        sqLiteDatabase.execSQL("CREATE TABLE User_favorite (\n" +
                "    \n" +
                "    Cinema_id INTEGER NOT NULL,\n" +
                "    User_id INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (Cinema_id) REFERENCES Cinemas (c_id) ,\n" +
                "    FOREIGN KEY (user_id) REFERENCES Users (User_id)\n" +
                ");");

        //create movie table
        sqLiteDatabase.execSQL("CREATE TABLE " + DataBeasInfo.MovieTable.TABLE_NAME + "("
                + DataBeasInfo.MovieTable.ID + " INTEGER PRIMARY KEY,"
                + DataBeasInfo.MovieTable.NAME + " text,"
                + DataBeasInfo.MovieTable.Type + " text,"
                + DataBeasInfo.MovieTable.img_url + " text,"
                + DataBeasInfo.MovieTable.Rating + " text,"
                + DataBeasInfo.MovieTable.Length + " text,"
                + DataBeasInfo.MovieTable.Language + " text,"
                + DataBeasInfo.MovieTable.releaseDate + " text,"
                + DataBeasInfo.MovieTable.trailer + " text,"
                + DataBeasInfo.MovieTable.category + " text,"
                + DataBeasInfo.MovieTable.SecondImgUrl + " text)");

        //create cinema table
        sqLiteDatabase.execSQL("CREATE TABLE " + DataBeasInfo.CinemaTable.TABLE_NAME
                + "(" + DataBeasInfo.CinemaTable.ID + " integer primary key, "
                + DataBeasInfo.CinemaTable.MOVIEcount + " text,"
                + DataBeasInfo.CinemaTable.img_url + " text,"
                + DataBeasInfo.CinemaTable.PHONE + " text,"
                + DataBeasInfo.CinemaTable.ADDRESS + " text,"
                + DataBeasInfo.CinemaTable.NAME + " text,"
                + DataBeasInfo.CinemaTable.price_time + " text,"
                + DataBeasInfo.CinemaTable.IS3D + " text)");

//creat cinema_movie
        sqLiteDatabase.execSQL("CREATE TABLE Cinema_Movie(\n" +
                "    Cinema_id INTEGER NOT NULL,\n" +
                "    Movie_id INTEGER NOT NULL,\n" +
                "    movie_time_money text NOT NULL,\n" +
                "    FOREIGN KEY (Cinema_id) REFERENCES Cinemas (c_id) ,\n" +
                "    FOREIGN KEY (Movie_id ) REFERENCES movies (m_id)\n" +
                ");");

//m to m relationship table  between movies and cinema
        sqLiteDatabase.execSQL("INSERT INTO " + DataBeasInfo.Cinema_movie.TABLE_NAME + "(" + DataBeasInfo.Cinema_movie.M_ID + ',' + DataBeasInfo.Cinema_movie.c_ID + ',' + DataBeasInfo.Cinema_movie.movie_time_money + ")" +
                "VALUES" +
                "\t(\"1\",\"1\",\"100LM 0:00 0:00 0:00\"),\n" +
                "\t(\"2\",\"1\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"3\",\"1\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"5\",\"1\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"1\",\"2\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"3\",\"2\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"4\",\"2\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"5\",\"2\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"2\",\"2\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"6\",\"2\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"7\",\"2\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"8\",\"2\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"12\",\"2\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"15\",\"2\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t(\"1\",\"3\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"11\",\"3\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"10\",\"3\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"14\",\"3\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"8\",\"3\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"9\",\"3\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"3\",\"3\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"2\",\"3\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"11\",\"4\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"12\",\"4\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"1\",\"4\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"1\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"2\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"3\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"4\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"5\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"6\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"7\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"8\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"9\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"10\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"11\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"12\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"13\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"14\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"15\",\"5\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"1\",\"6\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"2\",\"6\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"3\",\"6\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"4\",\"6\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"5\",\"6\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"6\",\"6\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"7\",\"6\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"8\",\"6\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"9\",\"6\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"10\",\"6\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"11\",\"6\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"12\",\"6\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"13\",\"6\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"1\",\"7\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"2\",\"7\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"3\",\"7\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"4\",\"7\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"5\",\"7\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"6\",\"7\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"7\",\"7\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"4\",\"8\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"5\",\"8\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"6\",\"8\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"7\",\"8\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"8\",\"8\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"9\",\"8\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"10\",\"8\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"11\",\"8\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"12\",\"8\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"13\",\"8\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"14\",\"8\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"15\",\"8\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"1\",\"8\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"1\",\"9\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"2\",\"9\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"3\",\"9\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"4\",\"9\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"5\",\"9\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"11\",\"10\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"12\",\"10\",\"60LM 8:00 12:00 0:00\"),\n" +
                "\t (\"13\",\"10\",\"60LM 8:00 12:00 0:00\");"

        );
//movie data              //id,name,type,ur1,ur2,len,lang,cat,rate,tril,redat
        sqLiteDatabase.execSQL("INSERT INTO " + DataBeasInfo.MovieTable.TABLE_NAME + "(" + DataBeasInfo.MovieTable.NAME + ',' + DataBeasInfo.MovieTable.Type + ',' + DataBeasInfo.MovieTable.img_url + ',' + DataBeasInfo.MovieTable.SecondImgUrl + ',' + DataBeasInfo.MovieTable.category + ',' + DataBeasInfo.MovieTable.Language + ',' + DataBeasInfo.MovieTable.Length + ',' + DataBeasInfo.MovieTable.Rating + ',' + DataBeasInfo.MovieTable.trailer + ',' + DataBeasInfo.MovieTable.releaseDate + ")" +
                "VALUES\n" +
                "(\"Hamilton\",\"Action\",\"https://whatsondisneyplus.com/wp-content/uploads/2020/06/EE39F1F3-71A2-4968-B71F-7903D4A318A7-scaled.jpeg\" ,\"https://img.cinemablend.com/filter:scale/quill/1/7/d/8/8/b/17d88b8a10faddfc857551f23ee680d68c0121a6.jpg?mw=600\",\"+13\",\"English\",\"120min\",\"7.0\",\"https://www.youtube.com/watch?v=DSCKfXpAGHc\",\"3/7/2020\"),\n" +
                "(\"Joker\", \"Crime  Drama Thriller\",\"https://upload.wikimedia.org/wikipedia/en/e/e1/Joker_%282019_film%29_poster.jpg\",\"https://th.bing.com/th/id/OIP.GCDa766_qLi_W0iEsTuY3QHaE8?pid=Api&rs=1\",\"+18\",\"English\",\"122min\",\"6.3\",\"https://www.youtube.com/watch?v=zAGVQLHvwOY\",\"28/9/2019\"),\n" +
                "(\"The Invisible Man\", \"Horror Sc-Fi Thriller\",\"https://th.bing.com/th/id/OIP.1mwXFD-BPWMzPXHXz5NPMQAAAA?w=118&h=180&c=7&o=5&dpr=1.25&pid=1.7\",\"https://cdn.flickeringmyth.com/wp-content/uploads/2020/02/the-invisible-man-2020.jpg\",\"+16\",\"English\",\"124min\",\"7.5\",\"https://www.youtube.com/watch?v=Pso0Aj_cTh0\",\"1/2/2020\"),\n" +
                "(\"Taw'am Rouhy\",\"Romance\",\"https://aljaras.com/wp-content/uploads/2020/02/A6698621-6F14-4A04-AACE-3E9E4B55CBC6-441x630.jpeg\",\"https://haveneg.com/wp-content/uploads/2020/06/IMG-20200614-WA0021.jpg\",\"AA\",\"Arabic\",\"105min\",\"8.2\",\"https://www.youtube.com/watch?v=b9QKNkSVRzM\",\"19/8/2020\"),\n" +
                "(\"The Iron Mask\",\"Fantasy Adventure\",\"https://upload.wikimedia.org/wikipedia/en/0/04/Viy_2-_Journey_to_China.jpg\",\"https://th.bing.com/th/id/OIP.SID8OnDWcn3eBGia2JWsAAHaEK?w=323&h=181&c=7&o=5&dpr=1.25&pid=1.7\",\"AA\",\"Chinese\", \"120min\",\"6.7\",\"https://www.youtube.com/watch?v=gQTK2v2kYk0\",\"10/4/2020\"),\n" +
                "(\"Ava\", \"Action Drama Crime\",\"https://m.media-amazon.com/images/M/MV5BMmQ4ZjA3NDctYmY0ZC00ZTlkLWEwYjAtNTMwMTYwNTBkYzdlXkEyXkFqcGdeQXVyNjU1NzU3MzE@._V1_SY1000_SX800_AL_.jpg\",\"https://m.media-amazon.com/images/M/MV5BMjMwNDYxMzI5MV5BMl5BanBnXkFtZTgwMDk0NTY2NjM@._V1_SY1000_CR0,0,1527,1000_AL_.jpg\",\"+16\",\"English\",\"96min\",\"5.0\",\"https://youtu.be/LNM4gXk1NC0\",\"12/8/2020\"),\n" +
                "(\"Becoming\",\"Thriller Sc-Fi Horror\",\"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTHuoPkDsRpXw1JkWJBx3QwqgtFuZwy_Nrxkb31__fyUmH4oXjV\",\"https://m.media-amazon.com/images/M/MV5BYTFiY2ZhNGUtOGM2NC00YjkzLTkxYjQtMTdhZGQ0YzE3Yjc2XkEyXkFqcGdeQXVyMTQ3NjQxMjY@._V1_SY1000_CR0,0,666,1000_AL_.jpg\",\"+16\",\"English\",\"89min\",\"7.0\",\"https://www.youtube.com/watch?v=eLEwNo78f0k\",\"5/8/2020\"),\n" +
                "(\"100% Wolf\",\"Comedy Animation\",\"https://th.bing.com/th/id/OIP.IctEokedzEE8Bbs9HiX2rAHaK6?w=127&h=187&c=7&o=5&dpr=1.25&pid=1.7\",\"https://www.animationmagazine.net/wordpress/wp-content/uploads/100-percent-wolf2-1.jpg\",\"+18\",\"English\",\"96min\",\"10\",\"https://www.youtube.com/watch?v=osbSKKS77SU\",\"29/5/2020\"),\n" +
                "(\"Al Ghasala\",\"Fantasy Comedy\",\"https://th.bing.com/th/id/OIP.rD1NlpfzxDO6Q4eGWhMkJgHaKX?pid=Api&rs=1\",\"https://www.mobtada.com/resize?src=uploads/images/2020/07/15960320350.jpg&w=750&h=450&zc=0&q=70\",\"+18\",\"Arabic\",\"110min\",\"8.0\",\"https://www.youtube.com/watch?v=3ULUohpQqMU\",\"5/8/2020\"),\n" +
                "(\"Black Water: Abyss\",\"Drama Fantasy Action\",\"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRFThVTT_7moEn3s_hv2tsbUlZVzOgXLXVAybfA07rpjGHjc0m1\",\"https://www.migratingmiss.com/wp-content/uploads/2018/01/Abyss_07-1024x640.jpg\",\"+12\",\"English\",\"98min\",\"2.0\",\"https://youtu.be/JKNv2YfrM7Y\",\"19/8/2020\"),\n" +
                "(\"BloodShot\",\"Fantasy Drama Action\",\"https://th.bing.com/th/id/OIP.U_z0BRhlNbZovBq9HBDHFAHaJQ?w=125&h=180&c=7&o=5&dpr=1.25&pid=1.7\",\"https://m.media-amazon.com/images/M/MV5BZjliMTVhYzQtOWU5Ny00ZjFkLWFiZmQtMWY1M2NkYTY3NzJkXkEyXkFqcGdeQXVyNzI1NzMxNzM@._V1_SY1000_SX1500_AL_.jpg\",\"+12\",\"English\",\"99min\",\"7.0\",\"https://youtu.be/vOUVVDWdXbo\",\"11/3/2020\"),\n" +
                "(\"Amulet\",\"Horror\",\"https://th.bing.com/th/id/OIP.MsR1ocFyPUgN9_3b02pPzAAAAA?w=115&h=180&c=7&o=5&dpr=1.25&pid=1.7\",\"https://www.lepolyester.com/wp-content/uploads/2019/12/amulet-3.jpg\",\"+18\",\"English\",\"99min\",\"7.0\",\"https://youtu.be/7peRCmvg3Kg\",\"29/7/2020\"),\n" +
                "(\"Live\",\"Crime Action\",\"https://th.bing.com/th/id/OIP.8KCXS1tsZ37FQed28tdZHAHaLG?w=115&h=180&c=7&o=5&dpr=1.25&pid=1.7\",\"https://th.bing.com/th/id/OIP.CrfHfgSA3OgfWQtgtuZFWAHaEK?w=314&h=180&c=7&o=5&dpr=1.25&pid=1.7\",\"+12\",\"English\",\"98min\",\"6.5\",\"https://youtu.be/H4EXeJSdwZ4\",\"19/8/2020\"),\n" +
                "(\"The Call of the Wild\",\"Animation Adventure Drama\",\"https://th.bing.com/th/id/OIP.JChUQHbRw5kspVtEfTXACAAAAA?w=115&h=180&c=7&o=5&dpr=1.25&pid=1.7\",\"https://pmcvariety.files.wordpress.com/2016/02/call-of-the-wild-2.jpg?w=1000&h=563&crop=1\",\" AC\",\"English\",\"100min\",\"5.7\",\"https://youtu.be/h1LG9So2ZBk\",\"26/2/2020\"),\n" +
                "(\"Brahms: The Boy II\",\"Horror Mystery Thriller\",\"https://posterspy.com/wp-content/uploads/2020/02/TheBoy2-1500x2122.jpg\",\"https://bloody-disgusting.com/wp-content/uploads/2019/02/the-boy-2-image-e1551898260582.jpeg\",\"+16\",\"English\",\"86 minutes\",\"3.5\",\"https://youtu.be/ytxEldPKnyA\",\"19/2/2020\"),\n" +
                "(\"The King of Staten Island\",\"Drama\",\"https://movies.universalpictures.com/media/staten-up-poster-5ea74677a466a-1.jpg\" ,\"https://img.cinemablend.com/filter:scale/quill/1/7/d/8/8/b/17d88b8a10faddfc857551f23ee680d68c0121a6.jpg?mw=600\",\"+13\",\"English\",\"90min\",\"8.0\",\"https://youtu.be/azkVr0VUSTA\",\"12/6/2020\");"

        );

// cinema data
        sqLiteDatabase.execSQL("INSERT INTO " + DataBeasInfo.CinemaTable.TABLE_NAME + "("
                + DataBeasInfo.CinemaTable.NAME + ',' + DataBeasInfo.CinemaTable.ADDRESS + ',' + DataBeasInfo.CinemaTable.MOVIEcount + ',' + DataBeasInfo.CinemaTable.img_url + ',' + DataBeasInfo.CinemaTable.PHONE + ',' + DataBeasInfo.CinemaTable.IS3D + ',' + DataBeasInfo.CinemaTable.price_time + ")" +
                "VALUES\n" +
                "\t(\"cairo mall Cinema\",\"269 Al Haram Street, El Nahda square\",\"4\",\"https://assets.cairo360.com/app/uploads/2019/10/VOX-Cinemas-1.jpg\",\"01026667836\",\"yes\",\"54LE 14:00 4:15 17:30\"),\n" +
                "\t(\"Cine Max - Cityscape\",\"6 October City, Giza - 6th of October - 6th of October),\",\"10\",\"https://media.filbalad.com/Places/logos/Large/86957_photo.jpg\",\"01206661336\" ,\"no\",\"45LE 13:12 17:15 5:40\"),\n" +
                "\t(\"cineplex Green Plaza\",\"14th Of May Rd.,Plaza Mall, Alexandria\",\"8\",\"https://pci-group.com/wp-content/uploads/CineplexLogoSq.jpg\",\" 03-4209155\",\"no\",\"40LE 13:00 15:15 2:30\"),\n" +
                "\t(\"galaxy cinema\",\"67 Abdel Aziz Al Saud Street, Manial Al Gharbi, Old Cairo\",\"3\",\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS5rj7CNhnBx5f8VLJ6GJKcDCY53jz_wfPUNw&usqp=CAU\",\"02 25325747\" ,\"yes\",\"88LE 16:00 13:15 2:30\"),\n" +
                "\t(\"Point 90\",\"3F Point90 mall Fifth Settlement In front of the American University\",\"15\",\"https://media.filbalad.com/Places/logos/Large/87024__310x310_76f8c5928d0ab6df7198333936b89d44f9eb2f9797d57f6c87b82919af35e27b.jpg\",\"0128 930 0032\",\"Yes\",\"90LE 18:00 23:15 5:30\"),\n" +
                "\t(\"Cfc Galaxy Cinema\" ,\"67 Abdel Aziz Al Saud Street, Manial Al Gharbi, Old Cairo\",\"13\",\"https://assets.cairo360.com/app/uploads/2017/03/VOX-Cinemas-250x250.jpg\",\"02 26186000\",\"yes\",\"45LE 1:00 7:15 2:30\"),\n" +
                "\t(\"Golf City Mall Cinema\",\"Obour City entrance - Golf City Mall - Obour City Cairo QH\",\"7\",\"https://media.filbalad.com/Places/logos/Large/21_golfcity-mall-cinem.png\",\"02 46106155\",\"no\",\"78LE 13:00 15:15 3:15\"),\n" +
                "\t(\"Sun City Mall Cinema\",\"El-Nasr Rd Sheraton Al Matar El Nozha Cairo Governorate\",\"12\",\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTQ8nOpDu5QHn_epuhaZO_kgub1Bf_gGGumEQ&usqp=CAU\",\"0120 811 3590\" ,\"yes\",\"50LE 11:00 9:15 2:30\"),\n" +
                "\t(\"Stefano Mall Cinema\",\"El-Gaish Rd, San Stifano, Qism El-Raml, Alexandria Governorate\",\"5\",\"https://www.rnscinemas.net/images/logo.png\",\"01065530865\",\"yes\",\"90LE 2:00 9:15 12:30\"),\n" +
                "\t(\"city center alamza cinema\",\"Sheraton Al MatarEl Nozha, Cairo Governorate\",\"3\",\"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR3qL55pHDXQ0SY_-luKvoTnmpb-EGul3RuWg&usqp=CAU\",\"02 25998260\"  ,\"yes\",\"60LE 10:00 12:15 2:30\");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + DataBeasInfo.MovieTable.TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists " + DataBeasInfo.Cinema_movie.TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists " + DataBeasInfo.CinemaTable.TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists " + DataBeasInfo.FavoriteList.TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists " + DataBeasInfo.Users.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    // create user function
    public void createuser(String mail, String pass, String Fname, String Lname) {
        ContentValues cv = new ContentValues();
        cv.put(DataBeasInfo.Users.Ufirstname, Fname);
        cv.put(DataBeasInfo.Users.uLastname, Lname);
        cv.put(DataBeasInfo.Users.uEmail, mail);
        cv.put(DataBeasInfo.Users.uPassword, pass);
        db = getWritableDatabase();
        db.insert(DataBeasInfo.Users.TABLE_NAME, null, cv);
        Log.d("test res", Fname);
        db.close();

    }

    //add cinema to user
    public void addTofavorite(String userID, String cinemaId) {
        ContentValues cv = new ContentValues();
        cv.put(DataBeasInfo.FavoriteList.User_id, userID);
        cv.put(DataBeasInfo.FavoriteList.Cinema_id, cinemaId);
        db = getWritableDatabase();
        db.insert(DataBeasInfo.FavoriteList.TABLE_NAME, null, cv);
        db.close();
    }

    // function to check is same value is exist or not using filter
    public boolean find(String valueToFind, String filter, String filter2, AtomicReference<String> userID) {
        boolean found = false;

        switch (valueToFind) {
            case "usermail": {
                String sql = "select " + DataBeasInfo.Users.uEmail + ',' + DataBeasInfo.Users.U_ID + " from " + DataBeasInfo.Users.TABLE_NAME + " where " + DataBeasInfo.Users.uEmail + " = " + "'" + filter + "'";
                db = getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                if (cursor.getCount() != 0) {
                    found = true;
                    cursor.moveToFirst();
                    userID.set(cursor.getString(1));
                    db.close();
                }
                cursor.close();
            }
            break;
            case "favCinemaOfUser": {
                String sql = "select " + DataBeasInfo.FavoriteList.Cinema_id + " from " + DataBeasInfo.FavoriteList.TABLE_NAME + " where " + DataBeasInfo.FavoriteList.User_id + " = " + "'" + filter + "'";
                db = getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        if (filter2.equals(cursor.getString(0)))
                            return true;
                        cursor.moveToNext();
                    }
                    cursor.close();
                    return false;
                } else
                    return false;

            }

        }

        return found;
    }

    // check if the user pass is right or not
    public boolean IsUpasswordMatch(String user_id, String password) {
        String sql = "select " + DataBeasInfo.Users.uPassword + " from " + DataBeasInfo.Users.TABLE_NAME + " where " + DataBeasInfo.Users.U_ID + " = " + "'" + user_id + "'";
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        Log.i("actual pass ", cursor.getString(0));
        if (cursor.getString(0).equals(password)) {
            Log.i("actual pass ", cursor.getString(0));
            cursor.close();
            return true;
        } else
            cursor.close();
        return false;
    }

    // show something based on mode and filter
    public Cursor show(String mode, String filter) {
        db = getReadableDatabase();
        String[] movieRow = {DataBeasInfo.MovieTable.ID, DataBeasInfo.MovieTable.NAME, DataBeasInfo.MovieTable.Type, DataBeasInfo.MovieTable.img_url, DataBeasInfo.MovieTable.SecondImgUrl, DataBeasInfo.MovieTable.Length, DataBeasInfo.MovieTable.Language, DataBeasInfo.MovieTable.category, DataBeasInfo.MovieTable.Rating, DataBeasInfo.MovieTable.trailer, DataBeasInfo.MovieTable.releaseDate}; //for movie mode
        String[] userRow = {DataBeasInfo.Users.U_ID, DataBeasInfo.Users.Ufirstname, DataBeasInfo.Users.uLastname, DataBeasInfo.Users.uEmail, DataBeasInfo.Users.uPassword, DataBeasInfo.Users.ImgUri};
        String[] cinemaRow = {DataBeasInfo.CinemaTable.ID, DataBeasInfo.CinemaTable.NAME, DataBeasInfo.CinemaTable.ADDRESS, DataBeasInfo.CinemaTable.MOVIEcount, DataBeasInfo.CinemaTable.img_url, DataBeasInfo.CinemaTable.PHONE, DataBeasInfo.CinemaTable.IS3D, DataBeasInfo.CinemaTable.price_time};//for cinema mode
        Cursor cursor = null;
        switch (mode) {
            case "Allmovies":
                cursor = db.query(DataBeasInfo.MovieTable.TABLE_NAME, movieRow, null, null, null, null, null);
                break;
            case "Allcinema":
                cursor = db.query(DataBeasInfo.CinemaTable.TABLE_NAME, cinemaRow, null, null, null, null, null);
                break;
            case "MostPopularMovie": {
                cursor = db.query(DataBeasInfo.MovieTable.TABLE_NAME, movieRow, DataBeasInfo.MovieTable.Rating + ">'" + 7 + "'", null, null, null, null);
            }
            break;
            case "favorite_cinema": {
                //    cursor=  db.query(DataBeasInfo.CinemaTable.TABLE_NAME, cinemaRow, null, null, null, null, null);
                String s = "Select " + DataBeasInfo.CinemaTable.ID + ',' + DataBeasInfo.CinemaTable.NAME + ',' + DataBeasInfo.CinemaTable.ADDRESS + ',' +
                        DataBeasInfo.CinemaTable.MOVIEcount + ',' + DataBeasInfo.CinemaTable.img_url + ',' + DataBeasInfo.CinemaTable.PHONE + ',' +
                        DataBeasInfo.CinemaTable.IS3D + ',' + DataBeasInfo.CinemaTable.price_time + " from " + DataBeasInfo.FavoriteList.TABLE_NAME +
                        " INNER JOIN " + DataBeasInfo.Users.TABLE_NAME + " on " + DataBeasInfo.Users.TABLE_NAME + '.' + DataBeasInfo.Users.U_ID + " = " + DataBeasInfo.FavoriteList.TABLE_NAME + '.' + DataBeasInfo.FavoriteList.User_id + "\n" +
                        "INNER JOIN " + DataBeasInfo.CinemaTable.TABLE_NAME + " on " + DataBeasInfo.CinemaTable.TABLE_NAME + '.' + DataBeasInfo.CinemaTable.ID + " = " + DataBeasInfo.FavoriteList.TABLE_NAME + '.' + DataBeasInfo.FavoriteList.Cinema_id
                        + " where " + "Users.User_id" + " = " + "'" + filter + "'";

                cursor = db.rawQuery(s, null);
            }
            break;
            case "cinema_of_movie": {
                String s = "Select " + DataBeasInfo.CinemaTable.ID + ',' + DataBeasInfo.CinemaTable.NAME + ',' + DataBeasInfo.CinemaTable.ADDRESS + ',' +
                        DataBeasInfo.CinemaTable.MOVIEcount + ',' + DataBeasInfo.CinemaTable.img_url + ',' + DataBeasInfo.CinemaTable.PHONE + ',' +
                        DataBeasInfo.CinemaTable.IS3D + ',' + DataBeasInfo.CinemaTable.price_time + " from " + DataBeasInfo.Cinema_movie.TABLE_NAME + " INNER JOIN " + DataBeasInfo.MovieTable.TABLE_NAME + " on " + DataBeasInfo.MovieTable.TABLE_NAME + '.' + DataBeasInfo.MovieTable.ID + " = " + DataBeasInfo.Cinema_movie.TABLE_NAME + '.' + DataBeasInfo.Cinema_movie.M_ID + "\n" +
                        "INNER JOIN " + DataBeasInfo.CinemaTable.TABLE_NAME + " on " + DataBeasInfo.CinemaTable.TABLE_NAME + '.' + DataBeasInfo.CinemaTable.ID + " = " + DataBeasInfo.Cinema_movie.TABLE_NAME + '.' + DataBeasInfo.Cinema_movie.c_ID +
                        " where movies.m_id " + " = " + "'" + filter + "'";

                cursor = db.rawQuery(s, null);
            }
            break;
            case "cinemaBysearch": {
                String[] cinemaName = {filter + "%"};
                cursor = db.query(DataBeasInfo.CinemaTable.TABLE_NAME, cinemaRow, DataBeasInfo.CinemaTable.NAME + " LIKE ?", cinemaName, null, null, null);
            }
            break;
            case "movieByType": {
                String[] movieType = {"%" + filter + "%"};
                cursor = db.query(DataBeasInfo.MovieTable.TABLE_NAME, movieRow, DataBeasInfo.MovieTable.Type + " LIKE ?", movieType, null, null, null);
            }
            break;
            case "UserByid": {
                cursor = db.query(DataBeasInfo.Users.TABLE_NAME, userRow, DataBeasInfo.Users.U_ID + "='" + filter + "'", null, null, null, null);
                cursor.moveToFirst();
            }
            break;
            case "movieOfcinema": { //id,name,type,ur1,ur2,len,lang,cat,rate
                String s = "Select " + DataBeasInfo.MovieTable.ID + ',' + DataBeasInfo.MovieTable.NAME + ',' + DataBeasInfo.MovieTable.Type + ',' +
                        DataBeasInfo.MovieTable.img_url + ',' + DataBeasInfo.MovieTable.SecondImgUrl + ',' + DataBeasInfo.MovieTable.Length + ','
                        + DataBeasInfo.MovieTable.Language + ',' +
                        DataBeasInfo.MovieTable.category + ',' + DataBeasInfo.MovieTable.Rating + ',' +
                        DataBeasInfo.MovieTable.trailer + ',' + DataBeasInfo.MovieTable.releaseDate + " from " + DataBeasInfo.Cinema_movie.TABLE_NAME +
                        " INNER JOIN " + DataBeasInfo.MovieTable.TABLE_NAME + " on " + DataBeasInfo.MovieTable.TABLE_NAME + '.' + DataBeasInfo.MovieTable.ID + " = " + DataBeasInfo.Cinema_movie.TABLE_NAME + '.' + DataBeasInfo.Cinema_movie.M_ID + "\n" +
                        "INNER JOIN " + DataBeasInfo.CinemaTable.TABLE_NAME + " on " + DataBeasInfo.CinemaTable.TABLE_NAME + '.' + DataBeasInfo.CinemaTable.ID + " = " + DataBeasInfo.Cinema_movie.TABLE_NAME + '.' + DataBeasInfo.Cinema_movie.c_ID +
                        " where " + DataBeasInfo.CinemaTable.ID + " = " + "'" + filter + "'";
                cursor = db.rawQuery(s, null);
            }
            break;

            case "3Dcinema": {


                cursor = db.query(DataBeasInfo.CinemaTable.TABLE_NAME, cinemaRow, DataBeasInfo.CinemaTable.IS3D + "='" + filter + "'", null, null, null, null);
                Log.e("3d cinema count", cursor.getCount() + "");
            }


        }

        if (cursor == null)
            Log.e("DataBase", "show() return null cur");
        return cursor;
    }

    public void deleteFavoriteCinema(String cinema_id, String user_id) {
        db = getWritableDatabase();
        db.delete(DataBeasInfo.FavoriteList.TABLE_NAME, DataBeasInfo.FavoriteList.Cinema_id + "='" + cinema_id + "'" + " AND " + DataBeasInfo.FavoriteList.User_id + "='" + user_id + "'", null);
        db.close();
    }

    public void updatUpassword(User user) {

        ContentValues row = new ContentValues();
        row.put(DataBeasInfo.Users.uPassword, user.getPassword());
        Log.i("the geten pass ", user.getPassword());
        db = getWritableDatabase();
        db.update(DataBeasInfo.Users.TABLE_NAME, row, DataBeasInfo.Users.U_ID + "='" + user.getId() + "'", null);
        db.close();

    }

    public void addUserProImg(String imgUri, String user_id) {
        Log.i("the img for user id=  ", user_id);
        Log.i("the img URI =  ", imgUri);
        ContentValues cv = new ContentValues();
        cv.put(DataBeasInfo.Users.ImgUri, imgUri);
        db = getWritableDatabase();
        db.update(DataBeasInfo.Users.TABLE_NAME, cv, DataBeasInfo.Users.U_ID + "='" + user_id + "'", null);
        db.close();
    }
}
