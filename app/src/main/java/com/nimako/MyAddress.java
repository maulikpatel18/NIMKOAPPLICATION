package com.nimako;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Item.Item_Ser;
import Item.Item_adapter;
import address.Add_adapter;
import address.Add_ser;
import user.Customer_ser;

public class MyAddress extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener {
    private final String TAG = "MYAddress";
    private RecyclerView recyclerView;
    private Add_adapter adapter;
    List<Add_ser> itemList;
    SharedPreferences sp;
    Button addAddr;
    TextView t1,t2;
     String custid1,email1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        sp=getSharedPreferences("zoomvanue",MODE_PRIVATE);
        Intent i=getIntent();
         email1=i.getStringExtra("ee");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_myadd);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);

       // email = getArguments().getString("ee");
        Log.e(TAG,"Email bhai:"+email1);
//        Intent i=getIntent();
////        i.getStringExtra("custemail");
//        email1=i.getStringExtra("ee");
        t2=(TextView) navigationView.getHeaderView(0).findViewById(R.id.emailhead1);
        t2.setText(email1+"");

        itemList = new ArrayList<>();
        addAddr = findViewById(R.id.add_new_add__btn);
//
        adapter = new Add_adapter(this, itemList);
////
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
//    recyclerView.addItemDecoration(new Cart_main.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        prepareAlbums();

        Customerid();
        addAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MyAddress.this, Add_address_my_add.class);
                i.putExtra("custid",custid1);
                i.putExtra("ee",email1);
                Log.e(TAG,"Email in the junglke:"+email1);
                startActivity(i);
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


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

        String url ="https://nimako.lbyts.com/admin268hvyowt/apis/view_address.php";

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
                            adapter = new Add_adapter(MyAddress.this, itemSer);
                            recyclerView.setAdapter(adapter);
                        }




                    }




                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error.networkResponse == null) {
                            if (error.getClass().equals(TimeoutError.class)) {
                                // Show timeout error message
                                Toast.makeText(MyAddress.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            }else if (error.getClass().equals(NoConnectionError.class)){
                                Toast.makeText(MyAddress.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(MyAddress.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }else
                        {

                            Toast.makeText(MyAddress.this,error.toString(),Toast.LENGTH_LONG).show();

                        }
                    }
                }){
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
                params.put("email",email1);
//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                // params.put("contact_number","8531552362");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MyAddress.this);
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

        String url1 ="https://nimako.lbyts.com/api/customers/?output_format=JSON&filter[email]="+email1;

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
                            Log.d(TAG,custid1);

                        }




                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error.networkResponse == null) {
                            if (error.getClass().equals(TimeoutError.class)) {
                                // Show timeout error message
                                Toast.makeText(MyAddress.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            }else if (error.getClass().equals(NoConnectionError.class)){
                                Toast.makeText(MyAddress.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(MyAddress.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }else
                        {

                            Toast.makeText(MyAddress.this,error.toString(),Toast.LENGTH_LONG).show();

                        }
                    }
                }){
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

        RequestQueue requestQueue = Volley.newRequestQueue(MyAddress.this);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cart_item) {
            Intent i=new Intent(MyAddress.this,Cart_main.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        int id = item.getItemId();
        FragmentManager fm=getFragmentManager();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent i=new Intent(MyAddress.this,Navigation.class);
            i.putExtra("ee",email1);
            startActivity(i);


        } else if (id == R.id.nav_Logout) {
            sp.edit().clear().commit();
            Intent intent=new Intent(getApplicationContext(),Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
//            logoutUser();
        }
        else if (id == R.id.nav_account) {
           Intent i=new Intent(MyAddress.this,YourAc.class);
           i.putExtra("ee",email1);
           startActivity(i);
        } else if(id==R.id.nav_Customerservice)
        {
            Intent i=new Intent(MyAddress.this,CustomerService.class);
            i.putExtra("ee",email1+"");
            startActivity(i);
        }else if (id == R.id.nav_deal) {
            Intent i=new Intent(MyAddress.this,ItemListOffer.class);
            i.putExtra("ee",email1+"");
            startActivity(i);
        }else if (id == R.id.nav_order) {
            Intent i=new Intent(MyAddress.this,Order_main.class);
            i.putExtra("ee",email1+"");
            startActivity(i);
        }

//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}

