package com.nimako;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.List;

import address.Add_adapter_incart;
import address.Add_ser;
import shipping_option.ShipoptionSer;
import shipping_option.ShippingOption_adapter;
import wishlist.WL_adapter;

/**
 * Created by dhruvildesai on 29/03/18.
 */

public class ShippingOption extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener {
    private final String TAG = "Shipping option";
    private RecyclerView recyclerView;
    ShippingOption_adapter adapter;
    List<ShipoptionSer> itemList;
    String  email1,shipid;
    String saddid,baddid,shippingcost;
    Button checkout;
    CheckBox term;
    SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shippingoption);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sp = getSharedPreferences("zoomvanue", MODE_PRIVATE);
        Intent i = getIntent();
        email1 = i.getStringExtra("ee");
        saddid=i.getStringExtra("saddid");
        baddid=i.getStringExtra("baddid");
        shippingcost=i.getStringExtra("shippingcost");
        Log.e(TAG,"clicknext3"+shippingcost);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewshippingopt);
        itemList = new ArrayList<>();

        adapter = new ShippingOption_adapter(ShippingOption.this, itemList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareAlbums();
        checkout=findViewById(R.id.addcheckout);
        term=findViewById(R.id.checkterm);

        BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Get extra data included in the Intent
                shipid = intent.getStringExtra("shipid");
               // Toast.makeText(ShippingOption.this,"shippingoption" +" "+shipid ,Toast.LENGTH_SHORT).show();
            }
        };
        LocalBroadcastManager.getInstance(ShippingOption.this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(term.isChecked()) {
                    Intent i = new Intent(ShippingOption.this, PaymentStep.class);
                    i.putExtra("ee", email1);
                    i.putExtra("shipid", shipid);
                    i.putExtra("saddid",saddid);
                    i.putExtra("baddid",baddid);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(ShippingOption.this,"Please check term and condition",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();

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
    private void prepareAlbums() {



        ShipoptionSer a = new ShipoptionSer(1,"My carrier ","Delivery time: Delivery next day! ","₹ "+shippingcost+" (tax incl.)");
        itemList.add(a);
        adapter.notifyDataSetChanged();

        }

}
