package cart;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.nimako.R;

import java.util.ArrayList;

/**
 * Created by dhruvildesai on 02/02/18.
 */

public class Recyclerviewcartholder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView totalproductamt,apr,apmt,txtname,color1,ctaname,subctaname,price1,aprice1,dis1,custid,productid;
    Spinner qty;
    ImageView imageview;
    Button remove,addwish;


    public Recyclerviewcartholder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        imageview=itemView.findViewById(R.id.pimg);
        txtname = (TextView) itemView.findViewById(R.id.title);
        apr=itemView.findViewById(R.id.apr);
        apmt=itemView.findViewById(R.id.apmt);
        color1 = (TextView) itemView.findViewById(R.id.color);
        ctaname = (TextView) itemView.findViewById(R.id.categorytitle);
        subctaname = (TextView) itemView.findViewById(R.id.subcategorytitle);
        custid = (TextView) itemView.findViewById(R.id.custid);
        productid = (TextView) itemView.findViewById(R.id.productid);
        qty = (Spinner) itemView.findViewById(R.id.qty);
        totalproductamt=itemView.findViewById(R.id.totalproductamt);
        price1=(TextView)itemView.findViewById(R.id.price1);
        aprice1=(TextView)itemView.findViewById(R.id.aprice1);
        dis1=(TextView)itemView.findViewById(R.id.disoff1);
        aprice1.setPaintFlags(aprice1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        remove=itemView.findViewById(R.id.removecart);
        addwish=itemView.findViewById(R.id.addwishcart);


    }

    @Override
    public void onClick(View v) {

    }
}