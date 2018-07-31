package com.nimako;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tempagain.Categories;
import tempagain.ItemObject;
import tempagain.Recyclerviewadapter;

/**
 * Created by dhruvildesai on 19/01/18.
 */

public class EkActivity extends AppCompatActivity {
    private final String TAG="EkActivity";
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Recyclerviewadapter adapter;
    JSONArray cate=new JSONArray();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view1);
        layoutManager=new LinearLayoutManager(EkActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        requestJsonObject();

    }


    public void requestJsonObject(){

        RequestQueue queue= Volley.newRequestQueue(this);

        String url="http://nimako.in/api/categories/?output_format=JSON&filter[active]=1&display=[id,name]&filter[level_depth]=2";

//       String url="http://toscanyacademy.com/blog/mp.php";

        StringRequest stringRequest=new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response" + response);
//                GsonBuilder builder = new GsonBuilder();
//                Gson mgson = builder.create();
//               // Gson g=new Gson();
//                List<Categories_Ser> categories=new ArrayList<Categories_Ser>();
//                //String oo=g.toJson(categories);
//               // List<Categories_Ser> categories = new ArrayList<Categories_Ser>();
//
//                categories =Arrays.asList(mgson.fromJson(response, Categories_Ser[].class));
//                adapter = new Recyclerviewadapter(EkActivity.this, categories);
//                recyclerView.setAdapter(adapter);


                Gson gson=new Gson();
                Categories result1=gson.fromJson(response,Categories.class);
                List<Categories> categories = new ArrayList<Categories>();
                for (int i=0;i<result1.getCategories().size()-1;i++) {

                        //result1.getCategories().get(i).getId();
                    result1.getCategories().get(i).getName();

                    categories.add(result1);
                    adapter=new Recyclerviewadapter(EkActivity.this,categories);
                    recyclerView.setAdapter(adapter);
                }



                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"Error"+error.getMessage());

        }
    })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String credentials = "nimakointhefabricstoreofyear2018:";
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };


        queue.add(stringRequest);
    };

}

