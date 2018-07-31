package com.nimako;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.nfc.Tag;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Item.Item_Ser;
import Item.Item_adapter;
import Item.StockAvaiSer;
import Item.Stock_id_Ser;
import mainproductSerialize.ColorSer;
import mainproductSerialize.MainProSerialize;
import user.Customer_ser;
import wishlist.WishlistResSer;

public class Item_Main extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener,BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener {
    private final String TAG = "ItemMain";
    SliderLayout sliderLayout;
    String stockid,stockavailable;
    int custid1;
    Spinner s;
    int apri;
    String condi;
    float apricetemp,fprice,di,diamt;
    String colorname, colorcode;
    Button wishlistbtndesc1,addtocartdescbtn1;
    ImageButton show, hide;
    String idi, lr,email;
    String pidimg, colorid;
    HashMap<String, String> Hash_file_maps;
    private RecyclerView recyclerView;
    private Item_adapter adapter;
    private List<Item_Ser> itemSerList;
    TextView name, price1, dis,condition,stockavai,outofstk, sprice1, desc, txtshow;
    SharedPreferences sp;
    String pattrib,qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("zoomvanue", MODE_PRIVATE);
        email=sp.getString("emailid","").toString();
        setContentView(R.layout.activity_main_productmain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        String namei = i.getStringExtra("Iname");
        String pricei = i.getStringExtra("Iprice");
        String desci = i.getStringExtra("Idesc");
        idi = i.getStringExtra("IID");
//        String disi = i.getStringExtra("Iqtydis");
        String unitratio1=i.getStringExtra("unitratio");
        String onsale=i.getStringExtra("Ionsale");





        name = findViewById(R.id.nameproduct);
        outofstk=findViewById(R.id.outofstock);
        condition = findViewById(R.id.condtion);
        stockavai = findViewById(R.id.stockavai);
        price1 = findViewById(R.id.pricepro);
        sprice1 = findViewById(R.id.apricepro);
        dis = findViewById(R.id.dispro);

        sprice1.setPaintFlags(sprice1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        desc = findViewById(R.id.descpro);
        wishlistbtndesc1=findViewById(R.id.wishlistbtndesc);
        addtocartdescbtn1=findViewById(R.id.addtocartdescbtn);


        String url1 ="https://nimako.lbyts.com/api/customers/?output_format=JSON&filter[email]="+email;

//       String url="http://toscanyacademy.com/blog/mp.php";

        StringRequest stringRequest1= new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response customer id mate", response + "");


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


                        custid1 = result1.getCustomers().get(0).getId();
                        //Log.d(TAG,custid1);

                    }




                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error.networkResponse == null) {
                            if (error.getClass().equals(TimeoutError.class)) {
                                // Show timeout error message
                                Toast.makeText(Item_Main.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            }else if (error.getClass().equals(NoConnectionError.class)){
                                Toast.makeText(Item_Main.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                // Toast.makeText(Add_address_my_add.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }else
                        {

                            //Toast.makeText(Add_address_my_add.this,error.toString(),Toast.LENGTH_LONG).show();

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

        RequestQueue requestQueue1 = Volley.newRequestQueue(Item_Main.this);
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

        wishlistbtndesc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(Item_Main.this);
                builder1.setMessage("Are you sure you want to add in Wishlist this Product?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // dialog.cancel();
                                addtowishlist();

                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        addtocartdescbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtocart();
            }
        });
        name.setText(namei + "");
        //price.setText(pricei + "");
        //dis.setText(disi + "");

        desc.setText(android.text.Html.fromHtml(desci).toString() + "");

        di = Integer.parseInt(onsale);
        Log.d(TAG,"di:"+di);
        if (di == 0) {
            float price = Float.valueOf(pricei);
            int mprice = (int) (Math.round(price));

            price1.setText("₹" + mprice + "/mt");

            sprice1.setVisibility(View.GONE);
            dis.setVisibility(View.GONE);
        } else if (onsale != null) {

            apricetemp = Float.valueOf(pricei);

            apri = (int) (Math.round(apricetemp));

//            fprice = (apri * di) / 100;

//
            float unitratio=Float.valueOf(unitratio1);
            float price = Float.valueOf(pricei);
            float mprice = (int) (Math.round(price));
            fprice=price/unitratio;
            float fprice1=(int)(Math.round(fprice));

            diamt = Float.valueOf(mprice-fprice1);
            float aa=Float.valueOf(diamt/fprice1);
            di=Float.valueOf(aa*100);


            price1.setText("₹" + (int)(Math.round(mprice)) + "/mt");
            sprice1.setText("₹" + (int)(Math.round(fprice1)) + "/mt");
            dis.setText((int)(Math.round(di)) + "% off");
        }





        txtshow = findViewById(R.id.textshow);

        show = (ImageButton) findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Show button");
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                desc.setMaxLines(Integer.MAX_VALUE);
                txtshow.setText("less");
            }
        });
        hide = (ImageButton) findViewById(R.id.hide);
        hide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Hide button");
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                desc.setMaxLines(2);
                txtshow.setText("more");

            }
        });
        txtshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(txtshow.getText()=="less")
                {
                    hide.setVisibility(View.INVISIBLE);
                    show.setVisibility(View.VISIBLE);
                    desc.setMaxLines(2);
                    txtshow.setText("more");
                }
                else{
                    show.setVisibility(View.INVISIBLE);
                    hide.setVisibility(View.VISIBLE);
                    desc.setMaxLines(Integer.MAX_VALUE);
                    txtshow.setText("less");
                }
            }
        });


//
//        String[] arraySpinner = new String[] {
//                "1", "2", "3", "4", "5"
//        };
        Integer[] items = new Integer[110];
        for (int a = 0; a < items.length; a++) {
            items[a] = (a + 1);
        }


         s = (Spinner) findViewById(R.id.qtyspin);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(Item_Main.this, android.R.layout.simple_spinner_item, items);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
//        qty=s.getSelectedItem().toString();



//        if (disi == null || disi.equals(0)) {
//            float apricetemp = Float.valueOf(pricei);
//
//            apri = (int) (Math.round(apricetemp));
//            di = Integer.parseInt(disi);
//
//            fprice = (apri * di) / 100;
////
//            float pricew = Float.valueOf(pricei);
//            int mprice = (int) (Math.round(pricew));
//
//            sprice.setText("₹" + mprice + "/mt");
//            price.setText("₹" + String.valueOf(fprice) + "/mt");
//            dis.setText(disi + "% off");
//        } else if (disi != null) {
//            float pricea = Float.valueOf(pricei);
//            int mprice = (int) (Math.round(pricea));
//
//            price.setText("₹" + mprice + "/mt");
//
//            sprice.setVisibility(View.GONE);
//            dis.setVisibility(View.GONE);
//
//
//        }

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
//

    }

    MainProSerialize result1;
    ColorSer result2;

    private void prepareAlbums() {
        RequestQueue queue = Volley.newRequestQueue(Item_Main.this);
        int aaa = 12;

        String url = "https://nimako.lbyts.com/api/products/" + idi + "?output_format=JSON";

//       String url="http://toscanyacademy.com/blog/mp.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response" + response);
//
                Gson gson = new Gson();
                result1 = gson.fromJson(response, MainProSerialize.class);
                //  List<MainProSerialize> mainitem = new ArrayList<MainProSerialize>();
                Hash_file_maps = new HashMap<String, String>();

                sliderLayout = (SliderLayout) findViewById(R.id.slider_item1);
                PagerIndicator pagerIndicator = (PagerIndicator) findViewById(R.id.banner_slider_indicator1);

                pattrib=result1.getProduct().getAssociations().getCombinations().get(0).getId();
                Log.e(TAG,"pattrib before butoon:"+pattrib);
                condi=result1.getProduct().getCondition().toString();
                condition.setText(condi+" Product");
                for (int i = 0; i < result1.getProduct().getAssociations().getImages().size(); i++) {

                    lr = result1.getProduct().getLinkRewrite();
                    //pidimg=result1.getProduct().getAssociations().getImages();

                    pidimg = result1.getProduct().getAssociations().getImages().get(i).getId();
                    Log.e(TAG, "Id:" + pidimg + "");
                    Log.e(TAG, "lr:" + lr + "");
                    Hash_file_maps.put("image" + i, "https://nimako.lbyts.com/" + pidimg + "/" + lr + ".jpg");


                }
                for (String name : Hash_file_maps.keySet()) {

                    DefaultSliderView df = new DefaultSliderView(Item_Main.this);
                    df.image(Hash_file_maps.get(name));
                    sliderLayout.addSlider(df);
                }
                sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
                //sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                sliderLayout.setCustomIndicator(pagerIndicator);
                sliderLayout.setCustomAnimation(new DescriptionAnimation());
                //sliderLayout.setDuration(3000);
                sliderLayout.stopAutoCycle();
                sliderLayout.addOnPageChangeListener(Item_Main.this);

//for color button

                for (int j = 0; j < result1.getProduct().getAssociations().getProductOptionValues().size(); j++) {

                    colorid = result1.getProduct().getAssociations().getProductOptionValues().get(j).getId();
                    Log.e(TAG, "colorid:" + colorid);


                    RequestQueue queue1 = Volley.newRequestQueue(Item_Main.this);
                    String url1 = "https://nimako.lbyts.com/api/product_option_values/" + colorid + "?output_format=JSON";
                    final int finalJ = j;
                    StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "Response" + response);
                            Gson gson = new Gson();
                            result2 = gson.fromJson(response, ColorSer.class);

                            LinearLayout ll = (LinearLayout) findViewById(R.id.layoutcolor);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                    60,
                                    60);
                           final Button btn = new Button(Item_Main.this);




                            int i;
                            for (i = 0; i < result1.getProduct().getAssociations().getProductOptionValues().size(); i++) {
                                colorcode = result2.getProductOptionValue().getColor();


//
                                    colorname=result2.getProductOptionValue().getColor();
//                                btn.setId(i);
                                btn.setId(result2.getProductOptionValue().getId());



                            }
                            btn.setTag(finalJ);

                            final int id_ = btn.getId();
                            final int tag_=Integer.parseInt(btn.getTag().toString());
//                    btn.setText("" + id_);
                            btn.setText("");
                            params.setMargins(20, 10, 10, 0);
                            btn.setLayoutParams(params);

                            btn.setBackgroundColor(Color.parseColor(colorname));
                            ll.addView(btn, params);

                            Button btn1 = ((Button) findViewById(id_));

                            btn1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {



                                    Toast.makeText(view.getContext(),
                                            "Button clicked index = " + id_+"tag"+result1.getProduct().getAssociations().getCombinations().get(tag_).getId(), Toast.LENGTH_SHORT)
                                            .show();

                                    pattrib=result1.getProduct().getAssociations().getCombinations().get(tag_).getId();
                                    Log.e(TAG,"Partrib after button click:"+pattrib);




                            RequestQueue queue1 = Volley.newRequestQueue(Item_Main.this);
                            String url1 = "https://nimako.lbyts.com/api/stock_availables/&filter[id_product_attribute]=["+pattrib+"]&output_format=JSON";
                            StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d(TAG, "Response" + response);
                                    Gson gson = new Gson();
                                    Stock_id_Ser result11 = gson.fromJson(response, Stock_id_Ser.class);
                                    stockid=result11.getStockAvailables().get(0).getId().toString();
                                    RequestQueue queue1 = Volley.newRequestQueue(Item_Main.this);


                                    String url1 = "https://nimako.lbyts.com/api/stock_availables/"+stockid+"&output_format=JSON";
                                    StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d(TAG, "Response" + response);
                                            Gson gson = new Gson();
                                            StockAvaiSer result12 = gson.fromJson(response, StockAvaiSer.class);
                                             stockavailable=result12.getStockAvailable().getQuantity().toString();

                                            if(Integer.parseInt(stockavailable)<=0) {
                                                stockavai.setVisibility(View.GONE);
                                                outofstk.setVisibility(View.VISIBLE);
                                                wishlistbtndesc1.setVisibility(View.GONE);
                                                addtocartdescbtn1.setVisibility(View.GONE);
                                            }
                                            else {
                                                stockavai.setVisibility(View.VISIBLE);
                                                stockavai.setText(stockavailable + " Items");
                                                outofstk.setVisibility(View.GONE);
                                                wishlistbtndesc1.setVisibility(View.VISIBLE);
                                                addtocartdescbtn1.setVisibility(View.VISIBLE);
                                            }

                                        }
//                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d(TAG, "Error" + error.getMessage());

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

                                    queue1.add(stringRequest1);

                                }
//                        }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d(TAG, "Error" + error.getMessage());

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

                            queue1.add(stringRequest1);
                                }
                            });
                            }
//                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "Error" + error.getMessage());

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

                    queue1.add(stringRequest1);

                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error" + error.getMessage());

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
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cart_item) {
            Intent i = new Intent(Item_Main.this, Cart_main.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm=getFragmentManager();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent i=new Intent(Item_Main.this,Navigation.class);
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
            Intent i=new Intent(Item_Main.this,YourAc.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        }else if (id == R.id.nav_wishlist) {
            Intent i=new Intent(Item_Main.this,Wishlist_main.class);
            i.putExtra("ee",email+"");
            startActivity(i);

        } else if(id==R.id.nav_Customerservice)
        {
            Intent i=new Intent(Item_Main.this,CustomerService.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        }else if (id == R.id.nav_deal) {
            Intent i=new Intent(Item_Main.this,ItemListOffer.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        }else if (id == R.id.nav_order) {
            Intent i=new Intent(Item_Main.this,Order_main.class);
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
    public void addtowishlist()
    {

        String url = "https://nimako.lbyts.com/admin268hvyowt/apis/add2wishlist.php";
//

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG,response+"");


                        Gson gson = new Gson();
                        WishlistResSer result=gson.fromJson(response, WishlistResSer.class);

                        try {
                            JSONObject jsonObject = new JSONObject(response);// Convert response string in to json object.
                            Toast.makeText(Item_Main.this,result.getWishlist().getMessage() , Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }






                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error.networkResponse == null) {
                            if (error.getClass().equals(TimeoutError.class)) {
                                // Show timeout error message
                                Toast.makeText(Item_Main.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            }else if (error.getClass().equals(NoConnectionError.class)){
                                Toast.makeText(Item_Main.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(Item_Main.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }else
                        {

                            Toast.makeText(Item_Main.this,error.toString(),Toast.LENGTH_LONG).show();

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
                // idcountry1,idstate1,id_customer1,alias1,company1,lastname1,firstname1,address11,address21,postcode1,city11,other1,phone1,pmobile1

                params.put("productid",String.valueOf(idi));

                params.put("custid",custid1+"");
                Log.d("Add to wisis","Add to wishlist button click:product id"+String.valueOf(idi)+"and custmoer id:"+custid1+"");


                return params;
            }
        };

        RequestQueue requestQueue1 = Volley.newRequestQueue(Item_Main.this);
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

    public void addtocart()
    {

        String url = "https://nimako.lbyts.com/admin268hvyowt/apis/add2cart.php";
//

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG,response+"");


                        Gson gson = new Gson();
                        WishlistResSer result=gson.fromJson(response, WishlistResSer.class);

                        try {
                            JSONObject jsonObject = new JSONObject(response);// Convert response string in to json object.
                            Toast.makeText(Item_Main.this,result.getCart().getMessage() , Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }






                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error.networkResponse == null) {
                            if (error.getClass().equals(TimeoutError.class)) {
                                // Show timeout error message
                                Toast.makeText(Item_Main.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            }else if (error.getClass().equals(NoConnectionError.class)){
                                Toast.makeText(Item_Main.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(Item_Main.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }else
                        {

                            Toast.makeText(Item_Main.this,error.toString(),Toast.LENGTH_LONG).show();

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
                // idcountry1,idstate1,id_customer1,alias1,company1,lastname1,firstname1,address11,address21,postcode1,city11,other1,phone1,pmobile1

                params.put("productID",String.valueOf(idi));
                params.put("custID",custid1+"");
                qty=s.getSelectedItem().toString();
                params.put("quantity",qty);
                params.put("pattrib",pattrib+"");



                Log.d("Add to cart","Add to wishlist button click:product id"+String.valueOf(idi)+"and custmoer id:"+custid1+"qty:"+qty+"pattrib"+pattrib);


                return params;
            }
        };

        RequestQueue requestQueue1 = Volley.newRequestQueue(Item_Main.this);
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
//
}


