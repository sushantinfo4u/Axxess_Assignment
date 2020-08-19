package com.example.axxessassignment.retrofit_mvvm;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @RetrofitService This class is used for the singleton pattern of the retofit class
 *
 */
public class RetrofitService {

    private static final String BASE_URL="https://api.imgur.com/3/gallery/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
