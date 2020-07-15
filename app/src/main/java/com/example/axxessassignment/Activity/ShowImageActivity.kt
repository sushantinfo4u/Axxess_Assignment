package com.example.axxessassignment.Activity

import android.content.res.Configuration
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.axxessassignment.Adapter.CommentAdapter
import com.example.axxessassignment.Model.Image
import com.example.axxessassignment.R
import com.example.axxessassignment.Room.AppDatabase
import com.example.axxessassignment.Room.CommentEntity
import com.example.axxessassignment.Utility.AndroidHeleper
import kotlinx.android.synthetic.main.activity_show_image.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @author Name : Sushant SUryawanshi
 * @ShowImageActivity This class is used for the detail view of the image
 * and contain comment section
 *
 */

class ShowImageActivity : AppCompatActivity() {

    var commentList= listOf<CommentEntity>()
    var imageTitle:String=""
    var formatted:String=""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)

        val imageData=intent.getSerializableExtra("Image") as? Image
        val actionbar = supportActionBar

        val stringData: List<String> = imageData?.link!!.split("/")
        imageTitle = stringData[stringData.size - 1]
        actionbar?.title=imageTitle
        actionbar?.setDisplayHomeAsUpEnabled(true)

        var orienation=resources.configuration.orientation

        if(orienation==Configuration.ORIENTATION_PORTRAIT)
            rcvComment.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        else if(orienation==Configuration.ORIENTATION_LANDSCAPE)
            rcvComment.layoutManager= LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)


        var dbObj=AppDatabase(this)
        setTORecycleView(imageTitle, dbObj)

        Glide.with(applicationContext)
                .load(imageData?.link)
                .centerCrop()
                .error(R.drawable.image_not)
                .into(imgViewShow)

        btnSubmit?.setOnClickListener {
            AndroidHeleper.hideSoftKeyBoard(this)
            saveDataToRoom(imageTitle,edtComment.text.toString(),dbObj)
        }
    }

    private fun setTORecycleView(title: String, dbObj: AppDatabase) {

        GlobalScope.launch {
            commentList= dbObj.commentDao().findByTitle(title)
            updateUI(commentList)
        }
    }

    private fun updateUI(commentList: List<CommentEntity>) {
        try{
            val adapter = CommentAdapter(commentList,applicationContext)
            rcvComment.adapter = adapter
        }catch (e:Exception){
            Log.d("test"," ${e.localizedMessage}")
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveDataToRoom(title: String, comment: String, dbObj: AppDatabase) {

        try{
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            formatted = current.format(formatter)
        }catch (e:Exception){
            e.printStackTrace()
        }

        GlobalScope.launch {
            dbObj.commentDao().insertAll(CommentEntity(id=0,title = title,comment = comment,time =formatted ))
            setTORecycleView(title,dbObj)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}