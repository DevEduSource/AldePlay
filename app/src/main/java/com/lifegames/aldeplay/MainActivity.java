package com.lifegames.aldeplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycleMovie;
    private RecyclerView recycleMovie2;
    private MovieAdapter movieAdapter;
    private MovieAdapter movieAdapter2;
    private ArrayList<Movie> movies;
    private ArrayList<Movie> movies2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleMovie = findViewById(R.id.recycleMovies);
        movies = new ArrayList<Movie>();
        movies.add(new Movie("Matrix", "https://i.stack.imgur.com/Bzcs0.png"));
        movies.add(new Movie("X-men - Evolutions", "https://i.stack.imgur.com/Bzcs0.png"));
        movies.add(new Movie("Deadpool", "https://i.stack.imgur.com/Bzcs0.png"));


        movieAdapter = new MovieAdapter(MainActivity.this, movies);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        recycleMovie.setLayoutManager(layoutManager);
        recycleMovie.setAdapter(movieAdapter);






        recycleMovie2 = findViewById(R.id.recycleMovies2);
        movies2 = new ArrayList<Movie>();
        movies2.add(new Movie("Matrix", "https://i.stack.imgur.com/Bzcs0.png"));
        movies2.add(new Movie("Matrix", "https://i.stack.imgur.com/Bzcs0.png"));
        movies2.add(new Movie("Matrix", "https://i.stack.imgur.com/Bzcs0.png"));


        movieAdapter2 = new MovieAdapter(MainActivity.this, movies2);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        recycleMovie2.setLayoutManager(layoutManager2);
        recycleMovie2.setAdapter(movieAdapter2);


    }
}

//não hospedamos nenhum dos arquivos nos nossos servidores, apenas o indexamos.