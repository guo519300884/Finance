package gjw.finance.fragment;


import android.widget.ListView;

import com.alibaba.fastjson.JSON;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import gjw.finance.R;
import gjw.finance.adapter.InvestAllAdapter;
import gjw.finance.bean.InvestAllBean;

/**
 * Created by 皇上 on 2017/3/14.
 */

public class InvestAllFragment extends BaseFragment {

    @InjectView(R.id.lv_all)
    ListView lvAll;
    private InvestAllBean investAllBean;
    private List<InvestAllBean.DataBean> investAllBeanData;
    private InvestAllAdapter investAllAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest_all;
    }

    @Override
    public void initData(String json) {
        investAllBean = JSON.parseObject(json, InvestAllBean.class);
        investAllBeanData = investAllBean.getData();


        investAllAdapter = new InvestAllAdapter(investAllBeanData);
        lvAll.setAdapter(investAllAdapter);

    }

    @Override
    protected String getChildUrl() {
        return null;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
