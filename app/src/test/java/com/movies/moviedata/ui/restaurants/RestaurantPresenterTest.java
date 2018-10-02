package com.movies.moviedata.ui.restaurants;

import com.movies.moviedata.data.Repository;
import com.movies.moviedata.data.Movie;
import com.movies.moviedata.ui.movies.MoviesContract;
import com.movies.moviedata.ui.movies.MoviesPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tarun on 9/30/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class MoviePresenterTest {

    @Mock
    private MoviesContract.View mockView;

    @Mock
    private Repository mockDataRepository;

    @Mock
    private List<Movie> moviesData;

    @Mock
    private Movie movie;

    private MoviesContract.Presenter presenter;

    @Before
    public void setup() {
        presenter = new MoviesPresenter(mockView, mockDataRepository);
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Test
    public void getMovies_testCallback() {
        int start = 0;
        int end = 50;
        when(mockDataRepository.getMovies(eq(start), eq(end)))
                .thenReturn(Observable.just(moviesData));

        presenter.getMovies();

        verify(mockView).setProgressBar(true);
        verify(mockDataRepository).getMovies(eq(start), eq(end));

        verify(mockView).showMovies(moviesData);
        verify(mockView, times(1)).setProgressBar(false);
    }

    @Test
    public void getMovieData_testCallback() {
        int resId = 9;
        when(mockDataRepository.getMovieData(eq(resId)))
                .thenReturn(Observable.just(movie));

        presenter.getMovieData(resId);

        verify(mockView).setProgressBar(true);
        verify(mockView).navigateToMovieDetails(movie);
        verify(mockView, times(1)).setProgressBar(false);
    }

}
