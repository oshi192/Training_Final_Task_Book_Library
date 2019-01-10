package model.dao.mysql;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlTransaction {
    private final static Logger logger = Logger.getLogger(MySqlTransaction.class);

    private Connection connection;

    MySqlTransaction(Connection connection) {
        this.connection = connection;
        try {
            logger.info("isolation level of connection: " + connection.getTransactionIsolation());
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("fail.... " + e);
        }
    }

    public void setLevelRepeatableRead() {
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("cannot set  isolation level to TRANSACTION_REPEATABLE_READ" + e);
        }
    }
    public void setLevelSerializable() {
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("cannot set  isolation level to TRANSACTION_SERIALIZABLE" + e);
        }
    }

    /**
     * disable autocommit
     */
    public void startTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Cannot disable autocommit", e);
        }
    }

    /**
     * rollback all uncommited the changes
     */
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Cannot rollback ", e);
        }
    }

    /**
     * close connection
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Cannot close connection", e);
        }
    }

    /**
     * Commit all changes
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Cannot commit ", e);
        }
    }
}
