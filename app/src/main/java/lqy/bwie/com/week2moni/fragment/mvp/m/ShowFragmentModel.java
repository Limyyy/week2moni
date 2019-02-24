package lqy.bwie.com.week2moni.fragment.mvp.m;

import lqy.bwie.com.week2moni.fragment.bean.Search;
import lqy.bwie.com.week2moni.fragment.mvp.SearchService;
import lqy.bwie.com.week2moni.fragment.mvp.ShowFragmentCallBack;
import lqy.bwie.com.week2moni.http.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public class ShowFragmentModel {
    public void getShowData(String keyword,int page,int count,final ShowFragmentCallBack showFragmentCallBack) {
        SearchService searchService = RetrofitUtil.getInstance().createApi(SearchService.class);
        Call<Search> data = searchService.getData(keyword, page, count);
        data.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                Search body = response.body();
                showFragmentCallBack.getDatas(body);
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });


    }
}
