package com.example.myapplication.UI.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.AdapterS.GeneralAdabter;
import com.example.myapplication.AdapterS.SliderAdpter;
import com.example.myapplication.Models.Movie;
import com.example.myapplication.R;
import com.example.myapplication.UI.Cinema;
import com.example.myapplication.UI.MovieDetails;
import com.example.myapplication.UI.MyIterFace;
import com.example.myapplication.database.DataBase;
import com.example.myapplication.database.DataBeasInfo;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MoviesFragment extends Fragment implements MyIterFace, PopupMenu.OnMenuItemClickListener, View.OnClickListener {

    DataBase db;
    SliderAdpter sliderAdpter;
    ViewPager viewPager;
    List<Movie> allMovieList;
    List<Movie> differentTypeMovieL;
    TabLayout imgIndicator;
    GeneralAdabter typeMovListAdapter;
    RecyclerView view2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movies_fragment, container, false);


        db = new DataBase(getContext());
        allMovieList = Movie.showMovieWithFillter("Allmovies", "null", db); //show movie function depend on mode and filter
        differentTypeMovieL = Movie.showMovieWithFillter("Allmovies", "null", db); //show movie function depend on mode and filter
        viewPager = view.findViewById(R.id.movie_img_slider);
        imgIndicator = view.findViewById(R.id.slider_img_indicator);
        ImageView popup1 = view.findViewById(R.id.popup);
        RecyclerView view1 = view.findViewById(R.id.movie_recycle);
        view2 = view.findViewById(R.id.movie_recycle2);
        RecyclerView view3 = view.findViewById(R.id.movie_recycle3);
        popup1.setOnClickListener(this);
        Timer timer = new Timer();

        GeneralAdabter allMovListAdapter = new GeneralAdabter(allMovieList, this, getContext(), 0);
        GeneralAdabter popularMovListAdapter = new GeneralAdabter(Movie.showMovieWithFillter("MostPopularMovie", "null", db), this, getContext(), 0);
        typeMovListAdapter = new GeneralAdabter(differentTypeMovieL, this, getContext(), 0);

        sliderAdpter = new SliderAdpter(allMovieList, getContext());
        imgIndicator.setupWithViewPager(viewPager);
        timer.scheduleAtFixedRate(new SliderTimer(), 1000, 3000);
        imgIndicator.setupWithViewPager(viewPager, true);
        viewPager.setAdapter(sliderAdpter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        view1.setLayoutManager(gridLayoutManager);
//         view1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
//        view1.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        view1.setAdapter(allMovListAdapter);
        view2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        view2.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        view2.setAdapter(typeMovListAdapter);
        view3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        view3.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        view3.setAdapter(popularMovListAdapter);

        return view;
    }

    @Override
    public void onMovieClik(Movie movie) {

        Intent intent = new Intent(getContext(), MovieDetails.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DataBeasInfo.MovieTable.TABLE_NAME, movie);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void onCinemaClik(Cinema movie) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        String movietypeFilter;
        switch (menuItem.getItemId()) {
            case R.id.action:
                movietypeFilter = "Action";
                break;
            case R.id.drama:
                movietypeFilter = "Drama";
                break;
            case R.id.horror:
                movietypeFilter = "Horror";
                break;
            case R.id.fantasy:
                movietypeFilter = "Fantasy";
                break;
            default:
                return false;
        }

        typeMovListAdapter = new GeneralAdabter(Movie.showMovieWithFillter("movieByType", movietypeFilter, db), this, getContext(), 0);
        view2.setAdapter(typeMovListAdapter);
        Log.i(movietypeFilter, "im  out");
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.popup) {
            PopupMenu popup = new PopupMenu(getContext(), view);
            popup.setOnMenuItemClickListener(this);
            popup.inflate(R.menu.movie_type_selector);
            popup.show();
        }
    }


    class SliderTimer extends TimerTask {
        @Override
        public void run() {
            if (getActivity() == null)
                return;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < 6) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1 % 6);
                    } else
                        viewPager.setCurrentItem(0);
                }
            });

        }
    }


}
