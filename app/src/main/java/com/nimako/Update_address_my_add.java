package com.nimako;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import countrystate.AddSerializeAdd;
import countrystate.CountrySer;
import countrystate.StateSer;
import countrystate.UpdateSerializeAdd;

public class Update_address_my_add extends AppCompatActivity {
    private final String TAG = "Add_Address_my_Add";
    // Add_ser result2;
    CountrySer result1;
    Button savel;
    StateSer result2;
    ArrayList<String> list;
    List<String> list1 = new ArrayList<>();;
    ArrayAdapter<String> adapter;
    EditText fname, lname, comp, add1, add2, zip, city, title, ph1, ph2, ai;
    String fname1, lname1, comp1, add11, cont1, add21, zip1, city1, title1, ph11, ph21, ai1, addid, custid, email11;
    Spinner cont, stat;
    int ccid,ssid,ccid1;
    int contid1, state1id;
    String conttext,statetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address_my_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        fname1 = i.getStringExtra("fname");
        lname1 = i.getStringExtra("lname");
        add11 = i.getStringExtra("add1");
        add21 = i.getStringExtra("add2");
        city1 = i.getStringExtra("city");
        cont1 = i.getStringExtra("country");
        zip1 = i.getStringExtra("postal");
        ph11 = i.getStringExtra("mob1");
        ph21 = i.getStringExtra("mob2");
        title1 = i.getStringExtra("alias");
        addid = i.getStringExtra("addid");
        email11 = i.getStringExtra("ee");
       // ccid = Integer.parseInt(i.getStringExtra("countryid"));


       // ssid = Integer.parseInt(i.getStringExtra("stateid"));

        Log.d(TAG, "contid:" + ccid + "stateid:" + ssid);

        comp1 = i.getStringExtra("company");
        ai1 = i.getStringExtra("other");
        conttext = i.getStringExtra("countrytxt");
        statetext = i.getStringExtra("statetxt");
        comp1 = i.getStringExtra("company");
        ai1 = i.getStringExtra("other");
        // i.putExtra("state",itemAList.get(position).getAddress().get(position).getState());
        custid = i.getStringExtra("custid");


        // contid1 = 110;

        savel = findViewById(R.id.btnsaveadd);
        fname = findViewById(R.id.fnameadd);
        lname = findViewById(R.id.lnameadd);
        comp = findViewById(R.id.companyadd);
        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        zip = findViewById(R.id.zip);

        cont = (Spinner) findViewById(R.id.countryadd);
        stat = (Spinner) findViewById(R.id.state1add);


        city = findViewById(R.id.cityadd);
        title = findViewById(R.id.titleonadd);
        ph1 = findViewById(R.id.hphone1);
        ph2 = findViewById(R.id.mphone1);
        ai = findViewById(R.id.Addinfo);


        fname.setText(fname1);
        lname.setText(lname1);
        add1.setText(add11);
        add2.setText(add21);
        zip.setText(zip1);
        cont.setPrompt(cont1);
        city.setText(city1);
        title.setText(title1);
        ph1.setText(ph11);
        ph2.setText(ph21);
        comp.setText(comp1);
        ai.setText(ai1);

        savel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdatemyAdd();
            }
        });

//        if (ccid > 0) {
//            country();
//            state();
//        }
//        else
//        {
//            country();
////        }
//    }
//
//        //prepareabc();
//
//
//    public void country() {
        String url = "https://nimako.lbyts.com/admin268hvyowt/apis/view_allcountry.php";
//

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG, response + "");


                        Gson gson = new Gson();

                        result1 = gson.fromJson(response, CountrySer.class);

                        List<CountrySer> itemSer = new ArrayList<CountrySer>();
//                                list.add("select Country");
                        List<String> list = new ArrayList<>();
                       // list.add(result1.getCountry().get(ccid - 1).getCountryname());
                        list.add("Select Country *");
                        list1.add("select state *");
                        for (int i = 1; i < result1.getCountry().size(); i++) {


//                            items[i] =
                            // items[i] = result1.getAddress().get(i).getAddreessId();
                            //itemSer.add(result1);
                            list.add(result1.getCountry().get(i).getCountryname());


                        }
                        adapter = new ArrayAdapter<String>(Update_address_my_add.this, android.R.layout.simple_spinner_item, list);
                        // cont.setPrompt("Select Country");
                        cont.setAdapter(adapter);
//                                country.setId(Integer.parseInt(result1.getAddress().get(i).getAddreessId()));

                        cont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(Update_address_my_add.this, result1.getCountry().get(position).getIdcountry(), Toast.LENGTH_LONG).show();

                               // ccid1 = Integer.parseInt(result1.getCountry().get(position).getIdcountry());
                                ccid = Integer.parseInt(result1.getCountry().get(position).getIdcountry());
                                list1.clear();
//                                if(position>0) {
//                                    statecontry();
//                                }
                                String url = "https://nimako.lbyts.com/admin268hvyowt/apis/view_states.php";
//

                                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {

                                                Log.d(TAG, response + "");


                                                Gson gson = new Gson();

                                                result2 = gson.fromJson(response, StateSer.class);

                                                List<CountrySer> itemSer = new ArrayList<CountrySer>();
//                                list.add("select Country");

//                                                list1.add(result2.getStates().get(state1id).getStateName());
                                                list1.add("Select State *");
                                                for (int i = 1; i < result2.getStates().size(); i++) {


//                            items[i] =
                                                    // items[i] = result1.getAddress().get(i).getAddreessId();
                                                    //itemSer.add(result1);
                                                    list1.add(result2.getStates().get(i).getStateName());


                                                }
                                                adapter = new ArrayAdapter<String>(Update_address_my_add.this, android.R.layout.simple_spinner_item, list1);
                                                //stat.setPrompt("Select Country");
                                                stat.setAdapter(adapter);
//                                country.setId(Integer.parseInt(result1.getAddress().get(i).getAddreessId()));

                                                stat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        // Toast.makeText(Update_address_my_add.this, result2.getStates().get(position).getIdState(), Toast.LENGTH_LONG).show();
                                                        if (position > 0) {
                                                            state1id = Integer.parseInt(result2.getStates().get(position).getIdState());
                                                        }
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                                if (error.networkResponse == null) {
                                                    if (error.getClass().equals(TimeoutError.class)) {
                                                        // Show timeout error message
                                                        Toast.makeText(Update_address_my_add.this,
                                                                "Oops. Timeout Re-Try..!",
                                                                Toast.LENGTH_LONG).show();

                                                    } else if (error.getClass().equals(NoConnectionError.class)) {
                                                        Toast.makeText(Update_address_my_add.this,
                                                                "Oops. Slow Internet Re-Try..!",
                                                                Toast.LENGTH_LONG).show();
                                                    } else {
                                                        Toast.makeText(Update_address_my_add.this, error.toString(), Toast.LENGTH_LONG).show();
                                                    }
                                                } else {

                                                    Toast.makeText(Update_address_my_add.this, error.toString(), Toast.LENGTH_LONG).show();

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
                                        params.put("countryid", String.valueOf(ccid));

//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                                        // params.put("contact_number","8531552362");
                                        return params;
                                    }
                                };

                                RequestQueue requestQueue = Volley.newRequestQueue(Update_address_my_add.this);
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
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error.networkResponse == null) {
                            if (error.getClass().equals(TimeoutError.class)) {
                                // Show timeout error message
                                Toast.makeText(Update_address_my_add.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            } else if (error.getClass().equals(NoConnectionError.class)) {
                                Toast.makeText(Update_address_my_add.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Update_address_my_add.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        } else {

                            Toast.makeText(Update_address_my_add.this, error.toString(), Toast.LENGTH_LONG).show();

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
//                        params.put("email","dkdesai1010@gmail.com");

//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                // params.put("contact_number","8531552362");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Update_address_my_add.this);
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

//public void state()
//{
//    String url = "http://nimako.in/admin268hvyowt/apis/view_states.php";
////
//
//    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//
//                    Log.d(TAG, response + "");
//
//
//                    Gson gson = new Gson();
//
//                    result2 = gson.fromJson(response, StateSer.class);
//
//                    List<CountrySer> itemSer = new ArrayList<CountrySer>();
////                                list.add("select Country");
//
//
//                    list1.add(result2.getStates().get(state1id).getStateName());
//                    for (int i = 1; i < result2.getStates().size(); i++) {
//
//
////                            items[i] =
//                        // items[i] = result1.getAddress().get(i).getAddreessId();
//                        //itemSer.add(result1);
//                        list1.add(result2.getStates().get(i).getStateName());
//
//
//                    }
//                    adapter = new ArrayAdapter<String>(Update_address_my_add.this, android.R.layout.simple_spinner_item, list1);
//                    //stat.setPrompt("Select Country");
//                    stat.setAdapter(adapter);
////                                country.setId(Integer.parseInt(result1.getAddress().get(i).getAddreessId()));
//
//                    stat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                             Toast.makeText(Update_address_my_add.this, result2.getStates().get(position).getIdState(), Toast.LENGTH_LONG).show();
//                            if (position > 0) {
//                                ssid = Integer.parseInt(result2.getStates().get(position).getIdState());
//                            }
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });
//                }
//            },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                    if (error.networkResponse == null) {
//                        if (error.getClass().equals(TimeoutError.class)) {
//                            // Show timeout error message
//                            Toast.makeText(Update_address_my_add.this,
//                                    "Oops. Timeout Re-Try..!",
//                                    Toast.LENGTH_LONG).show();
//
//                        } else if (error.getClass().equals(NoConnectionError.class)) {
//                            Toast.makeText(Update_address_my_add.this,
//                                    "Oops. Slow Internet Re-Try..!",
//                                    Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(Update_address_my_add.this, error.toString(), Toast.LENGTH_LONG).show();
//                        }
//                    } else {
//
//                        Toast.makeText(Update_address_my_add.this, error.toString(), Toast.LENGTH_LONG).show();
//
//                    }
//                }
//            }) {
//        @Override
//        public Map<String, String> getHeaders() throws AuthFailureError {
//            Map<String, String> headers = new HashMap<>();
//            String credentials = "nimakointhefabricstoreofyear2018:";
//            String auth = "Basic "
//                    + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
//            //  headers.put("Content-Type", "application/json");
//            headers.put("Authorization", auth);
//
//            return headers;
//        }
//
//        @Override
//        protected Map<String, String> getParams() throws AuthFailureError {
//            Map<String, String> params = new HashMap<String, String>();
//            params.put("countryid", String.valueOf(ccid));
//
////                params.put("ip_address",fip);
////                params.put("contact_number",et_sumno.getText().toString());
//            // params.put("contact_number","8531552362");
//            return params;
//        }
//    };
//
//    RequestQueue requestQueue = Volley.newRequestQueue(Update_address_my_add.this);
//    stringRequest.setRetryPolicy(new RetryPolicy() {
//        @Override
//        public int getCurrentTimeout() {
//            return 50000;
//        }
//
//        @Override
//        public int getCurrentRetryCount() {
//            return 50000;
//        }
//
//        @Override
//        public void retry(VolleyError error) throws VolleyError {
//
//        }
//    });
//    requestQueue.add(stringRequest);
//}
//
//    public void statecontry()
//    {
//        String url = "http://nimako.in/admin268hvyowt/apis/view_states.php";
////
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.d(TAG, response + "");
//
//
//                        Gson gson = new Gson();
//
//                        result2 = gson.fromJson(response, StateSer.class);
//
//                        List<CountrySer> itemSer = new ArrayList<CountrySer>();
////                                list.add("select Country");
//
//
//                       // list1.add(result2.getStates().get(state1id).getStateName());
//
//                        for (int i = 1; i < result2.getStates().size(); i++) {
//
//
////                            items[i] =
//                            // items[i] = result1.getAddress().get(i).getAddreessId();
//                            //itemSer.add(result1);
//                            list1.add(result2.getStates().get(i).getStateName());
//
//
//                        }
//                        adapter = new ArrayAdapter<String>(Update_address_my_add.this, android.R.layout.simple_spinner_item, list1);
//                        //stat.setPrompt("Select Country");
//                        stat.setAdapter(adapter);
////                                country.setId(Integer.parseInt(result1.getAddress().get(i).getAddreessId()));
//
//                        stat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                 Toast.makeText(Update_address_my_add.this, result2.getStates().get(position).getIdState(), Toast.LENGTH_LONG).show();
//if(position>0)
//{
//                                    ssid = Integer.parseInt(result2.getStates().get(position).getIdState());}
//
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//
//                            }
//                        });
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        if (error.networkResponse == null) {
//                            if (error.getClass().equals(TimeoutError.class)) {
//                                // Show timeout error message
//                                Toast.makeText(Update_address_my_add.this,
//                                        "Oops. Timeout Re-Try..!",
//                                        Toast.LENGTH_LONG).show();
//
//                            } else if (error.getClass().equals(NoConnectionError.class)) {
//                                Toast.makeText(Update_address_my_add.this,
//                                        "Oops. Slow Internet Re-Try..!",
//                                        Toast.LENGTH_LONG).show();
//                            } else {
//                                Toast.makeText(Update_address_my_add.this, error.toString(), Toast.LENGTH_LONG).show();
//                            }
//                        } else {
//
//                            Toast.makeText(Update_address_my_add.this, error.toString(), Toast.LENGTH_LONG).show();
//
//                        }
//                    }
//                }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<>();
//                String credentials = "nimakointhefabricstoreofyear2018:";
//                String auth = "Basic "
//                        + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
//                //  headers.put("Content-Type", "application/json");
//                headers.put("Authorization", auth);
//
//                return headers;
//            }
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("countryid", String.valueOf(ccid1));
//
////                params.put("ip_address",fip);
////                params.put("contact_number",et_sumno.getText().toString());
//                // params.put("contact_number","8531552362");
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(Update_address_my_add.this);
//        stringRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return 50000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return 50000;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//
//            }
//        });
//        requestQueue.add(stringRequest);
//    }

    public void UpdatemyAdd()
    {


        String url = "https://nimako.lbyts.com/admin268hvyowt/apis/updateaddress.php";
//

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG,response+"");



                        Gson gson = new Gson();
                        UpdateSerializeAdd result=gson.fromJson(response,UpdateSerializeAdd.class);

                        try {
                            JSONObject jsonObject = new JSONObject(response);// Convert response string in to json object.
                            // Toast.makeText(Update_address_my_add.this,result.getMessage() , Toast.LENGTH_LONG).show();
                               //JSONObject jsonLL = jsonObject.getJSONObject("status");// Get LL json object from jsonObject.
                            String status = jsonObject.getString("status");

                            if(status.equals(2)) {
                                Toast.makeText(Update_address_my_add.this,"Enter proper" , Toast.LENGTH_LONG).show();

                            }
                            else if(status.equals(0))
                            {
//                                email.setText("");
//                                pass.setText("");
                                Toast.makeText(Update_address_my_add.this,"Enter proper details",Toast.LENGTH_LONG).show();
                            }
                            else {
//                                FragmentManager fm=getFragmentManager();
//                                fm.beginTransaction().replace(R.id.contentframe,new MyAddress()).addToBackStack(null).commit();
                                Intent i=new Intent(Update_address_my_add.this,MyAddress.class);
                                i.putExtra("ee",email11);
                                startActivity(i);
//                               Toast.makeText(Login.this,"found", Toast.LENGTH_LONG).show();

                            }


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
                                Toast.makeText(Update_address_my_add.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            }else if (error.getClass().equals(NoConnectionError.class)){
                                Toast.makeText(Update_address_my_add.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(Update_address_my_add.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }else
                        {

//                            Toast.makeText(Add_address_my_add.this,error.toString(),Toast.LENGTH_LONG).show();

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
                params.put("idaddress",addid);
                params.put("idcountry",String.valueOf(ccid));
                params.put("idstate",String.valueOf(ssid));
                params.put("id_customer",custid);
                params.put("alias",title.getText().toString());
                params.put("company",comp.getText().toString());
                params.put("lastname",lname.getText().toString());
                params.put("firstname",fname.getText().toString());
                params.put("address1",add1.getText().toString());
                params.put("address2",add2.getText().toString());
                params.put("postcode",zip.getText().toString());
                params.put("city",city.getText().toString());
                params.put("other",ai.getText().toString());
                params.put("phone",ph1.getText().toString());
                params.put("pmobile",ph2.getText().toString());


                Log.d(TAG,"id address:"+addid);
                Log.d(TAG,"id country:"+String.valueOf(ccid));
                Log.d(TAG,"id state:"+String.valueOf(ssid));
                Log.d(TAG,"id customer:"+custid);
                Log.d(TAG,"alias:"+title.getText().toString());
                Log.d(TAG,"id company:"+comp.getText().toString());
                Log.d(TAG,"id lastname:"+lname.getText().toString());
                Log.d(TAG,"id firstname:"+fname.getText().toString());
                Log.d(TAG,"id addr1:"+add1.getText().toString());
                Log.d(TAG,"id addr2:"+add2.getText().toString());
                Log.d(TAG,"id postcode:"+zip.getText().toString());
                Log.d(TAG,"id city:"+city.getText().toString());
                Log.d(TAG,"id other:"+ai.getText().toString());
                Log.d(TAG,"id phone:"+ph1.getText().toString());
                Log.d(TAG,"id phonema:"+ph2.getText().toString());


//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                // params.put("contact_number","8531552362");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Update_address_my_add.this);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home ) {
            finish();
            return true;
        }
        // other menu select events may be present here

        return super.onOptionsItemSelected(item);
    }
    }



//