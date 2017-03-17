package gjw.finance.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import gjw.finance.R;
import gjw.finance.activity.BarChartActivity;
import gjw.finance.activity.LineChartActivity;
import gjw.finance.activity.MainActivity;
import gjw.finance.activity.PieChartActivity;
import gjw.finance.base.BaseFragment;
import gjw.finance.bean.UserInfo;
import gjw.finance.utils.AppNetConfig;
import gjw.finance.utils.BitmapUtils;
import gjw.finance.utils.CacheUtils;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.CropSquareTransformation;

/**
 * Created by 皇 上 on 2017/3/10.
 */

public class PropertFragment extends BaseFragment {


    @InjectView(R.id.tv_settings)
    TextView tvSettings;
    @InjectView(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @InjectView(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @InjectView(R.id.tv_me_name)
    TextView tvMeName;
    @InjectView(R.id.rl_me)
    RelativeLayout rlMe;
    @InjectView(R.id.recharge)
    ImageView recharge;
    @InjectView(R.id.withdraw)
    ImageView withdraw;
    @InjectView(R.id.ll_touzi)
    TextView llTouzi;
    @InjectView(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @InjectView(R.id.ll_zichan)
    TextView llZichan;
    private Bitmap bitmap;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_propert;
    }

    @Override
    protected void initData(String json) {

        MainActivity mainActivity = (MainActivity) getActivity();
//        UserInfo user = mainActivity.getUser();

        UserInfo user = CacheUtils.getUser();


        //设置用户名
        tvMeName.setText(user.getData().getName());
        //设置头像
        Picasso.with(getContext())
                .load(AppNetConfig.BASE_URL + "/images/tx.png")
//                .load("http://pic32.nipic.com/20130813/9422601_092545438000_2.jpg")
                //加颜色
                .transform(new ColorFilterTransformation(Color.parseColor("#22ff0000")))

//                .transform(new ContrastFilterTransformation(getContext()))
                //
                .transform(new CropCircleTransformation())
                //
                .transform(new CropSquareTransformation())
                //模糊处理 数越大越模糊
                .transform(new BlurTransformation(getActivity(), 10))

                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap source) {

                        bitmap = BitmapUtils.circleBitmap(source);
                        source.recycle();
                        return bitmap;
                    }

                    @Override
                    public String key() {
                        return "";
                    }
                }).into(ivMeIcon);

        Bitmap bitmap = getActivity().getIntent().getParcelableExtra("11");
        ivMeIcon.setImageBitmap(bitmap);

    }

    @Override
    protected String getChildUrl() {
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_settings, R.id.iv_me_icon, R.id.ll_touzi, R.id.ll_touzi_zhiguan, R.id.ll_zichan, R.id.recharge, R.id.withdraw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_settings:
                set();
                break;
            case R.id.iv_me_icon:
                set();
                break;
            case R.id.ll_touzi:
                startActivity(new Intent(getActivity(), LineChartActivity.class));
                break;
            case R.id.ll_touzi_zhiguan:
                startActivity(new Intent(getActivity(), BarChartActivity.class));
                break;
            case R.id.ll_zichan:
                startActivity(new Intent(getActivity(), PieChartActivity.class));
                break;
            case R.id.recharge:
                startActivity(new Intent(getActivity(), TopUpActivity.class));
                break;
            case R.id.withdraw:
                startActivity(new Intent(getActivity(), WithdrawActivity.class));
                break;
        }
    }

    private void set() {
        Intent intent = new Intent(getActivity(), SettingActivity.class);
        intent.putExtra("bitmap", bitmap);
        startActivity(intent);
    }
}
