package category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimako.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dhruvildesai on 02/01/18.
 */

public class Category_adapter  extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<Categories_Ser> itemList;
    private Context context;
    public Category_adapter(Context context,List<Categories_Ser> itemList)
    {
        this.itemList=itemList;
        this.context=context;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_cate_card,null);
        RecyclerViewHolder rcv=new RecyclerViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

       // Categories_Ser category = itemList.get(position);
        holder.txtname.setText(itemList.get(position).getCategories().get(position).getName());

        // loading album cover using Glide library
       // Glide.with(mContext).load(category.getThumbnail()).into(holder.thumbnail);
        Picasso.with(context).load("https://nimako.lbyts.com/img/tmp/category_"+itemList.get(position).getCategories().get(position).getId()+"-thumb.jpg").resize(200,200).into(holder.img);


       // http://nimako.in/img/tmp/category_12-thumb.jpg

//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    /**
     * Showing popup menu when tapping on 3 dots
//     */
//    private void showPopupMenu(View view) {
//        // inflate menu
//        PopupMenu popup = new PopupMenu(mContext, view);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.menu_album, popup.getMenu());
//       // popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
//      //  popup.show();
//    }

    /**
     * Click listener for popup menu items
     */
//    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
//
//        public MyMenuItemClickListener() {
//        }
//
//        @Override
//        public boolean onMenuItemClick(MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
////                case R.id.action_add_favourite:
////                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
////                    return true;
////                case R.id.action_play_next:
////                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
////                    return true;
//                default:
//            }
//            return false;
//        }
//    }

//    @Override
//    public int getItemCount() {
//        return albumList.size();
//    }


}

