package com.movies.moviedata.ui.movieDetails;

import com.movies.moviedata.dagger.AppComponent;
import com.movies.moviedata.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = {MovieDetailsModule.class}
)
public interface MovieDetailsComponent {
    void inject(MovieDetailActivity movieDetailActivity);
}
