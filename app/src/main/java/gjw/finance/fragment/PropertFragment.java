package gjw.finance.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import gjw.finance.R;
import gjw.finance.utils.AppNetConfig;

/**
 * Created by 皇 上 on 2017/3/10.
 */

public class PropertFragment extends BaseFragment {
    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    private View view;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_propert;
    }

    @Override
    protected void initData(String json) {

        baseTitle.setText("国库");
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);


    }

    @Override
    protected String getChildUrl() {
        return AppNetConfig.INDEX;
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
