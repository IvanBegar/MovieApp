package com.movieapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.movieapp.entity.MovieEntity;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieEntity movieEntity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(MovieEntity movieEntity);

    @Delete
    void delete(MovieEntity movieEntity);

    @Query("SELECT * FROM movie_table")
    List<MovieEntity> getAllMovies();

    @Query("DELETE FROM movie_table")
    void deleteAllMovies();
}
