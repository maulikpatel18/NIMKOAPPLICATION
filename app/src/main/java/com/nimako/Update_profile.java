package com.nimako;

import android.app.DatePickerDialog;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import countrystate.CountrySer;
import countrystate.StateSer;
import countrystate.UpdateSerializeAdd;
import user.UpdateSerializeProfile;

public class Update_profile extends AppCompatActivity {
    private final String TAG = "Update profile";

    Button savel;
    int newsc,genc;
    int optionc;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    EditText fname, lname,email,bdate,cpass,npass,conpass;
    String fname1, lname1,email1,custid,sletter,otpion,bdate1,gender;
    RadioGroup rad;
    RadioButton male,female;
    CheckBox c1,c2;
    final Calendar myCalendar = Calendar.getInstance();
    int ccid,ssid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        fname1 = i.getStringExtra("fname");
        lname1 = i.getStringExtra("lname");
        email1 = i.getStringExtra("email");
        custid = i.getStringExtra("custid");
        sletter = i.getStringExtra("sletter");
        otpion = i.getStringExtra("option");
        bdate1 = i.getStringExtra("bdate");
        gender = i.getStringExtra("gender");





        // contid1 = 110;

        savel = findViewById(R.id.Savebtn);
        rad = (RadioGroup) findViewById(R.id.gender);
        male=findViewById(R.id.maleradio);
        female=findViewById(R.id.femailradio);
        c1 = findViewById(R.id.check1);
        c2 = findViewById(R.id.check2);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        bdate = findViewById(R.id.bdate);
        cpass = findViewById(R.id.curtpass);
        npass = findViewById(R.id.newpass);
        conpass = findViewById(R.id.confpass);

        fname.setText(fname1);
        lname.setText(lname1);
        email.setText(email1);
        bdate.setText(bdate1);
        if(sletter.equals("0"))
        {
            c1.setChecked(false);
        }
        else
        {
            c1.setChecked(true);
        }
        if(otpion.equals("0"))
        {
            c2.setChecked(false);

        }
        else {
            c2.setChecked(true);
        }
        if(gender.equals("Male"))
        {
            male.setChecked(true);
            female.setChecked(false);
        }
        else {
            male.setChecked(false);
            female.setChecked(true);
        }


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        bdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Update_profile.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                bdate.setError(null);
            }
        });




        fname.setText(fname1);
        lname.setText(lname1);

        savel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (c2.isChecked()==true) {
                    optionc = 1;


                } else {
                    optionc = 0;

                }

                if (c1.isChecked()==true) {
                    newsc = 1;

                } else {
                    newsc = 0;


                }
//        int selectedId = rad.getCheckedRadioButtonId();
//        RadioButton rad1 = (RadioButton) findViewById(selectedId);
//        String genaaa = rad1.getText().toString();
                if (male.isChecked()==true) {
                    genc = 0;
                } else if(female.isChecked()==true){
                    genc = 1;
                }

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
                if(!isValidbdate(bdate.getText().toString()))
                {
                    Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                    er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                    bdate.setError("Birthdate is required",er);
                }
                if (!isValidEmail(email.getText().toString())) {
                    Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                    er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                    email.setError("Invalid Email",er);
                    email.setText("");
                }
                if (!isValidPassword(cpass.getText().toString())) {
                    Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                    er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                    cpass.setError("Invalid Password",er);
                    cpass.setText("");
                }

                if(isValidfname(fname.getText().toString()) && isValidlname(lname.getText().toString()) && isValidbdate(bdate.getText().toString()) && isValidEmail(email.getText().toString()))
                {
                    if(isValidPassword(cpass.getText().toString()))
                    {
                        UpdatemyAdd();
                    }
                    else
                    {
                        Toast.makeText(Update_profile.this,"Please Enter password to change setting",Toast.LENGTH_LONG).show();
                    }
                }else
                {
                    Toast.makeText(Update_profile.this,"Please Enter proper Details",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        bdate.setText(sdf.format(myCalendar.getTime()));
        Log.d("date","Date:"+sdf.format(myCalendar.getTime()));

//        String myFormat1 = "ddMMyyyy"; //In which you need put here
//        SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);
//
//        bdateinac=sdf1.format(myCalendar.getTime());
//
//        Log.d("date","Date:"+bdateinac);
    }


    public void UpdatemyAdd()
    {


        String url = "https://nimako.lbyts.com/admin268hvyowt/apis/editprofile.php";
//

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG,response+"");



                        Gson gson = new Gson();
                        UpdateSerializeProfile result=gson.fromJson(response,UpdateSerializeProfile.class);

                        try {
                            JSONObject jsonObject = new JSONObject(response);// Convert response string in to json object.
                            // Toast.makeText(Update_address_my_add.this,result.getMessage() , Toast.LENGTH_LONG).show();
                               //JSONObject jsonLL = jsonObject.getJSONObject("status");// Get LL json object from jsonObject.
//                            String status = jsonObject.getString("Status");
                            String status = result.getUpdateProfile().getStatus().toString();

                          if(status.equals("0"))
                            {
//                                email.setText("");
//                                pass.setText("");
                                Toast.makeText(Update_profile.this,"Enter proper details",Toast.LENGTH_LONG).show();
                            }
                            else {
//                                FragmentManager fm=getFragmentManager();
//                                fm.beginTransaction().replace(R.id.contentframe,new MyAddress()).addToBackStack(null).commit();
                                Intent i=new Intent(Update_profile.this,MyPersonalInfo.class);
                              i.putExtra("ee",email1);
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
                                Toast.makeText(Update_profile.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();

                            }else if (error.getClass().equals(NoConnectionError.class)){
                                Toast.makeText(Update_profile.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(Update_profile.this,error.toString(),Toast.LENGTH_LONG).show();
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

                params.put("fname",fname.getText().toString());
                params.put("lname",lname.getText().toString());
                params.put("email",email.getText().toString());
                params.put("gender",genc+"");
                params.put("UserID",custid);
                params.put("DOB",bdate.getText().toString());
                params.put("sletter",newsc+"");
                params.put("soffer",optionc+"");
                params.put("cpwd",cpass.getText().toString());
                if(!npass.getText().toString().equals("")||!conpass.getText().toString().equals("")) {
                params.put("npwd",npass.getText().toString());
                params.put("rpwd",conpass.getText().toString());
                }
                Log.e(TAG,"fname:"+fname.getText().toString());
                Log.e(TAG,"lname:"+lname.getText().toString());
                Log.e(TAG,"email:"+email.getText().toString());
                Log.e(TAG,"gender:"+genc+"");
                Log.e(TAG,"user id:"+custid);
                Log.e(TAG,"dob:"+bdate.getText().toString());
                Log.e(TAG,"sletter:"+newsc+"");
                Log.e(TAG,"soffer:"+optionc+"");
                Log.e(TAG,"cpwd:"+cpass.getText().toString());
                if(!npass.getText().toString().equals("")||!conpass.getText().toString().equals("")) {
                    Log.e(TAG, "npwd:" + npass.getText().toString());
                    Log.e(TAG, "confpwd:" + conpass.getText().toString());

                }


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Update_profile.this);
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

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
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
    private boolean isValidbdate(String date) {
        if (date != null && date.length()>0) {
            return true;
        }
        return false;
    }
    }



//