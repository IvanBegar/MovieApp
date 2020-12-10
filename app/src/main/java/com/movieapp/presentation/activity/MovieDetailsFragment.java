package com.movieapp.presentation.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.movieapp.R;
import com.movieapp.data.model.Movie;
import com.movieapp.databinding.FragmentMovieDetailsBinding;
import com.movieapp.util.MakeVisible;

public class MovieDetailsFragment extends Fragment {

    private FragmentMovieDetailsBinding binding;
    private static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Movie movie = (Movie) requireArguments().getSerializable("movie");

        Glide.with(this)
                .load(IMAGE_BASE_URL + movie.getImagePath())
                .centerInside()
                .into(binding.imageView);

        binding.tvTitle.setText(movie.getTitle());

        binding.tvYear.setText(movie.getReleaseDate());

        binding.tvOverview.setText(movie.getOverview());

        binding.tvYearTitle.setText(getString(R.string.release_year));

        binding.tvRate.setText(movie.getVoteAverage());
        return view;
    }

    @Override
    public void onDetach() {
        MakeVisible makeVisible = (MakeVisible) getActivity();
        makeVisible.makeVisible();
        super.onDetach();
    }
}