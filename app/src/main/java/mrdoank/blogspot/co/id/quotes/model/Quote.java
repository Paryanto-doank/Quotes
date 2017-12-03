package mrdoank.blogspot.co.id.quotes.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 02/12/17.
 */

public class Quote implements Parcelable {

    private String ID;
    private String title;
    private String content;
    private String link;

    public Quote() {

    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "ID='" + ID + '\'' +
                ", title='" + title + '\'' +
                ", content=" + content +
                ", link=" + link;
    }


    public Quote(Parcel in) {
        this.ID = in.readString();
        this.title = in.readString();
        this.content = in.readString();
        this.link = in.readString();
    }

    public static final Creator<Quote> CREATOR = new Creator<Quote>() {
        @Override
        public Quote createFromParcel(Parcel in) {
            return new Quote(in);
        }

        @Override
        public Quote[] newArray(int size) {
            return new Quote[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.link);
    }
}
