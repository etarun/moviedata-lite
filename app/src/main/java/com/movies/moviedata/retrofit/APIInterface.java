package com.movies.moviedata.retrofit;

import com.movies.moviedata.data.Movie;
import com.movies.moviedata.data.Response;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface APIInterface {

    @GET("/3/movie/now_playing")
    Observable<Response> getAllMovies(@QueryMap Map<String, String> queryMap);

    @GET("/3/movie/{id}")
    Observable<Movie> getMovieData(@Path(value = "id", encoded = true) int id, @QueryMap Map<String, String> queryMap);
}
