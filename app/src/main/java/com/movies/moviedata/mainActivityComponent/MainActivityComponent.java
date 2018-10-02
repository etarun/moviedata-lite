package com.movies.moviedata.mainActivityComponent;

import com.movies.moviedata.dagger.AppComponent;
import com.movies.moviedata.scope.ActivityScope;

import dagger.Component;


@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = {MainActivityModule.class}
)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
