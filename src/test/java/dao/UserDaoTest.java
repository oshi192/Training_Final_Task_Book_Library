package dao;

import model.dao.mysql.MySqlUserDao;
import model.entity.User;
import org.junit.Assert;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void testGet() throws Exception {
        MySqlUserDao userDao = new MySqlUserDao();
        User user = userDao.findByEmail("admin@gmail.com");
        Assert.assertTrue(user!=null);
    }
}
