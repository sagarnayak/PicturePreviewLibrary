package com.sagar.android.project.picturepreview.controller;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sagar.android.project.picturepreview.PreviewGrid;
import com.sagar.android.project.picturepreview.pojo.AdapterDataPojo;

import java.util.ArrayList;

/**
 * Created by SAGAR KUMAR NAYAK on 10/17/2017.
 * this is the picture preview controller class.
 * this have public methods that will be used to initialize different variables in the class.
 * the user can provide adapter data, title for the picture preview activity, the color for the
 * actionbar of the picture preview activity.
 * To use the library follow the steps below-
 * 1. create a object of PicturePreview.
 * 2. initialise the data (compulsory), title (optional), actionbar color(optional).
 * 3. call the start().
 * this activity shows the pictures in a gridview. on clicking the picture the full view of the
 * picture will be shown to the user. also the details of the picture will be shown with a y axis
 * flip animation.
 */
public class PicturePreview {
    /*
    context to start the activity.
     */
    private Context context;
    /*
    data set for the picture grid preview.
     */
    private ArrayList<AdapterDataPojo> adapterDataPojoArrayList;
    /*
    actionbar color
     */
    private int actionBarColor;
    /*
    title for the picture preview activity
     */
    private String title;

    /**
     * constructor for the initializing the context and data set to the activity
     *
     * @param context                  context
     * @param adapterDataPojoArrayList data set containing the picture url data
     */
    public PicturePreview(Context context, ArrayList<AdapterDataPojo> adapterDataPojoArrayList) {
        this.context = context;
        this.adapterDataPojoArrayList = adapterDataPojoArrayList;
    }

    /**
     * constructor for the initializing the context and data set to the activity and also the
     * actionbar color
     *
     * @param context                  context
     * @param adapterDataPojoArrayList data set containing the picture url data
     * @param actionBarColor           actionbar color
     */
    public PicturePreview(Context context, ArrayList<AdapterDataPojo> adapterDataPojoArrayList, int actionBarColor) {
        this.context = context;
        this.adapterDataPojoArrayList = adapterDataPojoArrayList;
        this.actionBarColor = actionBarColor;
    }

    /**
     * constructor for the initializing the context and data set to the activity and also the
     * title for the activity
     *
     * @param context                  context
     * @param adapterDataPojoArrayList data set containing the picture url data
     * @param title                    title for picture preview activity
     */
    public PicturePreview(Context context, ArrayList<AdapterDataPojo> adapterDataPojoArrayList, String title) {
        this.context = context;
        this.adapterDataPojoArrayList = adapterDataPojoArrayList;
        this.title = title;
    }

    /**
     * constructor for the initializing the context and data set to the activity and also the
     * title for the activity and actionbar color.
     *
     * @param context                  context
     * @param adapterDataPojoArrayList data set containing the picture url data
     * @param actionBarColor           actionbar color
     * @param title                    title for picture preview activity
     */
    public PicturePreview(Context context, ArrayList<AdapterDataPojo> adapterDataPojoArrayList, int actionBarColor, String title) {
        this.context = context;
        this.adapterDataPojoArrayList = adapterDataPojoArrayList;
        this.actionBarColor = actionBarColor;
        this.title = title;
    }

    /**
     * this is the method that will start the actual library for come to action. after initializing
     * all the params call this method to show the pictures on the screen.
     */
    public void start() {
        /*
        check if data set is blank. if yes show the error message to user.
         */
        if (adapterDataPojoArrayList.size() == 0) {
            Toast.makeText(context, "No data to display.", Toast.LENGTH_SHORT).show();
            return;
        }
        /*
        type token is for casting the data to string by GSON
         */
        TypeToken<ArrayList<AdapterDataPojo>> token = new TypeToken<ArrayList<AdapterDataPojo>>() {
        };
        /*
        put the data to the intent extra.
         */
        Intent intent = new Intent(context, PreviewGrid.class)
                .putExtra(PreviewGrid.ADAPTER_DATA,
                        new Gson().toJson(adapterDataPojoArrayList, token.getType()));
        /*
        check if actionbar color is provided by the user. if yes add in the intent extra param.
         */
        try {
            if (actionBarColor != 0) {
                intent.putExtra(PreviewGrid.ACTION_BAR_COLOR, actionBarColor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        check if the title data is provided by the user. if yes add it to the intent extra.
         */
        try {
            if (title != null) {
                if (title.length() > 0)
                    intent.putExtra(PreviewGrid.TITLE, title);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        start the activity, with the intent extra data.
         */
        context.startActivity(intent);

    }
}
