package lqy.bwie.com.week2moni.fragment.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import lqy.bwie.com.week2moni.R;
import lqy.bwie.com.week2moni.fragment.bean.ShopCarData;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public class ShopcarFragmentAdapter extends SwipeMenuRecyclerView.Adapter<ShopcarFragmentAdapter.MyViewHolder>{
    private ShopCarData mShopCarData;
    private Context context;
    private OnShopItemClick onShopItemClick;

    public ShopCarData getShopCarData() {
        return mShopCarData;
    }

    public ShopcarFragmentAdapter(OnShopItemClick onShopItemClick) {
        this.onShopItemClick = onShopItemClick;
    }
    public interface OnShopItemClick{
        void checkBoxClick(View view,int position);
        void subItemClick(View view,int position);
        void addItemClick(View view,int position);
    }
    public void setOnShopItemClick(OnShopItemClick onShopItemClick) {
        this.onShopItemClick = onShopItemClick;
    }

    public ShopcarFragmentAdapter(ShopCarData shopCarData, Context context) {
        mShopCarData = shopCarData;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_shopcar, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Picasso.with(context).load(mShopCarData.getResult().get(position).getPic()).into(holder.img_shop_pic);
        holder.tv_shop_name.setText(mShopCarData.getResult().get(position).getCommodityName());
        holder.tv_shop_price.setText("￥"+mShopCarData.getResult().get(position).getPrice());
        holder.tv_num.setText(mShopCarData.getResult().get(position).getCount()+"");
        int isCheck = mShopCarData.getResult().get(position).getIsCheck();
        if(isCheck == 1){
            holder.cb_check.setChecked(true);
        }else {
            holder.cb_check.setChecked(false);
        }
        holder.cb_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShopItemClick.checkBoxClick(view,position);
            }
        });

        //加减数量点击事件
        holder.tv_subnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShopItemClick.subItemClick(view,position);
            }
        });
        holder.tv_addnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShopItemClick.addItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mShopCarData.getResult().size();
    }

    public class MyViewHolder extends SwipeMenuRecyclerView.ViewHolder{
        private CheckBox cb_check;
        private ImageView img_shop_pic;
        private TextView tv_shop_name;
        private TextView tv_shop_price;
        private TextView tv_subnum;
        private TextView tv_num;
        private TextView tv_addnum;
        public MyViewHolder(View itemView) {
            super(itemView);

            cb_check = itemView.findViewById(R.id.ck_recyclerView_item_shop);
            img_shop_pic = itemView.findViewById(R.id.img_recyclerView_item_shop);
            tv_shop_name = itemView.findViewById(R.id.tv_name_recyclerView_item_shop);
            tv_shop_price = itemView.findViewById(R.id.tv_price_recyclerView_item_shop);
            tv_subnum = itemView.findViewById(R.id.tv_delnum);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_addnum = itemView.findViewById(R.id.tv_addnum);
        }
    }
}
