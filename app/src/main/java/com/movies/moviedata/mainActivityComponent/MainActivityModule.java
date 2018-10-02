package com.movies.moviedata.mainActivityComponent;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class MainActivityModule {
    private MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Binds
    public abstract MainActivityContract.View provideMainActivityView(MainActivity mainActivity);

    @Binds
    public abstract MainActivityContract.Presenter providePresenter(MainActivityPresenter presenter);
}
