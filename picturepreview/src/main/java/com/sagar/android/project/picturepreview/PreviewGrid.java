package com.sagar.android.project.picturepreview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sagar.android.project.picturepreview.Adapter.GridViewAdapter;
import com.sagar.android.project.picturepreview.pojo.AdapterDataPojo;

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
        adapterDataPojos.add(new AdapterDataPojo("https://www.google.co.in/url?sa=i&rct=j&q=&esrc=s&source=imgres&cd=&cad=rja&uact=8&ved=0ahUKEwjT_PSIgvbWAhUHsI8KHdfGCtIQjRwIBw&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fabstract%2F&psig=AOvVaw3nEUf42eQCygCcumDeM66n&ust=1508273305138045"));
        adapterDataPojos.add(new AdapterDataPojo("https://www.google.co.in/imgres?imgurl=https%3A%2F%2Fcdn.pixabay.com%2Fphoto%2F2016%2F06%2F28%2F05%2F39%2Fabstract-1484017_960_720.jpg&imgrefurl=https%3A%2F%2Fpixabay.com%2Fen%2Fabstract-border-colorful-strips-1484017%2F&docid=OzXxD0M4iucC5M&tbnid=RMrXvAEabRL-fM%3A&vet=10ahUKEwia74CCgvbWAhUMpo8KHSEPDmcQMwj2ASgGMAY..i&w=960&h=640&bih=700&biw=1366&q=abstract&ved=0ahUKEwia74CCgvbWAhUMpo8KHSEPDmcQMwj2ASgGMAY&iact=mrc&uact=8"));
        adapterDataPojos.add(new AdapterDataPojo("https://www.google.co.in/imgres?imgurl=https%3A%2F%2Fd2v9y0dukr6mq2.cloudfront.net%2Fvideo%2Fthumbnail%2Fabstract-rainbow-bubbles_wjwvrlweh__F0000.png&imgrefurl=https%3A%2F%2Fwww.videoblocks.com%2Fvideos%2Fmotion-backgrounds%2Fabstract&docid=9e2DcjcQ3H6c8M&tbnid=-wph0cBoGxpAqM%3A&vet=10ahUKEwia74CCgvbWAhUMpo8KHSEPDmcQMwj0ASgEMAQ..i&w=1920&h=1080&bih=700&biw=1366&q=abstract&ved=0ahUKEwia74CCgvbWAhUMpo8KHSEPDmcQMwj0ASgEMAQ&iact=mrc&uact=8"));

        recyclerViewPictures.setAdapter(new GridViewAdapter(adapterDataPojos, PreviewGrid.this));
    }
}
