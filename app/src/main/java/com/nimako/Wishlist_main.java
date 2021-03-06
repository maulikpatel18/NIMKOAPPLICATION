package com.nimako;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import address.Add_ser;
import cart.Cart_adapter;
import cart.Cart_ser;
import user.Customer_ser;
import wishlist.WL_adapter;
import wishlist.WL_ser;

public class Wishlist_main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener {
private final String TAG="wishlist";
private RecyclerView recyclerView;
private WL_adapter adapter;
SharedPreferences sp;
int count=0;
TextView txtnotf,itemcount;
String email,custid1,pro_id;
private List<WL_ser> itemList;
     WL_ser result1;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    sp=getSharedPreferences("zoomvanue",MODE_PRIVATE);
    email=sp.getString("emailid","").toString();
//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//    setSupportActionBar(toolbar);

    //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    recyclerView = (RecyclerView) findViewById(R.id.recycler_view_wishlist);
    txtnotf=(TextView)findViewById(R.id.notfoundwishlist);
    itemcount=(TextView)findViewById(R.id.itemcount);

//
    itemList = new ArrayList<>();
    adapter = new WL_adapter(Wishlist_main.this, itemList);
//
    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Wishlist_main.this, 1);
    recyclerView.setLayoutManager(mLayoutManager);
//    recyclerView.addItemDecoration(new Cart_main.GridSpacingItemDecoration(2, dpToPx(10), true));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);

    prepareAlbums();
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    TextView t2=(TextView) navigationView.getHeaderView(0).findViewById(R.id.emailhead1);
    t2.setText(email+"");



}
    private void prepareAlbums() {
        String url1 ="https://nimako.lbyts.com/api/customers/?output_format=JSON&filter[email]="+email;

//       String url="http://toscanyacademy.com/blog/mp.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response + "");


                        Gson gson = new Gson();

                        Customer_ser result1 = gson.fromJson(response, Customer_ser.class);

                        result1.getCustomers().get(0).getId();

                        custid1 = String.valueOf(result1.getCustomers().get(0).getId());
                        Log.d(TAG,custid1);


                        String url ="https://nimako.lbyts.com/admin268hvyowt/apis/view_whishlist.php";

//       String url="http://toscanyacademy.com/blog/mp.php";

                        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Log.d("Response", response + "");

                                    if(response.length()>20) {
                                        Gson gson = new Gson();

                                        WL_ser result1 = gson.fromJson(response, WL_ser.class);

                                        List<WL_ser> itemSer = new ArrayList<WL_ser>();


                                        for (int i = 0; i < result1.getWhishlist().size(); i++) {

                                            //result1.getCategories().get(i).getId();
                                            pro_id = result1.getWhishlist().get(i).getProductId();
                                            result1.getWhishlist().get(i).setEmail(email);
                                            result1.getWhishlist().get(i).setCustid(custid1);
                                            result1.getWhishlist().get(i).getIdWhishlist();
                                            count=count+1;


                                            // result1.getProducts().get(i).setIdDefaultImage("http://nimako.in/api/images/8/29");

                                            itemSer.add(result1);
                                            adapter = new WL_adapter(Wishlist_main.this, itemSer);
                                            recyclerView.setAdapter(adapter);
                                        }
                                        itemcount.setText(String.valueOf("Total Item: "+count));
                                    }
                                    else
                                    {
                                        recyclerView.setVisibility(View.GONE);
                                        txtnotf.setVisibility(View.VISIBLE);

                                    }



                                    }




                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        if (error.networkResponse == null) {
                                            if (error.getClass().equals(TimeoutError.class)) {
                                                // Show timeout error message
                                                Toast.makeText(Wishlist_main.this,
                                                        "Oops. Timeout Re-Try..!",
                                                        Toast.LENGTH_LONG).show();

                                            }else if (error.getClass().equals(NoConnectionError.class)){
                                                Toast.makeText(Wishlist_main.this,
                                                        "Oops. Slow Internet Re-Try..!",
                                                        Toast.LENGTH_LONG).show();
                                            }else {
                                                Toast.makeText(Wishlist_main.this,error.toString(),Toast.LENGTH_LONG).show();
                                            }
                                        }else
                                        {

                                            Toast.makeText(Wishlist_main.this,error.toString(),Toast.LENGTH_LONG).show();

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
                                params.put("customerid",custid1);
                                Log.d(TAG,"Customer id in wishlist:"+custid1);
//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                                // params.put("contact_number","8531552362");
                                return params;
                            }
                        };

                        RequestQueue requestQueue1 = Volley.newRequestQueue(Wishlist_main.this);
                        stringRequest1.setRetryPolicy(new RetryPolicy() {
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
                        requestQueue1.add(stringRequest1);


                    }




                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error.networkResponse == null) {
                            if (error.getClass().equals(TimeoutError.class)) {
                                // Show timeout error message
                                Toast.makeText(Wishlist_main.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            }else if (error.getClass().equals(NoConnectionError.class)){
                                Toast.makeText(Wishlist_main.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(Wishlist_main.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }else
                        {

                            Toast.makeText(Wishlist_main.this,error.toString(),Toast.LENGTH_LONG).show();

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

        RequestQueue requestQueue = Volley.newRequestQueue(Wishlist_main.this);
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













        //for single product








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



//
//    private void prepareAlbums() {
//        RequestQueue queue = Volley.newRequestQueue(Cart_main.this);
//
//        String url = "http://nimako.in/api/categories/?output_format=JSON&filter[active]=1&display=[id,name]&filter[level_depth]=2";
//
////       String url="http://toscanyacademy.com/blog/mp.php";
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, "Response" + response);
//
//                Gson gson = new Gson();
//                result1 = gson.fromJson(response, Add_ser.class);
//                List<Categories_Ser> categories = new ArrayList<Categories_Ser>();
//                for (int i = 0; i < result1.getCategories().size(); i++) {
//
//                    //result1.getCategories().get(i).getId();
//                    result1.getCategories().get(i).getName();
//
//                    .add(result1);
//                    adapter = new Add_adapter(Cart_main.this, categories);
//                    recyclerView.setAdapter(adapter);
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d(TAG, "Error" + error.getMessage());
//
//            }
//        })
//
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<>();
//                String credentials = "7RH4ZAGCU7LYA34Q5MNUZSPTWX3AZZ2T:";
//                String auth = "Basic "
//                        + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
//                headers.put("Content-Type", "application/json");
//                headers.put("Authorization", auth);
//                return headers;
//            }
//        };
//
//
//        queue.add(stringRequest);
//
//    }
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.cart_item) {
        Intent i=new Intent(Wishlist_main.this,Cart_main.class);
        startActivity(i);
        return true;
    }

    return super.onOptionsItemSelected(item);
}
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
//        } else {
//            super.onBackPressed();
//        }
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
            Intent i=new Intent(Wishlist_main.this,Navigation.class);
            i.putExtra("ee",email+"");
            startActivity(i);


        } else if (id == R.id.nav_Logout) {
            sp.edit().clear().commit();
            Intent intent=new Intent(getApplicationContext(),Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
//            logoutUser();
        }
        else if (id == R.id.nav_account) {
            Intent i=new Intent(Wishlist_main.this,YourAc.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        }else if (id == R.id.nav_wishlist) {
//            Intent i=new Intent(Wishlist_main.this,Wishlist_main.class);
//            i.putExtra("ee",email+"");
//            startActivity(i);

        } else if(id==R.id.nav_Customerservice)
        {
            Intent i=new Intent(Wishlist_main.this,CustomerService.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        }else if (id == R.id.nav_deal) {
            Intent i=new Intent(Wishlist_main.this,ItemListOffer.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        }else if (id == R.id.nav_order) {
            Intent i=new Intent(Wishlist_main.this,Order_main.class);
            i.putExtra("ee",email+"");
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
