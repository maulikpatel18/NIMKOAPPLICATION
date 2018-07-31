package Paymentstep;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.nimako.R;

import org.w3c.dom.Text;

/**
 * Created by dhruvildesai on 02/02/18.
 */

public class Recyclerviewpaycartholder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtname,ctaname,apr,apmt,color1,subctaname,price1,aprice1,dis1,custid,productid;
    TextView qty;
    ImageView imageview;
    Button remove,addwish;


    public Recyclerviewpaycartholder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        imageview=itemView.findViewById(R.id.pimg);
        apr=itemView.findViewById(R.id.apr);
        apmt=itemView.findViewById(R.id.apmt);
        txtname = (TextView) itemView.findViewById(R.id.title);
        color1 = (TextView) itemView.findViewById(R.id.color);
        ctaname = (TextView) itemView.findViewById(R.id.categorytitle);
        subctaname = (TextView) itemView.findViewById(R.id.subcategorytitle);
        custid = (TextView) itemView.findViewById(R.id.custid);
        productid = (TextView) itemView.findViewById(R.id.productid);
        qty = (TextView) itemView.findViewById(R.id.qty);
        price1=(TextView)itemView.findViewById(R.id.price1);
        aprice1=(TextView)itemView.findViewById(R.id.aprice1);
        dis1=(TextView)itemView.findViewById(R.id.disoff1);
        aprice1.setPaintFlags(aprice1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


    }

    @Override
    public void onClick(View v) {

    }
}