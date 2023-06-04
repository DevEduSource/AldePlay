package com.lifegames.aldeplay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.nameMovie.setText(movie.getMovieName());
        //holder.imgMovie.setImageResource(movie.getImgMovie());
        Glide.with(context).load(movie.getImgMovie()).into(holder.imgMovie);

        holder.imgMovie.setOnClickListener(v -> {
            Intent intentDetails = new Intent(context, MovieDetails.class);
            intentDetails.putExtra("nameMovie", movie.getMovieName());
            intentDetails.putExtra("imgMovie", movie.getImgMovie());
            intentDetails.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            v.getContext().startActivities(new Intent[]{intentDetails});
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
