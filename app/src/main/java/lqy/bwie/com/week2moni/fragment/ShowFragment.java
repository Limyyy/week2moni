package lqy.bwie.com.week2moni.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import lqy.bwie.com.week2moni.R;
import lqy.bwie.com.week2moni.fragment.adapter.ShowFragmentAdapter;
import lqy.bwie.com.week2moni.fragment.bean.Search;
import lqy.bwie.com.week2moni.fragment.mvp.p.SearchPresenter;
import lqy.bwie.com.week2moni.fragment.mvp.v.ShowFragmentView;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/23
 * desc:
 */
public class ShowFragment extends Fragment implements ShowFragmentView{
    public static int position;
    @BindView(R.id.img_pop)
    ImageView imgPop;
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.recycler_search)
    RecyclerView recyclerSearch;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SearchPresenter searchPresenter = new SearchPresenter(this);
        searchPresenter.getDatas("1",1,100);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.img_pop, R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_pop:
                break;
            case R.id.tv_search:
                String string = search.getText().toString();
                SearchPresenter searchPresenter = new SearchPresenter(this);
                searchPresenter.getDatas(string,1,100);
                break;
        }
    }

    @Override
    public void getDatas(Search search) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerSearch.setLayoutManager(manager);
        ShowFragmentAdapter showFragmentAdapter = new ShowFragmentAdapter(search,getActivity());
        recyclerSearch.setAdapter(showFragmentAdapter);

    }
}
