package yourorder.wishlist;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.nimako.R;

/**
 * Created by dhruvildesai on 02/02/18.
 */

public class Recyclervieworderholder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView orderref1,paytype1,date1,totalpr1,statusor1;
    LinearLayout buttonlayer;
    Button pdf,cancle;
    TextView custid;


    public Recyclervieworderholder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        orderref1 = (TextView) itemView.findViewById(R.id.orderref);
        date1 = (TextView) itemView.findViewById(R.id.date);
        totalpr1 = (TextView) itemView.findViewById(R.id.totalpr);
        custid = (TextView) itemView.findViewById(R.id.custid);
        paytype1 = (TextView) itemView.findViewById(R.id.paytype);
        statusor1=(TextView)itemView.findViewById(R.id.statusor);
        buttonlayer=itemView.findViewById(R.id.buttonlayer);

        pdf=itemView.findViewById(R.id.pdf);
        cancle=itemView.findViewById(R.id.cancleorder);

//
    }

    @Override
    public void onClick(View v) {

    }
}