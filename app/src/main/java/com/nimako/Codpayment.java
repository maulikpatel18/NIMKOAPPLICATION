package com.nimako;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dhruvildesai on 01/04/18.
 */

public class Codpayment extends AppCompatActivity {
    private final String TAG = "order";
    String custid,sadd,badd,paymentmethod;
    TextView totalincodtxt1;
    String email;
    double total;
    int pqty;
    Button conformorder;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cod_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        conformorder=findViewById(R.id.conformordercod);
        totalincodtxt1=findViewById(R.id.totalincodtxt);

        sp = getSharedPreferences("zoomvanue", MODE_PRIVATE);
        email = sp.getString("emailid", "").toString();

        Intent i=getIntent();

        custid=i.getStringExtra("custid");
        pqty=i.getIntExtra("pquantity",0);
        total=i.getIntExtra("price",0);
        sadd=i.getStringExtra("saddress");
        badd=i.getStringExtra("baddress");
        paymentmethod=i.getStringExtra("PaymentMethod");

        Log.e(TAG,"Custid:"+custid);
        Log.e(TAG,"pqty:"+pqty);
        Log.e(TAG,"total:"+total);
        Log.e(TAG,"sadd:"+sadd);
        Log.e(TAG,"badd:"+badd);

        totalincodtxt1.setText("- The total amount of your order is ₹ "+total+" (tax incl.)");

        conformorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareAlbums();
            }
        });


    }
    private void prepareAlbums() {


//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        int aaa = 12;

        String url = "https://nimako.lbyts.com/admin268hvyowt/apis/order.php";

//       String url="http://toscanyacademy.com/blog/mp.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response + "");

                        if(response.length()>0)
                        {
                            Intent i=new Intent(Codpayment.this,ConformOrder.class);
                            i.putExtra("ee", email);
                            i.putExtra("type", 1);

                            startActivity(i);
                        }

//                        Gson gson = new Gson();
                    }
                    },
                            new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            if (error.networkResponse == null) {
                                if (error.getClass().equals(TimeoutError.class)) {
                                    // Show timeout error message
                                    Toast.makeText(Codpayment.this,
                                            "Oops. Timeout Re-Try..!",
                                            Toast.LENGTH_LONG).show();

                                } else if (error.getClass().equals(NoConnectionError.class)) {
                                    Toast.makeText(Codpayment.this,
                                            "Oops. Slow Internet Re-Try..!",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(Codpayment.this, error.toString(), Toast.LENGTH_LONG).show();
                                }
                            } else {

                                Toast.makeText(Codpayment.this, error.toString(), Toast.LENGTH_LONG).show();

                            }
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> headers = new HashMap<>();
                            String credentials = "nimakointhefabricstoreofyear2018:";
                            String auth = "Basic "
                                    + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                            //  headers.put("Content-Type", "application/json");
                            headers.put("Authorization", auth);

                            return headers;
                        }

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("custID", custid);
                            params.put("pquantity", pqty+"");
                            params.put("price", total+"");
                            params.put("saddress", sadd);
                            params.put("baddress", badd);
                            params.put("PaymentMethod", paymentmethod);
//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                            // params.put("contact_number","8531552362");
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(Codpayment.this);
        stringRequest.setRetryPolicy(new RetryPolicy() {
                        @Override
                        public int getCurrentTimeout() {
                            return 50000;
                        }

                        @Override
                        public int getCurrentRetryCount() {
                            return 50000;
                        }

                        @Override
                        public void retry(VolleyError error) throws VolleyError {

                        }
                    });
        requestQueue.add(stringRequest);
                }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home ) {

            finish();
            return true;
        }
        // other menu select events may be present here

        return super.onOptionsItemSelected(item);
    }
    }

