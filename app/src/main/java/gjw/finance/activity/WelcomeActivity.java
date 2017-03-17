package gjw.finance.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.InjectView;
import gjw.finance.R;
import gjw.finance.base.BaseActivity;
import gjw.finance.utils.AppManager;
import gjw.finance.utils.CacheUtils;

public class WelcomeActivity extends BaseActivity {

    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_edition)
    TextView tvEdition;
    @InjectView(R.id.welcome)
    RelativeLayout welcome;

    public Handler handler = new Handler();
    @InjectView(R.id.welcome1)
    ImageView welcome1;
    @InjectView(R.id.welcome2)
    ImageView welcome2;
    @InjectView(R.id.ll_welcome)
    LinearLayout llWelcome;

    @Override
    public void initData() {

        //添加进Activity管理栈
        AppManager.getInstance().addActivity(this);
        //设置版本号
        setVersion();
        //設置動畫
        initView();

        /**
         *  handler.postDelayed(new Runnable() {
        @Override public void run() {
        onStartActivity();
        }
        }, 3000);
         */
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }


    private String getVersion() {
        try {
            //获取到包的管理器
            PackageManager packageManager = getPackageManager();
            //获取到包的信息
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            // versionCode 每次更新必须加 1
            int versionCode = packageInfo.versionCode;
            //获取当前的版本号
            String versionName = packageInfo.versionName;
            //返回版本号
            return versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void setVersion() {
        tvEdition.setText(getVersion());
    }

    //请求本地动图 & 设置欢迎动画
    private void initView() {

        Glide.with(this).load(R.drawable.mq).into(welcome1);
        Glide.with(this).load(R.drawable.me).into(welcome2);

        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(3000);
        aa.setFillBefore(true);
        //动画监听
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isLogin()) {
                    onStartActivity();
                    finish();
                } else {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        welcome.startAnimation(aa);
    }

    private boolean isLogin() {
//        String name = getUser().getData().getName();
        String name = CacheUtils.getUser().getData().getName();

        if (TextUtils.isEmpty(name)) {
            return false;
        }
        return true;
    }

    //跳转页面
    private void onStartActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().remove(this);
    }
}
