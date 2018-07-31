package com.nimako;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import forgotpasswrod.Forgotpass_Ser;
import user.Customer_ser;

public class Login extends AppCompatActivity {
    TextView t1,Forgot;
    EditText e,p;
    Button b1;
    String email,password;


    // Session Manager Class
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        getSupportActionBar().hide();
        t1=(TextView)findViewById(R.id.signuplink);
        e=findViewById(R.id.email);
        p=findViewById(R.id.passw);
        Forgot = (TextView) findViewById(R.id.Forgetpasslink);
        sp=getSharedPreferences("zoomvanue",MODE_PRIVATE);

        if (sp.getString("emailid","").length()!=0){
            Intent intent=new Intent(getApplicationContext(),Navigation.class);
            startActivity(intent);
        }




        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Login.this,Register.class);
                startActivity(i);
            }
        });


        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowForgotPasswordDialog();
            }

            private void ShowForgotPasswordDialog() {


                final Dialog custom_dialog = new Dialog(Login.this);
                custom_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                custom_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                custom_dialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT));

                custom_dialog.setCancelable(false);
                custom_dialog.setCanceledOnTouchOutside(true);
                custom_dialog.setContentView(R.layout.custom_forgot_pass_dialog);

                final EditText ettempEmail = (EditText) custom_dialog
                        .findViewById(R.id.etEmail);
//                String eemail=ettempEmail.getText().toString();
                Button btnsendpassword = (Button) custom_dialog
                        .findViewById(R.id.btnsendpassword);

                btnsendpassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "https://nimako.lbyts.com/admin268hvyowt/apis/forgotpassword.php";

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Log.d("Response", response + "");


                                        Gson gson = new Gson();
                                        Forgotpass_Ser result2 = gson.fromJson(response, Forgotpass_Ser.class);
                                        String link=result2.getLink().toString();
//                                        Intent i = new Intent(Intent.ACTION_VIEW);
//                                        i.setData(Uri.parse(link));
//                                        startActivity(i);


                                        HttpAsyncTask hat = new HttpAsyncTask();
                                        hat.execute(link);

                                        ettempEmail.setText("");
                                        Toast.makeText(Login.this,"Check your email",Toast.LENGTH_LONG).show();


                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        if (error.networkResponse == null) {
                                            if (error.getClass().equals(TimeoutError.class)) {
                                                // Show timeout error message
                                                Toast.makeText(Login.this,
                                                        "Oops. Timeout Re-Try..!",
                                                        Toast.LENGTH_LONG).show();
                                                Intent intent = getIntent();
                                                finish();
                                                startActivity(intent);
                                            } else if (error.getClass().equals(NoConnectionError.class)) {
                                                Toast.makeText(Login.this,
                                                        "Oops. Slow Internet Re-Try..!",
                                                        Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();
                                            }
                                        } else {

                                            Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();

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
                                params.put("email", ettempEmail.getText().toString());

//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                                // params.put("contact_number","8531552362");
                                return params;
                            }
                        };

                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
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
                    });


                custom_dialog.show();

            }

        });

        b1=(Button)findViewById(R.id.btnlogin);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=e.getText().toString().trim();
                password=p.getText().toString().trim();

                if(!isValidEmail(email))
                {
                    Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                    er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                    e.setError("Enter Valid Email",er);
                    e.setText("");
                }
                if(!isValidPassword(password))
                {
                    Drawable er=(Drawable)getResources().getDrawable(R.drawable.error);
                    er.setBounds(0, 0, er.getIntrinsicWidth(), er.getIntrinsicHeight());
                    p.setError("Enter Valid Password",er);
                    p.setText("");
                }
                if(isValidPassword(password) && isValidEmail(email)){
                    doLogin();
                }
//                Intent i=new Intent(Login.this,Navigation.class);
//                startActivity(i);

            }
        });

    }


    private void doLogin(){


       // String url="http://nimako.in/api/customers/?output_format=JSON&filter[email]="+email+"&filter[passwd]=md5[YHirTBPArqWRDzS4whfk79DahBEASOtM6vbndWEnhQyQIdiSZqSeZeFI."+password+"]";
       //String url="http://divyangnaojas.com/nimako.in/admin268hvyowt/apis/loginapi.php";
        String url="https://nimako.lbyts.com/admin268hvyowt/apis/loginapi.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response",response+"");


                        Gson gson = new Gson();

                       Customer_ser result1 = gson.fromJson(response, Customer_ser.class);




                       try {
                            JSONObject jsonObject = new JSONObject(response);// Convert response string in to json object.

                           // JSONObject jsonLL = jsonObject.getJSONObject("status");// Get LL json object from jsonObject.
                           Integer status = jsonObject.getInt("status");

                           if(status==2) {
                               Toast.makeText(Login.this,"not found" , Toast.LENGTH_LONG).show();

                           }
                           else {
                               sp.edit().putString("emailid",email).commit();

                               Intent i=new Intent(Login.this,Navigation.class);
                               i.putExtra("email1",e.getText().toString());
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
                                Toast.makeText(Login.this,
                                        "Oops. Timeout Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }else if (error.getClass().equals(NoConnectionError.class)){
                                Toast.makeText(Login.this,
                                        "Oops. Slow Internet Re-Try..!",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(Login.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }else
                        {

                            Toast.makeText(Login.this,error.toString(),Toast.LENGTH_LONG).show();

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
                params.put("email",e.getText().toString());
                params.put("passwd",p.getText().toString());
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
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            return httpRequestResponse(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

        }
    }

    //For HttpAsync Functions: sending requests and receiving responses
    public static String httpRequestResponse(String url){
        InputStream inputStream = null;
        String result = "";
        try {
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));


            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert InputStream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "InputStream did not work";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }
}
