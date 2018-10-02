package com.movies.moviedata;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.movies.moviedata.dagger.AppComponent;

/**
 * Base {@link Fragment} listener interface to be implemented by the hosting
 * {@link android.support.v4.app.ActivityCompat}. It should be extended by a
 * Fragment custom listener interface if any.
 */
public interface BaseFragmentInteractionListener {


    void showFragment(Class<? extends Fragment> fragmentClass, Bundle bundle,
                      boolean addToBackStack);

    AppComponent getAppComponent();
}
