package com.movies.moviedata.data;


import com.movies.moviedata.retrofit.APIInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;


public class DataSource {
    private APIInterface apiInterface;

    public DataSource(APIInterface apiInterface) {
        this.apiInterface = apiInterface;

    }

    Observable<List<Movie>> getMovies(int start, int end) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_key", "7bfe007798875393b05c5aa1ba26323e");
        queryMap.put("language", "en-US");
        queryMap.put("page", String.valueOf(1));

        return apiInterface.getAllMovies(queryMap)
                .flatMap(response -> Observable.just(response.getMovies()));
    }

    Observable<Movie> getMovieData(int movieId) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_key", "7bfe007798875393b05c5aa1ba26323e");
        queryMap.put("language", "en-US");
        return apiInterface.getMovieData(movieId, queryMap)
                .flatMap(Observable::just);
    }


}
