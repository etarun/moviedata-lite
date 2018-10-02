package com.movies.moviedata;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.movies.moviedata.dagger.AppComponent;


public abstract class BaseActivity extends AppCompatActivity implements
        BaseFragmentInteractionListener {

    protected AppComponent appComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDaggerComponent();
    }

    protected void initDaggerComponent() {

        if (appComponent == null) {
            appComponent = ((MovieDataApplication) getApplication()).getApplicationComponent();
            appComponent.inject(this);
        }
    }

    public void showFragment(Class<? extends Fragment> fragmentClass, Bundle bundle,
                             boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(
                fragmentClass.getSimpleName());
        Bundle container;
        if (fragment == null) {
            try {
                container = new Bundle();
                container.putBundle(Properties.BUNDLE_KEY_WRAPPED_BUNDLE, bundle);
                fragment = fragmentClass.newInstance();
                fragment.setArguments(container);
            } catch (InstantiationException e) {
                throw new RuntimeException("New Fragment should have been created", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("New Fragment should have been created", e);
            }
        }
        container = fragment.getArguments();
        container.putBundle(Properties.BUNDLE_KEY_WRAPPED_BUNDLE, bundle);
        fragmentTransaction.replace(R.id.fragmentPlaceHolder, fragment,
                fragmentClass.getSimpleName());


        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    public <T extends Fragment> void showFragment(Class<T> fragmentClass) {
        showFragment(fragmentClass, null, false);
    }


    public void showRetainedFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentPlaceHolder);
        if (fragment != null) {
            fragmentTransaction.replace(R.id.fragmentPlaceHolder, fragment,
                    fragment.getClass().getSimpleName());
        }
        fragmentTransaction.commit();
    }

    public void popFragmentBackStack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            popFragmentBackStack();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void onDestroy() {
        releaseDI();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        initDaggerComponent();
        super.onResume();
    }

    @Override
    protected void onPause() {
        releaseDI();
        super.onPause();
    }

    private void releaseDI() {
        appComponent = null;
    }
}
