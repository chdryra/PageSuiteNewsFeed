package com.chdryra.android.pagesuitenewsfeed;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * Created by: Rizwan Choudrey
 * On: 01/07/2017
 * Email: rizwan.choudrey@gmail.com
 */
class AsyncLatch {
    private static final int WAIT_TIME = 500000000;

    private CountDownLatch mLatch;
    private long mAwaitTime;
    private boolean mTriggered = false;

    AsyncLatch() {
        this(WAIT_TIME);
    }

    AsyncLatch(long awaitTime) {
        mLatch = new CountDownLatch(1);
        mAwaitTime = awaitTime;
    }

    void waitForTrigger() {
        try {
            mLatch.await(mAwaitTime, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Latch got interrupted: " + e.getMessage());
        }
    }

    void trigger() {
        mTriggered = true;
        mLatch.countDown();
    }

    boolean wasTriggered() {
        return mTriggered;
    }
}
