package tempagain;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nimako.R;

/**
 * Created by dhruvildesai on 19/01/18.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView name;
    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        name=(TextView)itemView.findViewById(R.id.name);
    }

    @Override
    public void onClick(View v) {

    }
}
