package com.example.myapplication.UI.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.AdapterS.FavoriteCinemaAdapter;
import com.example.myapplication.AdapterS.GeneralAdabter;
import com.example.myapplication.Models.AllFavoriteCinema;
import com.example.myapplication.Models.Movie;
import com.example.myapplication.Models.User;
import com.example.myapplication.R;
import com.example.myapplication.UI.Cinema;
import com.example.myapplication.UI.CinemaDetails;
import com.example.myapplication.UI.MyIterFace;
import com.example.myapplication.database.DataBase;
import com.example.myapplication.database.DataBeasInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FavoriteFragment extends Fragment implements MyIterFace {
    DataBase db;
    List<Cinema> cinemaList;
    List<Movie> movieList;
    GeneralAdabter adabter;
    FavoriteCinemaAdapter cinemasAdapter;
    List<AllFavoriteCinema> favoriteCinemas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.favorite_fragment, container, false);
        RecyclerView view1 = view.findViewById(R.id.vafo_cinema);
        movieList = new ArrayList<>();
        favoriteCinemas = new ArrayList<>();
        db = new DataBase(getContext());
        SharedPreferences sharedPreferences = Objects.requireNonNull(getContext()).getSharedPreferences(User.PREFERENCE_NAME, Context.MODE_PRIVATE);
        String user_id = sharedPreferences.getString(User.ID, "-1");

        //get the favorite cinema
        Cursor cursor = db.show("favorite_cinema", user_id);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            // Get Cinema name And ID
            String cinemaId=cursor.getString(1);
            //get Movies of this cinema using the cinema id
            movieList = Movie.showMovieWithFillter("movieOfcinema", cursor.getString(0), db);
            //add this movies list to the favorite cinema card
            favoriteCinemas.add(new AllFavoriteCinema(movieList,new Cinema(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4)
                    ,cursor.getString(5),cursor.getString(6),cursor.getString(7),"0"
        )));
            cursor.moveToNext();
        }
        adabter = new GeneralAdabter(cinemaList, this, getContext(), 1, 0);
        cinemasAdapter = new FavoriteCinemaAdapter(this, getContext(), favoriteCinemas);
        view1.setLayoutManager(new LinearLayoutManager(getContext()));
        //view1.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        view1.setAdapter(cinemasAdapter);
        return view;

    }

    @Override
    public void onMovieClik(Movie movie) {

    }

    @Override
    public void onCinemaClik(Cinema cinema) {
        Intent intent = new Intent(getContext(), CinemaDetails.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DataBeasInfo.CinemaTable.TABLE_NAME, cinema);
        intent.putExtras(bundle);
        startActivity(intent);
        Log.i("cinema frag","cinema price "+ cinema.getTimeMoneyInfo());
    }
}
