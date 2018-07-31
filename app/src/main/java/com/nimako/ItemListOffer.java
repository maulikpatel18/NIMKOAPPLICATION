package com.nimako;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
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
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Item.Item_Ser;
import Item.Item_adapter;

/**
 * Created by dhruvildesai on 24/01/18.
 */

public class ItemListOffer extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener,BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener {
    private final String TAG="ItemListOffer";
    private RecyclerView recyclerView;
    TextView tvnotf;
    private Item_adapter adapter;
    String email;
    private List<Item_Ser> itemSerList;

    int subcatename;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_productlist_offer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sp=getSharedPreferences("zoomvanue",MODE_PRIVATE);
        email=sp.getString("emailid","").toString();


        Intent i=getIntent();
        subcatename=i.getIntExtra("SID",0);




        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_item_list);
        tvnotf=(TextView)findViewById(R.id.notfoundtxtinitemlist);

        itemSerList = new ArrayList<>();
        adapter = new Item_adapter(ItemListOffer.this, itemSerList);
//
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ItemListOffer.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new ItemListOffer.GridSpacingItemDecoration(2, dpToPx(10), true));
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

    Item_Ser result1;

    private void prepareAlbums() {
        RequestQueue queue= Volley.newRequestQueue(ItemListOffer.this);
        int aaa=12;

        String url="https://nimako.lbyts.com/api/products/?display=[id,name,price,description,unit_price_ratio,link_rewrite,id_default_image,on_sale,wholesale_price]&output_format=JSON&filter[on_sale]=[1]";

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



                if(response.length()>=3) {
                        Gson gson = new Gson();
                        result1 = gson.fromJson(response, Item_Ser.class);
                        List<Item_Ser> itemSer = new ArrayList<Item_Ser>();



                            for (int i = 0; i < result1.getProducts().size(); i++) {

                                //result1.getCategories().get(i).getId();
                                result1.getProducts().get(i).getName();
                                //     result1.getProducts().get(i).getQuantityDiscount();
                                result1.getProducts().get(i).getPrice();
                                result1.getProducts().get(i).getIdDefaultImage();
                                result1.getProducts().get(i).getLinkRewrite();
                                result1.getProducts().get(i).setEmail(email);
                                result1.getProducts().get(i).getUnitPriceRatio();
                                result1.getProducts().get(i).getOnSale();
                                result1.getProducts().get(i).getWholesalePrice();
                                // result1.getProducts().get(i).setIdDefaultImage("http://nimako.in/api/images/8/29");
                                itemSer.add(result1);
                                adapter = new Item_adapter(ItemListOffer.this, itemSer);
                                adapter.notifyDataSetChanged();
                                recyclerView.setAdapter(adapter);

                            }
                        }
                        else
                        {
                            recyclerView.setVisibility(View.GONE);
                            tvnotf.setVisibility(View.VISIBLE);


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




//        recyclerView.addOnItemTouchListener(new RecyclerClick_Listener(ItemList.this, recyclerView, new RecyclerClick_Listener.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                productid=result1.getProducts().get(position).getId().toString();
//
//                ImageView btn=view.findViewById(R.id.btnaddtowish);
//                btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        AlertDialog.Builder builder1 = new AlertDialog.Builder(ItemList.this);
//                        builder1.setMessage("Are you sure you want to add in Wishlist this Product?");
//                        builder1.setCancelable(true);
//
//                        builder1.setPositiveButton(
//                                "Yes",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        // dialog.cancel();
//                                        addtowishlist();
//
//                                    }
//                                });
//
//                        builder1.setNegativeButton(
//                                "No",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//
//                        AlertDialog alert11 = builder1.create();
//                        alert11.show();
//
//
//                    }
//                });
//                Intent i = new Intent(ItemList.this, Item_Main.class);
//
//                i.putExtra("IID", result1.getProducts().get(position).getId().toString());
//                i.putExtra("Iname", result1.getProducts().get(position).getName().toString());
//                i.putExtra("Iprice", result1.getProducts().get(position).getPrice().toString());
//                i.putExtra("Idesc", result1.getProducts().get(position).getDescription().toString());
//                i.putExtra("Iqtydis", result1.getProducts().get(position).getQuantityDiscount().toString());
//              //  i.putExtra("Ishowprice", Integer.parseInt(result1.getProducts().get(position).getShowPrice().toString()));
//
//                startActivity(i);
//
//
//                Toast.makeText(ItemList.this,result1.getProducts().get(position).getName()+" is selected",Toast.LENGTH_LONG).show();
////                FragmentManager fm=getFragmentManager();
////                fm.beginTransaction().replace(R.id.contentframe,new SubCategory_Main()).commit();
//
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cart_item) {
            Intent i=new Intent(ItemListOffer.this,Cart_main.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        //Toast.makeText(getActivity(),slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    //this for navigation

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

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm=getFragmentManager();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent i=new Intent(ItemListOffer.this,Navigation.class);
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
            Intent i=new Intent(ItemListOffer.this,YourAc.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        }else if (id == R.id.nav_wishlist) {
            Intent i=new Intent(ItemListOffer.this,Wishlist_main.class);
            i.putExtra("ee",email+"");
            startActivity(i);

        } else if(id==R.id.nav_Customerservice)
        {
            Intent i=new Intent(ItemListOffer.this,CustomerService.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        } else if(id==R.id.nav_Customerservice)
        {
            Intent i=new Intent(ItemListOffer.this,CustomerService.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        }else if (id == R.id.nav_order) {
            Intent i=new Intent(ItemListOffer.this,Order_main.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        }

//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
