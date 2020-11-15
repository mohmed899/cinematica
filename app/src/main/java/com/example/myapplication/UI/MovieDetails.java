package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.AdapterS.GeneralAdabter;
import com.example.myapplication.Models.Movie;
import com.example.myapplication.R;
import com.example.myapplication.database.DataBase;
import com.example.myapplication.database.DataBeasInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MovieDetails extends AppCompatActivity implements View.OnClickListener, MyIterFace {

    ImageView movieImg;
    Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_deails);
        RecyclerView recyclerView=findViewById(R.id.cinemaOfMovie);
        FloatingActionButton playButton=findViewById(R.id.playButton);
        TextView movieCategory=findViewById(R.id.movie_category);
        TextView movieName=findViewById(R.id.details_movie_name);
        TextView movieLanguage=findViewById(R.id.movie_language);
        TextView movieLength=findViewById(R.id.movie_length);
        TextView moviegenre=findViewById(R.id.genre_tv_data);
        TextView movieReleasedDate=findViewById(R.id.date_tv_data);
         movieImg=findViewById(R.id.movie_img_detail);

        List<Cinema>cinemaList=new ArrayList<>();
        DataBase db=new DataBase(getApplicationContext());
        //get selected movie obj

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
         movie=(Movie)  bundle.getSerializable(DataBeasInfo.MovieTable.TABLE_NAME);
        assert movie != null;
        movieCategory.setText(movie.getCategory());
        movieLanguage.setText(movie.getLanguage());
        movieName.setText(movie.getName());
        movieLength.setText(movie.getLength());
        moviegenre.setText(movie.getType());
        movieReleasedDate.setText(movie.getRelaDate());
        Glide.with(this).load(movie.getSecondImgUrl()).into(movieImg);
        playButton.setOnClickListener(this);
        // get the cinemas where this movie is display
        Cursor cr = db.show("cinema_of_movie", movie.getId());
        cinemaList.clear();
        cr.moveToFirst();
        while (!cr.isAfterLast()) {
          //  Log.i("cinema id", "  " + cr.getString(0));
            cinemaList.add(new Cinema(cr.getString(0), cr.getString(1), cr.getString(2), cr.getString(3), cr.getString(4), cr.getString(5),cr.getString(6),cr.getString(7),"0"));
            cr.moveToNext();
        }
        GeneralAdabter adabter = new GeneralAdabter(cinemaList, this, this, 1,1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adabter);
    }


    @Override
    public void onClick(View view) {

        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(movie.getTrile())));
        Log.i("url ",movie.getTrile());
    }

    @Override
    public void onMovieClik(Movie movie) {

    }

    @Override
    public void onCinemaClik(Cinema cinema) {
        Intent intent = new Intent(this, CinemaDetails.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DataBeasInfo.CinemaTable.TABLE_NAME, cinema);
        intent.putExtras(bundle);
        startActivity(intent);
        Log.i("cinema frag", "cinema price " + cinema.getTimeMoneyInfo());
    }
}