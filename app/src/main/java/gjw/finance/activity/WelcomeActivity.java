package gjw.finance.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import butterknife.InjectView;
import gjw.finance.R;

public class WelcomeActivity extends AppCompatActivity {

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
    @InjectView(R.id.welcome3)
    ImageView welcome3;
    @InjectView(R.id.welcome4)
    ImageView welcome4;
    @InjectView(R.id.ll_welcome)
    LinearLayout llWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);

        //设置版本号
        setVersion();
        //設置動畫
        initView();

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                onStartActivity();
//            }
//        }, 3000);

    }

    private String setVersion() {
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

    //请求本地动图 & 设置欢迎动画
    private void initView() {

        Glide.with(this).load(R.drawable.mq).into(welcome1);
        Glide.with(this).load(R.drawable.me).into(welcome2);
        Glide.with(this).load(R.drawable.mw).into(welcome3);
        Glide.with(this).load(R.drawable.mu).into(welcome4);

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
                onStartActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        welcome.startAnimation(aa);
    }

    //跳转页面
    private void onStartActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        handler.removeCallbacksAndMessages(null);
    }
}
