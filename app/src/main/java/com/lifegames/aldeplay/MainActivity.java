package com.lifegames.aldeplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycleMovieSearch;
    RecyclerView recycleMovieAcao;
    RecyclerView recycleMovieAventura;
    RecyclerView recycleMovieFiccao;
    RecyclerView recycleMovieTerror;
    RecyclerView recycleMovieDrama;
    RecyclerView recycleMovieRomance;

    MovieAdapter movieAdapterAcao;
    MovieAdapter movieAdapterAventura;
    MovieAdapter movieAdapterFiccao;
    MovieAdapter movieAdapterTerror;
    MovieAdapter movieAdapterDrama;
    MovieAdapter movieAdapterRomance;
    MovieAdapterSearch movieAdapterSearch;

    ArrayList<Movie> moviesAcao;
    ArrayList<Movie> moviesAventura;
    ArrayList<Movie> moviesFiccao;
    ArrayList<Movie> moviesTerror;
    ArrayList<Movie> moviesDrama;
    ArrayList<Movie> moviesRomance;
    ArrayList<Movie> moviesSearch;

    SearchView searchView;
    ArrayList<Movie> movies;
    FirebaseFirestore db;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        db = FirebaseFirestore.getInstance();

        recycleMovieSearch = findViewById(R.id.recycleMoviesSearch);
        recycleMovieSearch.setHasFixedSize(true);
        recycleMovieSearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        moviesSearch = new ArrayList<Movie>();
        movieAdapterSearch = new MovieAdapterSearch(MainActivity.this, moviesSearch);
        recycleMovieSearch.setAdapter(movieAdapterSearch);

        recycleMovieAcao = findViewById(R.id.recycleMovies);
        recycleMovieAcao.setHasFixedSize(true);
        recycleMovieAcao.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        moviesAcao = new ArrayList<Movie>();
        movieAdapterAcao = new MovieAdapter(MainActivity.this, moviesAcao);
        recycleMovieAcao.setAdapter(movieAdapterAcao);

        recycleMovieAventura = findViewById(R.id.recycleMoviesAventura);
        recycleMovieAventura.setHasFixedSize(true);
        recycleMovieAventura.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        moviesAventura = new ArrayList<Movie>();
        movieAdapterAventura = new MovieAdapter(MainActivity.this, moviesAventura);
        recycleMovieAventura.setAdapter(movieAdapterAventura);

        recycleMovieFiccao = findViewById(R.id.recycleMoviesFiccao);
        recycleMovieFiccao.setHasFixedSize(true);
        recycleMovieFiccao.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        moviesFiccao = new ArrayList<Movie>();
        movieAdapterFiccao = new MovieAdapter(MainActivity.this, moviesFiccao);
        recycleMovieFiccao.setAdapter(movieAdapterFiccao);

        recycleMovieTerror = findViewById(R.id.recycleMoviesTerror);
        recycleMovieTerror.setHasFixedSize(true);
        recycleMovieTerror.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        moviesTerror = new ArrayList<Movie>();
        movieAdapterTerror = new MovieAdapter(MainActivity.this, moviesTerror);
        recycleMovieTerror.setAdapter(movieAdapterTerror);

        recycleMovieDrama = findViewById(R.id.recycleMoviesDrama);
        recycleMovieDrama.setHasFixedSize(true);
        recycleMovieDrama.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        moviesDrama = new ArrayList<Movie>();
        movieAdapterDrama = new MovieAdapter(MainActivity.this, moviesDrama);
        recycleMovieDrama.setAdapter(movieAdapterDrama);

        recycleMovieRomance = findViewById(R.id.recycleMoviesRomance);
        recycleMovieRomance.setHasFixedSize(true);
        recycleMovieRomance.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        moviesRomance = new ArrayList<Movie>();
        movieAdapterRomance = new MovieAdapter(MainActivity.this, moviesRomance);
        recycleMovieRomance.setAdapter(movieAdapterRomance);

        EventChangeListenerAcao();
        EventChangeListenerAventura();
        EventChangeListenerFiccao();
        EventChangeListenerTerror();
        EventChangeListenerDrama();
        EventChangeListenerRomance();
        EventChangeListenerSearch();
    }
    private void EventChangeListenerAcao() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference MoviesRef = db.collection("Movie");
        MoviesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Movie movie = document.toObject(Movie.class);
                    if(document.getString("Type").contains("ação")){
                        moviesAcao.add(movie);
                        movieAdapterAcao.notifyDataSetChanged();
                    }
                    movieAdapterAcao.notifyDataSetChanged();
                }
            } else {
                Log.d("Error task", "Error getting documents: ", task.getException());
            }
        });
    }
    private void EventChangeListenerAventura() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference MoviesRef = db.collection("Movie");
        MoviesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Movie movie = document.toObject(Movie.class);
                    if(document.getString("Type").contains("aventura")){
                        moviesAventura.add(movie);
                        movieAdapterAventura.notifyDataSetChanged();
                    }
                    movieAdapterAventura.notifyDataSetChanged();
                }
            } else {
                Log.d("Error task", "Error getting documents: ", task.getException());
            }
        });
    }
    private void EventChangeListenerFiccao() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference MoviesRef = db.collection("Movie");
        MoviesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Movie movie = document.toObject(Movie.class);
                    if(document.getString("Type").contains("ficção")){
                        moviesFiccao.add(movie);
                        movieAdapterFiccao.notifyDataSetChanged();
                    }
                    movieAdapterFiccao.notifyDataSetChanged();
                }
            } else {
                Log.d("Error task", "Error getting documents: ", task.getException());
            }
        });
    }
    private void EventChangeListenerTerror() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference MoviesRef = db.collection("Movie");
        MoviesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Movie movie = document.toObject(Movie.class);
                    if(document.getString("Type").contains("terror")){
                        moviesTerror.add(movie);
                        movieAdapterTerror.notifyDataSetChanged();
                    }
                    movieAdapterTerror.notifyDataSetChanged();
                }
            } else {
                Log.d("Error task", "Error getting documents: ", task.getException());
            }
        });
    }
    private void EventChangeListenerDrama() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference MoviesRef = db.collection("Movie");
        MoviesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Movie movie = document.toObject(Movie.class);
                    if(document.getString("Type").contains("drama")){
                        moviesDrama.add(movie);
                        movieAdapterDrama.notifyDataSetChanged();
                    }
                    movieAdapterDrama.notifyDataSetChanged();
                }
            } else {
                Log.d("Error task", "Error getting documents: ", task.getException());
            }
        });
    }
    private void EventChangeListenerRomance() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference MoviesRef = db.collection("Movie");
        MoviesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Movie movie = document.toObject(Movie.class);
                    if(document.getString("Type").contains("romance")){
                        moviesRomance.add(movie);
                        movieAdapterRomance.notifyDataSetChanged();
                    }
                    movieAdapterRomance.notifyDataSetChanged();
                }
            } else {
                Log.d("Error task", "Error getting documents: ", task.getException());
            }
        });
    }
    private void EventChangeListenerSearch() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query MoviesRef = db.collection("Movie");
        MoviesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Movie movie = document.toObject(Movie.class);
                    moviesSearch.add(movie);
                    movieAdapterSearch.notifyDataSetChanged();
                }
            } else {
                Log.d("Error task", "Error getting documents: ", task.getException());
            }
        });
    }
    private void filterList(String text) {
        List<Movie> filteredList = new ArrayList<>();
        for(Movie movie : moviesSearch){
            if(movie.getNameMovie().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(movie);
            }
            if(filteredList.isEmpty()){
                //isEmpty
            } else {
                movieAdapterSearch.setFilteredList(filteredList);
            }
        }
    }
}

