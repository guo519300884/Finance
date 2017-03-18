package gjw.finance.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import gjw.finance.R;
import gjw.finance.activity.BarChartActivity;
import gjw.finance.activity.GestureVerifyActivity;
import gjw.finance.activity.LineChartActivity;
import gjw.finance.activity.PieChartActivity;
import gjw.finance.activity.SettingActivity;
import gjw.finance.activity.TopUpActivity;
import gjw.finance.activity.WithdrawActivity;
import gjw.finance.base.BaseFragment;
import gjw.finance.bean.UserInfo;
import gjw.finance.utils.BitmapUtils;
import gjw.finance.utils.CacheUtils;
import gjw.finance.utils.GestureUtils;
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


    private File filesDir;
    private FileInputStream fis;
    private Bitmap zoomBitmap;
    private Bitmap bitmap;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_propert;
    }

    @Override
    protected void initData(String json) {

//        GestureMainActivity mainActivity = (GestureMainActivity) getActivity();
//        UserInfo user = mainActivity.getUser();

        UserInfo user = CacheUtils.getUser();


        //设置用户名
        tvMeName.setText(user.getData().getName());
        //设置头像
        Picasso.with(getContext())
//                .load(AppNetConfig.BASE_URL + "/images/tx.png")
                .load("http://pic32.nipic.com/20130813/9422601_092545438000_2.jpg")
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


    }

    @Override
    public void onResume() {
        super.onResume();
        Boolean update = CacheUtils.isUpdate();
        if (update) {
            try {
                //判断有没有内存卡
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    filesDir = getActivity().getExternalFilesDir("");
                } else {
                    //没有sd卡就保存在内存
                    filesDir = getActivity().getFilesDir();
                }

                File path = new File(filesDir, "123.png");

                if (path.exists()) {
                    //输出  从SD卡或内存中出来
                    fis = new FileInputStream(path);
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    Bitmap circleBitmap = BitmapUtils.circleBitmap(bitmap);
                    zoomBitmap = BitmapUtils.zoom(circleBitmap, ivMeIcon.getWidth(), ivMeIcon.getHeight());
                    ivMeIcon.setImageBitmap(zoomBitmap);
                    CacheUtils.saveImage(false);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
                setHead();
                break;
            case R.id.iv_me_icon:
                setHead();
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

//                SharedPreferences sp = getActivity().getSharedPreferences("tog", Context.MODE_PRIVATE);
//                boolean isOpen = sp.getBoolean("isOpen", false);

                boolean isOpen = GestureUtils.getState();

                //验证手势密码
                if (isOpen) {
                    startActivity(new Intent(getActivity(), GestureVerifyActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), WithdrawActivity.class));
                    Toast.makeText(getActivity(), "提现", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void setHead() {
        SharedPreferences sp = getActivity().getSharedPreferences("btn", Context.MODE_PRIVATE);
        boolean first = sp.getBoolean("First", true);
        if (first) {
            sp.edit().putBoolean("First", false).commit();
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            intent.putExtra("bitmap", bitmap);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            intent.putExtra("bitmap", zoomBitmap);
            startActivity(intent);
        }
    }

}
