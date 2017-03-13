package gjw.finance.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import gjw.finance.R;
import gjw.finance.bean.HomeBean;
import gjw.finance.utils.AppNetConfig;
import gjw.finance.utils.LoadNet;
import gjw.finance.utils.LoadNetHttp;

/**
 * Created by 皇 上 on 2017/3/10.
 */

public class HomeFragment extends BaseFragment {

    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.tv_home_product)
    TextView tvHomeProduct;
    @InjectView(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    @InjectView(R.id.banner)
    Banner banner;
    private View view;
    private HomeBean homeBean;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        //设置监听 设置标题栏
        initListener();

        //二次封装的优点：1.便于优化管理 2.使用方便快捷
        LoadNet.getDataPost(AppNetConfig.INDEX, new LoadNetHttp() {
            @Override
            public void success(String context) {
                processData(context);
            }

            @Override
            public void failure(String error) {
                Toast.makeText(context, "联网失败" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListener() {
        baseTitle.setText("首页");
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);
    }

    private void processData(String context) {

        homeBean = JSON.parseObject(context, HomeBean.class);

        tvHomeProduct.setText(homeBean.getProInfo().getName());
        tvHomeYearrate.setText(Double.parseDouble(homeBean.getProInfo().getYearRate()) / 100 + "%");
        //界面的展示一定要在主线程
        if (Thread.currentThread().getName() == "main") {
            initBanner(homeBean);
        }
    }

    private void initBanner(HomeBean homeBean) {

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //数据转化   转化为URL集合
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < homeBean.getImageArr().size(); i++) {
            urls.add(AppNetConfig.BASE_URL + homeBean.getImageArr().get(i).getIMAPAURL());
        }
        //设置图片集合
        banner.setImages(urls);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Glide 加载图片简单用法
//            Glide.with(context).load(path).into(imageView);

            //Picasso 加载图片简单用法
            Picasso.with(context).load(AppNetConfig.INDEX).into(imageView);

            //用 fresco加载图片简单用法，记得要写下面的createImageView方法
//            Uri uri = Uri.parse((String) path);
//            imageView.setImageURI(uri);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
