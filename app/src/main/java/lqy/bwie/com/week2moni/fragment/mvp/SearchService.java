package lqy.bwie.com.week2moni.fragment.mvp;

import lqy.bwie.com.week2moni.fragment.bean.Search;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public interface SearchService {

    @GET("commodity/v1/findCommodityByKeyword")
    Call<Search> getData(@Query("keyword")String keyword,@Query("page")int page,@Query("count")int count);
}
