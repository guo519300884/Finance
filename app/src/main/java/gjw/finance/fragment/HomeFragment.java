package gjw.finance.fragment;

import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import gjw.finance.R;
import gjw.finance.bean.HomeBean;
import gjw.finance.utils.AppNetConfig;
import gjw.finance.utils.ThreadPool;
import gjw.finance.view.MyProgress;

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
    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.tv_home_product)
    TextView tvHomeProduct;
    @InjectView(R.id.roundPro_home)
    MyProgress roundProHome;
    @InjectView(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    private HomeBean homeBean;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData(String json) {
        homeBean = JSON.parseObject(json, HomeBean.class);
        tvHomeProduct.setText(this.homeBean.getProInfo().getName());
        tvHomeYearrate.setText(Double.parseDouble(this.homeBean.getProInfo().getYearRate()) + "%");

        //界面的展示一定要在主线程
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            initProgress(this.homeBean.getProInfo());
            initBanner(this.homeBean);
        }
    }

    @Override
    protected String getChildUrl() {
        return AppNetConfig.INDEX;
    }

    public void initListener() {
        baseTitle.setText("首页");
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);
    }

    private void initProgress(final HomeBean.ProInfoBean proInfo) {

        ThreadPool.getInstance().getGlobalThread().execute(new Runnable() {
            @Override
            public void run() {
                int progress = Integer.parseInt(proInfo.getProgress());
                for (int i = 0; i <= progress; i++) {
                    SystemClock.sleep(30);
                    roundProHome.setProgress(i);
                }
            }
        });
    }

    private void initBanner(HomeBean homeBean) {

        //设置指示器
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //指示器位置
        banner.setBannerStyle(BannerConfig.RIGHT);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //数据转化   转化为URL集合
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < homeBean.getImageArr().size(); i++) {
            urls.add(AppNetConfig.BASE_URL + homeBean.getImageArr().get(i).getIMAURL());
        }
        //设置图片集合
        banner.setImages(urls);

        //动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        //轮播间隔时间
        banner.setDelayTime(4000);
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
            Picasso.with(context).load((String) path).into(imageView);

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
