package com.nimako;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
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
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import PayPal.PayPalConfig;
import Paymentstep.Cart_ser;
import Paymentstep.Payment_Cart_adapter;
import cart.Cart_adapter;

import single_product.Single_Pro_Ser;
import user.Customer_ser;

/**
 * Created by dhruvildesai on 31/03/18.
 */

public class PaymentStep extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener {
    private final String TAG = "Paymentstep";
    private RecyclerView recyclerView;
    private Payment_Cart_adapter adapter;
    int sum = 0;
    double maintotal;
    int qtytotal=0;
    int i,ll;
    int total1;
    String paymentAmount="";
    int sum2 = 0;
    public TextView cartprice1, cartwprice1, cartdis1, shippingcost1, lowertotl1;
    Single_Pro_Ser result2;
    int sum1 = 0;
    Button codbtn,paypalbtn;
    TextView txtnotf, itemcount;
    SharedPreferences sp;
    int count = 0;
    int shipid;
    double taxamt;
    String saddid,baddid;
    String email,custid1, pro_id;
    private List<Cart_ser> itemList;
    Cart_ser result1;
    Button paycod;
    public static final int PAYPAL_REQUEST_CODE = 123;
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentstep);
//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//    setSupportActionBar(toolbar);
        sp = getSharedPreferences("zoomvanue", MODE_PRIVATE);
        email = sp.getString("emailid", "").toString();

        Intent i=getIntent();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent ii = new Intent(this, PayPalService.class);

        ii.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        startService(ii);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view3);
        txtnotf = (TextView) findViewById(R.id.notfoundcartlist);
        itemcount = (TextView) findViewById(R.id.itemcount);
        //cartdis1 = (TextView) findViewById(R.id.cartdis);
        cartprice1 = (TextView) findViewById(R.id.cartprice);
       // cartwprice1 = (TextView) findViewById(R.id.cartwtotal);
        shippingcost1 = (TextView) findViewById(R.id.shippingcost);
        paypalbtn=(Button)findViewById(R.id.paypalbtn);
        paypalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPayment();

            }
        });
        paycod=findViewById(R.id.codbtn);

        lowertotl1 = (TextView) findViewById(R.id.lowertotal);
        codbtn = (Button) findViewById(R.id.codbtn);

//        codbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent(PaymentStep.this, MyAddressincart.class);
////                i.putExtra("ee", email);
////                startActivity(i);
//
//            }
//        });
        Intent intent=getIntent();
        saddid=i.getStringExtra("saddid");
        baddid=i.getStringExtra("baddid");

        shipid=Integer.parseInt(intent.getStringExtra("shipid"));
         if(shipid==1)
         {
              taxamt=12.48;
         }


//
        itemList = new ArrayList<>();
        adapter = new Payment_Cart_adapter(PaymentStep.this, itemList,cartprice1,lowertotl1);
//
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(PaymentStep.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
//    recyclerView.addItemDecoration(new Cart_main.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();



    }

    private void prepareAlbums() {
        String url1 = "https://nimako.lbyts.com/api/customers/?output_format=JSON&filter[email]=" + email;

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
                        Log.d(TAG, custid1);


                        String url = "https://nimako.lbyts.com/admin268hvyowt/apis/viewcart.php";

//       String url="http://toscanyacademy.com/blog/mp.php";

                        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Log.d("Response", response + "");


                                        Gson gson = new Gson();

                                        Cart_ser result1 = gson.fromJson(response, Cart_ser.class);


                                        List<Cart_ser> itemSer = new ArrayList<Cart_ser>();
                                        if (result1.getCart().getAssociations() != null) {

                                            ll=result1.getCart().getAssociations().getCartRows().size();
                                            Log.e(TAG,"ll:"+ll);
                                            for (  i = 0; i < result1.getCart().getAssociations().getCartRows().size(); i++) {

                                                                shippingcost1.setText("Rs.75");

                                                                int scost=75;
//                                                                 maintotal=wholsepr+scost;
//                                                                lowertotl1.setText("Rs."+maintotal);



                                                result1.getCart().getAssociations().getCartRows().get(i).setEmail(email);
                                                result1.getCart().getAssociations().getCartRows().get(i).setCustid(custid1);
                                                result1.getCart().getAssociations().getCartRows().get(i).getIdProductAttribute();
                                                result1.getCart().getAssociations().getCartRows().get(i).getQuantity();
                                                int qtya=Integer.parseInt(result1.getCart().getAssociations().getCartRows().get(i).getQuantity().toString());
                                                qtytotal=qtytotal+qtya;
                                                Log.e(TAG,"Qtytotal:"+qtytotal);

//                                                result1.getCart().getAssociations().getCartRows().get(i).setPricetoitem(pricetoitem);
//                                                result1.getCart().getAssociations().getCartRows().get(i).setWpricetoitem(wpricetoitem);
//                                                result1.getCart().getAssociations().getCartRows().get(i).setAddshipping(shippingchargetoitem);


                                                // result1.getProducts().get(i).setIdDefaultImage("http://nimako.in/api/images/8/29");

                                                itemSer.add(result1);
                                                adapter = new Payment_Cart_adapter(PaymentStep.this, itemSer,cartprice1,lowertotl1);
                                                recyclerView.setAdapter(adapter);



                                            }
                                            paycod.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent ii=new Intent(PaymentStep.this,Codpayment.class);
                                                    ii.putExtra("custid",custid1);
                                                    ii.putExtra("pquantity",qtytotal);
                                                    ii.putExtra("price",Integer.parseInt(lowertotl1.getText().toString()));
                                                    ii.putExtra("saddress",saddid);
                                                    ii.putExtra("baddress",baddid);
                                                    ii.putExtra("PaymentMethod","1");
                                                    startActivity(ii);
                                                }
                                            });



                                        } else {
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
                                                Toast.makeText(PaymentStep.this,
                                                        "Oops. Timeout Re-Try..!",
                                                        Toast.LENGTH_LONG).show();

                                            } else if (error.getClass().equals(NoConnectionError.class)) {
                                                Toast.makeText(PaymentStep.this,
                                                        "Oops. Slow Internet Re-Try..!",
                                                        Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(PaymentStep.this, error.toString(), Toast.LENGTH_LONG).show();
                                            }
                                        } else {

                                            Toast.makeText(PaymentStep.this, error.toString(), Toast.LENGTH_LONG).show();

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
                                params.put("custID", custid1);
                                Log.d(TAG, "Customer id in cart:" + custid1);
//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                                // params.put("contact_number","8531552362");
                                return params;
                            }
                        };

                        RequestQueue requestQueue1 = Volley.newRequestQueue(PaymentStep.this);
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
                                Toast.makeText(PaymentStep.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            } else if (error.getClass().equals(NoConnectionError.class)) {
                                Toast.makeText(PaymentStep.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(PaymentStep.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        } else {

                            Toast.makeText(PaymentStep.this, error.toString(), Toast.LENGTH_LONG).show();

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

        RequestQueue requestQueue = Volley.newRequestQueue(PaymentStep.this);
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
        if (item.getItemId() == android.R.id.home) {

            finish();
            return true;
        }
        // other menu select events may be present here

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
    private void Albums() {


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
                            Intent i=new Intent(PaymentStep.this,ConformOrder.class);
                            i.putExtra("ee", email);
                            i.putExtra("type",2);
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
                                Toast.makeText(PaymentStep.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            } else if (error.getClass().equals(NoConnectionError.class)) {
                                Toast.makeText(PaymentStep.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(PaymentStep.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        } else {

                            Toast.makeText(PaymentStep.this, error.toString(), Toast.LENGTH_LONG).show();

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
                params.put("custID", custid1);
                params.put("pquantity", qtytotal+"");
                params.put("price", Integer.parseInt(lowertotl1.getText().toString())+"");
                params.put("saddress", saddid);
                params.put("baddress", baddid);
                params.put("PaymentMethod","2");
//                params.put("contact_number",et_sumno.getText().toString());
                // params.put("contact_number","8531552362");
                Log.e("params",params.toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(PaymentStep.this);
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

    private void getPayment() {
        //Getting the amount from editText
        paymentAmount = lowertotl1.getText().toString();

        //Creating a paypalpayment
        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(paymentAmount)), "USD", "Simplified Coding Fee",
                PayPalPayment.PAYMENT_INTENT_SALE);

        //Creating Paypal Payment activity intent
        Intent intent = new Intent(this, PaymentActivity.class);

        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If the result is from paypal
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Log.i("paymentExample", paymentDetails);
                        Intent ii=new Intent(PaymentStep.this,Codpayment.class);
                        ii.putExtra("custid",custid1);
                        ii.putExtra("pquantity",qtytotal);
                        ii.putExtra("price",Integer.parseInt(lowertotl1.getText().toString()));
                        ii.putExtra("saddress",saddid);
                        ii.putExtra("baddress",baddid);
                        ii.putExtra("PaymentMethod","2");
                        startActivity(ii);
                        //Toast.makeText(this, paymentDetails+"----"+paymentAmount, Toast.LENGTH_SHORT).show();
                        //Albums();
                        //Starting a new activity for the payment details and also putting the payment details with intent
                       /* startActivity(new Intent(this, ConfirmationActivity.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", paymentAmount));*/

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }
}