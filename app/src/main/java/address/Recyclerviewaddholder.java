package address;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.nimako.R;
import com.nimako.Update_address_my_add;

/**
 * Created by dhruvildesai on 02/02/18.
 */

public class Recyclerviewaddholder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView title,addtxt;
    Button deleteadd,editadd;


    public Recyclerviewaddholder(final View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);;
        title = (TextView) itemView.findViewById(R.id.my_add_title);
        addtxt = (TextView) itemView.findViewById(R.id.my_add_txt);

        deleteadd=itemView.findViewById(R.id.add_delete_btn);
        editadd=itemView.findViewById(R.id.add_edit_btn);



    }

    @Override
    public void onClick(View v) {

    }
}