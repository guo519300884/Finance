package gjw.finance.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import gjw.finance.R;
import gjw.finance.base.BaseActivity;

public class AboutActivity extends BaseActivity {

    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;

    @Override
    public void initTitle() {
        baseTitle.setText("关于");
        baseSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @OnClick(R.id.base_back)
    public void onClick() {
        finish();
    }
}
