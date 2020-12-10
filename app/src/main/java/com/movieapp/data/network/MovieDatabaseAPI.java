package com.movieapp.data.network;

import com.movieapp.data.model.TopRatedMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDatabaseAPI {

    @GET("movie/top_rated")
    Call<TopRatedMoviesResponse> getTopRatedMovieList(@Query("page") int page);
}
