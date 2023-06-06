package com.lifegames.aldeplay;

import android.util.Log;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.PropertyName;
public class Movie {
    public String NameMovie, ImgUrl, UrlMovie, Sinopse, UrlBG;
    public Movie() {
    }
    public Movie(String NameMovie, String ImgUrl, String UrlMovie, String Sinopse, String UrlBG) {
        this.NameMovie = NameMovie;
        this.ImgUrl = ImgUrl;
        this.UrlMovie = UrlMovie;
        this.Sinopse = Sinopse;
        this.UrlBG = UrlBG;
    }
    @PropertyName("NameMovie")
    public String getNameMovie() {
        return NameMovie;
    }
    @PropertyName("NameMovie")
    public void setNameMovie(String NameMovie) {
        this.NameMovie = NameMovie;
    }

    @PropertyName("ImgUrl")
    public String getImgUrl() {
        return ImgUrl;
    }
    @PropertyName("ImgUrl")
    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }
    @PropertyName("UrlBG")
    public String getUrlBG() {
        return UrlBG;
    }
    @PropertyName("UrlBG")
    public void setUrlBG(String UrlBG) {
        this.UrlBG = UrlBG;
    }
    @PropertyName("UrlMovie")
    public String getUrlMovie() {
        return UrlMovie;
    }
    @PropertyName("UrlMovie")
    public void setUrlMovie(String UrlMovie) {
        this.UrlMovie = UrlMovie;
    }

    @PropertyName("Sinopse")
    public String getSinopse() {
        return Sinopse;
    }
    @PropertyName("Sinopse")
    public void setSinopse(String Sinopse) {
        this.Sinopse = Sinopse;
    }
}
