package com.nimako;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

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

import category.Categories_Ser;
import category.Category_adapter;
import category.RecyclerClick_Listener;

import static android.media.CamcorderProfile.get;

public class Home extends Fragment implements BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener {
    private final String TAG="Home";
    private RecyclerView recyclerView;
    private Category_adapter adapter;
    private List<Categories_Ser> categoryList;
String email;
    SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_home,container,false);


        Hash_file_maps = new HashMap<String, String>();

        sliderLayout = (SliderLayout)view.findViewById(R.id.slider);

        Hash_file_maps.put("Sofa Offer", "https://images.fabric.com/images/690/250/main/Homepage/December17/1226/CB-2370_1226_HP_Hero1c_site_qbp.jpg");
        Hash_file_maps.put("Curtain Offer", "https://images.fabric.com/images/690/250/main/Homepage/December17/1226/CB-2370_1226_HP_Hero3_site_FREESPIRIT.jpg");
        Hash_file_maps.put("SubCategory_Main Offer", "https://images.fabric.com/images/690/250/main/Homepage/December17/1226/CB-2370_1226_HP_Hero2_site_buffalo.jpg");

        for(String name : Hash_file_maps.keySet()){

//            TextSliderView textSliderView = new TextSliderView(getActivity());
//            textSliderView.description(name).image(Hash_file_maps.get(name)).setScaleType(BaseSliderView.ScaleType.Fit).setOnSliderClickListener(this);
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle().putString("extra",name);
//            sliderLayout.addSlider(textSliderView);

            DefaultSliderView df=new DefaultSliderView(getActivity());
            df.description(name).image(Hash_file_maps.get(name)).setOnSliderClickListener(new BaseSliderView.OnSliderClickListener(){
                @Override
                public void onSliderClick(BaseSliderView slider) {

                    Intent i=new Intent(getActivity(),ItemListOffer.class);
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

//        sliderLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Intent i=new Intent(getActivity(),ItemListOffer.class);
//                i.putExtra("ee",email);
//                startActivity(i);
//                return false;
//            }
//        });


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);


//
        categoryList = new ArrayList<>();
        adapter = new Category_adapter(getActivity(), categoryList);
//
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();
        //
//        try {
//            Glide.with(this).load(R.drawable.cover).into((ImageView) view.findViewById(R.id.backdrop));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        recyclerView.addOnItemTouchListener(new RecyclerClick_Listener(getActivity(), recyclerView, new RecyclerClick_Listener.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Categories_Ser category=categoryList.get(position);
//                String category_nm=category.getCategories().get(0).getName().toString().trim();
//                Toast.makeText(getActivity(),   category.getCategories().get(position)+" is selected!", Toast.LENGTH_SHORT).show();
////                if(category_nm.equals("CHAIR"))
////                {
////
//////                    Intent i=new Intent(getActivity(),EkActivity.class);
//////                    startActivity(i);
////                    FragmentManager fm=getFragmentManager();
////                    fm.beginTransaction().replace(R.id.contentframe,new SubCategory_Main()).commit();
////                }
//
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));


        return view;
    }
    Categories_Ser result1;

    private void prepareAlbums() {
//        int[] covers = new int[]{
//                R.drawable.chair1,
//                R.drawable.curtain1,
//                R.drawable.sofa2,
//                R.drawable.bed1};
//
//        Categories_Ser a = new Categories_Ser("SubCategory_Main",  covers[0]);
//        categoryList.add(a);
//
//        a = new Categories_Ser("Curtain",  covers[1]);
//        categoryList.add(a);
//
//        a = new Categories_Ser("Sofa",  covers[2]);
//        categoryList.add(a);
//
//        a = new Categories_Ser("Bed",  covers[3]);
//        categoryList.add(a);
//
//
//        adapter.notifyDataSetChanged();


        RequestQueue queue= Volley.newRequestQueue(getActivity());

        String url="https://nimako.lbyts.com/api/categories/?output_format=JSON&filter[active]=1&display=[id,name]&filter[level_depth]=2";

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
                    adapter=new Category_adapter(getActivity(),categories);
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

        recyclerView.addOnItemTouchListener(new RecyclerClick_Listener(getActivity(), recyclerView, new RecyclerClick_Listener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getActivity(), SubCategory.class);
                i.putExtra("ID", Integer.parseInt(result1.getCategories().get(position).getId().toString()));
                i.putExtra("ee",email);
                startActivity(i);
               // Toast.makeText(getActivity(),result1.getCategories().get(position).getId().toString()+" is selected",Toast.LENGTH_LONG).show();
//                FragmentManager fm=getFragmentManager();
//                Fragment abc=new Fragment();
//                Bundle b=new Bundle();
//                b.putInt("ID", Integer.parseInt(result1.getCategories().get(position).getId().toString()));
//                abc.setArguments(b);
//                fm.beginTransaction().add(R.id.contentframe,abc).replace(R.id.contentframe,new SubCategory_Main()).commit();

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
}
