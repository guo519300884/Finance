package gjw.finance.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;
import gjw.finance.R;
import gjw.finance.utils.AppNetConfig;
import gjw.finance.utils.LoadNet;
import gjw.finance.utils.LoadNetHttp;

public class RegisterActivity extends BaseActivity {

    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.et_register_number)
    EditText etRegisterNumber;
    @InjectView(R.id.et_register_name)
    EditText etRegisterName;
    @InjectView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @InjectView(R.id.et_register_pwdagain)
    EditText etRegisterPwdagain;
    @InjectView(R.id.btn_register)
    Button btnRegister;

    @Override
    public void initTitle() {
        baseTitle.setText("注册");
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
        return R.layout.activity_register;
    }

    @OnClick({R.id.base_back, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.base_back:
                finish();
                break;
            case R.id.btn_register:
                //校验
                String number = etRegisterNumber.getText().toString().trim();
                String name = etRegisterName.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
                String pwda = etRegisterPwdagain.getText().toString().trim();

                //判空
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number)
                        || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwda)) {

                    Toast.makeText(this, "不能有空的呀", Toast.LENGTH_SHORT).show();
                    return;

                } else if (!pwd.equals(pwda)) {
                    Toast.makeText(this, "两次密码不一样", Toast.LENGTH_SHORT).show();
                    etRegisterPwd.setText("");
                    etRegisterPwdagain.setText("");
                }

                Map<String, String> map = new HashMap<>();
                map.put("phone", number);
                map.put("name", name);
                map.put("password", pwd);
                LoadNet.getDataPost(AppNetConfig.REGISTER, map, new LoadNetHttp() {
                    @Override
                    public void success(String context) {

                        //                        JSONObject jsonObject = JSON.parseObject(context);
                        //                        Boolean isExist = jsonObject.getBoolean("isExist");
                        try {
                            org.json.JSONObject jsonObject = new org.json.JSONObject(context);
                            boolean isExist = jsonObject.optBoolean("isExist");
                            if (isExist) {
                                Toast.makeText(RegisterActivity.this, "此账号已存在", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failure(String error) {

                    }
                });

                break;
        }
    }

    public void isEmpty(String name, String alert) {
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, alert, Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
