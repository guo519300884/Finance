package gjw.finance.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import gjw.finance.viewholder.BaseHolder;

/**
 * Created by 皇上 on 2017/3/14.
 */

public abstract class BaseInvestAllAdapter03<T> extends BaseAdapter {

    public final List<T> investAllBeanData = new ArrayList<>();

    public BaseInvestAllAdapter03(List<T> investAllBeanData) {
        if (investAllBeanData != null && investAllBeanData.size() > 0) {
            this.investAllBeanData.clear();
            this.investAllBeanData.addAll(investAllBeanData);
        }
    }

    @Override
    public int getCount() {
        return investAllBeanData.size();
    }

    @Override
    public Object getItem(int position) {
        return investAllBeanData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseHolder baseHolder;
        if (convertView == null) {
            baseHolder = getHolder();
        } else {
            baseHolder = (BaseHolder) convertView.getTag();
        }

        T t = investAllBeanData.get(position);
        baseHolder.setData(t);

        return baseHolder.getView();
    }

    protected abstract BaseHolder getHolder();
}
