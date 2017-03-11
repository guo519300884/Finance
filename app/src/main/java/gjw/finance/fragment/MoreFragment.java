package gjw.finance.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import gjw.finance.R;

/**
 * Created by 皇 上 on 2017/3/10.
 */

public class MoreFragment extends BaseFragment {
    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    private View view;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_more, null);
        ButterKnife.inject(this, view);
        return view;

    }

    @Override
    public void initData() {
        super.initData();
        baseTitle.setText("更多");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
