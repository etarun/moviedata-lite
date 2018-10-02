package com.movies.moviedata.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tarun on 10/2/18.
 */

public class Genre implements Parcelable{
    @SerializedName("id")
    @Expose
    private int genreId;
    @SerializedName("name")
    @Expose
    private String name;

    protected Genre(Parcel in) {
        genreId = in.readInt();
        name = in.readString();
    }

    public static final Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(genreId);
        parcel.writeString(name);
    }
}
