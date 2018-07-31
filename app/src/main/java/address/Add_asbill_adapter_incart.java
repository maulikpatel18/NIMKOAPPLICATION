package address;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nimako.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dhruvildesai on 02/02/18.
 */

public class Add_asbill_adapter_incart extends RecyclerView.Adapter<Add_asbill_adapter_incart.Recyclerviewaddholderincart> {
    private List<Add_ser> itemAList;
    private RadioButton lastCheckedRB = null;
    private final String TAG = "Delete Add";
    private Context context;
    private int lastSelectedPosition = -1;
    String addid,email1,adddd;
    HashMap<String, String> resultp = new HashMap<String, String>();
    public Add_asbill_adapter_incart(Context context, List<Add_ser> itemAList)
    {
        this.itemAList=itemAList;
        this.context=context;
    }
    @Override
    public Recyclerviewaddholderincart onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_asbill_myadd_card_incart,null);
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
            title = (TextView) itemView.findViewById(R.id.my_add_title1);
            addtxt = (TextView) itemView.findViewById(R.id.my_add_txt1);
            rbt = (RadioButton) itemView.findViewById(R.id.radionbtn1);
            cv1 = (CardView) itemView.findViewById(R.id.card_view_myadd1);

            rbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();

//                    Toast.makeText(context,"selected offer is " +rbt.getTag().toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent("custom-message-bill");

                    intent.putExtra("addidbill", rbt.getTag().toString());
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                }
            });

        }
    }
}


