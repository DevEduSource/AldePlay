package com.lifegames.aldeplay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapterSearch extends RecyclerView.Adapter<MovieAdapterSearch.MyViewHolder> {
    Context context;
    ArrayList<Movie> movieArrayList;
    public MovieAdapterSearch(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }
    @NonNull
    @Override
    public MovieAdapterSearch.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_list_search_item, parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MovieAdapterSearch.MyViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        holder.name.setText(movie.getNameMovie());
        Glide.with(context).load(movie.getImgUrl()).into(holder.imgMovie);
        View.OnClickListener onClickListener = v -> {
            Bundle bundle = new Bundle();
            bundle.putString("nameMovie", movie.getNameMovie());
            bundle.putString("urlIMG", movie.getImgUrl());
            bundle.putString("urlMovie", movie.getUrlMovie());
            bundle.putString("txtSinopse", movie.getSinopse());
            bundle.putString("urlBG", movie.getUrlBG());
            Intent intent = new Intent(v.getContext(), MovieDetails.class);
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
        };
        holder.name.setOnClickListener(onClickListener);
        holder.imgMovie.setOnClickListener(onClickListener);
    }
    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }
    public void setFilteredList(List<Movie> filteredList){
        this.movieArrayList = (ArrayList<Movie>) filteredList;
        notifyDataSetChanged();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView imgMovie;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.txtNameMovie);
            this.imgMovie = itemView.findViewById(R.id.imgMovie);
        }
    }
}
