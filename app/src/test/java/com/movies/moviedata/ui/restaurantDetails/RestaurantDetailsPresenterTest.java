package com.movies.moviedata.ui.restaurantDetails;

import com.movies.moviedata.data.Repository;
import com.movies.moviedata.data.Movie;
import com.movies.moviedata.ui.movieDetails.MovieDetailsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tarun on 10/1/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class MovieDetailsPresenterTest {
    @Mock
    private MovieDetailContract.View mockView;

    @Mock
    private Repository mockDataRepository;

    @Mock
    private Movie movie;


    private MovieDetailContract.Presenter presenter;
    @Before
    public void setup() {
        presenter = new MovieDetailsPresenter(mockView, mockDataRepository);
    }
    @Test
    public void onCreate_test() {
        when(movie.getName()).thenReturn("testName");
        when(movie.getDeliveryFee()).thenReturn("100.00");
        when(movie.getOverview()).thenReturn("5053406053");
        presenter.onCreate(movie);
        verify(mockView).setResName(movie.getName());
        verify(mockView).setResDeliveryFee(movie.getDeliveryFee());
        verify(mockView).setResPhone(movie.getOverview());
    }
    @Test
    public void onCreate_test_Movie_ISNULL() {
        movie = null;
        presenter.onCreate(movie);
        verify(mockView).showErrorMessage();
    }
}
