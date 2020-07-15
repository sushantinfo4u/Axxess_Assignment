package com.example.axxessassignment.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.axxessassignment.Adapter.ImageAdapter;
import com.example.axxessassignment.Model.ApiResponse;
import com.example.axxessassignment.Model.Image;
import com.example.axxessassignment.R;
import com.example.axxessassignment.Retrofit_MVVM.ImageViewModel;
import com.example.axxessassignment.Room.AppDatabase;
import com.example.axxessassignment.Room.CommentEntity;
import com.example.axxessassignment.Utility.AndroidHeleper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ApiResponse mainResponse;
    private ImageViewModel imageViewModel;
    private GridView gridView;
    private EditText edtSearch;
    private List<Image> imageList;
    private ProgressDialog progressBar;
    private ImageAdapter imageAdapter;
    private ArrayList<CommentEntity> entityArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entityArrayList=new ArrayList<>();
        setTitle(getString(R.string.searchscreen));
        setupUI();
    }

    private void setupUI() {
        imageList = new ArrayList<>();
        gridView = findViewById(R.id.grdiView);
        edtSearch = findViewById(R.id.edtSearch);
        progressBar=new ProgressDialog(MainActivity.this);
        progressBar.setProgress(0);
        progressBar.setMessage("Please Wait");
        progressBar.setMax(100);
        progressBar.setProgress(ProgressDialog.STYLE_SPINNER);
        progressBar.setCancelable(true);
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(AndroidHeleper.isNetworkConnected(getApplicationContext())){
                        AndroidHeleper.hideSoftKeyBoard(MainActivity.this);
                        progressBar.show();
                        viewModelSetUp();
                        return true;
                    }else{
                        progressBar.dismiss();
                        Toast.makeText(MainActivity.this, getString(R.string.internet), Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Image image=imageList.get(i);
                Intent intent = new Intent(MainActivity.this, ShowImageActivity.class);
                intent.putExtra("Image",image);
                startActivity(intent);
            }
        });
    }


    private void resetUI(){

        progressBar.dismiss();
        if(imageAdapter!=null){
            gridView.setVisibility(View.GONE);
        }
    }

    private void viewModelSetUp() {
        imageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
        String str=edtSearch.getText().toString();
        AndroidHeleper.logUtils("Main Activity : USER INPUT : "+str);
        imageViewModel.init(str);
        imageViewModel.getImageRepo().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {

                AndroidHeleper.logUtils("Main activity API RESPONSE :"+apiResponse.getSuccess());

                mainResponse = apiResponse;
                if(mainResponse.getSuccess()&&mainResponse!=null) {
                    AndroidHeleper.logUtils("Main activity "+apiResponse.getSuccess());
                    if (mainResponse.getData() != null && mainResponse.getData().size() > 0) {
                        AndroidHeleper.logUtils("Main activity data size"+apiResponse.getData().size());
                        setDataToUI();
                    } else {
                        resetUI();
                        AndroidHeleper.logUtils("Main activity data size fails"+apiResponse.getData().size());
                        Toast.makeText(MainActivity.this, "Data not available", Toast.LENGTH_SHORT).show();

                    }
                }else
                    {
                    resetUI();
                    Toast.makeText(MainActivity.this, "Give valid inpur for search", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void setDataToUI() {
        progressBar.dismiss();
        if(mainResponse.getData().size()>0&&mainResponse.getData()!=null){
            for (int i = 0; i < mainResponse.getData().size(); i++) {
                if(mainResponse.getData().get(i).getImages()!=null&&mainResponse.getData().get(i).getImages().size()>0){
                    for (int j = 0; j < mainResponse.getData().get(i).getImages().size(); j++) {
                        imageList.add(mainResponse.getData().get(i).getImages().get(j));
                    }
                }
            }
        }

        gridView.setVisibility(View.VISIBLE);

          imageAdapter=new ImageAdapter(this,imageList);
        gridView.setAdapter(imageAdapter);
    }

    class GetDataTask extends AsyncTask<Void,Void,ArrayList<CommentEntity>>{

        @Override
        protected ArrayList<CommentEntity> doInBackground(Void... voids) {



            return null;
        }
    }

}