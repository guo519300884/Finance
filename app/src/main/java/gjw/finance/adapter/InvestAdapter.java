package gjw.finance.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import gjw.finance.base.BaseFragment;

/**
 * Created by 皇上 on 2017/3/14.
 */

public class InvestAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments = new ArrayList<>();

    public InvestAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        if (fragments != null && fragments.size() > 0) {
            this.fragments = fragments;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
