package com.nimako;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by dhruvildesai on 01/04/18.
 */

public class ConformOrder extends AppCompatActivity {
    Button conformordercod1;
    SharedPreferences sp;
    String email;
    TextView totalincodtxt,totalincodtxt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordersuccessfull_main);
        totalincodtxt=(TextView)findViewById(R.id.totalincodtxt);
        totalincodtxt1=(TextView)findViewById(R.id.totalincodtxt1);

        Intent ii=getIntent();
        if (ii.getIntExtra("type",-1)==1){
            totalincodtxt1.setVisibility(View.GONE);
        }else {
            totalincodtxt.setVisibility(View.GONE);
        }
        sp=getSharedPreferences("zoomvanue",MODE_PRIVATE);
        email=sp.getString("emailid","").toString();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        conformordercod1=findViewById(R.id.conformordercod);
        conformordercod1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ConformOrder.this,Order_main.class);
                i.putExtra("ee",email);
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home ) {

            Intent i=new Intent(ConformOrder.this,Navigation.class);
            finish();
            startActivity(i);
            return true;
        }
        // other menu select events may be present here

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

//        super.onBackPressed();
        Intent i=new Intent(ConformOrder.this,Navigation.class);
        finish();
        startActivity(i);

    }
}
