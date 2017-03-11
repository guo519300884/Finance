package gjw.finance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
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


        initView();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }, 3000);


        Glide.with(this).load(R.drawable.mq).into(welcome1);
        Glide.with(this).load(R.drawable.me).into(welcome2);
        Glide.with(this).load(R.drawable.mw).into(welcome3);
        Glide.with(this).load(R.drawable.mu).into(welcome4);

    }

    //跳转页面
    private void initData() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initView() {
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(3000);
        aa.setFillBefore(true);
        welcome.startAnimation(aa);
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                initData();
//                break;
//        }
//        return super.onTouchEvent(event);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
