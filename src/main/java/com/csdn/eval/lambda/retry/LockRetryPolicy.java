package com.csdn.eval.lambda.retry;

import java.util.concurrent.Callable;

public class LockRetryPolicy {
    private final int lockRetryInterval;
    private final int lockRetryTimes;

    public LockRetryPolicy(int lockRetryInterval, int lockRetryTimes) {
        this.lockRetryInterval = lockRetryInterval;
        this.lockRetryTimes = lockRetryTimes;
    }

    public <T> T execute(Callable<T> callable) throws Exception {
        return doRetryOnLockConflict(callable);
    }

    protected <T> T doRetryOnLockConflict(Callable<T> callable) throws Exception {
        LockRetryController lockRetryController = new LockRetryController(lockRetryInterval, lockRetryTimes);
        while (true) {
            try {
                return callable.call();
            } catch (Exception e) {
                onException(e);
                lockRetryController.sleep(e);
            }
        }
    }

    /**
     * Callback on exception in doLockRetryOnConflict.
     *
     * @param e invocation exception
     * @throws Exception error
     */
    protected void onException(Exception e) throws Exception {
    }
}