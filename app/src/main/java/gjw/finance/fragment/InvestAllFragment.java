package gjw.finance.fragment;


import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import gjw.finance.R;
import gjw.finance.adapter.InvestAllAdapter03;
import gjw.finance.bean.InvestAllBean;

/**
 * Created by 皇上 on 2017/3/14.
 */

public class InvestAllFragment extends BaseFragment {

    @InjectView(R.id.lv_all)
    ListView lvAll;
    @InjectView(R.id.tv_product_title)
    TextView tvProductTitle;
    private InvestAllBean investAllBean;
    private List<InvestAllBean.DataBean> investAllBeanData;
    private InvestAllAdapter03 investAllAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest_all;
    }

    @Override
    public void initData(String json) {
        investAllBean = JSON.parseObject(json, InvestAllBean.class);
        investAllBeanData = investAllBean.getData();


//        investAllAdapter = new InvestAllAdapter(investAllBeanData);
//        investAllAdapter = new InvestAllAdapter01(investAllBeanData);
//        investAllAdapter = new InvestAllAdapter02(investAllBeanData);
        investAllAdapter = new InvestAllAdapter03(investAllBeanData);
        lvAll.setAdapter(investAllAdapter);

        initListener();
    }

    @Override
    protected String getChildUrl() {
        return null;
    }

    @Override
    protected void initListener() {
        TranslateAnimation ta = new TranslateAnimation(500, -500, 0, 0);
        ta.setDuration(5000);
        ta.setRepeatCount(500);
        tvProductTitle.startAnimation(ta);

//        tvProductTitle.setFocusable(true);
//        tvProductTitle.setFocusableInTouchMode(true);
//        tvProductTitle.requestFocus();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
