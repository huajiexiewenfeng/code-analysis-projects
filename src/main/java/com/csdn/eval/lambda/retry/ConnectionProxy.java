package com.csdn.eval.lambda;

import java.sql.SQLException;

public class ConnectionProxy {

    private final LockRetryPolicy lockRetryPolicy = new LockRetryPolicy(1000, 2);

    public void commit() throws SQLException {
        try {
            lockRetryPolicy.execute(() -> {
                doCommit();
                return null;
            });
        } catch (SQLException e) {
            rollback();
            throw e;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private void doCommit() throws SQLException {
        System.out.println(1/0);
    }

    public void rollback() throws SQLException {

    }

    public static void main(String[] args) {
        ConnectionProxy connectionProxy = new ConnectionProxy();
        try {
            connectionProxy.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

