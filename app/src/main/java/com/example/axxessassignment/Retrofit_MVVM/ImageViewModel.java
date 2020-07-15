package com.example.axxessassignment.Retrofit_MVVM;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.axxessassignment.Model.ApiResponse;
import com.example.axxessassignment.Utility.AndroidHeleper;
/**
 * @ImageViewModel this class is reposible for the interaction between view and model
 *
 */
public class ImageViewModel extends ViewModel {

    private MutableLiveData<ApiResponse> apiResponseMutableLiveData;
    private ImageRepository imageRepository;
    private String query;

    public void init(String query){
        this.query=query;
        if(apiResponseMutableLiveData!=null){
            imageRepository=ImageRepository.getInstance();
            apiResponseMutableLiveData=imageRepository.getImageData(query);
            return;
        }
        else{
            imageRepository=ImageRepository.getInstance();
            apiResponseMutableLiveData=imageRepository.getImageData(query);
        }

    }

    public LiveData<ApiResponse> getImageRepo(){
        AndroidHeleper.logUtils("In View Model ");
        return apiResponseMutableLiveData;
    }


}
