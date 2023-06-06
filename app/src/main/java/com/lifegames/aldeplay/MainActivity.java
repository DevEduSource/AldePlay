package com.lifegames.aldeplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycleMovieAcao;
    RecyclerView recycleMovieAventura;
    MovieAdapter movieAdapterAcao;
    MovieAdapter movieAdapterAventura;
    ArrayList<Movie> moviesAcao;
    ArrayList<Movie> moviesAventura;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();


        recycleMovieAcao = findViewById(R.id.recycleMoviesAcao);
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

        EventChangeListenerAcao();
        EventChangeListenerAventura();
    }

    private void EventChangeListenerAcao() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference MoviesRef = db.collection("Movie");
        MoviesRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
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
            }
        });
    }

    private void EventChangeListenerAventura() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference MoviesRef = db.collection("Movie");
        MoviesRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
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
            }
        });
    }
}

