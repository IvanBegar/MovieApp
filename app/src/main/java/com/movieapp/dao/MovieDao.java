package com.movieapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.movieapp.entity.MovieDB;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieDB movieDB);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(MovieDB movieDB);

    @Delete
    void delete(MovieDB movieDB);

    @Query("SELECT * FROM movie_table ORDER BY vote_average DESC")
    List<MovieDB> getAllMovies();

    @Query("DELETE FROM movie_table")
    void deleteAllMovies();
}
