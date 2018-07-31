package category;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nimako.R;

/**
 * Created by dhruvildesai on 19/01/18.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtname;
    public ImageView img;
    public ProgressBar p;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        txtname=(TextView)itemView.findViewById(R.id.title);
        img=(ImageView)itemView.findViewById(R.id.img);
        p=(ProgressBar)itemView.findViewById(R.id.progressBar1);
    }

    @Override
    public void onClick(View v) {

    }
}
