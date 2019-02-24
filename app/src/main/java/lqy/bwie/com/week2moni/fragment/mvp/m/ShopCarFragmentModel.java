package lqy.bwie.com.week2moni.fragment.mvp.m;

import lqy.bwie.com.week2moni.fragment.bean.ShopCarData;
import lqy.bwie.com.week2moni.fragment.mvp.ShopcarService;
import lqy.bwie.com.week2moni.fragment.mvp.ShoppingCallback;
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
public class ShopCarFragmentModel {
    public void getShopCarData(int userId, String sessionId, final ShoppingCallback shoppingCallback) {
        ShopcarService shopService = RetrofitUtil.getInstance().createApi(ShopcarService.class);
        Call<ShopCarData> carData = shopService.getShopCarData(userId, sessionId);
        carData.enqueue(new Callback<ShopCarData>() {
            @Override
            public void onResponse(Call<ShopCarData> call, Response<ShopCarData> response) {
                String status = response.body().getStatus();
                ShopCarData shopCarBean = response.body();
                if (status.equals("0000")){
                    shoppingCallback.getShopCarData(shopCarBean);
                }else {
                    shoppingCallback.getShopCarFailure(shopCarBean.getMessage()+"");
                }
            }

            @Override
            public void onFailure(Call<ShopCarData> call, Throwable t) {

            }
        });
    }
}
