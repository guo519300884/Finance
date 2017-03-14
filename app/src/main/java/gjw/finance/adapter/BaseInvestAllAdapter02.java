package gjw.finance.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 皇上 on 2017/3/14.
 */

public abstract class BaseInvestAllAdapter02<T> extends BaseAdapter {

    public final List<T> investAllBeanData = new ArrayList<>();

    public BaseInvestAllAdapter02(List<T> investAllBeanData) {
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = initView(); //子类实现布局
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        T t = investAllBeanData.get(position);

        setData(t, convertView);
//        viewHolder.pName.setText(dataBean.getName());
//        viewHolder.pMoney.setText(dataBean.getMoney());
//        viewHolder.pMinnum.setText(dataBean.getMinTouMoney());
//        viewHolder.pMinzouzi.setText(dataBean.getMemberNum());
//        viewHolder.pSuodingdays.setText(dataBean.getSuodingDays());
//        viewHolder.pYearlv.setText(dataBean.getYearRate());
//        viewHolder.pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));

        return convertView;
    }

    protected abstract View initView();

    protected abstract void setData(T t, View convertView);

    class ViewHolder {
        ViewHolder(View view) {
            view.setTag(this);
        }
    }
}
