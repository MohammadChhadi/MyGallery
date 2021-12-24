package com.mohamad.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mohamad.gallery.Adapter.GalleryAdapter;
import com.mohamad.gallery.Model.Gallery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Gallery> galleryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        galleryList = new ArrayList<>();
        GetGallery();
    }
    private void GetGallery() {
        String url = "http://www.idsdemo.me/champsservice/GeneralServices.asmx/GetGallery?categoryId=1";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Items");
                            for (int i =0; i <jsonArray.length(); i++){
                                JSONObject galleries = jsonArray.getJSONObject(i);
                                String id = galleries.getString("Id");
                                String name = galleries.getString("Name");

                                JSONArray jsonArray1 =galleries.getJSONArray("Pictures");
                                for (int j = 0; j < jsonArray1.length(); j++) {
                                JSONObject image =jsonArray1.getJSONObject(j);
                                String picture = image.getString("CroppedImage200x200");

                                Gallery gallery = new Gallery(id,name,picture);
                                galleryList.add(gallery);
                              }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        GalleryAdapter adapter = new GalleryAdapter(MainActivity.this , galleryList);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }
}