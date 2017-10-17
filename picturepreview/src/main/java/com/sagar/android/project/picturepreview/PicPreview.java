package com.sagar.android.project.picturepreview;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.sagar.android.project.picturepreview.util.Rotate3dAnimation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PicPreview extends AppCompatActivity {

    AppCompatImageView appCompatImageView;
    ConstraintLayout constraintLayoutContainer;
    Rotate3dAnimation rotation;
    StartNextRotate startNext;

    public static final String PICTURE_URL_TO_SHOW = "PICTURE_URL_TO_SHOW";
    public static final String TEXT_TO_SHOW = "TEXT_TO_SHOW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_preview);

        appCompatImageView = (AppCompatImageView) findViewById(R.id.appcompatimageview_full_view);
        constraintLayoutContainer = (ConstraintLayout) findViewById(R.id.full_pic_preview_container);

        constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });

        supportPostponeEnterTransition();

        try {
            if (getIntent() == null)
                finishActivity();
            if (getIntent().getStringExtra(PICTURE_URL_TO_SHOW) == null)
                finishActivity();
            if (getIntent().getStringExtra(PICTURE_URL_TO_SHOW).length() == 0)
                finishActivity();
            if (getIntent().getStringExtra(TEXT_TO_SHOW) == null)
                finishActivity();
            if (getIntent().getStringExtra(TEXT_TO_SHOW).length() == 0)
                finishActivity();
        } catch (Exception e) {
            finishActivity();
        }

        Picasso.with(PicPreview.this).load(getIntent().getStringExtra(PICTURE_URL_TO_SHOW)).fit().centerCrop().into(appCompatImageView, new Callback() {
            @Override
            public void onSuccess() {
                supportStartPostponedEnterTransition();
                changeBackgroundToDark();
                proceedToRotate();
            }

            @Override
            public void onError() {
                supportStartPostponedEnterTransition();
                Toast.makeText(PicPreview.this, "Failed to load image.", Toast.LENGTH_SHORT).show();
                finishAfterTransition();
            }
        });
    }

    private void finishActivity() {
        Toast.makeText(this, "Invalid picture url", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void changeBackgroundToDark() {
        int colorFrom = ResourcesCompat.getColor(PicPreview.this.getResources(), android.R.color.transparent, null);
        int colorTo = ResourcesCompat.getColor(PicPreview.this.getResources(), R.color.pic_bg, null);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(250);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                constraintLayoutContainer.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    private void proceedToRotate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rotatePictureToHide();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void rotatePictureToHide() {
        final float centerX = appCompatImageView.getWidth() / 2.0f;
        final float centerY = appCompatImageView.getHeight() / 2.0f;
        Log.d("dsfgb", "centerX=" + centerX + ", centerY=" + centerY);
        rotation = new Rotate3dAnimation(0f, 90f, centerX, centerY, 0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        //rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setInterpolator(new LinearInterpolator());
        startNext = new StartNextRotate();
        rotation.setAnimationListener(startNext);
        appCompatImageView.startAnimation(rotation);
    }

    private class StartNextRotate implements Animation.AnimationListener {

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }

    }
}
