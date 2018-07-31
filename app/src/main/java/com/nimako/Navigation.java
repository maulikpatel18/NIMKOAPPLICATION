package com.nimako;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;



public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    // Session Manager Class
    SharedPreferences sp;
    TextView t1,t2;
    String ee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar);




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // get user data from session
        sp=getSharedPreferences("zoomvanue",MODE_PRIVATE);
        ee=sp.getString("emailid","").toString();


            FragmentManager fm=getFragmentManager();
            fm.beginTransaction().replace(R.id.contentframe,new Home()).commit();

         t1 = (TextView) navigationView.getHeaderView(0).findViewById(R.id.namehead);

        t2=(TextView) navigationView.getHeaderView(0).findViewById(R.id.emailhead1);
        t2.setText(ee+"");
        // name


//        t1.setText(name+"");
//        t2.setText(email+"");


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


    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

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
            Intent i=new Intent(Navigation.this,Cart_main.class);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm=getFragmentManager();

        if (id == R.id.nav_home) {
            // Handle the camera action
//            Bundle bundle = new Bundle();
//            bundle.putString("ee", ee+"");
//// set Fragmentclass Arguments
//            Home fragobj = new Home();
//            fragobj.setArguments(bundle);
                fm.beginTransaction().replace(R.id.contentframe,new Home()).addToBackStack(null).commit();
        } else if (id == R.id.nav_Logout) {
            sp.edit().clear().commit();
            Intent intent=new Intent(getApplicationContext(),Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
//            logoutUser();
        } else if (id == R.id.nav_account) {
            //fm.beginTransaction().replace(R.id.contentframe,new YourAc()).addToBackStack(null).commit();

//            YourAc ldf = new YourAc ();
//            Bundle args = new Bundle();
//            args.putString("ee", ee);
//            ldf.setArguments(args);
//            getFragmentManager().beginTransaction().remove(fm.findFragmentById(R.id.contentframe)).add(R.id.contentframe, ldf).commit();

            Intent i=new Intent(Navigation.this,YourAc.class);
            i.putExtra("ee",ee+"");
            startActivity(i);
        }else if (id == R.id.nav_wishlist) {
            Intent i=new Intent(Navigation.this,Wishlist_main.class);
            i.putExtra("ee",ee+"");
            startActivity(i);

        }
        else if(id==R.id.nav_Customerservice)
        {
            Intent i=new Intent(Navigation.this,CustomerService.class);
            i.putExtra("ee",ee+"");
            startActivity(i);
        }else if (id == R.id.nav_deal) {
            Intent i=new Intent(Navigation.this,ItemListOffer.class);
            i.putExtra("ee",ee+"");
            startActivity(i);
        }else if (id == R.id.nav_order) {
            Intent i=new Intent(Navigation.this,Order_main.class);
            i.putExtra("ee",ee+"");
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
//    private void logoutUser() {
//        session.setLogin(false);
//
//        db.deleteUsers();
//
//        // Launching the login activity
//        Intent intent = new Intent(getApplicationContext(), Login.class);
//        startActivity(intent);
//        finish();
//    }
}
