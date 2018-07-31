package com.nimako;

import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import address.Add_adapter;
import address.Add_adapter_incart;
import address.Add_asbill_adapter_incart;
import address.Add_ser;
import user.Customer_ser;

public class MyAddressincart extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener {
    private final String TAG = "MYAddress";
    private RecyclerView recyclerView,recyclerView2;
    private Add_adapter_incart adapter;
    private Add_asbill_adapter_incart adapter1;
    List<Add_ser> itemList;
    SharedPreferences sp;
    CheckBox cbox1;
    String addidbill,addid;
    Button addAddr,procced;
    RelativeLayout lbill;
    TextView t1, t2;

    String custid1, email1,shippingcost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addressincart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sp = getSharedPreferences("zoomvanue", MODE_PRIVATE);
        Intent i = getIntent();
        email1 = i.getStringExtra("ee");
        shippingcost = i.getStringExtra("shipingcost");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_myadd1);
        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view_myadd2);


        // email = getArguments().getString("ee");
        Log.e(TAG, "Email bhai:" + email1);
//        Intent i=getIntent();
////        i.getStringExtra("custemail");
//        email1=i.getStringExtra("ee");

        itemList = new ArrayList<>();
        addAddr = findViewById(R.id.add_new_add__btn);
//
        adapter = new Add_adapter_incart(this, itemList);
////
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView2.setLayoutManager(mLayoutManager1);
//    recyclerView.addItemDecoration(new Cart_main.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter1);

        procced = findViewById(R.id.addcheckout);
        lbill=(RelativeLayout) findViewById(R.id.billaddlayout);
        cbox1=(CheckBox)findViewById(R.id.checkasbill);
        cbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    lbill.setVisibility(View.GONE);
                }
                else {
                    lbill.setVisibility(View.VISIBLE);
                }

            }
        });







        prepareAlbums();

        Customerid();
        addAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MyAddressincart.this, Add_address_my_add.class);
                i.putExtra("custid", custid1);
                i.putExtra("ee", email1);
               // Log.e(TAG, "Email in the junglke:" + email1);
                startActivity(i);
            }
        });
         BroadcastReceiver mMessageReceiver1 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Get extra data included in the Intent
                 addidbill = intent.getStringExtra("addidbill");
                //Toast.makeText(MyAddressincart.this,"address id bill" +" "+addidbill ,Toast.LENGTH_SHORT).show();
            }
        };
        LocalBroadcastManager.getInstance(MyAddressincart.this).registerReceiver(mMessageReceiver1,
                new IntentFilter("custom-message-bill"));

        BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Get extra data included in the Intent
                 addid = intent.getStringExtra("addid");
               // Toast.makeText(MyAddressincart.this,"address id" +" "+addid ,Toast.LENGTH_SHORT).show();
            }
        };

        LocalBroadcastManager.getInstance(MyAddressincart.this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));



        procced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(addid!=null && cbox1.isChecked()!=false) {

                    Intent i = new Intent(MyAddressincart.this, ShippingOption.class);
                    i.putExtra("saddid",addid);
                    i.putExtra("baddid",addid);
                    i.putExtra("shippingcost",shippingcost+"");
                    Log.e(TAG,"clicknext2"+shippingcost);
                    startActivity(i);
                }
                if(addid!=null && cbox1.isChecked()==false)
                {
                    if(addidbill!=null)
                    {
                        Intent i = new Intent(MyAddressincart.this, ShippingOption.class);
                        i.putExtra("saddid",addid);
                        i.putExtra("baddid",addidbill);
                        i.putExtra("shippingcost",shippingcost+"");
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(MyAddressincart.this, "Please select Billing Address", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(addid==null && addidbill==null){
                    Toast.makeText(MyAddressincart.this, "Please select Address", Toast.LENGTH_SHORT).show();
                }

            };


        });

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private void prepareAlbums() {


//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        int aaa = 12;

        String url = "https://nimako.lbyts.com/admin268hvyowt/apis/view_address.php";

//       String url="http://toscanyacademy.com/blog/mp.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response + "");


                        Gson gson = new Gson();

                        Add_ser result1 = gson.fromJson(response, Add_ser.class);

                        List<Add_ser> itemSer = new ArrayList<Add_ser>();
                        // int exceptionThrownHere = itemSer.get(0).getAddress().size();

//                        if (response.equals(null)) {
//                            throw new ArrayIndexOutOfBoundsException();
//
//
//                        }else{

                        for (int i = 0; i < result1.getAddress().size(); i++) {

                            //result1.getCategories().get(i).getId();
                            result1.getAddress().get(i).getFirstname();
                            result1.getAddress().get(i).getLastname();
                            result1.getAddress().get(i).getAddress1();
                            result1.getAddress().get(i).getAddress2();
                            result1.getAddress().get(i).getAlias();
                            result1.getAddress().get(i).getCity();
                            result1.getAddress().get(i).getCountry();
                            result1.getAddress().get(i).getPostcode();
                            result1.getAddress().get(i).getPhoneMobile();
                            result1.getAddress().get(i).getPhone();
                            result1.getAddress().get(i).getCustomerId();
                            result1.getAddress().get(i).getEmail();


                            // result1.getProducts().get(i).setIdDefaultImage("http://nimako.in/api/images/8/29");

                            itemSer.add(result1);
                            adapter = new Add_adapter_incart(MyAddressincart.this, itemSer);
                            adapter1 = new Add_asbill_adapter_incart(MyAddressincart.this, itemSer);
                            recyclerView.setAdapter(adapter);
                            recyclerView2.setAdapter(adapter1);
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error.networkResponse == null) {
                            if (error.getClass().equals(TimeoutError.class)) {
                                // Show timeout error message
                                Toast.makeText(MyAddressincart.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            } else if (error.getClass().equals(NoConnectionError.class)) {
                                Toast.makeText(MyAddressincart.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MyAddressincart.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        } else {

                            Toast.makeText(MyAddressincart.this, error.toString(), Toast.LENGTH_LONG).show();

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
                params.put("email", email1);
//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                // params.put("contact_number","8531552362");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MyAddressincart.this);
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


    private void Customerid() {


//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        int aaa = 12;

        String url1 = "https://nimako.lbyts.com/api/customers/?output_format=JSON&filter[email]=" + email1;

//       String url="http://toscanyacademy.com/blog/mp.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response + "");


                        Gson gson = new Gson();

                        Customer_ser result1 = gson.fromJson(response, Customer_ser.class);

                        List<Customer_ser> itemSer = new ArrayList<Customer_ser>();
                        // int exceptionThrownHere = itemSer.get(0).getCustomers().size();

//                        if (response.equals(null)) {
//                            throw new ArrayIndexOutOfBoundsException();
//
//
//                        }else{

                        //  for (int i = 0; i < result1.getCustomers().size(); i++) {

                        //result1.getCategories().get(i).getId();
                        result1.getCustomers().get(0).getId();


                        // result1.getProducts().get(i).setIdDefaultImage("http://nimako.in/api/images/8/29");

                        itemSer.add(result1);
//                                adapter = new Add_adapter(MyAddress.this, itemSer);
//                                recyclerView.setAdapter(adapter);
                        // }


                        custid1 = String.valueOf(result1.getCustomers().get(0).getId());
                        Log.d(TAG, custid1);

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error.networkResponse == null) {
                            if (error.getClass().equals(TimeoutError.class)) {
                                // Show timeout error message
                                Toast.makeText(MyAddressincart.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            } else if (error.getClass().equals(NoConnectionError.class)) {
                                Toast.makeText(MyAddressincart.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MyAddressincart.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        } else {

                            Toast.makeText(MyAddressincart.this, error.toString(), Toast.LENGTH_LONG).show();

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
//                params.put("email",email1);
//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                // params.put("contact_number","8531552362");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MyAddressincart.this);
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

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        return false;
    }


}

