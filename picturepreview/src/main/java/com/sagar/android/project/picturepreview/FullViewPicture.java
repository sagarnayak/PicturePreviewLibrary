package com.sagar.android.project.picturepreview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.sagar.android.project.picturepreview.util.Rotate3dAnimation;

public class FullViewPicture extends AppCompatActivity {

    private AppCompatImageView appCompatImageViewFullView;
    private AppCompatImageView appCompatImageViewFullViewTwo;
    Rotate3dAnimation rotation;
    Rotate3dAnimation rotationtwo;
    StartNextRotate startNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view_picture);

        appCompatImageViewFullView = (AppCompatImageView) findViewById(R.id.appcompatimageview_full_view);
        appCompatImageViewFullViewTwo = (AppCompatImageView) findViewById(R.id.appcompatimageview_full_view_two);

        appCompatImageViewFullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final float centerX = appCompatImageViewFullView.getWidth() / 2.0f;
                final float centerY = appCompatImageViewFullView.getHeight() / 2.0f;
                Log.d("dsfgb", "centerX=" + centerX + ", centerY=" + centerY);
                rotation = new Rotate3dAnimation(0f, 90f, centerX, centerY, 0f, true);
                rotation.setDuration(1000);
                rotation.setFillAfter(true);
                //rotation.setInterpolator(new AccelerateInterpolator());
                rotation.setInterpolator(new LinearInterpolator());
                startNext = new StartNextRotate();
                rotation.setAnimationListener(startNext);
                appCompatImageViewFullView.startAnimation(rotation);
            }
        });
    }

    private class StartNextRotate implements Animation.AnimationListener {

        public void onAnimationEnd(Animation animation) {
            // TODO Auto-generated method stub
            Log.d("dfbdg", "onAnimationEnd......");
            appCompatImageViewFullViewTwo.setVisibility(View.VISIBLE);
            appCompatImageViewFullView.setVisibility(View.INVISIBLE);
            final float centerX = appCompatImageViewFullViewTwo.getWidth() / 2.0f;
            final float centerY = appCompatImageViewFullViewTwo.getHeight() / 2.0f;
            Log.d("dsfgb", "centerX=" + centerX + ", centerY=" + centerY);
            rotationtwo = new Rotate3dAnimation(90f, 180f, centerX, centerY, 0f, true);
            rotationtwo.setDuration(1000);
            rotationtwo.setFillAfter(true);
            //rotation.setInterpolator(new AccelerateInterpolator());
            rotationtwo.setInterpolator(new LinearInterpolator());
            appCompatImageViewFullViewTwo.startAnimation(rotationtwo);
        }

        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub
            Log.d("dfbdg", "onAnimationEnd......");

        }

        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
            Log.d("dfbdg", "onAnimationEnd......");

        }

    }

}
