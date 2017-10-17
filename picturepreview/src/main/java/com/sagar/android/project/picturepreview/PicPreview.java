package com.sagar.android.project.picturepreview;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sagar.android.project.picturepreview.util.Rotate3dAnimation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/*
created by SAGAR KUMAR NAYAK on 17 OCT 2017
this is the activity to show the picture in full size to the user.
also this will automatically rotate on y axis and show the picture details.
 */
public class PicPreview extends AppCompatActivity {

    /*
    appcompat image view to hold the picture
     */
    AppCompatImageView appCompatImageView;
    /*
    layout for the layout container
     */
    ConstraintLayout constraintLayoutContainer;
    /*
    detail container for the picture that contain the picture details
     */
    ConstraintLayout constraintLayoutDetailContainer;
    /*
    textview for picture detail
     */
    TextView textViewDetails;
    /*
    button in the picture details view
     */
    Button buttonDetails;
    /*
    cross button in the picture details view
     */
    View viewCross;

    /*
    the rotate 3d animation object for rotation animations
     */
    Rotate3dAnimation rotationToHideImage;
    Rotate3dAnimation rotationToShowDetails;
    Rotate3dAnimation rotationToHideDetails;
    Rotate3dAnimation rotationToShowImage;

    /*
    the center points for views
     */
    float centerX;
    float centerY;

    /*
    the constants for getting the data through intent
     */
    public static final String PICTURE_URL_TO_SHOW = "PICTURE_URL_TO_SHOW";
    public static final String TEXT_TO_SHOW = "TEXT_TO_SHOW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_preview);

        ////////////////////////////////////////////////////////////////////////////////////////////
        //view holder
        appCompatImageView = (AppCompatImageView) findViewById(R.id.appcompatimageview_full_view);
        constraintLayoutContainer = (ConstraintLayout) findViewById(R.id.full_pic_preview_container);
        constraintLayoutDetailContainer = (ConstraintLayout) findViewById(R.id.constraintlayout_text_container);
        textViewDetails = (TextView) findViewById(R.id.textview_picture_back_text);
        buttonDetails = (Button) findViewById(R.id.button_pic_detail);
        viewCross = findViewById(R.id.view_cross);
        ////////////////////////////////////////////////////////////////////////////////////////////

        /*
        on click of the container background the full picture preview will be closed.
         */
        constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateTextToHide();
            }
        });

        /*
        for doing the shared element transition after the picture is loaded to the imageview
         */
        supportPostponeEnterTransition();

        /*
        check if the data are present in the intent. if any essential data is not present then just
        finish the process with a error message.
         */
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

        /*
        load the picture to the imageview
         */
        Picasso.with(PicPreview.this)
                .load(getIntent().getStringExtra(PICTURE_URL_TO_SHOW))
                .fit().centerCrop()
                .into(appCompatImageView,
                        new Callback() {
                            @Override
                            public void onSuccess() {
                                supportStartPostponedEnterTransition();
                                changeBackgroundToDark();
                                proceedToRotate();
                            }

                            @Override
                            public void onError() {
                                supportStartPostponedEnterTransition();
                                Toast.makeText(PicPreview.this, "Failed to load image.",
                                        Toast.LENGTH_SHORT).show();
                                finishAfterTransition();
                            }
                        });

        /*
        set the details for the picture to the view
         */
        textViewDetails.setText(getIntent().getStringExtra(TEXT_TO_SHOW));

        /*
        on clicking the button details show a message to user
         */
        buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PicPreview.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        /*
        on clicking the cross button begin the closing of the picture full views
         */
        viewCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateTextToHide();
            }
        });
    }

    /**
     * method for finishing the activity with error message.
     */
    private void finishActivity() {
        Toast.makeText(this, "Invalid data provided.", Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * method to start the animation for darkening the full picture preview.
     */
    private void changeBackgroundToDark() {
        int colorFrom =
                ResourcesCompat
                        .getColor(PicPreview.this.getResources(),
                                android.R.color.transparent, null);
        int colorTo =
                ResourcesCompat.getColor(PicPreview.this.getResources(), R.color.pic_bg, null);
        ValueAnimator colorAnimation =
                ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(250);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                constraintLayoutContainer.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    /**
     * method to start the rotate animation at the activity start.
     */
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

    /**
     * method to start the animation for picture from 0 to 90 degree.
     */
    private void rotatePictureToHide() {
        centerX = appCompatImageView.getWidth() / 2.0f;
        centerY = appCompatImageView.getHeight() / 2.0f;
        rotationToHideImage = new Rotate3dAnimation(0f, 90f, centerX, centerY, 0f, true);
        rotationToHideImage.setDuration(500);
        rotationToHideImage.setFillAfter(true);
        rotationToHideImage.setInterpolator(new LinearInterpolator());
        rotationToHideImage.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /*
                on completion of this start the showing of the text picture details views.
                 */
                constraintLayoutDetailContainer.setVisibility(View.VISIBLE);
                appCompatImageView.setVisibility(View.GONE);
                rotateTextToShow();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        appCompatImageView.startAnimation(rotationToHideImage);
    }

    /**
     * method to start showing the picture details.
     */
    private void rotateTextToShow() {
        rotationToShowDetails = new Rotate3dAnimation(90f, 180f, centerX, centerY, 0f, true);
        rotationToShowDetails.setDuration(500);
        rotationToShowDetails.setFillAfter(true);
        rotationToShowDetails.setInterpolator(new LinearInterpolator());
        rotationToShowDetails.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        constraintLayoutDetailContainer.startAnimation(rotationToShowDetails);
    }

    /**
     * method to start hiding the picture details views. this will be done when the activity
     * closing sequence starts.
     */
    private void rotateTextToHide() {
        rotationToHideDetails = new Rotate3dAnimation(180f, 90f, centerX, centerY, 0f, true);
        rotationToHideDetails.setDuration(500);
        rotationToHideDetails.setFillAfter(true);
        rotationToHideDetails.setInterpolator(new LinearInterpolator());
        rotationToHideDetails.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /*
                after this animation is finished start the showing of the picture.
                 */
                appCompatImageView.setVisibility(View.VISIBLE);
                constraintLayoutDetailContainer.setVisibility(View.GONE);
                rotatePictureToShow();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        constraintLayoutDetailContainer.startAnimation(rotationToHideDetails);
    }

    /**
     * method to show the picture views.
     */
    private void rotatePictureToShow() {
        rotationToShowImage = new Rotate3dAnimation(90f, 0f, centerX, centerY, 0f, true);
        rotationToShowImage.setDuration(500);
        rotationToShowImage.setFillAfter(true);
        rotationToShowImage.setInterpolator(new LinearInterpolator());
        rotationToShowImage.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /*
                after this animation is finished start the closing of the activity.
                 */
                finishAfterTransition();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        appCompatImageView.startAnimation(rotationToShowImage);
    }

    @Override
    public void onBackPressed() {
        /*
        on back pressed start the process of activity closing.
         */
        rotateTextToHide();
    }
}
