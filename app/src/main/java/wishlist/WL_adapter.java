package wishlist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
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
import com.nimako.ItemList;
import com.nimako.Item_Main;
import com.nimako.MyAddress;
import com.nimako.R;
import com.nimako.Wishlist_main;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Item.Item_Ser;
import Item.Item_adapter;
import countrystate.UpdateSerializeAdd;
import single_product.Single_Pro_Ser;

/**
 * Created by dhruvildesai on 02/02/18.
 */

public class WL_adapter extends RecyclerView.Adapter<Recyclerviewwishholder> {
    private final String TAG="wishlist";
    Single_Pro_Ser result1;
    Cate_Subcate_Ser result2;
    int apri,proid1;

    float apricetemp,fprice,di,diamt;
    private List<WL_ser> itemList;
    private Context context;
    String pro_id,email1,custid,wishid;
    String custid1;
    public WL_adapter(Context context, List<WL_ser> itemList)
    {
        this.itemList=itemList;
        this.context=context;
    }
    @Override
    public Recyclerviewwishholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_wishlist_card,null);
        Recyclerviewwishholder rcv=new Recyclerviewwishholder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final Recyclerviewwishholder holder, final int position) {

        // Categories_Ser category = itemList.get(position);
//        holder.txtname.setText(itemList.get(position).getName());


        for (int i = 0; i < itemList.size(); i++) {
            pro_id = itemList.get(position).getWhishlist().get(position).getProductId();
            email1=itemList.get(position).getWhishlist().get(position).getEmail();
            custid=itemList.get(position).getWhishlist().get(position).getCustid();
            wishid=itemList.get(position).getWhishlist().get(position).getIdWhishlist();
            RequestQueue queue = Volley.newRequestQueue(context);


            String url = "https://nimako.lbyts.com/api/products/" + pro_id + "?output_format=JSON";
//       String url="http://toscanyacademy.com/blog/mp.php";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "single product mate" + response);
                    Gson gson = new Gson();
                    result1 = gson.fromJson(response, Single_Pro_Ser.class);

                    //result1.getCategories().get(i).getId();
                    String proidaa=result1.getProduct().getId().toString();
                    String proname = result1.getProduct().getName();
                    String proimgid = result1.getProduct().getIdDefaultImage();
                    String pro_lr = result1.getProduct().getLinkRewrite();
                    String categoid = result1.getProduct().getIdCategoryDefault();
                    Log.d(TAG, "Category id:" + categoid);

                    RequestQueue queue = Volley.newRequestQueue(context);

                    String url1 = "https://nimako.lbyts.com/api/categories/?display=[id,id_parent,name]&filter[id]=[" + categoid + "]&output_format=JSON";
//       String url="http://toscanyacademy.com/blog/mp.php";

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "single product mate" + response);
//
                            Gson gson = new Gson();
                            result2 = gson.fromJson(response, Cate_Subcate_Ser.class);
                            String maincate = result2.getCategories().get(0).getIdParent();
                            String subcatename = result2.getCategories().get(0).getName();
                            holder.subctaname.setText(subcatename);

                            RequestQueue queue = Volley.newRequestQueue(context);

                            String url1 = "https://nimako.lbyts.com/api/categories/?display=[id,id_parent,name]&filter[id]=[" + maincate + "]&output_format=JSON";
//       String url="http://toscanyacademy.com/blog/mp.php";

                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d(TAG, "single product mate" + response);
//
                                    Gson gson = new Gson();
                                    result2 = gson.fromJson(response, Cate_Subcate_Ser.class);
                                    String catename = result2.getCategories().get(0).getName();
                                    holder.ctaname.setText(catename);
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


                    holder.txtname.setText(proname);
                    holder.productid.setText(proidaa);
                    holder.custid.setText(custid);
                    di = Integer.parseInt(result1.getProduct().getOnSale().toString());
                    Log.d(TAG, "di:" + di);
                    if (di == 0) {
                        float price = Float.valueOf(result1.getProduct().getPrice().toString());
                        int mprice = (int) (Math.round(price));

                        holder.price1.setText("₹" + mprice + "/mt");

                        holder.aprice1.setVisibility(View.GONE);
                        holder.dis1.setVisibility(View.GONE);
                    } else if (result1.getProduct().getOnSale() != null) {

                        apricetemp = Float.valueOf(result1.getProduct().getPrice());

                        apri = (int) (Math.round(apricetemp));


                        float unitratio=Float.valueOf(result1.getProduct().getUnitPriceRatio());
                        float price = Float.valueOf(result1.getProduct().getPrice().toString());
                        float mprice = (int) (Math.round(price));
                        fprice=price/unitratio;
                        float fprice1 = (int) (Math.round(fprice));

                        diamt = Float.valueOf(mprice - fprice1);
                        Log.d(TAG, "Diamt:" + mprice);
                        float aa = Float.valueOf(diamt / fprice1);
                        Log.d(TAG, "dimain:" + aa);
                        di = Float.valueOf(aa * 100);
                        Log.d(TAG, "diin%:" + di);


                        holder.price1.setText("₹" + (int) (Math.round(mprice)) + "/mt");
                        holder.aprice1.setText("₹" + (int) (Math.round(fprice1)) + "/mt");
                        holder.dis1.setText((int) (Math.round(di)) + "% off");
                    }
                    Picasso.with(context).load("https://nimako.lbyts.com/" + proimgid + "/" + pro_lr + ".jpg").resize(200, 200).into(holder.imageview);


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
        }

        holder.addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(context);
                String url = "https://nimako.lbyts.com/api/products/" + itemList.get(position).getWhishlist().get(position).getProductId() + "?output_format=JSON";
//       String url="http://toscanyacademy.com/blog/mp.php";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "single product mate" + response);
                        Gson gson = new Gson();
                        Single_Pro_Ser result31 = gson.fromJson(response, Single_Pro_Ser.class);

                        //result1.getCategories().get(i).getId();
                        String proidaa=result1.getProduct().getId().toString();
                        String proname = result1.getProduct().getName();

                        Intent i=new Intent(context, Item_Main.class);
                        i.putExtra("IID", result31.getProduct().getId().toString());
                        i.putExtra("Iname", result31.getProduct().getName().toString());
                        i.putExtra("Iprice", result31.getProduct().getPrice().toString());
                        i.putExtra("Idesc", result31.getProduct().getDescription().toString());
                        i.putExtra("unitratio",result31.getProduct().getUnitPriceRatio().toString());
                        i.putExtra("Ionsale",result31.getProduct().getOnSale().toString());
                        context.startActivity(i);
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

            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure you want to Remove this Product?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // dialog.cancel();
                                proid1=Integer.parseInt(holder.productid.getText().toString());
                                custid1=holder.custid.getText().toString();
//                                DeleteAWish();
                                String url = "https://nimako.lbyts.com/admin268hvyowt/apis/deletewishlist.php";
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
                                                   // Toast.makeText(context,result.getMessage() , Toast.LENGTH_LONG).show();

                                                    //   JSONObject jsonLL = jsonObject.getJSONObject("status");// Get LL json object from jsonObject.
                                                    Integer status = jsonObject.getInt("Status");

                                                    if(status==2) {
                                                        //Toast.makeText(context,"Enter proper" , Toast.LENGTH_LONG).show();

                                                    }
                                                    else if(status==0)
                                                    {
//                                email.setText("");
//                                pass.setText("");
                                                        Toast.makeText(context,"Unable to delete",Toast.LENGTH_LONG).show();
                                                    }
                                                    else{
//                                FragmentManager fm=getFragmentManager();
//                                fm.beginTransaction().replace(R.id.contentframe,new MyAddress()).addToBackStack(null).commit();

                                                        Intent i=new Intent(context,Wishlist_main.class);
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
//                                                        Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
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
                                        params.put("customerID",custid1);
                                        params.put("wishlistID",itemList.get(position).getWhishlist().get(position).getIdWhishlist().toString());

                                        Log.d(TAG,"customerID:"+custid);
                                        Log.d(TAG,"wishlistid:"+itemList.get(position).getWhishlist().get(position).getIdWhishlist().toString()+"");
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
        // loading album cover using Glide library
        // Glide.with(context).load(itemList.get(position).getThumbnail()).into(holder.imageview);

//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });

    public void DeleteAWish()
    {








    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
