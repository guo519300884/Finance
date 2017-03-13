package gjw.finance.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import gjw.finance.R;
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


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initData(String json) {

    }

    @Override
    protected String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }

    @Override
    protected void initListener() {
        baseTitle.setText("投资");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
