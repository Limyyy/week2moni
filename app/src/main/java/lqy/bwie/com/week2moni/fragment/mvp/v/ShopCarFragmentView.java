package lqy.bwie.com.week2moni.fragment.mvp.v;

import lqy.bwie.com.week2moni.fragment.bean.ShopCarData;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public interface ShopCarFragmentView {
    void getShopCarData(ShopCarData shopCarData);
    void getShopCarFailure(String msg);
}
