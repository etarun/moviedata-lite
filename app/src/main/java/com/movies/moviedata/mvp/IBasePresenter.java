package com.movies.moviedata.mvp;


public interface IBasePresenter<ViewT> {

    void onViewActive(ViewT view);

}
