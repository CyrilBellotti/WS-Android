package com.example.geekpressmobile;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.wearable.activity.WearableActivity;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends WearableActivity {

   // private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(1);

     //   mTextView = (TextView) findViewById(R.id.text);

        TextView titreTextView = (TextView) findViewById(R.id.titre);
        TextView contenuTextView = (TextView) findViewById(R.id.article);
        TextView auteurTextView = (TextView) findViewById(R.id.auteur);

        DAOArticle daoArticle = new DAOArticle();
        DAOArticle.ObjectTest art = daoArticle.getListArticle().get(0);
        titreTextView.setText(Html.fromHtml(art.getTitre()));
        contenuTextView.setText(Html.fromHtml(art.getArticle()));
        auteurTextView.setText(art.getAuteur());

        // Enables Always-on
        setAmbientEnabled();
    }
}
