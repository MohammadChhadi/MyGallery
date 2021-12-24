package com.mohamad.gallery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mohamad.gallery.AlbumActivity;
import com.mohamad.gallery.Model.Gallery;
import com.mohamad.gallery.R;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    private Context context;
    private List<Gallery> data;
    private LayoutInflater layoutInflater;


    public GalleryAdapter(Context context , List<Gallery> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getItemViewType(int position){
        if(position %2==0){
            return 0;
        }
        return 1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType ==0) {
            View view = layoutInflater.inflate(R.layout.items_gallery_another, parent, false);
            return new MyViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.items_gallery, parent, false);
            return new MyViewHolder(view);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(data.get(position).getId());
        holder.title.setText(data.get(position).getName());
        Glide.with(context)
                .load(data.get(position).getImageUrl())
                .into(holder.imageView);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AlbumActivity.class);
                i.putExtra("AlbumId",data.get(position).getId());
                context.startActivity(i);
            }
        });

    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title,id;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.name);
            id=itemView.findViewById(R.id.GalleryId);
            constraintLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}