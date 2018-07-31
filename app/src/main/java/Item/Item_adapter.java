package Item;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.nimako.Add_address_my_add;
import com.nimako.Item_Main;
import com.nimako.MyAddress;
import com.nimako.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import countrystate.AddSerializeAdd;
import user.Customer_ser;
import wishlist.WishlistResSer;


/**
 * Created by dhruvildesai on 03/01/18.
 */

public class Item_adapter extends RecyclerView.Adapter<RecyclerViewHolderItemList> {
    private final String TAG = " Add to wishlist";
    int apri;
    float apricetemp,fprice,di,diamt;
    ProgressDialog pd;
    String email1,custid1;
    int productid;
    private List<Item_Ser> itemSerList;
    private Context context;
    String img;
    Bitmap myBitmap;
    public Item_adapter(Context context, List<Item_Ser> itemSerList) {
        this.itemSerList = itemSerList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolderItemList onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_item_card, null);
        RecyclerViewHolderItemList rcv = new RecyclerViewHolderItemList(layoutView);
        return rcv;
    }
    @Override
    public void onBindViewHolder(final RecyclerViewHolderItemList holder, final int position) {
        pd = new ProgressDialog(context);
        pd.setMessage("Downloading Image");
        final Item_Ser itemSer = itemSerList.get(position);
        holder.txtname1.setText(itemSerList.get(position).getProducts().get(position).getName());
        email1=itemSerList.get(position).getProducts().get(position).getEmail();


        di = Integer.parseInt(itemSerList.get(position).getProducts().get(position).getOnSale().toString());
        Log.d(TAG,"di:"+di);
        if (di == 0) {
            float price = Float.valueOf(itemSerList.get(position).getProducts().get(position).getPrice().toString());
            int mprice = (int) (Math.round(price));

            holder.price1.setText("₹" + mprice + "/mt");

            holder.aprice1.setVisibility(View.GONE);
            holder.dis1.setVisibility(View.GONE);
        } else if (itemSerList.get(position).getProducts().get(position).getOnSale() != null) {

             apricetemp = Float.valueOf(itemSerList.get(position).getProducts().get(position).getPrice());

            apri = (int) (Math.round(apricetemp));

//            fprice = (apri * di) / 100;

//
            float price = Float.valueOf(itemSerList.get(position).getProducts().get(position).getPrice().toString());
            float mprice = (int) (Math.round(price));
            float unitratio=Float.valueOf(itemSerList.get(position).getProducts().get(position).getUnitPriceRatio());
            fprice=price/unitratio;
            float fprice1=(int)(Math.round(fprice));

            diamt = Float.valueOf(mprice-fprice1);
            float aa=Float.valueOf(diamt/fprice1);
            di=Float.valueOf(aa*100);


            holder.price1.setText("₹" + (int)(Math.round(mprice)) + "/mt");
            holder.aprice1.setText("₹" + (int)(Math.round(fprice1)) + "/mt");
            holder.dis1.setText((int)(Math.round(di)) + "% off");
        }

        String url1 ="https://nimako.lbyts.com/api/customers/?output_format=JSON&filter[email]="+email1;

//       String url="http://toscanyacademy.com/blog/mp.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response + "");


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


                        custid1 = String.valueOf(result1.getCustomers().get(0).getId());
                        Log.d(TAG,custid1);

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

                            Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();

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




        holder.btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                productid=itemSerList.get(position).getProducts().get(position).getId();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure you want to add in Wishlist this Product?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // dialog.cancel();
                                addtowishlist();

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




//

        Picasso.with(context).load("https://nimako.lbyts.com/"+itemSerList.get(position).getProducts().get(position).getIdDefaultImage()+"/"+itemSerList.get(position).getProducts().get(position).getLinkRewrite()+".jpg").resize(200,200).into(holder.thumbnail);





        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Item_Main.class);
//
                i.putExtra("IID", itemSerList.get(position).getProducts().get(position).getId().toString());
                i.putExtra("Iname", itemSerList.get(position).getProducts().get(position).getName().toString());
                i.putExtra("Iprice", itemSerList.get(position).getProducts().get(position).getPrice().toString());
                i.putExtra("Idesc", itemSerList.get(position).getProducts().get(position).getDescription().toString());
                i.putExtra("unitratio", itemSerList.get(position).getProducts().get(position).getUnitPriceRatio().toString());
                i.putExtra("Ionsale", itemSerList.get(position).getProducts().get(position).getOnSale().toString());
              //  i.putExtra("Ishowprice", Integer.parseInt(result1.getProducts().get(position).getShowPrice().toString()));

                context.startActivity(i);


//                Toast.makeText(context,result1.getProducts().get(position).getName()+" is selected",Toast.LENGTH_LONG).show();
//                FragmentManager fm=getFragmentManager();
//                fm.beginTransaction().replace(R.id.contentframe,new SubCategory_Main()).commit();
//
            }
        });
        holder.relitemlist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Item_Main.class);
//
                i.putExtra("IID", itemSerList.get(position).getProducts().get(position).getId().toString());
                i.putExtra("Iname", itemSerList.get(position).getProducts().get(position).getName().toString());
                i.putExtra("Iprice", itemSerList.get(position).getProducts().get(position).getPrice().toString());
                i.putExtra("Idesc", itemSerList.get(position).getProducts().get(position).getDescription().toString());
                i.putExtra("Iwholeprice", itemSerList.get(position).getProducts().get(position).getWholesalePrice().toString());
                i.putExtra("Ionsale", itemSerList.get(position).getProducts().get(position).getOnSale().toString());
                //  i.putExtra("Ishowprice", Integer.parseInt(result1.getProducts().get(position).getShowPrice().toString()));

                context.startActivity(i);


//                Toast.makeText(context,result1.getProducts().get(position).getName()+" is selected",Toast.LENGTH_LONG).show();
//                FragmentManager fm=getFragmentManager();
//                fm.beginTransaction().replace(R.id.contentframe,new SubCategory_Main()).commit();
//
            }
        });



        // holder.thumbnail.setImageURI(Uri.parse(new File("http://nimako.in/api/images/products/8/"+itemSerList.get(position).getProducts().get(position).getIdDefaultImage()).toString()));
//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });
    }
    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd.show();
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            pd.dismiss();
            bmImage.setImageBitmap(result);
        }
    }
    @Override
    public int getItemCount() {
        return this.itemSerList.size();
    }



    public void addtowishlist()
    {

        String url = "https://nimako.lbyts.com/admin268hvyowt/apis/add2wishlist.php";
//

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG,response+"");


                        Gson gson = new Gson();
                        WishlistResSer result=gson.fromJson(response, WishlistResSer.class);

                        try {
                            JSONObject jsonObject = new JSONObject(response);// Convert response string in to json object.
                            Toast.makeText(context,result.getWishlist().getMessage() , Toast.LENGTH_LONG).show();







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

                            Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();

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

                params.put("productid",String.valueOf(productid));

                params.put("custid",custid1+"");
                Log.d("Add to wisis","Add to wishlist button click:product id"+String.valueOf(productid)+"and custmoer id:"+custid1+"");


                Log.d(TAG,"id product:"+productid+"");
                Log.d(TAG,"id customer:"+custid1);

                return params;
            }
        };

        RequestQueue requestQueue1 = Volley.newRequestQueue(context);
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
    }
}






