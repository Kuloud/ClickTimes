package com.kuloud.android.clicktimes;

import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

/**
 * Created by kuloud on 15-7-20.
 */
public final class TimeUtils {
    public static long since(long timeInMillis) {
        return TimeUnit.MILLISECONDS.toMillis(SystemClock.elapsedRealtime() - timeInMillis);
    }
}
