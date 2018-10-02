package com.movies.moviedata.retrofit;


import com.movies.moviedata.data.DataSource;
import com.movies.moviedata.data.Repository;
import com.movies.moviedata.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    @ApplicationScope
    public Repository provideDataRepository(DataSource remoteDataSource) {
        return new Repository(remoteDataSource);
    }

    @Provides
    @ApplicationScope
    public DataSource provideDataSource(APIInterface apiInterface) {
        return new DataSource(apiInterface);
    }

    @Provides
    @ApplicationScope
    APIInterface getApiInterface(Retrofit retroFit) {
        return retroFit.create(APIInterface.class);
    }

    @Provides
    @ApplicationScope
    Retrofit getRetrofit(OkHttpClient okHttpClient) {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(rxAdapter)
                .build();
    }

    @Provides
    @ApplicationScope
    OkHttpClient getOkHttpCleint(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @ApplicationScope
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}