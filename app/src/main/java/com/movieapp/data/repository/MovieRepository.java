package com.movieapp.data.repository;

import androidx.lifecycle.LiveData;

import com.movieapp.data.entity.MovieEntity;
import com.movieapp.data.model.Movie;

import java.util.List;

public interface MovieRepository {

    public void insert(MovieEntity movie);

    public void update(MovieEntity movie);

    public void delete(MovieEntity movie);

    public void deleteAllMovies();

    public LiveData<List<Movie>> getAllMovies(int page);
}
