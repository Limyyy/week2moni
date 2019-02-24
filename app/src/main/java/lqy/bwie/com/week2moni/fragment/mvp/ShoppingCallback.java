package lqy.bwie.com.week2moni.fragment.mvp;

import lqy.bwie.com.week2moni.fragment.bean.ShopCarData;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public interface ShoppingCallback {
    void getShopCarData(ShopCarData shopCarData);
    void getShopCarFailure(String msg);
}
