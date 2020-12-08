package com.movieapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class MovieEntity {
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

    public MovieEntity(String title, String imagePath, String releaseDate, String overview, String voteAverage) {
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
}
