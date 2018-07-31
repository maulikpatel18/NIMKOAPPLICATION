package Paymentstep;

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
import android.widget.ArrayAdapter;
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
import com.nimako.Cart_main;
import com.nimako.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cart.CombiSer;
import cart.OptValser;
import single_product.Single_Pro_Ser;
import wishlist.Cate_Subcate_Ser;
import wishlist.WishlistResSer;

/**
 * Created by dhruvildesai on 02/02/18.
 */

public class Payment_Cart_adapter extends RecyclerView.Adapter<Recyclerviewpaycartholder> {
    private final String TAG="Cart";
    Single_Pro_Ser result1,result3;
    String optionvalu;
    int apri,proid1;
    String proidaa;
    double a = 0;
    int qtysel;
    TextView cartprice1,lowertotl1;
    String priceitem,wpriceitem,addshiping;
    float apricetemp,fprice,di,diamt,mprice=0;
    Cate_Subcate_Ser result2;
    private List<Cart_ser> itemList;
    private Context context;

    String pro_id,email1,custid,pattribid;
    public Payment_Cart_adapter(Context context, List<Cart_ser> itemList, TextView cartprice1,TextView lowertotl1)
    {
        this.itemList=itemList;
        this.context=context;
        this.cartprice1=cartprice1;
        this.lowertotl1=lowertotl1;
    }
    @Override
    public Recyclerviewpaycartholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_cart_payment_card,null);
        Recyclerviewpaycartholder rcv=new Recyclerviewpaycartholder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final Recyclerviewpaycartholder holder, final int position) {




        //for (int i = 0; i < itemList.size(); i++) {

            pro_id = itemList.get(position).getCart().getAssociations().getCartRows().get(position).getIdProduct();
            email1=itemList.get(position).getCart().getAssociations().getCartRows().get(position).getEmail();
            custid=itemList.get(position).getCart().getAssociations().getCartRows().get(position).getCustid();
            pattribid=itemList.get(position).getCart().getAssociations().getCartRows().get(position).getIdProductAttribute();
            qtysel=Integer.parseInt(itemList.get(position).getCart().getAssociations().getCartRows().get(position).getQuantity());

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.qty.setText(qtysel+"");

//            float price11 = Float.valueOf(result1.getProduct().getPrice().toString());
//            int mprice1 = (int) (Math.round(price11));
//            sum = sum + priceitem;
//
//            Log.e(TAG,"Total:"+sum);

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
                     proidaa = result1.getProduct().getId().toString();
                    String proname = result1.getProduct().getName();
                    String proimgid = result1.getProduct().getIdDefaultImage();
                    String pro_lr = result1.getProduct().getLinkRewrite();
                    String categoid = result1.getProduct().getIdCategoryDefault();

                    RequestQueue queue1 = Volley.newRequestQueue(context);
                    String url = "https://nimako.lbyts.com/api/combinations/"+itemList.get(position).getCart().getAssociations().getCartRows().get(position).getIdProductAttribute().toString()+"?output_format=JSON";
//       String url="http://toscanyacademy.com/blog/mp.php";

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "combination mate" + response);
                            Gson gson = new Gson();
                            CombiSer result22 = gson.fromJson(response, CombiSer.class);
                            optionvalu=result22.getCombination().getAssociations().getProductOptionValues().get(0).getId().toString();

                            //for product option value
                            RequestQueue queue2 = Volley.newRequestQueue(context);
                            String url = "https://nimako.lbyts.com/api/product_option_values/"+optionvalu+"?output_format=JSON";
//       String url="http://toscanyacademy.com/blog/mp.php";

                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d(TAG, "combination mate" + response);
                                    Gson gson = new Gson();
                                    OptValser result23 = gson.fromJson(response, OptValser.class);
                                    String color=result23.getProductOptionValue().getName().toString();
                                    holder.color1.setText(color);

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

                            queue2.add(stringRequest);
                            // product option value end here----------


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

                    queue1.add(stringRequest);


                    Log.d(TAG, "Category id:" + categoid);

                    RequestQueue queue = Volley.newRequestQueue(context);

                    String url1 = "https://nimako.lbyts.com/api/categories/?display=[id,id_parent,name]&filter[id]=[" + categoid + "]&output_format=JSON";
//       String url="http://toscanyacademy.com/blog/mp.php";

                    StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
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

                    queue.add(stringRequest1);



                    holder.txtname.setText(proname);
                    holder.productid.setText(proidaa);
                    holder.custid.setText(custid);
                    di = Integer.parseInt(result1.getProduct().getOnSale().toString());
                    Log.d(TAG, "di:" + di);
                    if (di == 0) {


                        float price = Float.valueOf(result1.getProduct().getPrice().toString());
                        mprice = (int) (Math.round(price));


                        holder.price1.setText((int) (Math.round(mprice))+"");

                        holder.aprice1.setVisibility(View.GONE);
                        holder.dis1.setVisibility(View.GONE);
                        holder.apr.setVisibility(View.GONE);
                        holder.apmt.setVisibility(View.GONE);
//                        sum = sum + ((int) (Math.round(mprice)));

                    } else if (result1.getProduct().getOnSale() != null) {

                        apricetemp = Float.valueOf(result1.getProduct().getPrice());

                        apri = (int) (Math.round(apricetemp));


                        float unitratio=Float.valueOf(result1.getProduct().getUnitPriceRatio().toString());
                        float price = Float.valueOf(result1.getProduct().getPrice().toString());
                        mprice = (int) (Math.round(price));
                        fprice=price/unitratio;
                        float fprice1 = (int) (Math.round(fprice));

                        diamt = Float.valueOf(mprice - fprice1);
                        Log.d(TAG, "Diamt:" + mprice);
                        float aa = Float.valueOf(diamt / fprice1);
                        Log.d(TAG, "dimain:" + aa);
                        di = Float.valueOf(aa * 100);
                        Log.d(TAG, "diin%:" + di);



                        holder.price1.setText((int) (Math.round(mprice))+"");
                        holder.aprice1.setText((int) (Math.round(fprice1))+"");
                        holder.dis1.setText((int) (Math.round(di)) + "% off");
//                        sum = sum + ((int) (Math.round(mprice)));


                    }
                    double qtyyy=Double.parseDouble(itemList.get(position).getCart().getAssociations().getCartRows().get(position).getQuantity());
                    double priii=Double.parseDouble(result1.getProduct().getPrice().toString());
                    double tota=qtyyy*priii;
                    a = a + tota;
                    // Toast.makeText(context,result1.getProduct().getPrice().toString()+"",Toast.LENGTH_LONG).show();
                    cartprice1.setText("Rs."+(int) (Math.round(a)) + "");

                    lowertotl1.setText((int) (Math.round(a+75))+"");


//                    Float totalprice=Float.valueOf(result1.getProduct().getPrice().toString());
//                    totalprice=totalprice++;


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


    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
