package com.isabverma.letscode.modal;

import java.util.ArrayList;

/**
 * Created by isabverma on 3/7/2017.
 */

public class Flavour {
    private String flavourName = "";
    private ArrayList<Topic> topicList = new ArrayList<Topic>();

    public Flavour() {
    }

    public Flavour(String flavourName, ArrayList<Topic> topicList) {
        this.flavourName = flavourName;
        this.topicList = topicList;
    }

    public String getFlavourName() {
        return flavourName;
    }

    public void setFlavourName(String flavourName) {
        this.flavourName = flavourName;
    }

    public ArrayList<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(ArrayList<Topic> topicList) {
        this.topicList = topicList;
    }
}
