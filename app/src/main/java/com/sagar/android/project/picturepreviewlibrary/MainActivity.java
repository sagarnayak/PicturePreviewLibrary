package com.sagar.android.project.picturepreviewlibrary;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.sagar.android.project.picturepreview.controller.PicturePreview;
import com.sagar.android.project.picturepreview.pojo.AdapterDataPojo;

import java.util.ArrayList;

/*
created by SAGAR KUMAR NAYAK on 17 OCT 2017
This is a class for testing the picture preview android library.
it will call the public method of the picture preview library that is integrated into this class in
its gradle.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        create a arraylist of AdapterDataPojo for sending the data to the library.
         */
        ArrayList<AdapterDataPojo> adapterDataPojos = new ArrayList<>();

        /*
        the AdapterDataPojo takes a url for the picture and also a details for showing the it in
        details page.
         */
        adapterDataPojos.add(new AdapterDataPojo("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/1200px-Good_Food_Display_-_NCI_Visuals_Online.jpg", "Pic details"));
        adapterDataPojos.add(new AdapterDataPojo("https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/15_surprisingly_healthy_foods_slideshow/493ss_thinkstock_rf_spaghetti_with_tomatoes_on_plate.jpg", "Pic details"));
        adapterDataPojos.add(new AdapterDataPojo("https://www.bodybuilding.com/fun/images/2014/in-defense-of-processed-foods-graphics-1.jpg", "Pic details"));
        adapterDataPojos.add(new AdapterDataPojo("https://www.bodybuilding.com/fun/images/2014/in-defense-of-processed-foods-graphics-1.jpg", "Pic details"));
        adapterDataPojos.add(new AdapterDataPojo("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/1200px-Good_Food_Display_-_NCI_Visuals_Online.jpg", "Pic details"));
        adapterDataPojos.add(new AdapterDataPojo("https://www.bodybuilding.com/fun/images/2014/in-defense-of-processed-foods-graphics-1.jpg", "Pic details"));
        adapterDataPojos.add(new AdapterDataPojo("https://www.bodybuilding.com/fun/images/2014/in-defense-of-processed-foods-graphics-1.jpg", "Pic details"));
        adapterDataPojos.add(new AdapterDataPojo("https://www.bodybuilding.com/fun/images/2014/in-defense-of-processed-foods-graphics-1.jpg", "Pic details"));
        adapterDataPojos.add(new AdapterDataPojo("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/1200px-Good_Food_Display_-_NCI_Visuals_Online.jpg", "Pic details"));
        adapterDataPojos.add(new AdapterDataPojo("https://www.bodybuilding.com/fun/images/2014/in-defense-of-processed-foods-graphics-1.jpg", "Pic details"));
        adapterDataPojos.add(new AdapterDataPojo("https://www.bodybuilding.com/fun/images/2014/in-defense-of-processed-foods-graphics-1.jpg", "Pic details"));
        adapterDataPojos.add(new AdapterDataPojo("https://www.bodybuilding.com/fun/images/2014/in-defense-of-processed-foods-graphics-1.jpg", "Pic details"));
        adapterDataPojos.add(new AdapterDataPojo("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/1200px-Good_Food_Display_-_NCI_Visuals_Online.jpg", "Pic details"));

        /*
        make a object for the PicturePreview and call the start method.
         */
        new PicturePreview(MainActivity.this, adapterDataPojos, Color.parseColor("#467890"),"sagar").start();

    }
}
