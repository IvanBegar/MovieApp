package com.movieapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.movieapp.R;
import com.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private ImageView imageView;
    private TextView tvTitle, tvYear, tvOverview, tvYearTitle, tvRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent i = getIntent();
        Movie movie = (Movie) i.getSerializableExtra("movie");
        setUpViews();

        Picasso.get()
                .load(IMAGE_BASE_URL + movie.getImagePath())
                .fit()
                .centerInside()
                .into(imageView);

        tvTitle.setText(movie.getTitle());

        tvYear.setText(movie.getReleaseDate());

        tvOverview.setText(movie.getOverview());

        tvYearTitle.setText(getString(R.string.release_year));

        tvRate.setText(movie.getVoteAverage());
    }

    private void setUpViews() {
        imageView = findViewById(R.id.imageView2);
        tvTitle = findViewById(R.id.tvTitle);
        tvYear = findViewById(R.id.tvYear);
        tvOverview = findViewById(R.id.tvOverview);
        tvYearTitle = findViewById(R.id.tvYear12);
        tvRate = findViewById(R.id.tvRate);
    }
}