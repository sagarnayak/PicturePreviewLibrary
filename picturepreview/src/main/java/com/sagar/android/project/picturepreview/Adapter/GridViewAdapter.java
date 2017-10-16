package com.sagar.android.project.picturepreview.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sagar.android.project.picturepreview.R;
import com.sagar.android.project.picturepreview.pojo.AdapterDataPojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SAGAR on 10/17/2017.
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
    public void onBindViewHolder(ViewHolderGridView holder, int position) {
        Picasso.with(context).load(adapterDataPojoArrayList.get(position).getUrl()).into(holder.appCompatImageViewPicture);
    }

    @Override
    public int getItemCount() {
        return adapterDataPojoArrayList.size();
    }

    class ViewHolderGridView extends RecyclerView.ViewHolder {

        private AppCompatImageView appCompatImageViewPicture;

        public ViewHolderGridView(View itemView) {
            super(itemView);

            appCompatImageViewPicture = itemView.findViewById(R.id.appcompat_imageview_picture_preview);
        }
    }
}
