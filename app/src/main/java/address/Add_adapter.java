package address;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.bumptech.glide.Glide;
import com.nimako.MyAddress;
import com.nimako.R;
import com.nimako.Update_address_my_add;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import countrystate.UpdateSerializeAdd;

/**
 * Created by dhruvildesai on 02/02/18.
 */

public class Add_adapter extends RecyclerView.Adapter<Recyclerviewaddholder> {
    private List<Add_ser> itemAList;
    private final String TAG = "Delete Add";
    private Context context;
    String addid,email1;
    HashMap<String, String> resultp = new HashMap<String, String>();
    public Add_adapter(Context context, List<Add_ser> itemAList)
    {
        this.itemAList=itemAList;
        this.context=context;
    }
    @Override
    public Recyclerviewaddholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_myadd_card,null);
        Recyclerviewaddholder rcv=new Recyclerviewaddholder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(Recyclerviewaddholder holder, final int position) {

        // Categories_Ser category = itemList.get(position);
//        holder.txtname.setText(itemList.get(position).getName());
//


        holder.title.setText(itemAList.get(position).getAddress().get(position).getAlias());

        final String fname = itemAList.get(position).getAddress().get(position).getFirstname();
        final String lname = itemAList.get(position).getAddress().get(position).getLastname();
        final String addlin1 = itemAList.get(position).getAddress().get(position).getAddress1();
        final String addline2 = itemAList.get(position).getAddress().get(position).getAddress2();
        final String city = itemAList.get(position).getAddress().get(position).getCity();
        final String country = itemAList.get(position).getAddress().get(position).getCountry();
        final String postal = itemAList.get(position).getAddress().get(position).getPostcode();
        final String mob1 = itemAList.get(position).getAddress().get(position).getPhoneMobile();
        final String mob2 = itemAList.get(position).getAddress().get(position).getPhone();
         email1 = itemAList.get(position).getAddress().get(position).getEmail();
         Log.d(TAG,"email la ahiya:"+email1);
        Log.d(TAG,"email la :"+mob2);
        holder.addtxt.setText(fname + " " + lname + "\n" + addlin1 + "\n" + addline2 + "\n" + city + " " + postal + "\n" + country + "\n" + mob1 + "\n " + mob2);

        addid=itemAList.get(position).getAddress().get(position).getAddreessId();
        holder.editadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Update_address_my_add.class);
                i.putExtra("fname", fname);
                i.putExtra("lname", lname);
                i.putExtra("add1", addlin1);
                i.putExtra("add2", addline2);
                i.putExtra("city", city);
                i.putExtra("country", country);
                i.putExtra("postal", postal);
                i.putExtra("mob1", mob1);
                i.putExtra("mob2", mob2);
                i.putExtra("alias", itemAList.get(position).getAddress().get(position).getAlias());
                i.putExtra("addid", itemAList.get(position).getAddress().get(position).getAddreessId());
                 i.putExtra("countryid",itemAList.get(position).getAddress().get(position).getCountryID());
                 i.putExtra("stateid",itemAList.get(position).getAddress().get(position).getStateid());
                i.putExtra("countrytxt",itemAList.get(position).getAddress().get(position).getCountry());
                i.putExtra("statetxt",itemAList.get(position).getAddress().get(position).getStateName());
                i.putExtra("custid", itemAList.get(position).getAddress().get(position).getCustomerId());
                i.putExtra("company",itemAList.get(position).getAddress().get(position).getCompany());
                i.putExtra("other",itemAList.get(position).getAddress().get(position).getOther());
                i.putExtra("ee", itemAList.get(position).getAddress().get(position).getEmail());
                context.startActivity(i);

            }
        });

        holder.deleteadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure you want to delete this address?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // dialog.cancel();
//                                DeleteAdd();
                                String url = "https://nimako.lbyts.com/admin268hvyowt/apis/deleteaddress.php";
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
                                                    Toast.makeText(context,result.getMessage() , Toast.LENGTH_LONG).show();
                                                    Toast.makeText(context,addid , Toast.LENGTH_LONG).show();
                                                    //   JSONObject jsonLL = jsonObject.getJSONObject("status");// Get LL json object from jsonObject.
                                                    Integer status = jsonObject.getInt("Status");

                                                    if(status==2) {
                                                        Toast.makeText(context,"Enter proper" , Toast.LENGTH_LONG).show();

                                                    }
                                                    else if(status==0)
                                                    {
//                                email.setText("");
//                                pass.setText("");
                                                        Toast.makeText(context,"Enter proper details",Toast.LENGTH_LONG).show();
                                                    }
                                                    else{
//                                FragmentManager fm=getFragmentManager();
//                                fm.beginTransaction().replace(R.id.contentframe,new MyAddress()).addToBackStack(null).commit();

                                                        Intent i=new Intent(context,MyAddress.class);
                                                        i.putExtra("ee",email1);
                                                        Log.d(TAG,"email after delete:"+email1);
                                                        context.startActivity(i);

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
                                                        Toast.makeText(context,
                                                                "Oops. Timeout Re-Try..!",
                                                                Toast.LENGTH_LONG).show();

                                                    }else if (error.getClass().equals(NoConnectionError.class)){
                                                        Toast.makeText(context,
                                                                "Oops. Slow Internet Re-Try..!",
                                                                Toast.LENGTH_LONG).show();
                                                    }else {
                                                        Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
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
                                        params.put("addressID",itemAList.get(position).getAddress().get(position).getAddreessId().toString());


//                params.put("ip_address",fip);
//                params.put("contact_number",et_sumno.getText().toString());
                                        // params.put("contact_number","8531552362");
                                        return params;
                                    }
                                };

                                RequestQueue requestQueue = Volley.newRequestQueue(context);
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

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
    }

    public void DeleteAdd()
    {







    }




//        holder.title.setText(itemList.get(position).getLastname());

        // loading album cover using Glide library
        // Glide.with(context).load(itemList.get(position).getThumbnail()).into(holder.imageview);

//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });

    @Override
    public int getItemCount() {
        return this.itemAList.size();
    }
}
