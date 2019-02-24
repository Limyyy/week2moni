package lqy.bwie.com.week2moni.fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lqy.bwie.com.week2moni.R;
import lqy.bwie.com.week2moni.fragment.bean.CommonBean;
import lqy.bwie.com.week2moni.fragment.bean.ProductBean;
import lqy.bwie.com.week2moni.fragment.mvp.p.ProductPresenter;
import lqy.bwie.com.week2moni.fragment.mvp.v.ProductView;

import static android.content.Context.MODE_PRIVATE;
/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public class ShopProductFragment extends Fragment implements ProductView {
    private int position = ShowFragment.position;
    private TextView tv_title;
    private TextView tv_current_price;
    private TextView tv_old_price;
    private ImageView btn_add_shopcar;
    private Banner product_banner;
    private ProductBean.ResultBean productBeanResult;
    private ImageView img_spxq;
    private TextView tv_cpjs;
    private ImageView img_cpjs;
    private WebView webView;
    private int userId;
    private String sessionId;
    private ProductPresenter productPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_xiang_activvity, container, false);
        initView(view);
        //获取用户请求头
        SharedPreferences loginId = getActivity().getSharedPreferences("loginId", MODE_PRIVATE);
        userId = Integer.parseInt(loginId.getString("userId", ""));
        sessionId = loginId.getString("sessionId", "");
        //实例化Presenter
        productPresenter = new ProductPresenter(this);
        productPresenter.getProductData(position,userId,sessionId);
        //加入购物车
        btn_add_shopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int commodityId = productBeanResult.getCommodityId();
                try {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject();
                    jsonObject.put("commodityId", commodityId);
                    jsonObject.put("count", 1);
                    jsonArray.put(jsonObject);
                    productPresenter.addShopCarData(userId, sessionId, jsonArray.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    private void initView(View view) {
        //商品标题
        tv_title = view.findViewById(R.id.tv_title);
        //价格
        tv_current_price = view.findViewById(R.id.tv_current_price);
        tv_old_price = view.findViewById(R.id.tv_old_price);
        //加入购物车
        btn_add_shopcar = view.findViewById(R.id.img_shopping_cart);
        //商品详情轮播图
        product_banner = view.findViewById(R.id.product_banner);
        //商品详情
        img_spxq = view.findViewById(R.id.img_spxq);
        //产品介绍
        tv_cpjs = view.findViewById(R.id.tv_cpjs);
        img_cpjs = view.findViewById(R.id.img_cpjs);
        //webView
        webView = view.findViewById(R.id.product_webView);
    }

    // 初始化数据
    private void initData() {
        //设置数据内容
        tv_title.setText(productBeanResult.getCommodityName() + "");
        tv_old_price.setText("￥" + productBeanResult.getPrice());
        String picture = productBeanResult.getPicture();
        String[] split = picture.split(",");
        List<String> imgPath = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            imgPath.add(split[i]);
        }
        product_banner.setImages(imgPath).setImageLoader(new FrescoImageLoader()).start();
        Picasso.with(getContext()).load(split[0]).into(img_spxq);
        tv_cpjs.setText(productBeanResult.getCommodityName() + "");
        Picasso.with(getContext()).load(split[1]).into(img_cpjs);
        String htmlBody = productBeanResult.getDetails();
        webView.loadDataWithBaseURL(null, "<!DOCTYPE html><html><body>" + htmlBody + "</html></body>", "text/html", "utf-8", null);
    }

    //请求商品详情信息返回的数据
    @Override
    public void onProductSuccess(ProductBean productBean) {
        productBeanResult = productBean.getResult();
        initData();
    }

    @Override
    public void onAddShopCarMsg(CommonBean commonBean) {
        }

    public class FrescoImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(path).into(imageView);
        }
    }
}
