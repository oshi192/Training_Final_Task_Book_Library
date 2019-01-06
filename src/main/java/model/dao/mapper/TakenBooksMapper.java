package model.dao.mapper;

import model.entity.TakenBooks;
import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TakenBooksMapper implements  ObjectMapper<TakenBooks> {
    @Override
    public TakenBooks mapGet(ResultSet rs) throws SQLException {
        UserMapper userMapper = new UserMapper();
        TakenBooks takenBooks = new TakenBooks();
        takenBooks.setId(rs.getInt("book_id"));
        takenBooks.setName(rs.getString("book_name"));
        takenBooks.setSection(rs.getString("section"));
        takenBooks.setTakenDate(rs.getString("taken_date"));
        takenBooks.setWilReturnDate(rs.getString("end_date"));
        User user = userMapper.mapGet(rs);
        user.setPassword("");
        takenBooks.setUser(user);
        return takenBooks;
    }

    @Override
    public TakenBooks makeUnique(Map<Integer, TakenBooks> cache, TakenBooks teacher) {
        return null;
    }
}
