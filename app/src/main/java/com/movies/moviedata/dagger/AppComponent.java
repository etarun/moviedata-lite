package com.movies.moviedata.dagger;

import com.movies.moviedata.BaseActivity;
import com.movies.moviedata.data.Repository;
import com.movies.moviedata.retrofit.RetrofitModule;
import com.movies.moviedata.scope.ApplicationScope;

import dagger.Component;

/**
 * Dagger App Component with Application Scope
 */

@ApplicationScope
@Component(modules = {AppModule.class, RetrofitModule.class})
public interface AppComponent {
    Repository getDataRepository();

    void inject(BaseActivity baseActivity);
}
