package com.example.myapplication.UI;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.AdapterS.GeneralAdabter;
import com.example.myapplication.Models.Movie;
import com.example.myapplication.Models.User;
import com.example.myapplication.R;
import com.example.myapplication.database.DataBase;
import com.example.myapplication.database.DataBeasInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CinemaDetails extends AppCompatActivity implements MyIterFace, View.OnClickListener {
    DataBase db;
    List<Movie> movieList = new ArrayList<>();
    Cinema cinema;
    boolean thisCinemaisFavo;
    FloatingActionButton favoritBtn;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_details);
//do in
        ImageView cinemmImg = findViewById(R.id.cinema_img_detail);
        ImageView cinema3D = findViewById(R.id.img_3d);
        TextView cinemaName = findViewById(R.id.cinema_name_detail);
        TextView cinemaAddress = findViewById(R.id.textView2);
        TextView cinemaTime1 = findViewById(R.id.time_tv1);
        TextView cinemaTime2 = findViewById(R.id.time_tv2);
        TextView cinemaTime3 = findViewById(R.id.time_tv3);
        TextView cinemaPrice = findViewById(R.id.money_tv);
        favoritBtn = findViewById(R.id.favo_btn);
        FloatingActionButton locationBtn = findViewById(R.id.loca_btn);
        FloatingActionButton callBtn = findViewById(R.id.floatingActionButton8);
        RecyclerView recyclerView = findViewById(R.id.rec_view);
        db = new DataBase(getApplicationContext());
        android.content.SharedPreferences sharedPreferences = getSharedPreferences(User.PREFERENCE_NAME, Context.MODE_PRIVATE);
        userID = sharedPreferences.getString(User.ID, "-1");



        //get clicked cinema obj
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        cinema = (Cinema) bundle.getSerializable(DataBeasInfo.CinemaTable.TABLE_NAME);
        //set the view
        Glide.with(this).load(cinema.getImgURL()).into(cinemmImg);
        cinemaName.setText(cinema.getName());
        cinemaAddress.setText(cinema.getAddress());
        if (cinema.is3D.equals("no"))
            cinema3D.setVisibility(View.INVISIBLE);
        String str = cinema.getTimeMoneyInfo();
        String[] arrOfStr = str.split(" ", 5);
        cinemaPrice.setText(arrOfStr[0]);
        cinemaTime1.setText(arrOfStr[1]);
        cinemaTime2.setText(arrOfStr[2]);
        cinemaTime3.setText(arrOfStr[3]);
        callBtn.setOnClickListener(this);
        locationBtn.setOnClickListener(this);
        favoritBtn.setOnClickListener(this);
// get the movies of this cinema
        movieList=Movie.showMovieWithFillter("movieOfcinema",cinema.getId(),db);
//check if this cinema is now favorite for user
        thisCinemaisFavo = db.find("favCinemaOfUser", userID, cinema.getId(), null);
        if (thisCinemaisFavo)
            favoritBtn.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_selected_favorite_24));
        else
            favoritBtn.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_not_selected_favorite_24));

        GeneralAdabter adabter = new GeneralAdabter(movieList, this, this,1);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adabter);
    }

    @Override
    public void onMovieClik(Movie movie) {
        Intent intent = new Intent(this,MovieDetails.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DataBeasInfo.MovieTable.TABLE_NAME, movie);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onCinemaClik(Cinema movie) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.floatingActionButton8) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + cinema.getPhone()));
            startActivity(intent);
        } else if (view.getId() == R.id.loca_btn) {
           Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + cinema.getAddress() + "\"");
          //  Uri gmmIntentUri = Uri.parse("geo:30.081439,31.364656?q=cinema");
           // geo:0,0?q=34.99,-106.61(Treasure)
            // Uri gmmIntentUri = Uri.parse("http://plus.codes/26R7+32");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        } else if (view.getId() == R.id.favo_btn) {
            if (thisCinemaisFavo) {

                favoritBtn.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_not_selected_favorite_24));
                db.deleteFavoriteCinema(cinema.getId(), userID);
                thisCinemaisFavo = false;
            } else if (!thisCinemaisFavo) {

                favoritBtn.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_selected_favorite_24));
                thisCinemaisFavo = true;
                db.addTofavorite(userID, cinema.getId());
            }


        }

    }
}