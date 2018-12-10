package util;

import model.dao.ConsumerRT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class QueryManager {
    private static final Logger logger = LoggerFactory.getLogger(QueryManager.class);

    public static void executeQuery(Connection connection, String query, ConsumerRT<PreparedStatement> preparedStatementConsumer) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatementConsumer.accept(preparedStatement);

            preparedStatement.executeUpdate();
        } catch (Exception sqlException) {
            logger.error("Error:", sqlException);
            throw new RuntimeException(sqlException);//todo create my exception
        } finally {
            try {
                safeClose(new AutoCloseable[]{preparedStatement});
                //closeConnection(connectionPool, connection);
            } catch (Exception e) {
                logger.error("s",e);
            }
        }
    }

    private static void safeClose(AutoCloseable[] closeables) throws Exception {
        for (AutoCloseable closable : closeables) {
            if (closable != null) {
                closable.close();
            }
        }
    }

}
