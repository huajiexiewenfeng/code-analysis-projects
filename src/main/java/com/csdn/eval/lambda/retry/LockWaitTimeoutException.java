package com.csdn.eval.lambda.retry;

import java.sql.SQLException;

/**
 * The type Lock wait timeout exception.
 *
 */
public class LockWaitTimeoutException extends SQLException {
    private static final long serialVersionUID = -6754599774015964707L;

    /**
     * Instantiates a new Lock wait timeout exception.
     */
    public LockWaitTimeoutException() {
    }

    /**
     * Instantiates a new Lock wait timeout exception.
     *
     * @param reason the reason
     * @param cause  the cause
     */
    public LockWaitTimeoutException(String reason, Throwable cause) {
        super(reason, cause);
    }

    /**
     * Instantiates a new Lock wait timeout exception.
     *
     * @param e the e
     */
    public LockWaitTimeoutException(Throwable e) {
        super(e);
    }
}