package model.dao.mapper;

import model.entity.Book;
import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class BookMapper implements ObjectMapper<Book> {

    @Override
    public Book extractFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setName(rs.getString("name"));
        book.setSection(rs.getString("section"));
        return book;
    }

    @Override
    public Book makeUnique(Map<Integer, Book> cache, Book book) {
        cache.putIfAbsent(book.getId(), book);
        return cache.get(book.getId());
    }
}
