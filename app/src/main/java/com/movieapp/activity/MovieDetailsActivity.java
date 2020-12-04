package com.movieapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.movieapp.R;
import com.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetailsActivity extends AppCompatActivity {

    private static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    ImageView imageView;
    TextView tvTitle, tvYear, tvOverview, tvYearTitle, tvRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent i = getIntent();
        Movie movie = (Movie) i.getSerializableExtra("movie");
        imageView = findViewById(R.id.imageView2);
        Picasso.get()
                .load(IMAGE_BASE_URL + movie.getImagePath())
                .fit()
                .centerInside()
                .into(imageView);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(movie.getTitle());
        tvYear = findViewById(R.id.tvYear);
        tvYear.setText(movie.getReleaseDate());
        tvOverview = findViewById(R.id.tvOverview);
        tvOverview.setText(movie.getOverview());
        tvYearTitle = findViewById(R.id.tvYear12);
        tvYearTitle.setText(getString(R.string.release_year));
        tvRate = findViewById(R.id.tvRate);
        tvRate.setText(movie.getVoteAverage());
    }
}