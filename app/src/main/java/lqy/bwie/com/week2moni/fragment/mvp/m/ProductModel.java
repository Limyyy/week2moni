package lqy.bwie.com.week2moni.fragment.mvp.m;

import lqy.bwie.com.week2moni.fragment.bean.CommonBean;
import lqy.bwie.com.week2moni.fragment.bean.ProductBean;
import lqy.bwie.com.week2moni.fragment.mvp.ProductCallBack;
import lqy.bwie.com.week2moni.fragment.mvp.ProductService;
import lqy.bwie.com.week2moni.http.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public class ProductModel {
    //获取商品详情数据
    public void getProductData(int id,int userId,String sessionId, final ProductCallBack productCallBack){
        ProductService productService = RetrofitUtil.getInstance().createApi(ProductService.class);
        Call<ProductBean> call = productService.getProductData(userId, sessionId, id);
        call.enqueue(new Callback<ProductBean>() {
            @Override
            public void onResponse(Call<ProductBean> call, retrofit2.Response<ProductBean> response) {
                ProductBean productBean = response.body();
                productCallBack.onProductSuccess(productBean);
            }

            @Override
            public void onFailure(Call<ProductBean> call, Throwable t) {

            }
        });
    }
    //添加商品到购物车
    public void addShopCarData(int userId,String sessionId,String data, final ProductCallBack productCallBack){
        ProductService productService = RetrofitUtil.getInstance().createApi(ProductService.class);
        Call<CommonBean> call = productService.addShopCarData(userId,sessionId,data);
        call.enqueue(new Callback<CommonBean>() {
            @Override
            public void onResponse(Call<CommonBean> call, Response<CommonBean> response) {
                CommonBean commonBean = response.body();
                productCallBack.onAddShopCarMsg(commonBean);
            }

            @Override
            public void onFailure(Call<CommonBean> call, Throwable t) {

            }
        });
    }
}
