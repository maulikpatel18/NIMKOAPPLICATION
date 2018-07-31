package yourorder.wishlist;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Environment;
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
import com.nimako.Item_Main;
import com.nimako.Order_main;
import com.nimako.R;
import com.nimako.Wishlist_main;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import countrystate.UpdateSerializeAdd;
import single_product.Single_Pro_Ser;

/**
 * Created by dhruvildesai on 02/02/18.
 */

public class Order_adapter extends RecyclerView.Adapter<Recyclervieworderholder> {
    private final String TAG="Order history";
    private List<Order_ser> itemList;
    private Context context;
    String email1,custid,statusor;
    String orderid,invoiceno;
    String custid1;
    ProgressDialog pDialog;
    public Order_adapter(Context context, List<Order_ser> itemList)
    {
        this.itemList=itemList;
        this.context=context;
    }
    @Override
    public Recyclervieworderholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_order_card,null);
        Recyclervieworderholder rcv=new Recyclervieworderholder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final Recyclervieworderholder holder, final int position) {




//        for (int i = 0; i < itemList.size(); i++) {
            holder.orderref1.setText(itemList.get(position).getOrderHistory().get(position).getReference().toString());
            holder.date1.setText(itemList.get(position).getOrderHistory().get(position).getDateAdd().toString());
            float abc=Float.parseFloat(itemList.get(position).getOrderHistory().get(position).getTotalPaid().toString());
            holder.totalpr1.setText("Rs. "+new DecimalFormat("##.##").format(abc));
            holder.paytype1.setText(itemList.get(position).getOrderHistory().get(position).getPayment().toString());
            statusor=itemList.get(position).getOrderHistory().get(position).getIdOrderState().toString();
             orderid=itemList.get(position).getOrderHistory().get(position).getIdOrder().toString();
             invoiceno=itemList.get(position).getOrderHistory().get(position).getInvoiceNumber().toString();



            RequestQueue queue = Volley.newRequestQueue(context);


            String url = "https://nimako.lbyts.com/api/order_states/"+statusor+"?output_format=JSON";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, " your order:" + response);
                    Gson gson = new Gson();
                    StatusOrdSer result1 = gson.fromJson(response, StatusOrdSer.class);
                    holder.statusor1.setTextColor(Color.parseColor(result1.getOrderState().getColor().toString()));
                    holder.statusor1.setText(result1.getOrderState().getName().toString());

                    if(holder.statusor1.getText().equals("Canceled") || holder.statusor1.getText().equals("Payment error") )
                    {
                        holder.cancle.setVisibility(View.GONE);
                        holder.pdf.setVisibility(View.GONE);
                    }
                    else {
                        holder.buttonlayer.setVisibility(View.VISIBLE);
                        holder.cancle.setVisibility(View.VISIBLE);
                        holder.pdf.setVisibility(View.VISIBLE);
                    }


                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "Error" + error.getMessage());

                }
            })

            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    String credentials = "nimakointhefabricstoreofyear2018:";
                    String auth = "Basic "
                            + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization", auth);
                    return headers;
                }
            };

            queue.add(stringRequest);

            holder.pdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pDialog = new ProgressDialog(context);
                    pDialog.setMessage("Please wait...");
                    pDialog.setCancelable(false);


                    new DownloadFile().execute("https://nimako.lbyts.com/index.php?controller=pdf-invoice&id_order="+orderid, "IN0000"+invoiceno+".pdf");
                    Log.e(TAG,"link orderid:"+orderid);

                }
            });

            holder.cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Are you sure you want to cancle this Order?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // dialog.cancel();


//                                    DeleteAorder();
                                    String url = "https://nimako.lbyts.com/admin268hvyowt/apis/CancelOrder.php";
//

                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {

                                                    Log.d(TAG,response+"");



                                                    Gson gson = new Gson();
                                                    OrderResSer result=gson.fromJson(response,OrderResSer.class);

                                                    try {
                                                        JSONObject jsonObject = new JSONObject(response);// Convert response string in to json object.
                                                        Toast.makeText(context,result.getOrderState().getMessage() , Toast.LENGTH_LONG).show();

                                                        //   JSONObject jsonLL = jsonObject.getJSONObject("status");// Get LL json object from jsonObject.
//                            Integer status = jsonObject.getInt("Status");
                                                        String status=result.getOrderState().getStatus().toString();

                                                        if(status.equals(false)) {
                                                            //Toast.makeText(context,"Enter proper" , Toast.LENGTH_LONG).show();

                                                        }
                                                        else{
//                                FragmentManager fm=getFragmentManager();
//                                fm.beginTransaction().replace(R.id.contentframe,new MyAddress()).addToBackStack(null).commit();

                                                            Intent i=new Intent(context,Order_main.class);
                                                            i.putExtra("ee",email1);
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
                                            params.put("orderid",itemList.get(position).getOrderHistory().get(position).getIdOrder().toString());


                                            Log.d(TAG,"order id passss::"+itemList.get(position).getOrderHistory().get(position).getIdOrder().toString());
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
//        }
    }
        // loading album cover using Glide library
        // Glide.with(context).load(itemList.get(position).getThumbnail()).into(holder.imageview);

//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });
private class DownloadFile extends AsyncTask<String, Void, Void> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showpDialog();
    }

    @Override
    protected Void doInBackground(String... strings) {

        String fileUrl = strings[0];
// -> https://letuscsolutions.files.wordpress.com/2015/07/five-point-someone-chetan-bhagat_ebook.pdf
        String fileName = strings[1];
// ->five-point-someone-chetan-bhagat_ebook.pdf
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(extStorageDirectory, "NIMAKOINVOICE");
        folder.mkdir();

        File pdfFile = new File(folder, fileName);

        try{
            pdfFile.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        FileDownloader.downloadFile(fileUrl, pdfFile);
        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        hidepDialog();
        Toast.makeText(context, "Download PDF successfully", Toast.LENGTH_SHORT).show();

        Log.e("Download complete", "complete----------");
    }
}


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }



    public void DeleteAorder()
    {




    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
