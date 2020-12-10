package com.movieapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.movieapp.data.model.Movie;
import com.movieapp.data.repository.MovieRepository;
import com.movieapp.data.repository.impl.MovieRepositoryImpl;

import java.util.List;

public class MovieListViewModel extends ViewModel {
    private MovieRepository movieRepository;
    private LiveData<List<Movie>> movieList;

    public MovieListViewModel() {
        movieRepository = new MovieRepositoryImpl();
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
