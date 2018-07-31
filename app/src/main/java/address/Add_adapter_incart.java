package address;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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

public class Add_adapter_incart extends RecyclerView.Adapter<Add_adapter_incart.Recyclerviewaddholderincart> {
    private List<Add_ser> itemAList;
    private RadioButton lastCheckedRB = null;
    private final String TAG = "Delete Add";
    private Context context;
    private int lastSelectedPosition = -1;
    String addid,email1,adddd;
    HashMap<String, String> resultp = new HashMap<String, String>();
    public Add_adapter_incart(Context context, List<Add_ser> itemAList)
    {
        this.itemAList=itemAList;
        this.context=context;
    }
    @Override
    public Recyclerviewaddholderincart onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_myadd_card_incart,null);
        Recyclerviewaddholderincart rcv=new Recyclerviewaddholderincart(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final Recyclerviewaddholderincart holder, final int position) {

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
        Log.d(TAG, "email la ahiya:" + email1);
        Log.d(TAG, "email la :" + mob2);
        holder.addtxt.setText(fname + " " + lname + "\n" + addlin1 + "\n" + addline2 + "\n" + city + " " + postal + "\n" + country + "\n" + mob1 + "\n " + mob2);


        addid = itemAList.get(position).getAddress().get(position).getAddreessId();
//        holder.cv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               if(holder.cv1.isActivated())
//               {
//                holder.cv1.setBackgroundColor(context.getResources().getColor(R.color.green));
//
//               }
//               else {
//                   holder.cv1.setBackgroundColor(context.getResources().getColor(R.color.white));
//               }
//            }
//        });
        //holder.cv1.setCardBackgroundColor(holder.cv1.isSelected() ? Color.LTGRAY : Color.WHITE);

//        holder.rbt.setTag(addid);
////            holder.seladd.setChecked(false);
////        adddd = holder.seladd.getTag().toString();
//
//        View.OnClickListener rbtn = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RadioButton checked_rb = (RadioButton) v;
//                if (lastCheckedRB != null) {
//                    lastCheckedRB.setChecked(false);
//                }
//                lastCheckedRB = checked_rb;
//
//                adddd = holder.rbt.getTag().toString();
//
//
//                Log.e(TAG, "radiotag:" + adddd);
//
//                Intent intent = new Intent("custom-message");
//                //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
//                intent.putExtra("addid", adddd);
//                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//
//
//            }
//        };


        holder.rbt.setTag(addid);
        holder.rbt.setChecked(lastSelectedPosition == position);







//
//        holder.seladd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton checked_rb = (RadioButton) group.findViewById(checkedId);
//                if (lastCheckedRB != null) {
//                    lastCheckedRB.setChecked(false);
//                }
//                //store the clicked radiobutton
//                lastCheckedRB = checked_rb;
//            }
//        });
//            if(holder.rbt.isChecked()) {
//
//
//    }
    }
    @Override
    public int getItemCount() {
        return this.itemAList.size();
    }

    public class Recyclerviewaddholderincart extends RecyclerView.ViewHolder {

        public TextView title, addtxt;
        RadioButton rbt;
        CardView cv1;


        public Recyclerviewaddholderincart(final View itemView) {
            super(itemView);
//            itemView.setOnClickListener(context);
            title = (TextView) itemView.findViewById(R.id.my_add_title);
            addtxt = (TextView) itemView.findViewById(R.id.my_add_txt);
            rbt = (RadioButton) itemView.findViewById(R.id.radionbtn);
            cv1 = (CardView) itemView.findViewById(R.id.card_view_myadd);

            rbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();

                   // Toast.makeText(context,"selected offer is " +rbt.getTag().toString(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent("custom-message");

                    intent.putExtra("addid", rbt.getTag().toString());
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                }
            });

        }
    }
}


