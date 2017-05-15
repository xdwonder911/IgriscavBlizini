package com.example.xdwonder.igriscavblizini;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by xdwonder on 4.3.2017.
 */

public class Playground {
    private String idPlayground;
    private String address;
    private String city;
    private ArrayList<String> imgPathList;
    private ArrayList<Tag> tagList;
    private long date_added;
    private String idUser;
    private float rating;
    private double x,y;

    public float getRating() {
        return rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Playground(ArrayList<String> imgPathList, ArrayList<Tag> tagList, long date_added, String idUser, double x, double y) {
        this.idPlayground = UUID.randomUUID().toString().replaceAll("-", "");
        this.imgPathList = imgPathList;
        this.tagList = tagList;
        this.date_added = date_added;
        this.idUser = idUser;
        this.x = x;
        this.y = y;
    }
    public Playground(double x,double y)
    {
        this.idPlayground = UUID.randomUUID().toString().replaceAll("-", "");
        this.imgPathList = imgPathList;
        this.tagList = tagList;
        this.date_added = date_added;
        this.idUser = idUser;
        this.x = x;
        this.y = y;
    }

    public String getIdPlayground() {
        return idPlayground;
    }

    public void setIdPlayground(String idPlayground) {
        this.idPlayground = idPlayground;
    }

    public ArrayList<String> getImgPathList() {
        return imgPathList;
    }

    public void setImgPathList(ArrayList<String> imgPathList) {
        this.imgPathList = imgPathList;
    }

    public void addImgPath(String path){
        this.imgPathList.add(imgPathList.size(),path);
    }
    public ArrayList<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<Tag> tagList) {
        this.tagList = tagList;
    }

    public long getDate_added() {
        return date_added;
    }

    public void setDate_added(long date_added) {
        this.date_added = date_added;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Playground{" +
                "idPlayground='" + idPlayground + '\'' +
                ", imgPathList=" + imgPathList +
                ", tagList=" + tagList +
                ", date_added=" + date_added +
                ", idUser='" + idUser + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
