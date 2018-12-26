package model.dao.mapper;

import model.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AuthorMapper implements ObjectMapper<Author> {
    @Override
    public Author mapGet(ResultSet rs) throws SQLException {
        Author author = new Author();
        author.setId(rs.getInt("author_id"));
        author.setFirstName(rs.getString("first_name"));
        author.setSecond_name(rs.getString("second_name"));
        author.setPatronymicName(rs.getString("patronymic_name"));
        return author;
    }

    @Override
    public Author makeUnique(Map<Integer, Author> cache, Author teacher) {
        return null;
    }
}
