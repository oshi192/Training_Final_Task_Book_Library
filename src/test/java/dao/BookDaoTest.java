package dao;

import model.dao.mysql.MySqlBookDao;
import model.entity.Book;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class BookDaoTest {
    @Test
    public void get(){
        MySqlBookDao bookDao = new MySqlBookDao();
        Book book = bookDao.get(1);
        assertTrue("Dune".equals(book.getNameEn()));
    }
    @Test
    public void getAllPaginate(){
        MySqlBookDao bookDao = new MySqlBookDao();
        List<Book> books = bookDao.getAllPaginate(10,0);
        assertTrue(books.size()==10);
    }


}
