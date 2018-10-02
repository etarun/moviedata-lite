package com.movies.moviedata.ui.movieDetails;

import com.movies.moviedata.data.Repository;
import com.movies.moviedata.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tarun on 9/29/18.
 */

@Module
public class MovieDetailsModule {

    MovieDetailActivity movieDetailActivity;

    public MovieDetailsModule(MovieDetailActivity movieDetailActivity) {
        this.movieDetailActivity = movieDetailActivity;
    }

    @Provides
    @ActivityScope
    public MovieDetailContract.View provideMovieView() {
        return this.movieDetailActivity;
    }

    @Provides
    @ActivityScope
    public MovieDetailContract.Presenter provideMoviePresenter(MovieDetailContract.View view, Repository repository) {
        return new MovieDetailsPresenter(view, repository);
    }
}
