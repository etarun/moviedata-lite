package com.movies.moviedata.dagger;

import com.movies.moviedata.MovieDataApplication;
import com.movies.moviedata.retrofit.RetrofitModule;
import com.movies.moviedata.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;


@Module(includes = RetrofitModule.class)
public class AppModule {
    private MovieDataApplication baseApplication;

    public AppModule(MovieDataApplication baseApplication) {
        this.baseApplication = baseApplication;
    }

    @Provides
    @ApplicationScope
    public MovieDataApplication provideApplication() {
        return baseApplication;
    }
}
