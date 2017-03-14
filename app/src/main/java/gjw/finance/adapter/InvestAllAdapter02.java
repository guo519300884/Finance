package gjw.finance.adapter;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import gjw.finance.R;
import gjw.finance.bean.InvestAllBean;
import gjw.finance.utils.UIUtils;

/**
 * Created by 皇上 on 2017/3/14.
 */

public class InvestAllAdapter02 extends BaseInvestAllAdapter02<InvestAllBean.DataBean> {

    public InvestAllAdapter02(List<InvestAllBean.DataBean> investAllBeanData) {
        super(investAllBeanData);

    }

    @Override
    protected void setData(InvestAllBean.DataBean dataBean, View convertView) {
        TextView pName = (TextView) convertView.findViewById(R.id.p_name);
        pName.setText(dataBean.getName());
    }

    @Override
    protected View initView() {
        return UIUtils.getView(R.layout.adapter_invest_all);
    }

}
