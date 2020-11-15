package com.example.myapplication.Models;

import com.example.myapplication.UI.Cinema;

import java.util.List;

public class AllFavoriteCinema {

List<Movie> movieList;
 Cinema cinema;

    public AllFavoriteCinema(List<Movie> movieList,  Cinema cinema) {
        this.movieList = movieList;
        this.cinema = cinema;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public String getCinemaName()
    {
        return cinema.getName();
    }
}
