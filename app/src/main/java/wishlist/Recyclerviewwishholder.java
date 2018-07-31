package wishlist;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.nimako.R;

/**
 * Created by dhruvildesai on 02/02/18.
 */

public class Recyclerviewwishholder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtname,ctaname,price1,aprice1,dis1,subctaname;
    Spinner qty;
    ImageView imageview;
    Button remove,addcart;
    TextView custid,productid;


    public Recyclerviewwishholder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        imageview=itemView.findViewById(R.id.pimg);
        txtname = (TextView) itemView.findViewById(R.id.title);
        ctaname = (TextView) itemView.findViewById(R.id.categorytitle);
        subctaname = (TextView) itemView.findViewById(R.id.subcategorytitle);
//        qty = (Spinner) itemView.findViewById(R.id.qty);
        custid=(TextView)itemView.findViewById(R.id.custid);
        productid=(TextView)itemView.findViewById(R.id.productid);
        price1=(TextView)itemView.findViewById(R.id.price);
        aprice1=(TextView)itemView.findViewById(R.id.aprice1);
        dis1=(TextView)itemView.findViewById(R.id.disoff1);
        aprice1.setPaintFlags(aprice1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        remove=itemView.findViewById(R.id.removewlbtn);
        addcart=itemView.findViewById(R.id.addtocartwlbtn);

//        Integer[] items = new Integer[100];
//        for (int a = 0; a < items.length; a++) {
//            items[a] = (a + 1);
//        }
//
//        //Integer[] items = new Integer[]{1,2,3,4};
//        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(itemView.getContext(),android.R.layout.simple_spinner_item, items);
//        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        qty.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

    }
}