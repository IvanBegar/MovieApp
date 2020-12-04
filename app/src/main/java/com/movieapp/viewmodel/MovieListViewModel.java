package com.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movieapp.model.Movie;
import com.movieapp.model.TopRatedMoviesResponse;
import com.movieapp.network.MovieDatabaseAPI;
import com.movieapp.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movieList;

    public MovieListViewModel() {
        movieList = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovieListObserver() {
        return movieList;
    }

    public void makeApiCall(int page) {
        MovieDatabaseAPI movieDatabaseApi = RetroInstance.getRetroClient().create(MovieDatabaseAPI.class);
        Call<TopRatedMoviesResponse> call = movieDatabaseApi.getTopRatedMovieList(page);
        call.enqueue(new Callback<TopRatedMoviesResponse>() {
            @Override
            public void onResponse(Call<TopRatedMoviesResponse> call, Response<TopRatedMoviesResponse> response) {
                movieList.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TopRatedMoviesResponse> call, Throwable t) {
                movieList.postValue(null);
            }
        });
    }
}
