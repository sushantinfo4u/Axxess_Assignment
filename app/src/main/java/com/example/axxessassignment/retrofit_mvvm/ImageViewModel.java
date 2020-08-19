package com.example.axxessassignment.retrofit_mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.axxessassignment.model.ApiResponse;
import com.example.axxessassignment.utility.AndroidHeleper;
/**
 * @ImageViewModel this class is reposible for the interaction between view and model
 *
 */
public class ImageViewModel extends ViewModel {

    private MutableLiveData<ApiResponse> apiResponseMutableLiveData;
    private ImageRepository imageRepository;
    private String query;
    private boolean isDataCleared=false;



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
