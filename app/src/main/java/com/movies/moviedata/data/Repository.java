package com.movies.moviedata.data;

import java.util.List;

import rx.Observable;


public class Repository {

    private DataSource remoteDataSource;

    public Repository(DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public Observable<List<Movie>> getMovies(int start, int end) {

        Observable<List<Movie>> observable;

        observable = remoteDataSource.getMovies(start, end);

        return observable;
    }

    public Observable<Movie> getMovieData(int movieId) {

        Observable<Movie> observable;

        observable = remoteDataSource.getMovieData(movieId);

        return observable;
    }
}
