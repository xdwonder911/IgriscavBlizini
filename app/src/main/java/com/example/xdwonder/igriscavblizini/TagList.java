package com.example.xdwonder.igriscavblizini;

import java.util.ArrayList;

/**
 * Created by xdwonder on 4.3.2017.
 */

public class TagList {
    private ArrayList<Tag> tagList;

    public TagList() {
        tagList=new ArrayList<>();
        tagList.add(new Tag("basketball","0"));
        tagList.add(new Tag("football","1"));
        tagList.add(new Tag("playground","2"));
        tagList.add(new Tag("volleyball","3"));
        tagList.add(new Tag("tennis","4"));
        tagList.add(new Tag("golf","5"));
    }

    public ArrayList<Tag> getTagList() {
        return tagList;
    }

    public Tag getPrvi(){
        return tagList.get(0);
    }

    public void setTagList(ArrayList<Tag> tagList) {
        this.tagList = tagList;
    }
}
