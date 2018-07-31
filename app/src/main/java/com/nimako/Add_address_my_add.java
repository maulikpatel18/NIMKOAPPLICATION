package com.nimako;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import countrystate.CustomAdapterCountry;
import countrystate.CustomAdapterState;
import countrystate.StateSer;
import user.Customer_ser;

public class Add_address_my_add extends AppCompatActivity {
    private final String TAG = "Add_Address_my_Add";
     String idcountry1,idstate1,alias1,company1,lastname1,firstname1,address11,address21,postcode1,city11,other1,phone1,pmobile1;
    StateSer result2;
    CountrySer result1;
    String contid,emails;
    List<String> list1 = new ArrayList<>();
    Button saveadd;
    ArrayList<String> list ;
    ArrayAdapter<String> adapter;
    EditText fname,lname,comp,add1,add2,zip,city,title,ph1,ph2,ai;
    Spinner cont,stat;
    Intent ia;
    int custid1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address_my_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ia=getIntent();
       // id_customer1=ia.getStringExtra("custid");
        emails=ia.getStringExtra("ee");
        Log.d(TAG,"eemail"+emails);
        //Log.d(TAG,"custo idddd"+id_customer1);
        fname = findViewById(R.id.fnameadd);
        lname = findViewById(R.id.lnameadd);
        comp = findViewById(R.id.companyadd);
        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        zip = findViewById(R.id.zip);


        cont = (Spinner) findViewById(R.id.countryadd);
        stat = findViewById(R.id.state1add);

        city = findViewById(R.id.cityadd);
        title = findViewById(R.id.titleonadd);
        ph1 = findViewById(R.id.hphone1);
        ph2 = findViewById(R.id.mphone1);
        ai = findViewById(R.id.Addinfo);

        alias1=title.getText().toString().trim();
        company1=comp.getText().toString().trim();
        lastname1=lname.getText().toString().trim();
        firstname1=fname.getText().toString().trim();
        address11=add1.getText().toString().trim();
        address21=add2.getText().toString().trim();
        postcode1=zip.getText().toString().trim();
        city11=city.getText().toString().trim();
        other1=ai.getText().toString().trim();
        phone1=ph1.getText().toString().trim();
        pmobile1=ph2.getText().toString().trim();


        String url1 ="https://nimako.lbyts.com/api/customers/?output_format=JSON&filter[email]="+ia.getStringExtra("ee");

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
                                Toast.makeText(Add_address_my_add.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            }else if (error.getClass().equals(NoConnectionError.class)){
                                Toast.makeText(Add_address_my_add.this,
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

        RequestQueue requestQueue1 = Volley.newRequestQueue(Add_address_my_add.this);
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


        saveadd=findViewById(R.id.btnsaveadd);

        saveadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(!isValidfname(fname.getText().toString()))
                {
                    Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                    er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                    fname.setError("Firstname is Required",er);
                }
                if(!isValidlname(lname.getText().toString()))
                {
                    Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                    er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                    lname.setError("Lastname is Required",er);
                }
                if(!isValidcity(city.getText().toString()))
                {
                    Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                    er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                    city.setError("City is Required",er);
                }
                if(!isValidaddress(add1.getText().toString()))
                {
                    Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                    er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                    add1.setError("Address line is Required",er);
                }
                if(!zip_postal(zip.getText().toString()))
                {
                    Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                    er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                    zip.setError("Zip code is Required",er);
                }
                if(phone1.equals(null) && pmobile1.equals(null))
                {
                    Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                    er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                    ph1.setError("any one phone is Required",er);
                    ph2.setError("any one phone is Required",er);
                }
                if(!isValidhomephn(ph1.getText().toString()))
                {
                    Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                    er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                    ph1.setError("Enter valid contact",er);
                }
                if(!isValidhomephn(ph2.getText().toString()))
                {
                    Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                    er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                    ph2.setError("Enter valid contact",er);
                }
                if(!isValidtitle(title.getText().toString()))
                {
                    Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                    er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                    title.setError("Enter address Title ",er);
                }
                if(isValidfname(fname.getText().toString()) && isValidlname(lname.getText().toString()) && isValidaddress(add1.getText().toString()) && isValidcity(city.getText().toString()) &&   isValidtitle(title.getText().toString()) && isValidhomephn(ph1.getText().toString()) && zip_postal(zip.getText().toString())) {
                    addAdressmy();
                }
            }
        });
        //prepareabc();




                String url = "https://nimako.lbyts.com/admin268hvyowt/apis/view_allcountry.php";
//

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.d(TAG,response+"");



                                Gson gson = new Gson();

                                 result1= gson.fromJson(response, CountrySer.class);

                                List<CountrySer> itemSer = new ArrayList<CountrySer>();
//                                list.add("select Country");
                                List<String> list = new ArrayList<>();
                                list.add("Select country *");

                                for (int i=1;i<result1.getCountry().size() ;i++) {


//                            items[i] =
                                    // items[i] = result1.getAddress().get(i).getAddreessId();
                                    //itemSer.add(result1);

                                    list.add(result1.getCountry().get(i).getCountryname());




                                }
                                CustomAdapterCountry customAdapter=new CustomAdapterCountry(getApplicationContext(),list);
                                //adapter = new ArrayAdapter<String>(Add_address_my_add.this, android.R.layout.simple_spinner_item, list);
                               // cont.setPrompt("Select Country");
                                cont.setAdapter(customAdapter);
//                                country.setId(Integer.parseInt(result1.getAddress().get(i).getAddreessId()));

                                cont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        Toast.makeText(Add_address_my_add.this, result1.getCountry().get(position).getIdcountry(), Toast.LENGTH_LONG).show();


                                        contid = result1.getCountry().get(position).getIdcountry();
                                        list1.clear();




                                        String url2 = "https://nimako.lbyts.com/admin268hvyowt/apis/view_states.php";
//

                                        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url2,
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {

                                                        Log.d(TAG,response+"");



                                                        Gson gson = new Gson();

                                                        result2= gson.fromJson(response, StateSer.class);

                                                        List<StateSer> itemSer = new ArrayList<StateSer>();
//                                list.add("select Country");



                                                        list1.add("Select state *");
                                                        for (int i=1;i<result2.getStates().size() ;i++) {



//                            items[i] =
                                                            // items[i] = result1.getAddress().get(i).getAddreessId();
                                                            //itemSer.add(result1);
                                                            list1.add(result2.getStates().get(i).getStateName());




                                                        }
                                                        CustomAdapterState customAdapter1=new CustomAdapterState(getApplicationContext(),list1);

                                                        //adapter = new ArrayAdapter<String>(Add_address_my_add.this, android.R.layout.simple_spinner_item, list1);
                                                        //stat.setPrompt("Select Country");
                                                        stat.setAdapter(customAdapter1);
//                                country.setId(Integer.parseInt(result1.getAddress().get(i).getAddreessId()));

                                                        stat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                            @Override
                                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                               if(position>0) {
                                                                   //Toast.makeText(Add_address_my_add.this, result2.getStates().get(position).getIdState(), Toast.LENGTH_LONG).show();
                                                                    idstate1=result2.getStates().get(position).getIdState();
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
                                                                Toast.makeText(Add_address_my_add.this,
                                                                        "Oops. Timeout Re-Try..!",
                                                                        Toast.LENGTH_LONG).show();

                                                            }else if (error.getClass().equals(NoConnectionError.class)){
                                                                Toast.makeText(Add_address_my_add.this,
                                                                        "Oops. Slow Internet Re-Try..!",
                                                                        Toast.LENGTH_LONG).show();
                                                            }else {
                                                                Toast.makeText(Add_address_my_add.this,error.toString(),Toast.LENGTH_LONG).show();
                                                            }
                                                        }else
                                                        {

                                                            Toast.makeText(Add_address_my_add.this,error.toString(),Toast.LENGTH_LONG).show();

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
                                                params.put("countryid",contid);

//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                                                // params.put("contact_number","8531552362");
                                                return params;
                                            }
                                        };

                                        RequestQueue requestQueue2 = Volley.newRequestQueue(Add_address_my_add.this);
                                        stringRequest2.setRetryPolicy(new RetryPolicy() {
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
                                        requestQueue2.add(stringRequest2);


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
                                        Toast.makeText(Add_address_my_add.this,
                                                "Oops. Timeout Re-Try..!",
                                                Toast.LENGTH_LONG).show();

                                    }else if (error.getClass().equals(NoConnectionError.class)){
                                        Toast.makeText(Add_address_my_add.this,
                                                "Oops. Slow Internet Re-Try..!",
                                                Toast.LENGTH_LONG).show();
                                    }else {
                                      //  Toast.makeText(Add_address_my_add.this,error.toString(),Toast.LENGTH_LONG).show();
                                    }
                                }else
                                {

                                    Toast.makeText(Add_address_my_add.this,error.toString(),Toast.LENGTH_LONG).show();

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
                      //  params.put("email","dkdesai1010@gmail.com");

//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                        // params.put("contact_number","8531552362");
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(Add_address_my_add.this);
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





    public void addAdressmy()
    {


        String url = "https://nimako.lbyts.com/admin268hvyowt/apis/addaddress.php";
//

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG,response+"");


                        Gson gson = new Gson();
                        AddSerializeAdd result=gson.fromJson(response,AddSerializeAdd.class);

                        try {
                            JSONObject jsonObject = new JSONObject(response);// Convert response string in to json object.
                            Toast.makeText(Add_address_my_add.this,result.getMessage() , Toast.LENGTH_LONG).show();




                            // JSONObject jsonLL = jsonObject.getJSONObject("status");// Get LL json object from jsonObject.
                            Integer status = jsonObject.getInt("status");
//
                            if(status==2) {
                                Toast.makeText(Add_address_my_add.this,"Enter proper" , Toast.LENGTH_LONG).show();

                            }
                            else if(status==0)
                            {
//                                email.setText("");
//                                pass.setText("");
                                Toast.makeText(Add_address_my_add.this,"Enter proper details",Toast.LENGTH_LONG).show();
                            }
                            else{
//                                FragmentManager fm=getFragmentManager();
//                                fm.beginTransaction().replace(R.id.contentframe,new MyAddress()).addToBackStack(null).commit();
                                Intent i=new Intent(Add_address_my_add.this,MyAddress.class);
                                i.putExtra("ee",emails);
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
                                Toast.makeText(Add_address_my_add.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            }else if (error.getClass().equals(NoConnectionError.class)){
                                Toast.makeText(Add_address_my_add.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(Add_address_my_add.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }else
                        {

                            Toast.makeText(Add_address_my_add.this,error.toString(),Toast.LENGTH_LONG).show();

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
                params.put("idcountry",contid);
                params.put("idstate",idstate1);
                params.put("id_customer",custid1+"");
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

                Log.d(TAG,"id country:"+contid);
                Log.d(TAG,"id state:"+idstate1);
                Log.d(TAG,"id customer:"+custid1);
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

        RequestQueue requestQueue = Volley.newRequestQueue(Add_address_my_add.this);
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

    private void Customerid() {



//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        int aaa = 12;





    }

    private boolean isValidfname(String fname) {
        if (fname != null && fname.length()>0) {
            return true;
        }
        return false;
    }
    private boolean isValidlname(String lname) {
        if (lname != null && lname.length()>0) {
            return true;
        }
        return false;
    }
    private boolean isValidaddress(String add) {
        if (add != null && add.length()>0) {
            return true;
        }
        return false;
    }
    private boolean zip_postal(String zip) {
        if (zip != null && zip.length()>0) {
            return true;
        }
        return false;
    }
    private boolean isValidcity(String city) {
        if (city != null && city.length()>0) {
            return true;
        }
        return false;
    }
    private boolean isValidcountry(int country) {
        if (country != 0 ) {
            return true;
        }
        return false;
    }
    private boolean isValidstate(int state) {
        if (state != 0) {
            return true;
        }
        return false;
    }
    private boolean isValidhomephn(String phn) {
        if (phn != null && phn.length()==10) {
            return true;
        }
        return false;
    }
    private boolean isValidtitle(String alias) {
        if (alias != null && alias.length()>0) {
            return true;
        }
        return false;
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





