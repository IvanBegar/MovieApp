package com.movieapp;

import android.app.Application;

import com.movieapp.util.MovieDatabase;

public class MovieApplication extends Application {

    private static MovieDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = MovieDatabase.getInstance(getApplicationContext());
    }

    public static MovieDatabase getDatabase() {
        return database;
    }
}
