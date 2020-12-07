package com.movieapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movieapp.model.Movie;
import com.movieapp.model.TopRatedMoviesResponse;
import com.movieapp.network.MovieDatabaseAPI;
import com.movieapp.network.NetworkAPI;
import com.movieapp.repository.MovieRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;
    private LiveData<List<Movie>> movieList;

    public MovieListViewModel(Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        movieList = movieRepository.getAllMovies(1);
    }

    public LiveData<List<Movie>> getMovieListObserver() {
        return movieList;
    }

    public void makeApiCallForTopRatedMovieList(int page) {
        movieList = movieRepository.getAllMovies(page);
    }

    public void deleteAllMovies() {
        movieRepository.deleteAllMovies();
    }
}
