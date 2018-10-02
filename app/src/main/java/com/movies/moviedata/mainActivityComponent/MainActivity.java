package com.movies.moviedata.mainActivityComponent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.movies.moviedata.BaseActivity;
import com.movies.moviedata.R;
import com.movies.moviedata.ui.movies.MoviesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MainActivity holds a Fragment to make with which screen rotation problem can be solved.
 * This has a Fragment Oriented Architecture
 */
public class MainActivity extends BaseActivity implements MainActivityContract.View {

    private MainActivityComponent mainActivityComponent;
    MainActivityContract.Presenter presenter; //For future use

    @BindView(R.id.fragmentPlaceHolder)
    FrameLayout fragmentPlaceholder;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMainActivityComponent();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_title);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        if (savedInstanceState == null)
            initViews();
        else
            showRetainedFragment();
    }

    @Override
    protected void onPause() {
        releaseDependencyInjection();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        releaseDependencyInjection();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        initMainActivityComponent();
        super.onResume();
    }

    private void initViews() {
        showFragment(MoviesFragment.class);
    }

    private void initMainActivityComponent() {
        super.initDaggerComponent();
        if (mainActivityComponent == null) {
            mainActivityComponent = DaggerMainActivityComponent.builder()
                    .appComponent(appComponent)
                    .build();

            mainActivityComponent.inject(this);
        }
    }

    private void releaseDependencyInjection() {
        mainActivityComponent = null;
    }

}
