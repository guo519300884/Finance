package gjw.finance.adapter;

import java.util.List;

import gjw.finance.bean.InvestAllBean;
import gjw.finance.viewholder.BaseHolder;
import gjw.finance.viewholder.InvestAllHolder;

/**
 * Created by 皇上 on 2017/3/14.
 */

public class InvestAllAdapter03 extends BaseInvestAllAdapter03<InvestAllBean.DataBean> {

    public InvestAllAdapter03(List<InvestAllBean.DataBean> investAllBeanData) {
        super(investAllBeanData);
    }

    @Override
    public BaseHolder getHolder() {
        return new InvestAllHolder();
    }

}
