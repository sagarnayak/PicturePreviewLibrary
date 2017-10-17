package com.sagar.android.project.picturepreview.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sagar.android.project.picturepreview.PicPreview;
import com.sagar.android.project.picturepreview.R;
import com.sagar.android.project.picturepreview.pojo.AdapterDataPojo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SAGAR KUMAR NAYAK on 10/17/2017.
 * this is the adapter for binding the data to the gridview in the picture screen.
 */
public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolderGridView> {
    private ArrayList<AdapterDataPojo> adapterDataPojoArrayList;
    private Context context;

    public GridViewAdapter(ArrayList<AdapterDataPojo> adapterDataPojoArrayList, Context context) {
        this.adapterDataPojoArrayList = adapterDataPojoArrayList;
        this.context = context;
    }

    @Override
    public ViewHolderGridView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderGridView(LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview_picture_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolderGridView holder, int position) {
        /*
        load the picture to imageview
         */
        Picasso.with(context)
                .load(adapterDataPojoArrayList.get(position).getUrl())
                .fit().centerCrop()
                .error(R.drawable.picture_place_holder)
                .into(holder.appCompatImageViewPicture,
                        new Callback() {
                            @Override
                            public void onSuccess() {
                                //success
                            }

                            @Override
                            public void onError() {
                                //failed
                            }
                        });
    }

    @Override
    public int getItemCount() {
        return adapterDataPojoArrayList.size();
    }

    class ViewHolderGridView extends RecyclerView.ViewHolder {

        private AppCompatImageView appCompatImageViewPicture;

        ViewHolderGridView(View itemView) {
            super(itemView);

            appCompatImageViewPicture = itemView.findViewById(R.id.appcompatimageview_preview);

            /*
            on click listener for the imageview.
            after clicking the imageview the full view for the image will open in the PicPreview
            activity.
            also the image url and text to display at the back are sent as extra params to be used
            at the PicPreview activity.
             */
            appCompatImageViewPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                                    appCompatImageViewPicture,
                                    ViewCompat.getTransitionName(appCompatImageViewPicture));
                    Intent intent = new Intent(context, PicPreview.class)
                            .putExtra(PicPreview.PICTURE_URL_TO_SHOW,
                                    adapterDataPojoArrayList.get(getAdapterPosition()).getUrl())
                            .putExtra(PicPreview.TEXT_TO_SHOW,
                                    adapterDataPojoArrayList.get(getAdapterPosition()).getDetails());
                    context.startActivity(intent, options.toBundle());
                }
            });
        }
    }
}
