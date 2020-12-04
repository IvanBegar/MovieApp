package com.movieapp.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopRatedMoviesResponse {
    @SerializedName("results")
    @Expose
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    @NonNull
    @Override
    public String toString() {
        return results.toString();
    }
}
