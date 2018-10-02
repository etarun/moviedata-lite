package com.movies.moviedata.ui.movies;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.movies.moviedata.BaseFragmentInteractionListener;
import com.movies.moviedata.R;
import com.movies.moviedata.dagger.AppComponent;
import com.movies.moviedata.data.Movie;
import com.movies.moviedata.mvp.BaseView;
import com.movies.moviedata.ui.movieDetails.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.movies.moviedata.Properties.MOVIE;


/**
 * The {@link Fragment} that receives movie data from its {@link MoviesContract.Presenter} and
 * renders a list of movies and also handles user actions, such as clicks on movie,
 * and passes it to its {@link MoviesContract.Presenter}.
 */
public class MoviesFragment extends BaseView implements MoviesContract.View {

    RecyclerView rvMovies;
    ProgressBar progressBar;

    @Inject
    MoviesContract.Presenter presenter;
    private MoviesRecyclerAdapter recyclerAdapter;

    private MoviesComponent moviesComponent;
    private BaseFragmentInteractionListener fragmentInteractionListener;
    protected AppComponent appComponent;
    private List<Movie> movies;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentInteractionListener = (BaseFragmentInteractionListener) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMemberVariables();
        setRetainInstance(true);
    }

    private void initMemberVariables() {
        movies = new ArrayList<>();
    }

    @Override
    public void showMovies(List<Movie> movies) {
        recyclerAdapter.addAll(movies);
    }

    @Override
    public void setProgressBar(boolean show) {
        if (show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        showToastMessage("Unable to Load Movies Data");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initDepedencyInjection();
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        rvMovies = rootView.findViewById(R.id.rvMovies);
        progressBar = rootView.findViewById(R.id.progressBar);

        initViews(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            initData();
        }
    }

    private void initData() {
        refreshPhotos();
    }

    private void refreshPhotos() {
        presenter.getMovies();
    }

    private void initViews(View rootView) {
        recyclerAdapter = new MoviesRecyclerAdapter(this, presenter, movies, movie -> startDetailsActivity(movie));
        rvMovies.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvMovies.getContext(),
                linearLayoutManager.getOrientation());
        rvMovies.addItemDecoration(dividerItemDecoration);
        rvMovies.setLayoutManager(linearLayoutManager);
    }

    private void startDetailsActivity(Movie movie) {
        presenter.getMovieData(movie.getMovieId());
    }

    @Override
    public void navigateToMovieDetails(Movie movie) {
        Intent i = new Intent(this.getContext(), MovieDetailActivity.class);
        i.putExtra(MOVIE, movie);
        startActivity(i);
    }

    private void initDepedencyInjection() {
        moviesComponent = DaggerMoviesComponent.builder()
                .appComponent(fragmentInteractionListener.getAppComponent())
                .moviesModule(new MoviesModule(this))
                .build();

        moviesComponent.inject(this);
    }
}
