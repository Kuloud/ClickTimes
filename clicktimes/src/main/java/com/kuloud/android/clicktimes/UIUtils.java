package com.kuloud.android.clicktimes;

import android.os.SystemClock;
import android.view.View;

/**
 * Created by kuloud on 7/20/15.
 */
public final class UIUtils {
    /**
     * 定义两次正常的点击之间的时间间隔不少于800ms
     */
    public static final long MIN_CLICK_DURATION = 800L;

    public static boolean isClickValid(View v) {
        return isClickValid(v, MIN_CLICK_DURATION);
    }

    /**
     * 给View设置一个timestamp,判断两次点击的时间间距,防止非正常连续点击的多次事件触发
     *
     * @param v
     * @param offset
     * @return
     */
    public static boolean isClickValid(View v, long offset) {
        boolean valid;
        long current = SystemClock.elapsedRealtime();
        Object tag = v.getTag(R.id.tag_view_click_valid);
        if (tag == null) {
            valid = true;
        } else {
            long last = (Long) tag;
            long duration = current - last;
            valid = duration > offset;
        }
        if (valid) {
            v.setTag(R.id.tag_view_click_valid, current);
        }
        return valid;
    }
}
