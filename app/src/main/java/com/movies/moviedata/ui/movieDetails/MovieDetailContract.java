package com.movies.moviedata.ui.movieDetails;

import com.movies.moviedata.data.Genre;
import com.movies.moviedata.data.Movie;

import java.util.List;


class MovieDetailContract {

    interface View {

        void showErrorMessage();

        void setProgressBar(boolean show);

        void showMovieImage(String url);

        void setResName(String name);

        void setResPhone(String phone);

        void setResDeliveryFee(List<Genre> genreList);
    }

    interface Presenter {
        void onCreate(Movie movie);
    }
}
