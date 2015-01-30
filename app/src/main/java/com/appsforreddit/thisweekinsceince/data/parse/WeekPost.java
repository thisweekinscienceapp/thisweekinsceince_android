package com.appsforreddit.thisweekinsceince.data.parse;

import com.parse.ParseObject;

/**
 * Created by Michael on 1/29/2015.
 */
public class WeekPost {
    public String getImageUrl() {
        return imageUrl;
    }

    public WeekPost(){

    }

    public WeekPost(ParseObject parseObject){
        description = parseObject.getString("description");
        imageUrl = parseObject.getString("imageUrl");
        infoUrl = parseObject.getString("infoUrl");
        infoUrl2 = parseObject.getString("infoUrl2");
        infoUrl3 = parseObject.getString("infoUrl3");
        infoUrl4 = parseObject.getString("infoUrl4");
        infoUrl5 = parseObject.getString("infoUrl5");
        redditUrl = parseObject.getString("redditUrl");
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRedditUrl() {
        return redditUrl;
    }

    public void setRedditUrl(String redditUrl) {
        this.redditUrl = redditUrl;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    public String getInfoUrl2() {
        return infoUrl2;
    }

    public void setInfoUrl2(String infoUrl2) {
        this.infoUrl2 = infoUrl2;
    }

    public String getInfoUrl3() {
        return infoUrl3;
    }

    public void setInfoUrl3(String infoUrl3) {
        this.infoUrl3 = infoUrl3;
    }

    public String getInfoUrl4() {
        return infoUrl4;
    }

    public void setInfoUrl4(String infoUrl4) {
        this.infoUrl4 = infoUrl4;
    }

    public String getInfoUrl5() {
        return infoUrl5;
    }

    public void setInfoUrl5(String infoUrl5) {
        this.infoUrl5 = infoUrl5;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String imageUrl, redditUrl, infoUrl, infoUrl2, infoUrl3, infoUrl4, infoUrl5, description;
}
