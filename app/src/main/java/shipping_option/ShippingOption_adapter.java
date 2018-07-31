package shipping_option;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nimako.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import category.Categories_Ser;
import category.RecyclerViewHolder;

/**
 * Created by dhruvildesai on 02/01/18.
 */

public class ShippingOption_adapter extends RecyclerView.Adapter<ShippingOption_adapter.RecyclerViewShippingHolder> {
    private List<ShipoptionSer> itemList;
    private Context context;
    private int lastSelectedPosition = -1;
    public ShippingOption_adapter(Context context, List<ShipoptionSer> itemList)
    {
        this.itemList=itemList;
        this.context=context;
    }
    @Override
    public RecyclerViewShippingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_shippingoption_card,null);
        RecyclerViewShippingHolder rcv=new RecyclerViewShippingHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewShippingHolder holder, int position) {


        holder.txttitle.setText(itemList.get(position).getOptiontitle());
        holder.txtoption.setText(itemList.get(position).getOptiontxt());
        holder.tax.setText(itemList.get(position).getTax());
        int taxid = itemList.get(position).getId1();


//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });
        holder.rbt.setTag(taxid);
        holder.rbt.setChecked(lastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    /**
     * Showing popup menu when tapping on 3 dots
//     */
//    private void showPopupMenu(View view) {
//        // inflate menu
//        PopupMenu popup = new PopupMenu(mContext, view);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.menu_album, popup.getMenu());
//       // popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
//      //  popup.show();
//    }

    /**
     * Click listener for popup menu items
     */
//    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
//
//        public MyMenuItemClickListener() {
//        }
//
//        @Override
//        public boolean onMenuItemClick(MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
////                case R.id.action_add_favourite:
////                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
////                    return true;
////                case R.id.action_play_next:
////                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
////                    return true;
//                default:
//            }
//            return false;
//        }
//    }

//    @Override
//    public int getItemCount() {
//        return albumList.size();
//    }
    public class RecyclerViewShippingHolder extends RecyclerView.ViewHolder {

        public TextView txttitle,txtoption,tax;
        RadioButton rbt;


        public RecyclerViewShippingHolder(final View itemView) {
            super(itemView);
//            itemView.setOnClickListener(context);
            txtoption = (TextView) itemView.findViewById(R.id.otion_txt);
            txttitle=(TextView) itemView.findViewById(R.id.option_title);
            tax=(TextView) itemView.findViewById(R.id.taxoption);
            rbt = (RadioButton) itemView.findViewById(R.id.radionbtn);

            rbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();

//                    Toast.makeText(context,
//                            "selected offer is " +rbt.getTag().toString(),
//
//                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent("custom-message");

                    intent.putExtra("shipid", rbt.getTag().toString());
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                }
            });

        }
    }

}

