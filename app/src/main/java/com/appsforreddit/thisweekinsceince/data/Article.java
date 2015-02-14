package com.appsforreddit.thisweekinsceince.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Michael on 2/12/2015.
 */
public class Article implements Parcelable {

    private String imageUrl;
    private String shortDescription;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUrl);
        dest.writeString(this.shortDescription);
    }

    public Article() {
    }

    private Article(Parcel in) {
        this.imageUrl = in.readString();
        this.shortDescription = in.readString();
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
