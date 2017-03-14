package gjw.finance.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import gjw.finance.R;
import gjw.finance.bean.InvestAllBean;
import gjw.finance.view.MyProgress;

/**
 * Created by 皇上 on 2017/3/14.
 */

public class InvestAllAdapter extends BaseAdapter {

    private final List<InvestAllBean.DataBean> investAllBeanData;

    public InvestAllAdapter(List<InvestAllBean.DataBean> investAllBeanData) {
        this.investAllBeanData = investAllBeanData;
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

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_invest_all, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        InvestAllBean.DataBean dataBean = investAllBeanData.get(position);

        viewHolder.pName.setText(dataBean.getName());
        viewHolder.pMoney.setText(dataBean.getMoney());
        viewHolder.pMinnum.setText(dataBean.getMinTouMoney());
        viewHolder.pMinzouzi.setText(dataBean.getMemberNum());
        viewHolder.pSuodingdays.setText(dataBean.getSuodingDays());
        viewHolder.pYearlv.setText(dataBean.getYearRate());
        viewHolder.pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.p_name)
        TextView pName;
        @InjectView(R.id.p_money)
        TextView pMoney;
        @InjectView(R.id.p_yearlv)
        TextView pYearlv;
        @InjectView(R.id.p_suodingdays)
        TextView pSuodingdays;
        @InjectView(R.id.p_minzouzi)
        TextView pMinzouzi;
        @InjectView(R.id.p_minnum)
        TextView pMinnum;
        @InjectView(R.id.p_progresss)
        MyProgress pProgresss;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
