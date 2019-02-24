package lqy.bwie.com.week2moni.fragment.mvp.p;

import lqy.bwie.com.week2moni.fragment.bean.Search;
import lqy.bwie.com.week2moni.fragment.mvp.ShowFragmentCallBack;
import lqy.bwie.com.week2moni.fragment.mvp.m.ShowFragmentModel;
import lqy.bwie.com.week2moni.fragment.mvp.v.ShowFragmentView;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public class SearchPresenter {
    private ShowFragmentModel mShowFragmentModel;
    private ShowFragmentView mShowFragmentView;

    public SearchPresenter(ShowFragmentView showFragmentView) {
        mShowFragmentView = showFragmentView;
        mShowFragmentModel = new ShowFragmentModel();
    }

    public void getDatas(String keyword,int page,int count){
        mShowFragmentModel.getShowData(keyword, page, count, new ShowFragmentCallBack() {
            @Override
            public void getDatas(Search search) {
                mShowFragmentView.getDatas(search);
            }
        });
    }
}
