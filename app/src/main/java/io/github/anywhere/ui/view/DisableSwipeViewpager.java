package io.github.anywhere.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by PCPC on 2017-06-05.
 */

public class DisableSwipeViewpager extends ViewPager {
    private boolean mIsEnabledSwipe = true;

    public DisableSwipeViewpager(Context context) {
        super(context);
    }

    public DisableSwipeViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mIsEnabledSwipe) {
            return false;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (!mIsEnabledSwipe) {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }

    public void setEnabledSwipe(boolean enabled) {
        mIsEnabledSwipe = enabled;
    }

}