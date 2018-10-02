package com.movies.moviedata.ui.movies;

import com.movies.moviedata.data.Repository;
import com.movies.moviedata.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {

    MoviesContract.View view;

    public MoviesModule(MoviesContract.View view) {
        this.view = view;
    }


    @Provides
    @FragmentScope
    public MoviesContract.View providePhotosView() {
        return this.view;
    }

    @Provides
    @FragmentScope
    public MoviesContract.Presenter providePhotosPresenter(MoviesContract.View view, Repository repository) {
        return new MoviesPresenter(view, repository);
    }
}
