package com.movieapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.movieapp.R;
import com.movieapp.adapter.MovieListAdapter;
import com.movieapp.model.Movie;
import com.movieapp.util.RecyclerItemClickListener;
import com.movieapp.viewmodel.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

public class TopRatedMovieListActivity extends AppCompatActivity {
    private List<Movie> movieList = new ArrayList<>();
    private MovieListAdapter adapter;
    private MovieListViewModel viewModel;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieListAdapter(this, movieList);
        recyclerView.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewModel.getMovieListObserver().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (movies != null) {
                    if (movieList.isEmpty()) {
                        movieList = movies;
                    } else {
                        movieList.addAll(movies);
                    }
                    adapter.setMovieList(movieList);
                }
            }
        });
        viewModel.makeApiCall(page);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Movie movie = movieList.get(position);
                        Intent i = new Intent(getApplicationContext(), MovieDetailsActivity.class);
                        i.putExtra("movie", movie);
                        startActivity(i);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        page++;
                        viewModel.makeApiCall(page);
                    }

                }
            }
        });
    }
}