package com.movieapp.util;

import com.movieapp.entity.MovieEntity;
import com.movieapp.model.Movie;

public class EntityUtils {

    public static MovieEntity mapMovieToMovieDB(Movie movie) {
        MovieEntity movieEntity = new MovieEntity(
                movie.getTitle(),
                movie.getImagePath(),
                movie.getReleaseDate(),
                movie.getOverview(),
                movie.getVoteAverage());
        movieEntity.setId(movie.getId());
        return movieEntity;
    }

    public static Movie mapMovieDBToMovie(MovieEntity movieEntity) {
        return new Movie(
                movieEntity.getTitle(),
                movieEntity.getImagePath(),
                movieEntity.getReleaseDate(),
                movieEntity.getOverview(),
                movieEntity.getVoteAverage());
    }
}
