package com.lifegames.aldeplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        TextView txtMovieNameDetails = findViewById(R.id.txtMovieNameDetails);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("nameMovie");
            txtMovieNameDetails.setText(j);
        }

        WebView webMovie = findViewById(R.id.webMovie);
        CookieManager.getInstance().setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webMovie, true);
        }
        webMovie.setWebViewClient(new WebViewClient());
        webMovie.setWebChromeClient(new WebChromeClient());
        webMovie.getSettings().setJavaScriptEnabled(true);
        webMovie.getSettings().setDomStorageEnabled(true);
        webMovie.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);

        String webContent="<iframe src=\"https://drive.google.com/file/d/1l1tIHmPdOh4uePmnh_zJHpPEQXhTbRyQ\"></iframe>";
        if(webContent.contains("iframe")){
            Matcher matcher = Pattern.compile("src=\"([^\"]+)\"").matcher(webContent);
            matcher.find();
            String src = matcher.group(1);
            webContent=src;

            try {
                URL myURL = new URL(src);
                webMovie.loadUrl(src);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else {
            webMovie.loadDataWithBaseURL(null, "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + webContent, "text/html", "UTF-8", null);
        }
    }
}
