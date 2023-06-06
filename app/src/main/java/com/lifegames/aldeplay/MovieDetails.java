package com.lifegames.aldeplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieDetails extends AppCompatActivity {

    private WebView webMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        TextView txtMovieNameDetails = findViewById(R.id.txtMovieNameDetails);
        TextView txtSinopse = findViewById(R.id.txtSinopse);
        ImageView imgBG = findViewById(R.id.ImgBG);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Settings").document("ShareApp");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        ImageView shareIMG = findViewById(R.id.imgShare);
                        shareIMG.setOnClickListener(v -> {
                            Intent intentShare = new Intent(Intent.ACTION_SEND);
                            intentShare.setType("text/plain");
                            String Body = "\"" + document.getString("body") + "\"";
                            String Sub = "\"" + document.getString("url") + "\"";
                            intentShare.putExtra(Intent.EXTRA_SUBJECT, Body);
                            intentShare.putExtra(Intent.EXTRA_TEXT, Sub);
                            v.getContext().startActivity(Intent.createChooser(intentShare, "Share The App!"));
                        });

                        Log.d("Error", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d("Error", "No such document");
                    }
                } else {
                    Log.d("Error", "get failed with ", task.getException());
                }
            }
        });

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        txtMovieNameDetails.setText(b.get("nameMovie").toString());
        txtSinopse.setText(b.get("txtSinopse").toString());
        String webContent="<iframe src=\"" + b.get("urlMovie").toString() + "\"></iframe>";
        Glide.with(this).load(b.get("urlBG").toString()).into(imgBG);


        webMovie = findViewById(R.id.webMovie);
        CookieManager.getInstance().setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webMovie, true);
        }
        webMovie.setWebViewClient(new WebViewClient());
        webMovie.setWebChromeClient(new MyChrome());

        webMovie.getSettings().setJavaScriptEnabled(true);
        webMovie.getSettings().setDomStorageEnabled(true);
        webMovie.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);

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

    private class MyChrome extends WebChromeClient {
        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;
        MyChrome() {}

        public Bitmap getDefaultVideoPoster()
        {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }

        public void onHideCustomView()
        {
            ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
        {
            if (this.mCustomView != null)
            {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webMovie.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        webMovie.restoreState(savedInstanceState);
    }
}

