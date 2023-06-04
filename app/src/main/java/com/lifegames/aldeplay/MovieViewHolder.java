package com.lifegames.aldeplay;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    TextView nameMovie;
    ImageView imgMovie;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        nameMovie = itemView.findViewById(R.id.txtNameMovie);
        imgMovie = itemView.findViewById(R.id.imgMovie);
    }
}
