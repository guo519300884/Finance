package gjw.finance.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;
import gjw.finance.R;
import gjw.finance.utils.AppNetConfig;
import gjw.finance.utils.LoadNet;
import gjw.finance.utils.LoadNetHttp;

public class LoginActivity extends BaseActivity {

    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.tv_login_number)
    TextView tvLoginNumber;
    @InjectView(R.id.login_et_number)
    EditText loginEtNumber;
    @InjectView(R.id.rl_login)
    RelativeLayout rlLogin;
    @InjectView(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @InjectView(R.id.login_et_pwd)
    EditText loginEtPwd;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.login_regitster_tv)
    TextView loginRegitsterTv;

    @Override
    public void initTitle() {
        baseTitle.setText("登录");
        baseSetting.setVisibility(View.INVISIBLE);
        baseBack.setVisibility(View.INVISIBLE);

    }

    @Override
    public void initListener() {
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public void initData() {


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.base_back, R.id.btn_login, R.id.login_regitster_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                login();
                break;
            case R.id.login_regitster_tv:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }

    private void login() {
        String phone = loginEtNumber.getText().toString().trim();
        String pw = loginEtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pw)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", pw);
        //去服务区登录
        LoadNet.getDataPost(AppNetConfig.LOGIN, map, new LoadNetHttp() {

            @Override
            public void success(String context) {
                Log.e("TAG", "LoginActivity success()" + context);
                Toast.makeText(LoginActivity.this, "success" + context, Toast.LENGTH_SHORT).show();
                JSONObject jsonObject = JSON.parseObject(context);
                Log.e("TAG", "LoginActivity success()" + jsonObject);
                Boolean success = jsonObject.getBoolean("success");
                if (success) {
                    //成功就解析数据
//                            JSON.parseObject(context,);

                }


            }

            @Override
            public void failure(String error) {
                Toast.makeText(LoginActivity.this, "error" + error, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
