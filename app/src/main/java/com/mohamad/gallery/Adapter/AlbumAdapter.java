package com.mohamad.gallery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mohamad.gallery.Model.Album;
import com.mohamad.gallery.R;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    private Context context;
    private List<Album> data;
    private LayoutInflater layoutInflater;

    public AlbumAdapter(Context context , List<Album> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getItemViewType(int position){
        if((position) % 3*2==0){
            return 2;
        }
        return 1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType ==2) {
            View view = layoutInflater.inflate(R.layout.items_album, parent, false);
            return new AlbumAdapter.MyViewHolder(view);
        } else {
                View view = layoutInflater.inflate(R.layout.items_album_another, parent, false);
                return new AlbumAdapter.MyViewHolder(view);
        }

    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.caption.setText(data.get(position).getCaption());
        Glide.with(context)
                .load(data.get(position).getImage())
                .into(holder.imageView);

    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView caption;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView3);
            caption = itemView.findViewById(R.id.caption);

        }
    }
}