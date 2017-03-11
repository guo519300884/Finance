package gjw.finance.fragment;

import android.view.View;

import gjw.finance.R;

/**
 * Created by 皇 上 on 2017/3/10.
 */

public class HomeFragment extends BaseFragment {

    private View view;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_home, null);
        return view;
    }




}
