/**
 *
 */

package com.kuloud.android.clicktimes;

import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;


/**
 * @author kuloud
 */
public abstract class OnValidClickListener implements OnClickListener {
    private static final long MIN_CLICK_DURATION = 800;
    private long mOffset;
    private long mInvalidOffset;

    public OnValidClickListener() {
        this(MIN_CLICK_DURATION);
    }

    public OnValidClickListener(long offset) {
        this.mOffset = offset;
    }

    /**
     * @param offset
     * @param invalidOffset
     */
    public OnValidClickListener(long offset, long invalidOffset) {
        this.mOffset = offset;
        this.mInvalidOffset = invalidOffset;
    }

    public abstract void onValidClick(View v);

    /**
     * @param v
     */
    public void onInvalidClick(View v) {
        // NOTE: Implements this if needed.
    }

    @Override
    public void onClick(View v) {
        if (UIUtils.isClickValid(v, mOffset)) {
            onValidClick(v);
        } else if (filterInvalidClick(v)) {
            onInvalidClick(v);
        }
    }

    /**
     * @param v
     * @return
     */
    private boolean filterInvalidClick(View v) {
        if (mInvalidOffset <= 0) {
            return true;
        }
        long current = SystemClock.elapsedRealtime();
        Object tag = v.getTag(R.id.tag_view_click_valid_offset);
        boolean valid;
        if (tag == null) {
            valid = true;
        } else {
            long last = (Long) tag;
            long duration = current - last;
            valid = duration > mInvalidOffset;
        }
        if (valid) {
            v.setTag(R.id.tag_view_click_valid_offset, current);
        }
        return valid;
    }
}
