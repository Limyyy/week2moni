package lqy.bwie.com.week2moni.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import lqy.bwie.com.week2moni.R;
import lqy.bwie.com.week2moni.fragment.ShopProductFragment;
import lqy.bwie.com.week2moni.fragment.bean.Search;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public class ShowFragmentAdapter extends RecyclerView.Adapter<ShowFragmentAdapter.MyViewHolder>{
   private Search search;
   private Context context;

    public ShowFragmentAdapter(Search search, Context context) {
        this.search = search;
        this.context = context;
    }


    @Override
    public ShowFragmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ShowFragmentAdapter.MyViewHolder holder, int position) {
        holder.name.setText(search.getResult().get(position).getCommodityName());
        holder.price.setText(search.getResult().get(position).getPrice()+"");
        Picasso.with(context).load(search.getResult().get(position).getMasterPic()).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventBus.getDefault().post(new ShopProductFragment());

            }
        });
    }

    @Override
    public int getItemCount() {
        return search.getResult().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final ImageView img;
        private TextView name,price;

        public MyViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_item_search);
            name = itemView.findViewById(R.id.tv_name_search);
            price = itemView.findViewById(R.id.tv_price_search);
        }
    }
}
