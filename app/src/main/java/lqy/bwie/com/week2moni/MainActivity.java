package lqy.bwie.com.week2moni;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lqy.bwie.com.week2moni.f.Orther2Fragment;
import lqy.bwie.com.week2moni.f.OrtherFragment;
import lqy.bwie.com.week2moni.fragment.ShowFragment;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFragments = new ArrayList<>();
        mFragments.add(new ShowFragment());
        mFragments.add(new OrtherFragment());
        mFragments.add(new Orther2Fragment());
        mFragments.add(new Orther2Fragment());
        mFragments.add(new Orther2Fragment());

        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
    }

    @OnClick({R.id.tv_show, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_show:
                vp.setCurrentItem(0);
                break;
            case R.id.tv2:
                vp.setCurrentItem(1);
                break;
            case R.id.tv3:
                vp.setCurrentItem(2);
                break;
            case R.id.tv4:
                vp.setCurrentItem(3);
                break;
            case R.id.tv5:
                vp.setCurrentItem(4);
                break;
        }
    }
}
