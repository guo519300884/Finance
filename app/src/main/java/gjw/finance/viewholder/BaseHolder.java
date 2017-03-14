package gjw.finance.viewholder;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by 皇上 on 2017/3/14.
 */

public abstract class BaseHolder<T> {

    private final View rootView;
    private T t;


    public BaseHolder() {
        rootView = initView();
        ButterKnife.inject(this, rootView);
        rootView.setTag(this);
    }

    public View getView() {
        return rootView;
    }

    public T getT() {
        return t;
    }

    public void setData(T t) {
        this.t = t;
        setChildData();
    }

    public abstract View initView();

    public abstract void setChildData();

}
