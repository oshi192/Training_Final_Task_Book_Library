package model.dao.mapper;

import model.entity.Book;
import model.entity.TakenBook;
import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserTakenBookMapper implements ObjectMapper<TakenBook> {

    @Override
    public TakenBook extractFromResultSet(ResultSet rs) throws SQLException {
        TakenBook takenBook = new TakenBook();
        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setName(rs.getString("book_name"));
        takenBook.setBook(book);
        takenBook.setTakenDate(rs.getString("taken_date"));
        takenBook.setWilReturnDate(rs.getString("end_date"));
        return takenBook;
    }

    @Override
    public TakenBook makeUnique(Map<Integer, TakenBook> cache, TakenBook teacher) {

        return null;
    }
}
