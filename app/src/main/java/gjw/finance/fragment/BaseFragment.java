package gjw.finance.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import gjw.finance.view.LoadingPager;

/**
 * Created by 皇 上 on 2017/3/10.
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPager loadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        loadingPager = new LoadingPager(getActivity()) {
            @Override
            protected void onSuccess(ResultState resufltState, View sucessView) {
                ButterKnife.inject(BaseFragment.this, sucessView);
                initData(resufltState.getJson());
            }

            @Override
            protected String getUrl() {
                return getChildUrl();
            }

            @Override
            public int getViewId() {
                return getLayoutId();
            }
        };
        return loadingPager;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
//        initData();
        //初始化监听
//        initListener();
        loadingPager.loadData();
    }

    protected abstract  @LayoutRes
    int getLayoutId();

    protected abstract void initData(String json);

    //每一个fragment返回的地址
    protected abstract String getChildUrl();


    protected abstract void initListener();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
