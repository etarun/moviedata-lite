package com.movies.moviedata.ui.movieDetails;

import com.movies.moviedata.data.Repository;
import com.movies.moviedata.data.Movie;
import com.movies.moviedata.mvp.BasePresenter;

public class MovieDetailsPresenter extends BasePresenter<MovieDetailContract.View> implements MovieDetailContract.Presenter {

    private Repository dataRepository;


    @Override
    public void onViewActive(MovieDetailContract.View view) {

    }

    public MovieDetailsPresenter(MovieDetailContract.View view, Repository dataRepository) {
        this.view = view;
        this.dataRepository = dataRepository;
    }


    @Override
    public void onCreate(Movie movie) {
        if (movie == null) {
            view.showErrorMessage();
            return;
        }
        if (movie.getImageURL() != null) {
            view.showMovieImage(movie.getImageURL());
        }
        if (movie.getOverview() != null) {
            view.setResPhone(movie.getOverview());
        }

        if (movie.getGenres() != null && movie.getGenres().size() >0) {

            view.setResDeliveryFee(movie.getGenres());
        }
        if (movie.getName() != null) {
            view.setResName(movie.getName());
        }
    }
}
