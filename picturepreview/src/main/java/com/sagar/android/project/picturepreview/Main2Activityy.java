package com.sagar.android.project.picturepreview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;

import com.squareup.picasso.Picasso;

public class Main2Activityy extends AppCompatActivity {

    AppCompatImageView appCompatImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_activityy);

        appCompatImageView = (AppCompatImageView) findViewById(R.id.imageview_appcompat);

        Picasso.with(Main2Activityy.this).load("https://www.bodybuilding.com/fun/images/2014/in-defense-of-processed-foods-graphics-1.jpg").into(appCompatImageView);
    }

}
