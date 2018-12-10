package model.dao.impl;

import model.dao.UserDao;
import model.dao.mapper.UserMapper;
import model.entity.User;

import java.sql.*;
import java.util.*;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection=connection;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> result = Optional.empty();
        try(PreparedStatement ps = connection.prepareCall("SELECT * FROM users WHERE email = ?")){
            ps.setString( 1, email);
            ResultSet rs;
            rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
            if (rs.next()){
                result = Optional.of(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            //todo my exception
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> result = Optional.empty();
        try(PreparedStatement ps = connection.prepareCall("SELECT * FROM users WHERE user_id = ?")){
            ps.setInt( 1, id);
            ResultSet rs;
            rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
            if (rs.next()){
                result = Optional.of(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            //todo my exception
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public List<User> findAll(int offset) {
//        Map<Integer, User> students = new HashMap<>();
//        Map<Integer, Teacher> teachers = new HashMap<>();
//
//        final String query = "" +
//                " select * from studen" +
//                " left join studen_has_teacher using (idstuden)" +
//                " left join teacher using (idteacher)";
//        try (Statement st = connection.createStatement()) {
//            ResultSet rs = st.executeQuery(query);
//
//            ua.training.model.dao.mapper.TeacherMapper teacherMapper = new ua.training.model.dao.mapper.TeacherMapper();
//            ua.training.model.dao.mapper.StudentMapper studentMapper = new ua.training.model.dao.mapper.StudentMapper();
//
//            while (rs.next()) {
//                Student student = studentMapper
//                        .extractFromResultSet(rs);
//                Teacher teacher = teacherMapper
//                        .extractFromResultSet(rs);
//                student = studentMapper
//                        .makeUnique(students, student);
//                teacher = teacherMapper
//                        .makeUnique(teachers, teacher);
//                student.getTeachers().add(teacher);
//            }
//            return new ArrayList<>(students.values());
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
