package com.nimako;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cart.Cart_adapter;
import cart.Cart_ser;
import single_product.Single_Pro_Ser;
import user.Customer_ser;

public class Cart_main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener {
private final String TAG="ItemList";
private RecyclerView recyclerView;
private Cart_adapter adapter;
CardView cardviewlower;
    int sum=0;
    int sum2=0,qtya;
    TextView totallbl,pricedelbl,cartprice1,shippingcost1,cartwprice1,cartdis1,uppertotal1,lowertotl1;
    Single_Pro_Ser result2;
    int sum1=0;
    int i;
Button placeorder;
String pricetoitem,wpricetoitem,shippingchargetoitem;
TextView txtnotf,itemcount;
    SharedPreferences sp;
    int count=0;
    String email,custid1,pro_id,qty_sel,id;
private List<Cart_ser> itemList;
    Cart_ser result1;
    Customer_ser result3;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_main);
//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//    setSupportActionBar(toolbar);
    sp=getSharedPreferences("zoomvanue",MODE_PRIVATE);
    email=sp.getString("emailid","").toString();

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
    txtnotf=(TextView)findViewById(R.id.notfoundcartlist);
    itemcount=(TextView)findViewById(R.id.itemcount);
    //cartdis1=(TextView)findViewById(R.id.cartdis);
    totallbl=findViewById(R.id.totallabl);
    pricedelbl=findViewById(R.id.pricedtelbl);
    cartprice1=(TextView)findViewById(R.id.cartprice);
   // cartwprice1=(TextView)findViewById(R.id.cartwtotal);
    shippingcost1=(TextView)findViewById(R.id.shippingcost);
    uppertotal1=(TextView)findViewById(R.id.uppertotal);
    lowertotl1=(TextView)findViewById(R.id.lowertotal);
    placeorder=(Button)findViewById(R.id.placeorder);
    cardviewlower=findViewById(R.id.card_viewlower);



//
    itemList = new ArrayList<>();
    adapter = new Cart_adapter(Cart_main.this, itemList,cartprice1, uppertotal1,lowertotl1);
//
    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Cart_main.this, 1);
    recyclerView.setLayoutManager(mLayoutManager);
//    recyclerView.addItemDecoration(new Cart_main.GridSpacingItemDecoration(2, dpToPx(10), true));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);





        String url1 ="https://nimako.lbyts.com/api/customers/?output_format=JSON&filter[email]="+email;

//       String url="http://toscanyacademy.com/blog/mp.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response + "");

                        Gson gson = new Gson();

                        result3= gson.fromJson(response, Customer_ser.class);

                        result3.getCustomers().get(0).getId();

                        custid1 = String.valueOf(result3.getCustomers().get(0).getId());
                        Log.d(TAG,custid1);

                        String url ="https://nimako.lbyts.com/admin268hvyowt/apis/viewcart.php";

                        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Log.d("CartRes", response + "");

                                        if(response.length()>=1){
                                            Gson gson = new Gson();
                                            result1= gson.fromJson(response, Cart_ser.class);

                                            List<Cart_ser> itemSer = new ArrayList<Cart_ser>();
                                            if(result1.getCart().getAssociations()!=null) {

                                                for (i = 0; i < result1.getCart().getAssociations().getCartRows().size(); i++) {

                                                    result1.getCart().getAssociations().getCartRows().get(i).setEmail(email);
                                                    result1.getCart().getId();
                                                    result1.getCart().getAssociations().getCartRows().get(i).setCustid(custid1);
                                                    result1.getCart().getAssociations().getCartRows().get(i).getIdProductAttribute();
                                                    result1.getCart().getAssociations().getCartRows().get(i).getQuantity();

                                                    pro_id=result1.getCart().getAssociations().getCartRows().get(i).getIdProduct();


//                                                RequestQueue queue1 = Volley.newRequestQueue(Cart_main.this);
//                                                String url ="https://nimako.lbyts.com/api/products/" + result1.getCart().getAssociations().getCartRows().get(i).getIdProduct().toString() + "?output_format=JSON";
//
//                                                qtya=Integer.parseInt(result1.getCart().getAssociations().getCartRows().get(i).getQuantity().toString());
//                                                StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url,
//                                                        new Response.Listener<String>() {
//                                                            @Override
//                                                            public void onResponse(String response) {
//
//                                                                Log.d("Response", response + "");
//
//
//                                                                Gson gson = new Gson();
//
//                                                                result2 = gson.fromJson(response, Single_Pro_Ser.class);

//                                                                float priceitem1=Float.valueOf(result2.getProduct().getPrice().toString());
//                                                                int mprice1 = ((int) (Math.round(priceitem1)))*(qtya);
//                                                                sum=sum+mprice1;
//                                                                Log.e(TAG,"total price:"+sum);

//                                                                float ship=Float.valueOf(result2.getProduct().getAdditionalShippingCost().toString());
//                                                                int ship1 = (int) (Math.round(ship));
//                                                                sum2=sum2+ship1;
//                                                                Log.e(TAG,"total shipping cost:"+sum2);


//                                                                float wpriceitem2=Float.valueOf(result2.getProduct().getPrice().toString());
//                                                                float ratiounit=Float.valueOf(result2.getProduct().getUnitPriceRatio().toString());
//                                                                float fprice=wpriceitem2/ratiounit;
//                                                                int mprice2 = ((int) (Math.round(fprice)))*(qtya);
//                                                                sum1=sum1+mprice2;

//                                                                Log.e(TAG,"total Wholeslae price:"+sum1);
                                                    count=count+1;
                                                    itemcount.setText(String.valueOf("Total Item: "+count));
                                                    // cartwprice1.setText("Rs."+String.valueOf(sum));
                                                    //  cartprice1.setText("Rs."+String.valueOf(sum1));
                                                    // cartdis1.setText("-Rs."+String.valueOf(sum1-sum));
                                                    shippingcost1.setText("Rs.75");

//                                                                int wholsepr=sum;
//                                                              //  int scost=sum2;
//                                                                int scost=75;
//                                                                int totalmain=+scost;
//
//                                                                lowertotl1.setText("Total: Rs."+totalmain);
//                                                                uppertotal1.setText(""+totalmain);
                                                    placeorder.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            Intent i=new Intent(Cart_main.this,MyAddressincart.class);
                                                            i.putExtra("ee",email);
                                                            //  i.putExtra("shipingcost",sum2+"");
                                                            i.putExtra("shipingcost",75+"");
                                                            // Log.e(TAG,"clicknext"+sum2);
                                                            startActivity(i);

                                                        }
                                                    });

//                                                            }
//
//                                                            }, new Response.ErrorListener() {
//                                                                @Override
//                                                                public void onErrorResponse(VolleyError error) {
//                                                                    Log.d(TAG, "Error" + error.getMessage());
//
//                                                                }
//                                                            })
//
//                                                            {
//                                                                @Override
//                                                                public Map<String, String> getHeaders() throws AuthFailureError {
//                                                                Map<String, String> headers = new HashMap<>();
//                                                                String credentials = "nimakointhefabricstoreofyear2018:";
//                                                                String auth = "Basic "
//                                                                        + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
//                                                                headers.put("Content-Type", "application/json");
//                                                                headers.put("Authorization", auth);
//                                                                return headers;
//                                                            }
//                                                            };
//
//                                             queue1.add(stringRequest1);

                                                    itemSer.add(result1);
                                                    adapter = new Cart_adapter(Cart_main.this, itemSer,cartprice1,lowertotl1,uppertotal1);
                                                    recyclerView.setAdapter(adapter);

                                                }

                                            }
                                            else
                                            {
                                                recyclerView.setVisibility(View.GONE);
                                                txtnotf.setVisibility(View.VISIBLE);
                                                cardviewlower.setVisibility(View.GONE);
                                                totallbl.setVisibility(View.GONE);
                                                pricedelbl.setVisibility(View.GONE);
                                            }

                                        }

                                    }

                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        if (error.networkResponse == null) {
                                            if (error.getClass().equals(TimeoutError.class)) {
                                                // Show timeout error message
                                                Toast.makeText(Cart_main.this,
                                                        "Oops. Timeout Re-Try..!",
                                                        Toast.LENGTH_LONG).show();

                                            }else if (error.getClass().equals(NoConnectionError.class)){
                                                Toast.makeText(Cart_main.this,
                                                        "Oops. Slow Internet Re-Try..!",
                                                        Toast.LENGTH_LONG).show();
                                            }else {
                                                Toast.makeText(Cart_main.this,error.toString(),Toast.LENGTH_LONG).show();
                                            }
                                        }else
                                        {

                                            Toast.makeText(Cart_main.this,error.toString(),Toast.LENGTH_LONG).show();

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
                                params.put("custID",custid1);
                                Log.d(TAG,"Customer id in cart:"+custid1);
//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                                // params.put("contact_number","8531552362");
                                return params;
                            }
                        };

                        RequestQueue requestQueue1 = Volley.newRequestQueue(Cart_main.this);
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
                                Toast.makeText(Cart_main.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            }else if (error.getClass().equals(NoConnectionError.class)){
                                Toast.makeText(Cart_main.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(Cart_main.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }else
                        {

                            Toast.makeText(Cart_main.this,error.toString(),Toast.LENGTH_LONG).show();

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

        RequestQueue requestQueue = Volley.newRequestQueue(Cart_main.this);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
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
