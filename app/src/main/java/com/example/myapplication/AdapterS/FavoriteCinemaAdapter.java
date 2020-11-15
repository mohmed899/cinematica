package com.example.myapplication.AdapterS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Models.AllFavoriteCinema;
import com.example.myapplication.Models.Movie;
import com.example.myapplication.R;
import com.example.myapplication.UI.MyIterFace;

import java.util.List;

public class FavoriteCinemaAdapter extends RecyclerView.Adapter<FavoriteCinemaAdapter.MainViewHolder> {
    MyIterFace iterFace;
    Context context;
    List<AllFavoriteCinema> allFavoriteCinemas;

    public FavoriteCinemaAdapter(MyIterFace iterFace, Context context, List<AllFavoriteCinema> allFavoriteCinemas) {
        this.iterFace = iterFace;
        this.context = context;
        this.allFavoriteCinemas = allFavoriteCinemas;
    }

    @NonNull
    @Override
    public FavoriteCinemaAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_cinema_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteCinemaAdapter.MainViewHolder holder, int position) {
        holder.cinemaName.setText(allFavoriteCinemas.get(position).getCinemaName());
        Glide.with(context).load(allFavoriteCinemas.get(position).getCinema().getImgURL()).into(holder.cinemaImg);
        setMoviesOfCinema(allFavoriteCinemas.get(position).getMovieList(), holder.cinemaView);
    }

    @Override
    public int getItemCount() {
        return allFavoriteCinemas.size();
    }

    void setMoviesOfCinema(List<Movie> moviesOfCinema, RecyclerView recyclerView) {
        GeneralAdabter movieAdapter = new GeneralAdabter(moviesOfCinema, iterFace, context, 1);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(movieAdapter);
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView cinemaName;
        ImageView cinemaImg;
        RecyclerView cinemaView;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            cinemaName = itemView.findViewById(R.id.favo_cinema_name);
            cinemaView = itemView.findViewById(R.id.favo_cinema_view);
            cinemaImg=itemView.findViewById(R.id.imageCinema);
            cinemaName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() != -1)
//

                        iterFace.onCinemaClik(allFavoriteCinemas.get(getAdapterPosition()).getCinema());
                }
            });

        }
    }
}
