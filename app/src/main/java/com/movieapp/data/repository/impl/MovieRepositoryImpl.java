package com.movieapp.data.repository.impl;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.movieapp.MovieApplication;
import com.movieapp.data.dao.MovieDao;
import com.movieapp.data.entity.MovieEntity;
import com.movieapp.data.model.Movie;
import com.movieapp.data.model.TopRatedMoviesResponse;
import com.movieapp.data.network.MovieDatabaseAPI;
import com.movieapp.data.network.NetworkAPI;
import com.movieapp.data.repository.MovieRepository;
import com.movieapp.util.EntityUtils;
import com.movieapp.util.MovieDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepositoryImpl implements MovieRepository {
    private MovieDao movieDao;
    private MovieDatabaseAPI movieDatabaseAPI;
    private MutableLiveData<List<Movie>> movieList;
    private List<Movie> responseList;
    private MovieDatabase database;
    private ExecutorService executorService;

    public MovieRepositoryImpl() {
        database = MovieApplication.getDatabase();
        movieDao = database.movieDao();
        movieList = new MutableLiveData<>();
        responseList = new ArrayList<>();
        movieDatabaseAPI = NetworkAPI.getRetroClient().create(MovieDatabaseAPI.class);
        executorService = Executors.newCachedThreadPool();
    }

    public void insert(MovieEntity movie) {
        executorService.execute(() -> movieDao.insert(movie));
    }

    public void update(MovieEntity movie) {
        executorService.execute(() -> movieDao.update(movie));
    }

    public void delete(MovieEntity movie) {
        executorService.execute(() -> movieDao.delete(movie));
    }

    public void deleteAllMovies() {
        executorService.execute(() -> movieDao.deleteAllMovies());
    }

    public LiveData<List<Movie>> getAllMovies(int page) {
        Call<TopRatedMoviesResponse> call = movieDatabaseAPI.getTopRatedMovieList(page);
        call.enqueue(new Callback<TopRatedMoviesResponse>() {
            @Override
            public void onResponse(Call<TopRatedMoviesResponse> call, Response<TopRatedMoviesResponse> response) {
                responseList = response.body().getResults();
                movieList.setValue(responseList);
                for (Movie m : responseList) {
                    MovieEntity movieEntity = EntityUtils.mapMovieToMovieDB(m);
                    insert(movieEntity);
                }
            }

            @Override
            public void onFailure(Call<TopRatedMoviesResponse> call, Throwable t) {
                movieList.postValue(null);
            }
        });
        return movieList;
    }
}
