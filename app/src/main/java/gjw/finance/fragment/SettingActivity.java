package gjw.finance.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import gjw.finance.R;
import gjw.finance.activity.BaseActivity;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class SettingActivity extends BaseActivity {

    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @InjectView(R.id.tv_user_change)
    TextView tvUserChange;
    @InjectView(R.id.btn_user_logout)
    Button btnUserLogout;

    String[] names = {"相机", "相册"};

    @Override
    public void initTitle() {
        baseTitle.setText("設置");
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
        return R.layout.activity_setting;
    }


    @OnClick({R.id.base_back, R.id.iv_user_icon, R.id.tv_user_change, R.id.btn_user_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.base_back:
                finish();
                break;
            case R.id.iv_user_icon:
                dialog();
                break;
            case R.id.tv_user_change:
                dialog();
                break;
            case R.id.btn_user_logout:

                new android.app.AlertDialog.Builder(this)
                        .setTitle("你不爱我了吗？")
                        .setPositiveButton("……", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);

                            }
                        })
                        .setNegativeButton("爱", null)
                        .show();
                break;
        }
    }

    public void dialog() {
        new AlertDialog.Builder(this)
                .setTitle("你想去哪儿拿图")
                .setItems(names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                //调用摄像头
                                camera();
                                break;
                            case 1:
                                //调用图库
                                photoAlbum();
                                break;
                        }
                    }
                }).show();
    }


    private void camera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA);
    }

    private void photoAlbum() {
        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picture, 6);
    }
}
