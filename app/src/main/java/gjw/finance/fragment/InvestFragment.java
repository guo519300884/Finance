package gjw.finance.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import gjw.finance.R;
import gjw.finance.adapter.InvestAdapter;
import gjw.finance.base.BaseFragment;
import gjw.finance.utils.AppNetConfig;

/**
 * Created by 皇 上 on 2017/3/10.
 */

public class InvestFragment extends BaseFragment {
    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.vp_invest)
    ViewPager vpInvest;
    @InjectView(R.id.tv_invest_all)
    TextView tvInvestAll;
    @InjectView(R.id.tv_invest_recommend)
    TextView tvInvestRecommend;
    @InjectView(R.id.tv_invest_hot)
    TextView tvInvestHot;
    private List<BaseFragment> fragments;
    private InvestAdapter investAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initData(String json) {

        baseTitle.setText("投资");
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);

        //初始化 Fragment
        initFragment();
        //初始化 ViewPaper
        initViewPager();

        //默认选中第一个
        selectText(0);

        vpInvest.setOnPageChangeListener(new MyOnPageChangeListener());

    }

    private void initViewPager() {
        investAdapter = new InvestAdapter(getChildFragmentManager(), fragments);
        vpInvest.setAdapter(investAdapter);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new InvestAllFragment());
        fragments.add(new InvestRecommendFragment());
        fragments.add(new InvestHotFragment());

    }

    @Override
    protected String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_invest_all, R.id.tv_invest_recommend, R.id.tv_invest_hot})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_invest_all:
                vpInvest.setCurrentItem(0);
                break;
            case R.id.tv_invest_recommend:
                vpInvest.setCurrentItem(1);
                break;
            case R.id.tv_invest_hot:
                vpInvest.setCurrentItem(2);
                break;
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            selectText(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void selectText(int position) {

        //设置默认颜色
        tvDefaultColor();

        switch (position) {
            case 0:
                tvInvestAll.setBackgroundColor(Color.GRAY);
                tvInvestAll.setTextColor(Color.RED);
                break;
            case 1:
                tvInvestRecommend.setBackgroundColor(Color.GRAY);
                tvInvestRecommend.setTextColor(Color.RED);
                break;
            case 2:
                tvInvestHot.setBackgroundColor(Color.GRAY);
                tvInvestHot.setTextColor(Color.RED);
                break;
        }
    }

    private void tvDefaultColor() {
        tvInvestAll.setBackgroundColor(Color.WHITE);
        tvInvestRecommend.setBackgroundColor(Color.WHITE);
        tvInvestHot.setBackgroundColor(Color.WHITE);
        tvInvestAll.setTextColor(Color.BLACK);
        tvInvestRecommend.setTextColor(Color.BLACK);
        tvInvestHot.setTextColor(Color.BLACK);
    }
}
