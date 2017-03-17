package gjw.finance.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.InjectView;
import butterknife.OnClick;
import gjw.finance.R;
import gjw.finance.base.BaseActivity;

public class TopUpActivity extends BaseActivity {

    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.chongzhi_text)
    TextView chongzhiText;
    @InjectView(R.id.view)
    View view;
    @InjectView(R.id.et_chongzhi)
    EditText etChongzhi;
    @InjectView(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @InjectView(R.id.yue_tv)
    TextView yueTv;
    @InjectView(R.id.btn_chongzhi)
    Button btnChongzhi;

    @Override
    public void initTitle() {
        baseTitle.setText("充值");
        baseSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        etChongzhi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String money = s.toString().trim();

                if (TextUtils.isEmpty(money)) {
                    btnChongzhi.setClickable(false);
                    btnChongzhi.setBackgroundResource(R.drawable.btn_02);
                } else {
                    btnChongzhi.setClickable(true);
                    btnChongzhi.setBackgroundResource(R.drawable.btn_01);
                }


            }
        });


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_top_up;
    }

    @OnClick({R.id.base_back, R.id.btn_chongzhi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.base_back:
                finish();
                break;
            case R.id.btn_chongzhi:
                Toast.makeText(this, "充", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
