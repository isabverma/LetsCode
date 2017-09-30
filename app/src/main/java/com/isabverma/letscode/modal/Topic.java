package com.isabverma.letscode.modal;

import java.util.ArrayList;

/**
 * Created by isabverma on 3/7/2017.
 */

public class Topic {
    private long topicNumber = 1l;
    private String topicName = "";
    private String topicDescription = "";

    public Topic() {
    }

    public Topic(long topicNumber, String topicName, String topicDescription) {
        this.topicNumber = topicNumber;
        this.topicName = topicName;
        this.topicDescription = topicDescription;
    }

    public long getTopicNumber() {
        return topicNumber;
    }

    public void setTopicNumber(long topicNumber) {
        this.topicNumber = topicNumber;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }
}
