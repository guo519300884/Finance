package gjw.finance.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import gjw.finance.R;
import gjw.finance.activity.AboutActivity;
import gjw.finance.activity.GestureEditActivity;
import gjw.finance.activity.RegisterActivity;
import gjw.finance.base.BaseFragment;
import gjw.finance.utils.AppNetConfig;
import gjw.finance.utils.GestureUtils;
import gjw.finance.utils.LoadNet;
import gjw.finance.utils.LoadNetHttp;

import static gjw.finance.utils.GestureUtils.saveState;

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
    @InjectView(R.id.tv_more_regist)
    TextView tvMoreRegist;
    @InjectView(R.id.toggle_more)
    ToggleButton toggleMore;
    @InjectView(R.id.tv_more_reset)
    TextView tvMoreReset;
    @InjectView(R.id.tv_more_phone)
    TextView tvMorePhone;
    @InjectView(R.id.rl_more_contact)
    RelativeLayout rlMoreContact;
    @InjectView(R.id.tv_more_fankui)
    TextView tvMoreFankui;
    @InjectView(R.id.tv_more_share)
    TextView tvMoreShare;
    @InjectView(R.id.tv_more_about)
    TextView tvMoreAbout;
    private View view;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initData(String json) {
        baseTitle.setText("还有呢");
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);

        toggleMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {//打开手势密码
                    //存储当前的状态 用来记录是否打开手势密码
                    saveState(isChecked);

                    if (!GestureUtils.getIsSrtting()) {//判断是否设置过手势密码

                        GestureUtils.setSetting(true);//第一次设置手势密码
                        startActivity(new Intent(getActivity(), GestureEditActivity.class));
                    }
                } else {//关闭手势密码
                    //存储当前的状态 用来记录是否打开手势密码
                    saveState(isChecked);
                }
            }
        });
    }


    @Override
    protected String getChildUrl() {
        return AppNetConfig.INDEX;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_more_regist, R.id.tv_more_reset, R.id.tv_more_fankui, R.id.tv_more_share, R.id.tv_more_about, R.id.tv_more_phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_more_regist:
                startActivity(new Intent(getActivity(), RegisterActivity.class));
                break;
            case R.id.tv_more_reset:
                //重置手势密码
                startActivity(new Intent(getActivity(), GestureEditActivity.class));
                break;
            case R.id.tv_more_fankui:
                view = View.inflate(getActivity(), R.layout.more_feedback, null);

                new AlertDialog.Builder(getActivity())
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Map<String, String> map = new HashMap<String, String>();
                                map.put("department", "");
                                map.put("content", "");
                                LoadNet.getDataPost(AppNetConfig.FEEDBACK, map, new LoadNetHttp() {
                                    @Override
                                    public void success(String context) {
                                        Toast.makeText(getActivity(), "反馈成功", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void failure(String error) {
                                        Toast.makeText(getActivity(), "嘿嘿嘿", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();


                break;
            case R.id.tv_more_share:

                break;
            case R.id.tv_more_about:

                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.tv_more_phone:
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:010-56253825");
                intent.setData(uri);
                startActivity(intent);
                break;
        }
    }
}
