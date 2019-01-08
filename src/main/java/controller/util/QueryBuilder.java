package controller.util;

import model.connectionpool.ConnectionPoolHolder;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
@Deprecated
public class QueryBuilder {
    private static final Logger logger = Logger.getLogger(QueryBuilder.class);
    private static final String PAGINATE_QUERY_PART = " LIMIT %d OFFSET %d";
    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";
    private String query;
    private String clean;
    private String pagination = "";
    private String condition = "";

    public QueryBuilder(String clean) {
        this.query = clean;
        this.clean = clean;
    }


    public ResultSet execute() throws SQLException {
        query+=" "+condition+" "+pagination;
        query=query.replace(";", "");
        query += ";";
        Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
        PreparedStatement ps = connection.prepareCall(query);
        logger.info("executing query:"+query);
            ResultSet rs = ps.executeQuery();
            return rs;

    }

    public String getQuery() {
        return query;
    }

    public void addSearchBooksProperties(Map<String, String> property) {
        StringBuilder conditionQuery = new StringBuilder();
        query = query.replaceAll(";", "");
        if (query.toLowerCase().contains("where")) {
            conditionQuery.append(" and ");
        } else {
            conditionQuery.append(" where ");
        }

        for (Map.Entry<String, String> entry : property.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            conditionQuery.append(key);
            conditionQuery.append(" like ");
            conditionQuery.append(value);
            conditionQuery.append(" ");
            conditionQuery.append(" and ");
        }
        condition = conditionQuery.substring(0, conditionQuery.length() - 4);
    }
}

