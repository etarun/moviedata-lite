package com.movies.moviedata.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Movie implements Parcelable {

    @SerializedName("id")
    @Expose
    private int movieId;
    @SerializedName("title")
    @Expose
    private String name;
    @SerializedName("poster_path")
    @Expose
    private String imageURL;
    @SerializedName("imdb_id")
    @Expose
    private String status;
    @SerializedName("release_date")
    @Expose
    private String description;
    @SerializedName("delivery_fee")
    @Expose
    private String deliveryFee;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("vote_average")
    @Expose
    private String rating;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres;

    protected Movie(Parcel in) {
        movieId = in.readInt();
        name = in.readString();
        imageURL = in.readString();
        status = in.readString();
        description = in.readString();
        deliveryFee = in.readString();
        overview = in.readString();
        rating = in.readString();
        genres = in.readArrayList(Genre.class.getClassLoader());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(movieId);
        parcel.writeString(name);
        parcel.writeString(imageURL);
        parcel.writeString(status);
        parcel.writeString(description);
        parcel.writeString(deliveryFee);
        parcel.writeString(overview);
        parcel.writeString(rating);
        parcel.writeList(genres);
    }
}
