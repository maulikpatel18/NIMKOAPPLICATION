package shipping_option;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nimako.R;

/**
 * Created by dhruvildesai on 19/01/18.
 */

public class RecyclerViewShippingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txttitle,txtoption,tax;
    RadioButton rbt;

    public RecyclerViewShippingHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        txtoption = (TextView) itemView.findViewById(R.id.otion_txt);
        txttitle=(TextView) itemView.findViewById(R.id.option_title);
        tax=(TextView) itemView.findViewById(R.id.taxoption);
        rbt = (RadioButton) itemView.findViewById(R.id.radionbtn);

    }

    @Override
    public void onClick(View v) {

    }
}
