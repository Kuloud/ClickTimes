/**
 *
 */

package com.kuloud.android.clicktimes;

import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;

import java.security.InvalidParameterException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * @author kuloud
 */
public abstract class OnCountClickListener implements OnClickListener {
    private static final int VALID_DURATION_IN_MILLIS = 3000;
    private static final int START_CLICK_COUNT = 3;
    private static final int END_CLICK_COUNT = 5;
    private Queue<Long> mClickTimestamps;
    private int startTimes;
    private int endTimes;

    public OnCountClickListener() {
        this(START_CLICK_COUNT, END_CLICK_COUNT);
    }

    /**
     * @param startTimes
     * @param endTimes
     */
    public OnCountClickListener(int startTimes, int endTimes) {
        if (startTimes < 0 || startTimes > endTimes || endTimes <= 0) {
            throw new InvalidParameterException("Valid params: endTimes >= startTimes >= 0 && endTimes > 0");
        }
        mClickTimestamps = new LinkedBlockingQueue<Long>(endTimes);
        this.startTimes = startTimes;
        this.endTimes = endTimes;
    }

    @Override
    public void onClick(View v) {
        long currentTimestamp = SystemClock.elapsedRealtime();
        while (!mClickTimestamps.isEmpty()
                && ((currentTimestamp - mClickTimestamps.peek()) >= VALID_DURATION_IN_MILLIS)) {
            mClickTimestamps.remove();
        }
        mClickTimestamps.add(currentTimestamp);
        int count = mClickTimestamps.size() - startTimes;
        if (count >= 0) {
            int countDown = endTimes - startTimes - count;
            if (countDown >= 0) {
                onCountdown(count);
            }
        }
    }

    protected abstract void onCountdown(int count);
}
