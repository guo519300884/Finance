package gjw.finance.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by 皇上 on 2017/3/13.
 */

public class MyScrollView extends ScrollView {


    private int lastY;
    private View childView;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getChildCount() == 0) {
            return super.onTouchEvent(ev);
        }
        /**
         * getY(); 相对于父布局的Y值
         * getrawY(); 相对于屏幕的Y值
         */
        int evevtY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录按下时的坐标
                lastY = evevtY;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isNeedMove()) {
                    int moveY = evevtY - lastY;
                    childView.layout(childView.getLeft(),
                            childView.getTop() + moveY / 2,
                            childView.getRight(),
                            childView.getBottom() + moveY / 2);
                }
                lastY = evevtY;
                break;
            case MotionEvent.ACTION_UP:

                break;

        }
        return super.onTouchEvent(ev);
    }

    private boolean isNeedMove() {
        //scrollView的高度
        int scrollViewHeight = this.getMeasuredHeight();
        //子视图的高度
        int childHeight = childView.getMeasuredHeight();
        //滑动距离差
        int dy = childHeight - scrollViewHeight;
        //拿到滑动的距离  往下滑动是变小 往上滑动是变大
        int scrollY = getScrollY();
        if (scrollY <= 0 || scrollY >= dy) {
            return true;
        }
        return false;
    }

    /**
     * 布局加载完成后调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            childView = getChildAt(0);
        }
    }
}
