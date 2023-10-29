package com.example.midterm;

public class Athletics {
    private String imgUrl;
    private String Title;
    private String Number;

    public Athletics(){

    }

    //Constructor
    public Athletics(String imgUrl, String title,String number) {
        this.imgUrl = imgUrl;
        Title = title;
        Number = number;
    }

    // Getting and Setting
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number =number;
    }
}
