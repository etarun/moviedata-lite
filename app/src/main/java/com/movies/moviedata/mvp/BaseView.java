package com.movies.moviedata.mvp;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Abstract base class to be extended by any MVP View such as {@link Fragment} and
 * {@link android.support.v4.app.ActivityCompat}. It contains common attributes and methods to be
 * shared across Presenters.
 */
public abstract class BaseView extends Fragment implements IBaseView {

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

}
