package gjw.finance.activity;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import gjw.finance.R;
import gjw.finance.base.BaseActivity;
import gjw.finance.fragment.PropertFragment;
import gjw.finance.utils.BitmapUtils;
import gjw.finance.utils.CacheUtils;
import gjw.finance.utils.UIUtils;

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
    private Bitmap circleBitmap;

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
        Bitmap bitmap = getIntent().getParcelableExtra("bitmap");
        ivUserIcon.setImageBitmap(bitmap);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }


    @OnClick({R.id.base_back, R.id.iv_user_icon, R.id.tv_user_change, R.id.btn_user_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.base_back:

                Intent intent = new Intent(SettingActivity.this, PropertFragment.class);
                intent.putExtra("11", circleBitmap);

                finish();
                break;
            case R.id.iv_user_icon:
                dialog();
                break;
            case R.id.tv_user_change:
                dialog();
                break;
            case R.id.btn_user_logout:
                exit();
                break;
        }
    }

    private void exit() {
        new android.app.AlertDialog.Builder(this)
                .setTitle("你不爱我了吗？")
                .setPositiveButton("……", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
                        //清除保存的数据
                        sp.edit().clear().commit();

                        startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("爱", null)
                .show();
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
                                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                //带回调数据的启动
                                startActivityForResult(camera, CAMERA);
                                break;
                            case 1:
                                //调用图库
                                Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                //带回调数据的启动
                                startActivityForResult(picture, 9);
                                break;
                        }
                    }
                }).show();
    }


    //数据回传
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA && data != null && resultCode == RESULT_OK) {
            //获取图片
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            //压缩图片
            bitmap = BitmapUtils.zoom(bitmap, UIUtils.dp2px(62), UIUtils.dp2px(62));
            //剪裁图片
            circleBitmap = BitmapUtils.circleBitmap(bitmap);

            ivUserIcon.setImageBitmap(circleBitmap);
            //保存图片
            CacheUtils.saveBitmap(circleBitmap);

        } else {
            //解析图库的操作，跟android系统有很大相关性。不同的系统使用uri的authority有很大不同。
            //android各个不同的系统版本,对于获取外部存储上的资源，返回的Uri对象都可能各不一样,
            // 所以要保证无论是哪个系统版本都能正确获取到图片资源的话就需要针对各种情况进行一个处理了
            //这里返回的uri情况就有点多了
            //在4.4.2之前返回的uri是:content://media/external/images/media/3951或者file://....
            // 在4.4.2返回的是content://com.android.providers.media.documents/document/image

            Uri selectedImage = data.getData();
            String path = getPath(selectedImage);
            //保存到内存中
            Bitmap decodeFile = BitmapFactory.decodeFile(path);
            //压缩图片
            Bitmap zoomBitmap = BitmapUtils.zoom(decodeFile, ivUserIcon.getWidth(), ivUserIcon.getHeight());
            //裁剪图片
            Bitmap circleBitmap = BitmapUtils.circleBitmap(zoomBitmap);
            //显示
            ivUserIcon.setImageBitmap(circleBitmap);
            //保存到本地
            CacheUtils.saveBitmap(decodeFile);
        }
    }


    /**
     * 根据系统相册选择的文件获取路径
     *
     * @param uri
     */
    @SuppressLint("NewApi")
    private String getPath(Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        //高于4.4.2的版本
        if (sdkVersion >= 19) {
            Log.e("TAG", "uri auth: " + uri.getAuthority());
            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));
                return getDataColumn(this, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(this, contentUri, selection, selectionArgs);
            } else if (isMedia(uri)) {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor actualimagecursor = this.managedQuery(uri, proj, null, null, null);
                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToFirst();
                return actualimagecursor.getString(actual_image_column_index);
            }


        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(this, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * uri路径查询字段
     *
     * @param context
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isMedia(Uri uri) {
        return "media".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
