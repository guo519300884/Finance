package gjw.finance.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import gjw.finance.R;
import gjw.finance.fragment.BaseFragment;
import gjw.finance.fragment.HomeFragment;
import gjw.finance.fragment.InvestFragment;
import gjw.finance.fragment.MoreFragment;
import gjw.finance.fragment.PropertFragment;

public class MainActivity extends AppCompatActivity {


    List<BaseFragment> fragments;

    @InjectView(R.id.rg_btn)
    RadioGroup rgBtn;
    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    private int position;
    //当前页面
    private Fragment nowFragment;
    //前一页面
    private Fragment tempFragment;
    private boolean isDoulbe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initListener();
        inivData();
    }

    private void initListener() {
        rgBtn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_invest:
                        position = 1;
                        break;
                    case R.id.rb_propert:
                        position = 2;
                        break;
                    case R.id.rb_more:
                        position = 3;
                        break;

                }
                nowFragment = fragments.get(position);
                switchFragment(nowFragment);
            }
        });
    }

    private void switchFragment(Fragment nowFragment) {
        if (tempFragment != nowFragment) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if (!nowFragment.isAdded()) {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.fl_main, nowFragment);
            } else {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(nowFragment);
            }
            ft.commit();
            tempFragment = nowFragment;
        }
    }

    private void inivData() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new InvestFragment());
        fragments.add(new PropertFragment());
        fragments.add(new MoreFragment());
        //默认选择主页
        switchFragment(fragments.get(0));
    }


    //双击退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Toast.makeText(this, "再点我分手", Toast.LENGTH_SHORT).show();

            if (isDoulbe) {
                finish();
            }

            isDoulbe = true;
            //定时器
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isDoulbe = false;
                }
            }, 2000);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
