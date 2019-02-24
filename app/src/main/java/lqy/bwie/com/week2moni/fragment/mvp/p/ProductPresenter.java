package lqy.bwie.com.week2moni.fragment.mvp.p;

import lqy.bwie.com.week2moni.fragment.bean.CommonBean;
import lqy.bwie.com.week2moni.fragment.bean.ProductBean;
import lqy.bwie.com.week2moni.fragment.mvp.ProductCallBack;
import lqy.bwie.com.week2moni.fragment.mvp.m.ProductModel;
import lqy.bwie.com.week2moni.fragment.mvp.v.ProductView;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public class ProductPresenter {
    private ProductModel mProductModel;
    private ProductView mProductView;

    public ProductPresenter(ProductView productView) {
        mProductView = productView;
        mProductModel = new ProductModel();
    }

    //获取商品详细数据
    public void getProductData(int id,int userId,String sessionId ){
        mProductModel.getProductData(id,userId,sessionId, new ProductCallBack() {
            @Override
            public void onProductSuccess(ProductBean productBean) {
                mProductView.onProductSuccess(productBean);
            }

            @Override
            public void onAddShopCarMsg(CommonBean commonBean) {

            }
        });
    }
    //添加商品至购物车
    public void addShopCarData(int userId,String sessionId,String data){
        mProductModel.addShopCarData(userId, sessionId, data, new ProductCallBack() {
            @Override
            public void onProductSuccess(ProductBean productBean) {

            }
            @Override
            public void onAddShopCarMsg(CommonBean commonBean) {
                mProductView.onAddShopCarMsg(commonBean);
            }
        });
    }
}
