package com.movieapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.movieapp.dao.MovieDao;
import com.movieapp.entity.MovieDB;
import com.movieapp.model.Movie;
import com.movieapp.model.TopRatedMoviesResponse;
import com.movieapp.network.MovieDatabaseAPI;
import com.movieapp.network.NetworkAPI;
import com.movieapp.util.MovieDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private MovieDao movieDao;
    private MovieDatabaseAPI movieDatabaseAPI;
    private MutableLiveData<List<Movie>> movieList;
    private List<Movie> responseList;

    public MovieRepository(Application application) {
        MovieDatabase database = MovieDatabase.getInstance(application);
        movieDao = database.movieDao();
        movieList = new MutableLiveData<>();
        responseList = new ArrayList<>();
        movieDatabaseAPI = NetworkAPI.getRetroClient().create(MovieDatabaseAPI.class);
    }

    public void insert(MovieDB movie) {
        new InsertMovieAsyncTask(movieDao).execute(movie);
    }

    public void update(MovieDB movie) {
        new UpdateMovieAsyncTask(movieDao).execute(movie);
    }

    public void delete(MovieDB movie) {
        new DeleteMovieAsyncTask(movieDao).execute(movie);
    }

    public void deleteAllMovies() {
        new DeleteAllMovieAsyncTask(movieDao).execute();
    }

    public LiveData<List<Movie>> getAllMovies(int page) {
        Call<TopRatedMoviesResponse> call = movieDatabaseAPI.getTopRatedMovieList(page);
        call.enqueue(new Callback<TopRatedMoviesResponse>() {
            @Override
            public void onResponse(Call<TopRatedMoviesResponse> call, Response<TopRatedMoviesResponse> response) {
                responseList = response.body().getResults();
                movieList.setValue(responseList);
                for (Movie m : responseList) {
                    MovieDB movieDB = MovieDB.mapMovieToMovieDB(m);
                    insert(movieDB);
                }
            }

            @Override
            public void onFailure(Call<TopRatedMoviesResponse> call, Throwable t) {
                movieList.postValue(null);
            }
        });
        return movieList;
    }

    private static class InsertMovieAsyncTask extends AsyncTask<MovieDB, Void, Void> {
        private MovieDao movieDao;

        private InsertMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(MovieDB... movieDBS) {
            movieDao.insert(movieDBS[0]);
            return null;
        }
    }

    private static class UpdateMovieAsyncTask extends AsyncTask<MovieDB, Void, Void> {
        private MovieDao movieDao;

        private UpdateMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(MovieDB... movieDBS) {
            movieDao.update(movieDBS[0]);
            return null;
        }
    }

    private static class DeleteMovieAsyncTask extends AsyncTask<MovieDB, Void, Void> {
        private MovieDao movieDao;

        private DeleteMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(MovieDB... movieDBS) {
            movieDao.delete(movieDBS[0]);
            return null;
        }
    }

    private static class DeleteAllMovieAsyncTask extends AsyncTask<Void, Void, Void> {
        private MovieDao movieDao;

        private DeleteAllMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            movieDao.deleteAllMovies();
            return null;
        }
    }
}
