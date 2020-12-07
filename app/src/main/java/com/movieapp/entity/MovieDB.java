package com.movieapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.movieapp.model.Movie;

@Entity(tableName = "movie_table")
public class MovieDB {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    @ColumnInfo(name = "poster_path")
    private String imagePath;
    @ColumnInfo(name = "release_date")
    private String releaseDate;

    private String overview;
    @ColumnInfo(name = "vote_average")
    private String voteAverage;

    public MovieDB(String title, String imagePath, String releaseDate, String overview, String voteAverage) {
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

    public static MovieDB mapMovieToMovieDB(Movie movie) {
        MovieDB movieDB = new MovieDB(
                movie.getTitle(),
                movie.getImagePath(),
                movie.getReleaseDate(),
                movie.getOverview(),
                movie.getVoteAverage());
        movieDB.setId(movie.getId());
        return movieDB;
    }
}
