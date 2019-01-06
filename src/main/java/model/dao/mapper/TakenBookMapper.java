package model.dao.mapper;

import model.entity.Book;
import model.entity.TakenBook;
import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TakenBookMapper implements ObjectMapper<TakenBook>{

    @Override
    public TakenBook mapGet(ResultSet rs) throws SQLException {
        TakenBook takenBook = new TakenBook();
        Book book = new Book();
        User user = new User();
        book.setId(rs.getInt("book_id"));
        book.setName(rs.getString("book_name"));
        user.setName(rs.getString("user_name"));
        user.setSurname(rs.getString("surname"));
        user.setEmail(rs.getString("email"));
        user.setPhoneNumber(rs.getString("phone_number"));
        takenBook.setBook(book);
        takenBook.setUser(user);
        takenBook.setTakenDate(rs.getString("taken_date"));
        takenBook.setWilReturnDate(rs.getString("end_date"));
        return takenBook;
    }

    @Override
    public TakenBook makeUnique(Map<Integer, TakenBook> cache, TakenBook teacher) {
        return null;
    }
}
