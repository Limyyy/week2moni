package lqy.bwie.com.week2moni.fragment.mvp.p;

import lqy.bwie.com.week2moni.fragment.bean.ShopCarData;
import lqy.bwie.com.week2moni.fragment.mvp.ShoppingCallback;
import lqy.bwie.com.week2moni.fragment.mvp.m.ShopCarFragmentModel;
import lqy.bwie.com.week2moni.fragment.mvp.v.ShopCarFragmentView;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public class ShopCarFragmentPresenter {
    private ShopCarFragmentModel mShopCarFragmentModel;
    private ShopCarFragmentView mShopCarFragmentView;

    public ShopCarFragmentPresenter(ShopCarFragmentView shopCarFragmentView) {
        mShopCarFragmentView = shopCarFragmentView;
        mShopCarFragmentModel = new ShopCarFragmentModel();
    }

    public void getData(int userId,String sessionId){
        mShopCarFragmentModel.getShopCarData(userId, sessionId, new ShoppingCallback() {
            @Override
            public void getShopCarData(ShopCarData shopCarData) {
                mShopCarFragmentView.getShopCarData(shopCarData);
            }

            @Override
            public void getShopCarFailure(String msg) {
                mShopCarFragmentView.getShopCarFailure(msg);
            }
        });
    }
}
