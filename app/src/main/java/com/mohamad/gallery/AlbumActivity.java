package com.mohamad.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mohamad.gallery.Adapter.AlbumAdapter;
import com.mohamad.gallery.Model.Album;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {
    RecyclerView recyclerView1;
    List<Album> albumList;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        recyclerView1 = findViewById(R.id.recyclerviewAlbum);
        GridLayoutManager layoutManager = new GridLayoutManager(
                this,2
        );
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                if((position) % 3*2==0){
                    return 2;
                }
                    return 1;
            }
        });

        recyclerView1.setLayoutManager(layoutManager);
        albumList = new ArrayList<>();
        id=getIntent().getStringExtra("AlbumId");
        getAlbum(id);
    }

    public void getAlbum(String id){
        String url = "http://www.idsdemo.me/champsservice/GeneralServices.asmx/GetGalleryPictures?albumId="+id+"&start=1&end=20";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Items");
                            for (int i =0; i <jsonArray.length(); i++){
                                JSONObject albums = jsonArray.getJSONObject(i);
                                String name = albums.getString("Caption");
                                String image = albums.getString("CroppedImage320x308");

                                   Album album = new Album(name,image);
                                   albumList.add(album);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AlbumAdapter adapter = new AlbumAdapter(AlbumActivity.this , albumList);
                        recyclerView1.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AlbumActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }


}