package gjw.finance.viewholder;

import android.view.View;
import android.widget.TextView;

import butterknife.InjectView;
import gjw.finance.R;
import gjw.finance.bean.InvestAllBean;
import gjw.finance.utils.UIUtils;
import gjw.finance.view.MyProgress;

/**
 * Created by 皇上 on 2017/3/14.
 */

public class InvestAllHolder extends BaseHolder<InvestAllBean.DataBean> {

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

    @Override
    public View initView() {
        return UIUtils.getView(R.layout.adapter_invest_all);
    }

    @Override
    public void setChildData() {
        InvestAllBean.DataBean dataBean = getT();
        pName.setText(dataBean.getName());
        pMoney.setText(dataBean.getMoney());
        pMinnum.setText(dataBean.getMinTouMoney());
        pMinzouzi.setText(dataBean.getMemberNum());
        pSuodingdays.setText(dataBean.getSuodingDays());
        pYearlv.setText(dataBean.getYearRate());

        int parseInt = Integer.parseInt(dataBean.getProgress());

        pProgresss.setPp(parseInt);
//        pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));
    }
}
