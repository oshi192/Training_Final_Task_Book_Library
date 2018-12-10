package model.dao.mapper;

import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setPhone_number(rs.getString("phone_number"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        return user;
    }

    public User makeUnique(Map<Integer, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
