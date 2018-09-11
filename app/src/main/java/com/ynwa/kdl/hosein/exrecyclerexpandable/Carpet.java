package com.ynwa.kdl.hosein.exrecyclerexpandable;

public class Carpet {

    private String title;

    private String subTitle;
    private String imageUrl;

    private boolean isExpandable;



    public Carpet(String title, String subTitle, String imageUrl, boolean expanded) {
        this.title = title;
        this.subTitle = subTitle;
        this.imageUrl = imageUrl;
        this.isExpandable = expanded;
    }

    public Carpet() {
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        this.isExpandable = expandable;
    }
}
