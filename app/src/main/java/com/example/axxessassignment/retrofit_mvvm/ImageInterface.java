package com.example.axxessassignment.retrofit_mvvm;

import com.example.axxessassignment.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @ImageInterface responsible the retrofit operation
 *
 */
public interface ImageInterface {

    @Headers("Authorization: Client-ID 137cda6b5008a7c")
    @GET("search/1")
    Call<ApiResponse> getImageList(@Query("q") String query);
}
