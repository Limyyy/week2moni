package lqy.bwie.com.week2moni.fragment.mvp;

import lqy.bwie.com.week2moni.fragment.bean.CommonBean;
import lqy.bwie.com.week2moni.fragment.bean.ProductBean;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public interface ProductCallBack {
    void onProductSuccess(ProductBean productBean);
    void onAddShopCarMsg(CommonBean commonBean);
}
