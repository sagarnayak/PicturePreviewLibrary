package com.sagar.android.project.picturepreview.pojo;

/**
 * Created by SAGAR KUMAR NAYAK on 10/17/2017.
 * pojo class to hold the data that will have the url and the picture details that will be sent by
 * the user.
 */
public class AdapterDataPojo {
    private String url;
    private String details;

    public AdapterDataPojo(String url, String details) {
        this.url = url;
        this.details = details;
    }

    @SuppressWarnings("unused")
    public AdapterDataPojo() {
    }

    public String getUrl() {
        return url;
    }

    @SuppressWarnings("unused")
    public void setUrl(String url) {
        this.url = url;
    }

    public String getDetails() {
        return details;
    }

    @SuppressWarnings("unused")
    public void setDetails(String details) {
        this.details = details;
    }
}
