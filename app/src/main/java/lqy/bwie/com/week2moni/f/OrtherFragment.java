package lqy.bwie.com.week2moni.f;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lqy.bwie.com.week2moni.R;
import lqy.bwie.com.week2moni.fragment.adapter.ShopcarFragmentAdapter;
import lqy.bwie.com.week2moni.fragment.bean.ShopCarData;
import lqy.bwie.com.week2moni.fragment.mvp.p.ShopCarFragmentPresenter;
import lqy.bwie.com.week2moni.fragment.mvp.v.ShopCarFragmentView;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/23
 * desc:
 */
public class OrtherFragment extends Fragment implements ShopCarFragmentView {

    @BindView(R.id.recyclerView_shop)
    SwipeMenuRecyclerView recyclerViewShop;
    @BindView(R.id.ck_sum_shop)
    CheckBox ckSumShop;
    @BindView(R.id.tv_sum_shop)
    TextView tvSumShop;
    @BindView(R.id.btn_sum_shop)
    Button btnSumShop;
    Unbinder unbinder;
    private ShopcarFragmentAdapter mShopcarFragmentAdapter;
    private SharedPreferences loginId;
    private ShopCarFragmentPresenter mShopCarFragmentPresenter;
    private int userId;
    private String sessionId;
    private List<ShopCarData.ResultBean> mResult;
    private double mNums;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLogin();
    }

    SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            int height = ViewPager.LayoutParams.MATCH_PARENT;
            String test = "删除";
            SwipeMenuItem delItem = new SwipeMenuItem(getActivity())
                    .setBackgroundColor(Color.RED)
                    .setText(test)
                    .setTextColor(Color.BLACK)
                    .setTextSize(20)
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(delItem);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ck_sum_shop, R.id.btn_sum_shop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ck_sum_shop:
                for (int i = 0; i < mShopcarFragmentAdapter.getItemCount(); i++) {
                    ShopCarData.ResultBean resultBean = mResult.get(i);
                    if (ckSumShop.isChecked()) {
                        resultBean.setIsCheck(1);
                    } else {
                        resultBean.setIsCheck(0);
                    }
                }
                mShopcarFragmentAdapter.notifyDataSetChanged();
                getTotal();
                break;
            case R.id.btn_sum_shop:

                break;
        }
    }

    @Override
    public void getShopCarData(final ShopCarData shopCarData) {
        mResult = shopCarData.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewShop.setLayoutManager(linearLayoutManager);

        //recyclerViewShop.setItemViewSwipeEnabled(true);
        recyclerViewShop.setSwipeMenuCreator(swipeMenuCreator);
        recyclerViewShop.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();

                //  int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。0是左，右是1，暂时没有用到
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                // int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

                mResult.remove(adapterPosition);
                mShopcarFragmentAdapter.notifyItemRemoved(adapterPosition);
                Toast.makeText(getActivity(), "删除" + adapterPosition, Toast.LENGTH_SHORT).show();

            }
        });

        mShopcarFragmentAdapter = new ShopcarFragmentAdapter(shopCarData, getActivity());
        recyclerViewShop.setAdapter(mShopcarFragmentAdapter);

        //当点击单选框
        mShopcarFragmentAdapter.setOnShopItemClick(new ShopcarFragmentAdapter.OnShopItemClick() {
            @Override
            public void checkBoxClick(View view, int position) {
                if (shopCarData.getResult().get(position).getIsCheck() == 1) {
                    shopCarData.getResult().get(position).setIsCheck(0);
                    ckSumShop.setChecked(false);
                } else {
                    shopCarData.getResult().get(position).setIsCheck(1);
                }
                getTotal();
                mShopcarFragmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void subItemClick(View view, int position) {
                int count = shopCarData.getResult().get(position).getCount();
                if (count <= 1) {
                    Toast.makeText(getActivity(), "不能再少了", Toast.LENGTH_SHORT).show();
                } else {
                    count--;
                    shopCarData.getResult().get(position).setCount(count);
                    if (shopCarData.getResult().get(position).getIsCheck() == 1) {
                        getTotal();
                    }
                }
                mShopcarFragmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void addItemClick(View view, int position) {
                int count = shopCarData.getResult().get(position).getCount();
                count++;
                shopCarData.getResult().get(position).setCount(count);
                if (shopCarData.getResult().get(position).getIsCheck() == 1) {
                    getTotal();
                }
                mShopcarFragmentAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void getShopCarFailure(String msg) {
        Toast.makeText(getActivity(), msg + "", Toast.LENGTH_SHORT).show();
    }

    //计算总价
    public void getTotal() {
        double total = 0;
        int num = 0;
        for (int i = 0; i < mShopcarFragmentAdapter.getItemCount(); i++) {
            ShopCarData.ResultBean resultBean = mResult.get(i);
            if (resultBean.getIsCheck() == 1) {
                total += resultBean.getPrice() * resultBean.getCount();
                num += resultBean.getCount();
            }
        }
        tvSumShop.setText("￥" + total);
        mNums = total;
    }

    public void isLogin() {
        loginId = getActivity().getSharedPreferences("loginId", Context.MODE_PRIVATE);
        userId = Integer.parseInt(loginId.getString("userId", ""));
        sessionId = loginId.getString("sessionId", "");
        //判断用户是否登录
        if (!loginId.getBoolean("isLogin", false)) {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        } else {
            //实例化presenter,获取购物车数据
            mShopCarFragmentPresenter = new ShopCarFragmentPresenter(this);
            mShopCarFragmentPresenter.getData(userId, sessionId);
        }
    }

}
