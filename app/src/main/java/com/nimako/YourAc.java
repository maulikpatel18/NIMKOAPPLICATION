package com.nimako;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

/**
 * Created by dhruvildesai on 07/03/18.
 */

public class YourAc extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,ViewPagerEx.OnPageChangeListener {
    private final String TAG="Addadd";
    CardView c1,c2,c3,c4,c5;
    String email;
    SharedPreferences sp;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.youraccount);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sp=getSharedPreferences("zoomvanue",MODE_PRIVATE);
        Intent i=getIntent();
         email = i.getStringExtra("ee");
         Log.d(TAG,"enma"+email);

        c1=findViewById(R.id.card_view);

        c3=findViewById(R.id.card_view3);
        c4=findViewById(R.id.card_view4);
        c5=findViewById(R.id.card_view5);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(YourAc.this,Order_main.class);
                i.putExtra("ee",email);
                startActivity(i);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(YourAc.this,Wishlist_main.class);
                i.putExtra("ee",email);
                startActivity(i);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(YourAc.this,MyPersonalInfo.class);
                i.putExtra("ee",email);
                startActivity(i);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fm=getFragmentManager();
////                fm.beginTransaction().replace(R.id.contentframe,new MyAddress()).commit();
//                MyAddress ldf = new MyAddress ();
//                Bundle args = new Bundle();
//                args.putString("ee", email);
//                ldf.setArguments(args);
//                getFragmentManager().beginTransaction().remove(fm.findFragmentById(R.id.contentframe)).add(R.id.contentframe, ldf).commit();
       Intent i=new Intent(YourAc.this,MyAddress.class);
       i.putExtra("ee",email);
       startActivity(i);

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);

        TextView t2=(TextView) navigationView.getHeaderView(0).findViewById(R.id.emailhead1);
        t2.setText(email+"");

    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cart_item) {
            Intent i=new Intent(YourAc.this,Cart_main.class);
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

            Intent intent=new Intent(getApplicationContext(),Navigation.class);
            intent.putExtra("ee",email);
            startActivity(intent);

        } else if (id == R.id.nav_Logout) {
            sp.edit().clear().commit();
            Intent intent=new Intent(getApplicationContext(),Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
//            logoutUser();
        }
        else if (id == R.id.nav_account) {

        }
        else if(id==R.id.nav_Customerservice)
        {
            Intent i=new Intent(YourAc.this,CustomerService.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        }else if (id == R.id.nav_deal) {
            Intent i=new Intent(YourAc.this,ItemListOffer.class);
            i.putExtra("ee",email+"");
            startActivity(i);
        }else if (id == R.id.nav_order) {
            Intent i=new Intent(YourAc.this,Order_main.class);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
