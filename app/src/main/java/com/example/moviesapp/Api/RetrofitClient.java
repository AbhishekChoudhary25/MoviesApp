package com.example.moviesapp.Api;


import okhttp3.OkHttpClient;
import okhttp3.Request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private MoviesApi moviesApi;

    public RetrofitClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder().addHeader("X-RapidAPI-Key", MoviesApi.ApiKey).build();
                    return chain.proceed(request);
                }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(moviesApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        moviesApi = retrofit.create(MoviesApi.class);
    }


    public MoviesApi getMoviesApi(){
        return moviesApi;
    }

}
