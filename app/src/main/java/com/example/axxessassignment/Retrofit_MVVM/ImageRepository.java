package com.example.axxessassignment.Retrofit_MVVM;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.axxessassignment.Model.ApiResponse;
import com.example.axxessassignment.Model.Image;
import com.example.axxessassignment.Utility.AndroidHeleper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ImageRepository is class used for the getting the data fform the data source(API )
 *
 */
public class ImageRepository {

    private static ImageRepository imageRepository;
    private ImageInterface imageInterface;


    public static ImageRepository getInstance(){

            if(imageRepository==null){
                imageRepository=new ImageRepository();
            }
            return imageRepository;
    }

    public ImageRepository(){
        imageInterface=RetrofitService.cteateService(ImageInterface.class);
    }

    public MutableLiveData<ApiResponse> getImageData(String query){
        AndroidHeleper.logUtils(" In repository API CALL query "+query);
            final MutableLiveData<ApiResponse> imageMutableLiveData=new MutableLiveData<>();
            imageInterface.getImageList(query).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.code()==200&&response.isSuccessful()){
                            AndroidHeleper.logUtils(" In repository API CALL  success ");
                            imageMutableLiveData.setValue(response.body());
                        }
                }
                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    AndroidHeleper.logUtils(" In repository API CALL  fail "+t.getMessage());
                    imageMutableLiveData.setValue(null);
                }
            });
            return imageMutableLiveData;
    }

}
