package com.movies.moviedata;

import android.app.Application;

import com.movies.moviedata.dagger.AppComponent;
import com.movies.moviedata.dagger.AppModule;
import com.movies.moviedata.dagger.DaggerAppComponent;
import com.movies.moviedata.retrofit.RetrofitModule;


public class MovieDataApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule())
                .build();
    }

    public AppComponent getApplicationComponent() {
        return appComponent;
    }
}
