package com.csdn.eval.lambda;

public class LockRetryController {

    private final int lockRetryInterval;

    private int lockRetryTimes;

    public LockRetryController(int lockRetryInterval, int lockRetryTimes) {
        this.lockRetryInterval = lockRetryInterval;
        this.lockRetryTimes = lockRetryTimes;
    }

    /**
     * Sleep.
     */
    public void sleep(Exception e) throws Exception {
        // prioritize the rollback of other transactions
        if (--lockRetryTimes < 0) {
            throw new RuntimeException("Global lock wait timeout", e);
        }

        try {
            Thread.sleep(lockRetryInterval);
        } catch (InterruptedException ignore) {
        }
    }
}
