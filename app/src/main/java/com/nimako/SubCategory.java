package com.nimako;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.Toast;

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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import category.Categories_Ser;
import category.RecyclerClick_Listener;
import subcategory.SubCategory_adapter;

public class SubCategory extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener,BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener {
    private final String TAG="SubCategory_Main";
    private RecyclerView recyclerView;
    private SubCategory_adapter adapter;
    private List<Categories_Ser> categoryList;
    String email;
    int catename;
    SharedPreferences sp;
    SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_subcategory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sp=getSharedPreferences("zoomvanue",MODE_PRIVATE);
        email=sp.getString("emailid","").toString();

        Intent i=getIntent();
        catename=i.getIntExtra("ID",0);





        Hash_file_maps = new HashMap<String, String>();

        sliderLayout = (SliderLayout)findViewById(R.id.slider);

        Hash_file_maps.put("Sofa Offer", "https://images.fabric.com/images/690/250/main/Homepage/December17/1226/CB-2370_1226_HP_Hero1c_site_qbp.jpg");
        Hash_file_maps.put("Curtain Offer", "https://images.fabric.com/images/690/250/main/Homepage/December17/1226/CB-2370_1226_HP_Hero3_site_FREESPIRIT.jpg");
        Hash_file_maps.put("SubCategory_Main Offer", "https://images.fabric.com/images/690/250/main/Homepage/December17/1226/CB-2370_1226_HP_Hero2_site_buffalo.jpg");

        for(String name : Hash_file_maps.keySet()){

//            TextSliderView textSliderView = new TextSliderView(getActivity());
//            textSliderView.description(name).image(Hash_file_maps.get(name)).setScaleType(BaseSliderView.ScaleType.Fit).setOnSliderClickListener(this);
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle().putString("extra",name);
//            sliderLayout.addSlider(textSliderView);
            DefaultSliderView df=new DefaultSliderView(SubCategory.this);
            df.description(name).image(Hash_file_maps.get(name)).setOnSliderClickListener(new BaseSliderView.OnSliderClickListener(){
                @Override
                public void onSliderClick(BaseSliderView slider) {

                    Intent i=new Intent(SubCategory.this,ItemListOffer.class);
                    i.putExtra("ee",email);
                    startActivity(i);

                }
            });
            df.image(Hash_file_maps.get(name));
            sliderLayout.addSlider(df);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


//
        categoryList = new ArrayList<>();
        adapter = new SubCategory_adapter(SubCategory.this, categoryList);
//
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(SubCategory.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new SubCategory.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        TextView t2=(TextView) navigationView.getHeaderView(0).findViewById(R.id.emailhead1);
        t2.setText(email+"");

//        FragmentManager fm=getFragmentManager();
//        fm.beginTransaction().replace(R.id.contentframemain,new SubCategory_Main()).commit();




    }

    Categories_Ser result1;

    private void prepareAlbums() {
        RequestQueue queue= Volley.newRequestQueue(SubCategory.this);
        int aaa=12;

        String url="https://nimako.lbyts.com/api/categories/?output_format=JSON&filter[active]=1&display=[id,name]&filter[id_parent]="+catename;

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
                result1=gson.fromJson(response,Categories_Ser.class);
                List<Categories_Ser> categories = new ArrayList<Categories_Ser>();
                for (int i=0;i<result1.getCategories().size() ;i++) {

                    //result1.getCategories().get(i).getId();
                    result1.getCategories().get(i).getName();
                    result1.getCategories().get(i).getId();

                    categories.add(result1);
                    adapter=new SubCategory_adapter(SubCategory.this,categories);
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

        recyclerView.addOnItemTouchListener(new RecyclerClick_Listener(SubCategory.this, recyclerView, new RecyclerClick_Listener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(SubCategory.this, ItemList.class);
                i.putExtra("SID", Integer.parseInt(result1.getCategories().get(position).getId().toString()));
                startActivity(i);


                Toast.makeText(SubCategory.this,result1.getCategories().get(position).getName()+" is selected",Toast.LENGTH_LONG).show();
//                FragmentManager fm=getFragmentManager();
//                fm.beginTransaction().replace(R.id.contentframe,new SubCategory_Main()).commit();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    };


    @Override
    public void onStop() {
        sliderLayout.stopAutoCycle();

        super.onStop();
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cart_item) {
            Intent i=new Intent(SubCategory.this,Cart_main.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            Intent i=new Intent(SubCategory.this,Navigation.class);
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
            Intent i=new Intent(SubCategory.this,YourAc.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        }else if (id == R.id.nav_wishlist) {
            Intent i=new Intent(SubCategory.this,Wishlist_main.class);
            i.putExtra("ee",email+"");
            startActivity(i);

        } else if(id==R.id.nav_Customerservice)
        {
            Intent i=new Intent(SubCategory.this,CustomerService.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        }else if (id == R.id.nav_deal) {
            Intent i=new Intent(SubCategory.this,ItemListOffer.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        }else if (id == R.id.nav_order) {
            Intent i=new Intent(SubCategory.this,Order_main.class);
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
