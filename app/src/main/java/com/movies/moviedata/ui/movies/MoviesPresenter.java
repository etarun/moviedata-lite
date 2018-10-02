package com.movies.moviedata.ui.movies;

import com.movies.moviedata.data.Repository;
import com.movies.moviedata.data.Movie;
import com.movies.moviedata.mvp.BasePresenter;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * The Presenter that fetches movie data by calling {@link Repository} at the request of
 * its View "{@link MoviesContract.View}", and then delivers the data back to
 * its View.
 * The presenter also calls other relevant methods of its View such as for
 * showing/hiding progress bar.
 */
public class MoviesPresenter extends BasePresenter<MoviesContract.View> implements MoviesContract.Presenter {

    private Repository dataRepository;

    //To destroy Subscriptions IfAny.
    @Override
    public void onViewActive(MoviesContract.View view) {

    }

    public MoviesPresenter(MoviesContract.View view, Repository dataRepository) {
        this.view = view;
        this.dataRepository = dataRepository;
    }

    @Override
    public void getMovies() {
        view.setProgressBar(true);
        Observable<List<Movie>> moviesData = dataRepository.getMovies(0, 50);
        moviesData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                    view.showMovies(movies);
                    view.setProgressBar(false);
                }, throwable -> {
                    view.showErrorMessage();
                    view.setProgressBar(false);
                });
    }

    @Override
    public void getMovieData(int movieId) {
        view.setProgressBar(true);
        Observable<Movie> movieData = dataRepository.getMovieData(movieId);
        movieData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie -> {
                    view.setProgressBar(false);
                    view.navigateToMovieDetails(movie);
                }, throwable -> {
                    view.setProgressBar(false);
                    view.showErrorMessage();
                });
    }
}
