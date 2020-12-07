package com.movieapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.movieapp.entity.MovieDB;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("poster_path")
    @Expose
    private String imagePath;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("vote_average")
    @Expose
    private String voteAverage;

    public Movie() {

    }

    public Movie(String title, String imagePath, String releaseDate, String overview, String voteAverage) {
        this.title = title;
        this.imagePath = imagePath;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.voteAverage = voteAverage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public static Movie mapMovieDBToMovie(MovieDB movieDB) {
        return new Movie(
                movieDB.getTitle(),
                movieDB.getImagePath(),
                movieDB.getReleaseDate(),
                movieDB.getOverview(),
                movieDB.getVoteAverage());
    }
}
