package Item;

import android.graphics.Paint;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nimako.R;

/**
 * Created by dhruvildesai on 19/01/18.
 */

public class RecyclerViewHolderItemList extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtname1,price1,aprice1,dis1;
    public ImageView thumbnail;
    public RelativeLayout relitemlist1;
    public ImageView btn;
    public RecyclerViewHolderItemList(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        txtname1=(TextView)itemView.findViewById(R.id.title1);
        price1=(TextView)itemView.findViewById(R.id.price1);
        aprice1=(TextView)itemView.findViewById(R.id.aprice1);
        dis1=(TextView)itemView.findViewById(R.id.disoff1);
        relitemlist1=(RelativeLayout)itemView.findViewById(R.id.relitemlist);

        aprice1.setPaintFlags(aprice1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        thumbnail=(ImageView)itemView.findViewById(R.id.thumbnail1);
        btn=(ImageView) itemView.findViewById(R.id.btnaddtowish);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


    }
}
