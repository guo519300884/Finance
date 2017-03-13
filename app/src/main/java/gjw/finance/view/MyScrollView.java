package gjw.finance.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by 皇上 on 2017/3/13.
 */

public class MyScrollView extends ScrollView {


    private int lastY;
    private View childView;
    private Rect rect = new Rect();
    private boolean isAnimtionEnd = true;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getChildCount() == 0 || !isAnimtionEnd) {
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
                    //判断是否已经记录  若没有就保存
                    if (rect.isEmpty()) {
                        rect.set(childView.getLeft(), childView.getTop(),
                                childView.getRight(), childView.getBottom());
                    }

                    childView.layout(childView.getLeft(),
                            childView.getTop() + moveY / 2,
                            childView.getRight(),
                            childView.getBottom() + moveY / 2);
                }
                lastY = evevtY;
                break;
            case MotionEvent.ACTION_UP:
                //当原来的位置有记录的时候(不爲空)并且动画是结束的时候再执行
                if (!rect.isEmpty() && isAnimtionEnd) {
                    //获取原来的高度和现在拉动位置的差
                    int translateY = childView.getBottom() - rect.bottom;
                    //平移动画移动的距离
                    TranslateAnimation ta = new TranslateAnimation(0, 0, 0, -translateY);
                    ta.setDuration(200);
                    //动画监听
                    ta.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            //动画开始时候调用 要把判断设置成false；
                            isAnimtionEnd = false;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            //动画结束时调用
                            //清除动画
                            childView.clearAnimation();
                            //回弹  将动画返回到记录的位置
                            childView.layout(rect.left, rect.top, rect.right, rect.bottom);
                            //清除记录的位置
                            rect.setEmpty();
                            isAnimtionEnd = true;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    childView.startAnimation(ta);
                }
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
     * 拦截事件
     * 返回true 拦截
     * 返回false 不拦截
     */

    private int downy;
    private int downx;
    private int lastX;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean isOnIntercept = false;

        int eventx = (int) ev.getX();
        int eventy = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录点击位置
                lastY = downy = eventy;
                lastX = downx = eventy;
                break;
            case MotionEvent.ACTION_MOVE:

                int absx = Math.abs(eventx - downx);
                int absy = Math.abs(eventy - downy);

                if (absy > absx && absy >= 10) {
                    isOnIntercept = true;
                }
                lastX = eventx;
                lastY = eventy;
                break;
        }
        return isOnIntercept;
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
