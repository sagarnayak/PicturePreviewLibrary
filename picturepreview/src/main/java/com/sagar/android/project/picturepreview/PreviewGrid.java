package com.sagar.android.project.picturepreview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sagar.android.project.picturepreview.Adapter.GridViewAdapter;
import com.sagar.android.project.picturepreview.pojo.AdapterDataPojo;
import com.sagar.android.project.picturepreview.util.GridSpacingItemDecoration;

import java.util.ArrayList;

public class PreviewGrid extends AppCompatActivity {

    private RecyclerView recyclerViewPictures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_grid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            setTitle(String.valueOf("Pictures"));

        recyclerViewPictures = (RecyclerView) findViewById(R.id.recyclerview_picture_preview);

        recyclerViewPictures.setLayoutManager(new GridLayoutManager(PreviewGrid.this, 2));

        ArrayList<AdapterDataPojo> adapterDataPojos = new ArrayList<>();

        adapterDataPojos.add(new AdapterDataPojo("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/1200px-Good_Food_Display_-_NCI_Visuals_Online.jpg"));
        adapterDataPojos.add(new AdapterDataPojo("https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/15_surprisingly_healthy_foods_slideshow/493ss_thinkstock_rf_spaghetti_with_tomatoes_on_plate.jpg"));
        adapterDataPojos.add(new AdapterDataPojo("https://www.bodybuilding.com/fun/images/2014/in-defense-of-processed-foods-graphics-1.jpg"));

        int spanCount = 2;
        int spacing = 30;
        boolean includeEdge = true;
        recyclerViewPictures.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        recyclerViewPictures.setAdapter(new GridViewAdapter(adapterDataPojos, PreviewGrid.this));
    }
}
