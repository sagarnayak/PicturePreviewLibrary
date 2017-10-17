package com.sagar.android.project.picturepreview;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sagar.android.project.picturepreview.Adapter.GridViewAdapter;
import com.sagar.android.project.picturepreview.pojo.AdapterDataPojo;
import com.sagar.android.project.picturepreview.util.GridSpacingItemDecoration;

import java.util.ArrayList;

public class PreviewGrid extends AppCompatActivity {

    private RecyclerView recyclerViewPictures;

    public static final String ADAPTER_DATA = "ADAPTER_DATA";
    public static final String ACTION_BAR_COLOR = "ACTION_BAR_COLOR";
    public static final String TITLE = "TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_grid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            setTitle(String.valueOf("Pictures"));

        try {
            if (getIntent().getStringExtra(TITLE).length() != 0)
                setTitle(getIntent().getStringExtra(TITLE));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getIntent().getIntExtra(ACTION_BAR_COLOR, ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        recyclerViewPictures = (RecyclerView) findViewById(R.id.recyclerview_picture_preview);

        recyclerViewPictures.setLayoutManager(new GridLayoutManager(PreviewGrid.this, 2));

        int spanCount = 2;
        int spacing = 30;
        boolean includeEdge = true;
        recyclerViewPictures.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        TypeToken<ArrayList<AdapterDataPojo>> token = new TypeToken<ArrayList<AdapterDataPojo>>() {
        };
        recyclerViewPictures.setAdapter(new GridViewAdapter((ArrayList<AdapterDataPojo>) new Gson().fromJson(getIntent().getStringExtra(ADAPTER_DATA), token.getType()), PreviewGrid.this));
    }
}
