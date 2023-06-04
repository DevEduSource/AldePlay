package com.lifegames.aldeplay;

public class Movie {
    public Movie(String movieName, String imgMovie) {
        this.movieName = movieName;
        this.imgMovie = imgMovie;
    }

    private String movieName;
    private String imgMovie;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public String getImgMovie() {
        return imgMovie;
    }

    public void setImgMovie(String typeMovie) {
        this.imgMovie = imgMovie;
    }
}
