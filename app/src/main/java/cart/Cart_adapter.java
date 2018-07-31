package cart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.bumptech.glide.Glide;
import com.nimako.Cart_main;
import com.nimako.Item_Main;
import com.nimako.MyAddress;
import com.nimako.R;
import com.nimako.Wishlist_main;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Item.Com_Ser;
import address.Add_ser;
import category.Categories_Ser;
import category.RecyclerViewHolder;
import countrystate.UpdateSerializeAdd;
import single_product.Single_Pro_Ser;
import wishlist.Cate_Subcate_Ser;
import wishlist.WishlistResSer;

/**
 * Created by dhruvildesai on 02/02/18.
 */

public class Cart_adapter extends RecyclerView.Adapter<Recyclerviewcartholder> {
    private final String TAG="Cart";
    Single_Pro_Ser result1,result3;
    String optionvalu;
    int apri,proid1,cartid;
    String proidaa;
    int sum=0;
    int sum1=0;
    int qtysel;
    int qtyup;
    String priceitem,wpriceitem,addshiping;
    float apricetemp,fprice,di,diamt,mprice=0;
    Cate_Subcate_Ser result2;
    private List<Cart_ser> itemList;
    private Context context;
    TextView cartprice1,lowertotl1,uppertotal1;
    double a = 0;
    String pro_id,email1,custid,pattribid;
    public Cart_adapter(Context context,List<Cart_ser> itemList,TextView cartprice1,TextView lowertotl1,TextView uppertotal1)
    {
        this.itemList=itemList;
        this.context=context;
        this.cartprice1=cartprice1;
        this.uppertotal1=uppertotal1;
        this.lowertotl1=lowertotl1;

    }
    @Override
    public Recyclerviewcartholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_cart_card,null);
        Recyclerviewcartholder rcv=new Recyclerviewcartholder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final Recyclerviewcartholder holder, final int position) {


       // Toast.makeText(context,cartprice1.getText().toString()+"",Toast.LENGTH_LONG).show();


        //for (int i = 0; i < itemList.size(); i++) {

            cartid=itemList.get(position).getCart().getId();
            pro_id = itemList.get(position).getCart().getAssociations().getCartRows().get(position).getIdProduct();
            email1=itemList.get(position).getCart().getAssociations().getCartRows().get(position).getEmail();
            custid=itemList.get(position).getCart().getAssociations().getCartRows().get(position).getCustid();
            pattribid=itemList.get(position).getCart().getAssociations().getCartRows().get(position).getIdProductAttribute();
            qtysel=Integer.parseInt(itemList.get(position).getCart().getAssociations().getCartRows().get(position).getQuantity());
        Integer ia[]=new Integer[100];
            ArrayList<Integer> items = new ArrayList();

        items.add(qtysel);
        Log.e(TAG,"qty selec:"+qtysel);
        for (int a = 1; a < ia.length; a++) {
            ia[a]=a;
            items.add(ia[a]);
        }
        //Integer[] items = new Integer[]{1,2,3,4};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(holder.itemView.getContext(),android.R.layout.simple_spinner_item, items);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.qty.setAdapter(adapter);

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


// for cmbination
                    RequestQueue queue1 = Volley.newRequestQueue(context);
                    String url = "https://nimako.lbyts.com/api/combinations/"+itemList.get(position).getCart().getAssociations().getCartRows().get(position).getIdProductAttribute().toString()+"?output_format=JSON";
//       String url="http://toscanyacademy.com/blog/mp.php";


                    StringRequest stringRequest11 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "combination mate" + response);
                            Gson gson = new Gson();
                            CombiSer result22 = gson.fromJson(response, CombiSer.class);
                            optionvalu=result22.getCombination().getAssociations().getProductOptionValues().get(0).getId().toString();

                            //for product option value
                            RequestQueue queue2 = Volley.newRequestQueue(context);
                            String url2 = "https://nimako.lbyts.com/api/product_option_values/"+optionvalu+"?output_format=JSON";
//       String url="http://toscanyacademy.com/blog/mp.php";

                            StringRequest stringRequest12 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
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

                            queue2.add(stringRequest12);
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

                    queue1.add(stringRequest11);
//combination end here-----




                    Log.d(TAG, "Category id:" + categoid);

                    RequestQueue queue3 = Volley.newRequestQueue(context);

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

                    queue3.add(stringRequest1);



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

                        uppertotal1.setText((int) (Math.round(a+75))+"");
                    lowertotl1.setText("Total Rs."+(int) (Math.round(a+75)));

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
                                custid=holder.custid.getText().toString();
//                                DeleteAWish();
                                String url = "https://nimako.lbyts.com/admin268hvyowt/apis/deleteproduct_cart.php";
//

                                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {

                                                Log.d(TAG,response+"");



                                                Gson gson = new Gson();
                                                CartDeleResSer result=gson.fromJson(response, CartDeleResSer.class);

                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);// Convert response string in to json object.
                                                    Toast.makeText(context,result.getCart().getMessage() , Toast.LENGTH_LONG).show();

                                                    //   JSONObject jsonLL = jsonObject.getJSONObject("status");// Get LL json object from jsonObject.

//                            Integer status = jsonObject.getInt("status");
                                                    Integer status = Integer.parseInt(result.getCart().getStatus());

                                                    if(status==2) {
                                                        Toast.makeText(context,"Enter proper" , Toast.LENGTH_LONG).show();

                                                    }
                                                    else if(status==0)
                                                    {
//                                email.setText("");
//                                pass.setText("");
                                                        Intent i=new Intent(context,Cart_main.class);
                                                        i.putExtra("ee",email1);
                                                        Log.d(TAG,"email after delete:"+email1);
                                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                        context.startActivity(i);

                                                    }
                                                    else{
//                                FragmentManager fm=getFragmentManager();
//                                fm.beginTransaction().replace(R.id.contentframe,new MyAddress()).addToBackStack(null).commit();

                                                        Toast.makeText(context,"Enter proper details",Toast.LENGTH_LONG).show();

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
                                        params.put("custID",custid);
                                        params.put("productID",itemList.get(position).getCart().getAssociations().getCartRows().get(position).getIdProduct().toString());
                                       Log.e(TAG,"product id passssssss:"+itemList.get(position).getCart().getAssociations().getCartRows().get(position).getIdProduct().toString());
                                        params.put("pattrib",itemList.get(position).getCart().getAssociations().getCartRows().get(position).getIdProductAttribute());


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

        holder.qty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position1, long id) {
                qtyup=Integer.parseInt(parent.getSelectedItem().toString());
                Log.e(TAG,"Selected item after qty:"+qtyup);


//                String textxx=holder.price1.getText().toString();
//                int a = Integer.parseInt(textxx);
//                final int total = qtyup * a;
//                holder.totalproductamt.setText(total + "");
//                Log.e(TAG, "Total under adapter upadet qty" + total);
                if(position1>0) {
//                    updateQTY();

                    //holder.price1.setText(total+"");
                    Log.e(TAG, "cartid:" + cartid + "prodid" + itemList.get(position).getCart().getAssociations().getCartRows().get(position).getIdProduct() + "qty afeter:" + qtyup);

                    String url = "https://nimako.lbyts.com/admin268hvyowt/apis/UpdateCartQuantity.php";


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    Log.d("Response", response + "");


                                    Gson gson = new Gson();

                                    UpdateQtySer resultqty = gson.fromJson(response, UpdateQtySer.class);
                                    String stat = resultqty.getUpdateQuantity().getStatus().toString();
                                    if (stat.equals("true")) {
                                        Intent i = new Intent(context, Cart_main.class);
                                        i.putExtra("ee", email1);
                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        context.startActivity(i);

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

                                        } else if (error.getClass().equals(NoConnectionError.class)) {
                                            Toast.makeText(context,
                                                    "Oops. Slow Internet Re-Try..!",
                                                    Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                                        }
                                    } else {

                                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();

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
                            params.put("cartid", cartid + "");
                            params.put("ProductID", itemList.get(position).getCart().getAssociations().getCartRows().get(position).getIdProduct() + "");
                            params.put("quantity", String.valueOf(qtyup));
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        holder.addwish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure you want to Add product into wishlist?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // dialog.cancel();
                                proid1=Integer.parseInt(holder.productid.getText().toString());
                                custid=holder.custid.getText().toString();
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
    }
    public void DeleteAWish()
    {








    }
    public void updateQTY()
    {

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

                params.put("productid",String.valueOf(proid1));

                params.put("custid",custid+"");
                Log.d("Add to wisis","Add to wishlist button click:product id"+String.valueOf(pro_id)+"and custmoer id:"+custid+"");


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
    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
