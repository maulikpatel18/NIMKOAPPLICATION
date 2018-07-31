package tempagain;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimako.R;

import java.util.List;

/**
 * Created by dhruvildesai on 19/01/18.
 */

public class Recyclerviewadapter extends RecyclerView.Adapter<RecyclerViewHolders> {
    private List<Categories> itemList;
    private Context context;
    public Recyclerviewadapter(Context context,List<Categories> itemList)
    {
        this.itemList=itemList;
        this.context=context;
    }
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_list_item,null);
        RecyclerViewHolders rcv=new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {


            holder.name.setText("Name:" + itemList.get(position).getCategories().get(position).getName());

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
