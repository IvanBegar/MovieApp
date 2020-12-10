package com.movieapp.presentation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.movieapp.R;
import com.movieapp.data.model.Movie;
import com.movieapp.presentation.adapter.MovieListAdapter;
import com.movieapp.presentation.viewmodel.MovieListViewModel;
import com.movieapp.util.MakeVisible;
import com.movieapp.util.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class TopRatedMovieListActivity extends AppCompatActivity implements MakeVisible {
    private List<Movie> movieList = new ArrayList<>();
    private MovieListAdapter adapter;
    private MovieListViewModel viewModel;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int page = 1;
    private TextView tvHeader;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerView();

        setMovieListObserve();
    }

    private void setMovieListObserve() {
        viewModel.deleteAllMovies();
        viewModel.getMovieListObserver().observe(this, movies -> {
            if (movies != null) {
                if (movieList.isEmpty()) {
                    movieList = movies;
                } else {
                    movieList.addAll(movies);
                }
                adapter.setMovieList(movieList);
            }
        });
    }

    private void setOnItemClickListener(RecyclerView recyclerView) {
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        recyclerView.setVisibility(View.GONE);
                        tvHeader.setVisibility(View.GONE);

                        Movie movie = movieList.get(position);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("movie", movie);

                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.fragment_container_view, MovieDetailsFragment.class, bundle)
                                .addToBackStack(null)
                                .commit();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    private void setOnScrollListener(RecyclerView recyclerView, LinearLayoutManager layoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx > 0) //check for scroll down
                {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        page++;
                        viewModel.makeApiCallForTopRatedMovieList(page);
                    }
                }
            }
        });
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        tvHeader = findViewById(R.id.tvHeader);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieListAdapter(this, movieList);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        setOnItemClickListener(recyclerView);

        setOnScrollListener(recyclerView, layoutManager);
    }

    @Override
    public void makeVisible() {
        recyclerView.setVisibility(View.VISIBLE);
        tvHeader.setVisibility(View.VISIBLE);
    }
}