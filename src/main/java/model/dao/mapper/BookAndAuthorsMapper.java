package model.dao.mapper;

import model.entity.BookAndAuthors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class BookAndAuthorsMapper implements ObjectMapper<BookAndAuthors> {
    @Override
    public BookAndAuthors extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public BookAndAuthors makeUnique(Map<Integer, BookAndAuthors> cache, BookAndAuthors teacher) {
        return null;
    }
}
