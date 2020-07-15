package com.example.axxessassignment.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.axxessassignment.Model.Image;
import com.example.axxessassignment.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private List<Image> imageList;
    private  LayoutInflater layoutInflater;
    private CircularProgressDrawable circularProgressDrawable;

    public ImageAdapter(Context context, List<Image> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final Holder holder = new Holder();
        View rowView;

        rowView = layoutInflater.inflate(R.layout.activity_gridview, null);
        holder.img = (ImageView) rowView.findViewById(R.id.imgView);
        holder.progressBar = (ProgressBar) rowView.findViewById(R.id.progress);


        Glide.with(context)
                .load(imageList.get(i).getLink())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        if(holder.progressBar!=null)
                            holder.progressBar.setVisibility(View.GONE);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        if(holder.progressBar!=null)
                            holder.progressBar.setVisibility(View.GONE);

                        return false;

                    }
                })
                .into(holder.img);

//        Picasso.with(context).load(imageList.get(i).getLink()).into(holder.img);

        return rowView;
    }

    class Holder{
        private ImageView img;
        private ProgressBar progressBar;
    }
}
