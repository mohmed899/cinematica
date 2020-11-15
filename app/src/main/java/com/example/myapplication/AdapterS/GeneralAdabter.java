package com.example.myapplication.AdapterS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myapplication.UI.Cinema;
import com.example.myapplication.Models.Movie;
import com.example.myapplication.UI.MyIterFace;
import com.example.myapplication.R;

import java.util.List;

public class GeneralAdabter extends RecyclerView.Adapter<GeneralAdabter.Holder>{
 List<Movie>movieList;
    List<Cinema>cinemaList;
    MyIterFace iterFace;
    Context context;
    int cinema=0,needTimeInfo=0,isMovieItemCard=0;
    public GeneralAdabter(List<Movie> movieList, MyIterFace iterFace, Context context,int isCard) {
        this.movieList = movieList;
        this.iterFace = iterFace;
        this.context = context;
        this.isMovieItemCard=isCard;
    }
    public GeneralAdabter(List<Cinema> movieList, MyIterFace iterFace, Context context, int isCinema, int needTimeInfo) {
        this.cinemaList = movieList;
        this.iterFace = iterFace;
        this.context = context;
        this.cinema=isCinema; // for cinema flag
        this.needTimeInfo=needTimeInfo;

    }
    @NonNull
    @Override
    public GeneralAdabter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(cinema==1) // in case we use this adapter for cinema
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cinema_item, parent, false));
        else { // use it for move
//            if(isMovieItemCard==1)
//                return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_card, parent, false));
//            //normal movie
                return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull GeneralAdabter.Holder holder, int position) {
        if(cinema==0){
            if(isMovieItemCard==0)
       holder.name.setText(movieList.get(position).getName());
       holder.movieRating.setText((movieList.get(position).getRating()));
       Glide.with(context).load(movieList.get(position).getImgURL()).into(holder.img);}
         else
        {
            holder.name.setText(cinemaList.get(position).getName());
            holder.cinemaAddress.setText((cinemaList.get(position).getAddress()));
            holder.movieCount.setText(cinemaList.get(position).getMovie_count()+"\nMovie(s)");
            Glide.with(context).load(cinemaList.get(position).getImgURL()).circleCrop().into(holder.img);
            if(needTimeInfo==1)
                holder.moneyTimeInfo.setText(cinemaList.get(position).getTimeMoneyInfo());
             //   Glide.with(context).load(secMovieURLimg).into(holder.secMovieImg);

        }
    }

    @Override
    public int getItemCount() {
        if(cinema==0)
        return movieList.size();
        return cinemaList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name,movieCount,cinemaAddress,moneyTimeInfo,movieRating;
        ImageView img,secMovieImg;
        int name_id,img_id;
        public Holder(@NonNull View itemView) {
            super(itemView);
            if(cinema==0)
            {
                name_id=R.id.movie_name;
                img_id=R.id.movie_img;
            }
            else {
                name_id=R.id.cinema_name;
                img_id=R.id.cinema_img;
                movieCount=itemView.findViewById(R.id.Cinema_movie_count);
                cinemaAddress=itemView.findViewById(R.id.Cinema_address);
                moneyTimeInfo=itemView.findViewById(R.id.movie_time_money);
                //secMovieImg=itemView.findViewById(R.id.movie_img_detail);
            }
            name=itemView.findViewById(name_id);
            img=itemView.findViewById(img_id);
            movieRating=itemView.findViewById(R.id.Rating);
            itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if(getAdapterPosition()!=-1)
//                                                    //if(getAdapterPosition()==null)
//                                                        Log.d("null",getAdapterPosition()+"");  l
                                                    if(cinema==0)
                                                iterFace.onMovieClik(movieList.get(getAdapterPosition()));
                                                    else
                                                        iterFace.onCinemaClik(cinemaList.get(getAdapterPosition()));
                                            }
                                        }
            );
        }
    }
}