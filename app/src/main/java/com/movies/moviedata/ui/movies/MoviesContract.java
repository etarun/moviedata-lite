package com.movies.moviedata.ui.movies;

import com.movies.moviedata.data.Movie;
import com.movies.moviedata.mvp.IBasePresenter;
import com.movies.moviedata.mvp.IBaseView;

import java.util.List;


public class MoviesContract {

    public interface View extends IBaseView {

        void showMovies(List<Movie> movies);

        void showErrorMessage();

        void setProgressBar(boolean show);

        void navigateToMovieDetails(Movie movie);
    }

    public interface Presenter extends IBasePresenter<View> {

        void getMovies();

        void getMovieData(int movieId);
    }
}
