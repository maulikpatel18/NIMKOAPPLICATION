package com.nimako;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import user.Customer_ser;

public class Register extends AppCompatActivity {
    RadioButton r1,r2;
    EditText fname,lname,bdate,email,pass;
    CheckBox c1,c2;
    Button b1;
    RadioGroup rad;
    String bdateinac;
    TextView t1;
    final Calendar myCalendar = Calendar.getInstance();
     int newsc,genc;
     int optionc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        t1 = findViewById(R.id.backtologin);
//        r1=findViewById(R.id.maleradio);
//        r2=findViewById(R.id.femailradio);
        fname = findViewById(R.id.fname);
        rad = (RadioGroup) findViewById(R.id.gender);
        lname = findViewById(R.id.lname);
        bdate = findViewById(R.id.bdate);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        c1 = findViewById(R.id.check1);
        c2 = findViewById(R.id.check2);

//
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
                new DatePickerDialog(Register.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                bdate.setError(null);
            }
        });
//        if(r1.isChecked())
//        {genc="0";
//            Log.d("option","gender ninmaako:"+genc);}
//        else
//        {genc="1";
//            Log.d("option","gender ninmaako:"+genc);}
//        if(c1.isChecked())
//        {
//         newsc="1";
//            Log.d("news","news ninmaako:"+newsc);
//        }
//        else
//        {
//         newsc="0";
//            Log.d("option","news ninmaako:"+newsc);
//
//        }
//        if(c2.isChecked())
//        {
//            optionc="1";
//            Log.d("option","option ninmaako:"+optionc);
//
//
//        }
//        else{ optionc="0";
//            Log.d("option","option ninmaako:"+optionc);}
            b1 = findViewById(R.id.btnreg);
            b1.setOnClickListener(new View.OnClickListener() {
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
                    if (!isValidPassword(pass.getText().toString())) {
                        Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                        er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                        pass.setError("Invalid Password",er);
                        pass.setText("");
                    }

                    if(isValidfname(fname.getText().toString()) && isValidlname(lname.getText().toString()) && isValidbdate(bdate.getText().toString()) && isValidEmail(email.getText().toString()) && isValidPassword(pass.getText().toString()))
                    {
                        if (c2.isChecked()) {
                            optionc = 1;
                            Log.d("option", "option ninmaako:" + optionc);


                        } else {
                            optionc = 0;
                            Log.d("option", "option ninmaako:" + optionc);
                        }

                        if (c1.isChecked()) {
                            newsc = 1;
                            Log.d("news", "news ninmaako:" + newsc);
                        } else {
                            newsc = 0;
                            Log.d("option", "news ninmaako:" + newsc);

                        }


                        int selectedId = rad.getCheckedRadioButtonId();

                        // find the radiobutton by returned id
                        RadioButton rad1 = (RadioButton) findViewById(selectedId);
//                Toast.makeText(Register.this,rad1.getText(), Toast.LENGTH_SHORT).show();

                        String genaaa = rad1.getText().toString();
                        if (genaaa.equals("Male")) {
                            genc = 0;
                            // Log.d("genderyo","genderyo:"+genaaa);
                        } else {
                            genc = 1;
                        }
                        Log.d("genderyo", "genderyo:" + genc);
                        doRegister();
                    }else
                    {
                        Toast.makeText(Register.this,"Please Enter proper Details",Toast.LENGTH_LONG).show();
                    }
                }

            });
            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Register.this, Login.class);
                    startActivity(i);
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

    private void doRegister(){


        String url="https://nimako.lbyts.com/admin268hvyowt/apis/insert_customer.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response",response+"");


                        Gson gson = new Gson();

                        Customer_ser result1 = gson.fromJson(response, Customer_ser.class);
//
//                        if(result1==null) {
//                            Toast.makeText(Register.this,"not found" , Toast.LENGTH_LONG).show();
//
//                        }
//                        else {
//                            Toast.makeText(Register.this,result1.getCustomers().get(0).getId().toString(), Toast.LENGTH_LONG).show();
//
//                        }
                        try {
                            JSONObject jsonObject = new JSONObject(response);// Convert response string in to json object.

                            // JSONObject jsonLL = jsonObject.getJSONObject("status");// Get LL json object from jsonObject.
                            Integer status = jsonObject.getInt("status");

                            if(status==2) {
                                Toast.makeText(Register.this,"Enter proper" , Toast.LENGTH_LONG).show();

                            }
                            else if(status==0)
                            {
                                email.setText("");
                                pass.setText("");
                                Toast.makeText(Register.this,"Enter proper details",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Intent i=new Intent(Register.this,Login.class);
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
                                Toast.makeText(Register.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }else if (error.getClass().equals(NoConnectionError.class)){
                                Toast.makeText(Register.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(Register.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }else
                        {

                            Toast.makeText(Register.this,error.toString(),Toast.LENGTH_LONG).show();

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
                params.put("gender",genc+"");
                params.put("firstname",fname.getText().toString());
                params.put("lastname",lname.getText().toString());
                params.put("email",email.getText().toString());
                params.put("password",pass.getText().toString());
                params.put("birthday",bdate.getText().toString());
                params.put("newsletter",newsc+"");
                params.put("optin",optionc+"");
//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                // params.put("contact_number","8531552362");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
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
