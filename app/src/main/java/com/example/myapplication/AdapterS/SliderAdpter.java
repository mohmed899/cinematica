package com.example.myapplication.AdapterS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.myapplication.Models.Movie;
import com.example.myapplication.R;

import java.util.List;

public class SliderAdpter extends PagerAdapter {
List<Movie>movieList;
    Context context;

    public SliderAdpter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

    View v= LayoutInflater.from(container.getContext()).inflate(R.layout.movie_img_slider_item, container, false);
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflater.inflate(R.layout.movie_img_slider_item,null);

        ImageView imageView=v.findViewById(R.id.slider_img);
        TextView textView=v.findViewById(R.id.slider_movieTitle);
        Glide.with(context).load(movieList.get(position).getImgURL()).into(imageView);
        textView.setText(movieList.get(position).getName());
        container.addView(v);
                return v;

    }
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
