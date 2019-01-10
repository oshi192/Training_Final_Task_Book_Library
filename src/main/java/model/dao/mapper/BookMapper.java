package model.dao.mapper;

import model.entity.Book;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

public class BookMapper implements ObjectMapper<Book> {
private static final Logger logger = Logger.getLogger(BookMapper.class);
    @Override
    public Book mapGet(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setUserId(rs.getInt("users_user_id"));
        book.setAdminId(rs.getInt("users_admin_id"));
        book.setStatus(rs.getInt("status"));
        book.setNameUk(rs.getString("book_name_uk"));
        book.setNameEn(rs.getString("book_name_en"));
        book.setSection(rs.getString("section"));
        book.setTakeBeginDate(rs.getString("taken_begin_date"));
        book.setTakeEndDate(rs.getString("taken_end_date"));
        return book;
    }

    @Override
    public Book makeUnique(Map<Integer, Book> cache, Book book) {
        cache.putIfAbsent(book.getId(), book);
        return cache.get(book.getId());
    }
    public void mapCreate(PreparedStatement ps, Book book) throws SQLException {
        ps.setString(1, book.getNameEn());
        ps.setString(2, book.getNameUk());
        ps.setString( 3, book.getSection());
        ps.setInt( 4, book.getUserId());
        ps.setInt( 5, book.getAdminId());
        ps.setString( 6, book.getTakeBeginDate());
        ps.setString( 7, book.getTakeEndDate());
        ps.setInt( 8, book.getStatus());
        if(book.getId()>0){
            ps.setInt( 9, book.getId());
        }
    }
    public Book mapGetFromRequest(HttpServletRequest request) {
        Book book = new Book();
        String id =request.getParameter("edit");
        logger.info(id+"book-name["+request.getParameter("book-name-uk"+id)+request.getParameter("book-name-en"+id)+"] book-section["+request.getParameter("book-section"+id)+"]");
        book.setNameUk(request.getParameter("book-name-uk"+id));
        book.setNameEn(request.getParameter("book-name-en"+id));
        book.setSection(request.getParameter("book-section"+id));
        return book;
    }
}

