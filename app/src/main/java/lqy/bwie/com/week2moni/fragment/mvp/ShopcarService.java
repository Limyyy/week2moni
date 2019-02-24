package lqy.bwie.com.week2moni.fragment.mvp;

import lqy.bwie.com.week2moni.fragment.bean.ShopCarData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public interface ShopcarService {
    @GET("order/verify/v1/findShoppingCart")
    Call<ShopCarData> getShopCarData(@Header("userId") int userId, @Header("sessionId") String sessionId);

}
