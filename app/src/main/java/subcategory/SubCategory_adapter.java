package subcategory;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nimako.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.util.List;

import category.Categories_Ser;
import category.RecyclerViewHolder;
import okhttp3.Authenticator;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by dhruvildesai on 02/01/18.
 */

public class SubCategory_adapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<Categories_Ser> itemList;
    private Context context;

    public SubCategory_adapter(Context context, List<Categories_Ser> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_cate_card, null);
        RecyclerViewHolder rcv = new RecyclerViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        // Categories_Ser category = itemList.get(position);
        holder.txtname.setText(itemList.get(position).getCategories().get(position).getName());

        // loading album cover using Glide library
        // Glide.with(mContext).load(category.getThumbnail()).into(holder.thumbnail);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .authenticator(new Authenticator()
                {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException
                    {
                        String credential =  Credentials.basic("nimakointhefabricstoreofyear2018","");
                        return response.request().newBuilder()
                                .header("Authorization", credential)
                                .build();
                    }
                }).build();

       final Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
        final Transformation blurTransformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                Bitmap blurred = Blur.fastblur(context, source, 10);
                source.recycle();
                return blurred;
            }

            @Override
            public String key() {
                return "blur()";
            }
        };

        picasso
                .load("https://nimako.lbyts.com/api/images/categories/"+itemList.get(position).getCategories().get(position).getId()) // thumbnail url goes here
                .placeholder(R.drawable.placeholder).resize(200,200).onlyScaleDown()
                .transform(blurTransformation)
                .into(holder.img, new Callback() {
                    @Override
                    public void onSuccess() {
                    holder.p.setVisibility(View.INVISIBLE);
                        picasso
                                .load("https://nimako.lbyts.com/api/images/categories/"+itemList.get(position).getCategories().get(position).getId()) // image url goes here
                                .placeholder(holder.img.getDrawable()).resize(200,200)
                                .into(holder.img);
                    }

                    @Override
                    public void onError() {
                    }
                });

        //picasso.load("http://nimako.in/api/images/categories/"+itemList.get(position).getCategories().get(position).getId()).resize(200,200).into(holder.img);



        //Picasso.with(context).load("http://nimako.in/api/images/categories/"+itemList.get(position).getCategories().get(position).getId()).resize(200,200).into(holder.img);


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
}
