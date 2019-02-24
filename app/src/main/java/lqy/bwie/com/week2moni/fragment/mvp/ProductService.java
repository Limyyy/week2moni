package lqy.bwie.com.week2moni.fragment.mvp;

import lqy.bwie.com.week2moni.fragment.bean.CommonBean;
import lqy.bwie.com.week2moni.fragment.bean.ProductBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public interface ProductService {
    //商品详情
    @GET("commodity/v1/findCommodityDetailsById")
    Call<ProductBean> getProductData(@Header("userId")int userId, @Header("sessionId")String sessionId, @Query("commodityId")int commodityId);
    @PUT("order/verify/v1/syncShoppingCart")
    Call<CommonBean> addShopCarData(@Header("userId") int userId, @Header("sessionId")String sessionId, @Query("data")String data);

}
