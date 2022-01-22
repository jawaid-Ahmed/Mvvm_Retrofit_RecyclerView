package com.example.viewmodelretrofitrecyclerview.model;

public class MovieModel {

    private String title;
    private String thumbnailUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getthumbnailUrl() {
        return thumbnailUrl;
    }

    public void setthumbnailUrl(String image) {
        this.thumbnailUrl = image;
    }

    public MovieModel(String title, String image) {
        this.title = title;
        this.thumbnailUrl = image;
    }
}
