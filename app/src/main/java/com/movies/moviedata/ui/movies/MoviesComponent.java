package com.movies.moviedata.ui.movies;

import com.movies.moviedata.dagger.AppComponent;
import com.movies.moviedata.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(
        dependencies = {AppComponent.class},
        modules = {MoviesModule.class}
)
public interface MoviesComponent {

    void inject(MoviesFragment moviesFragment);
}
