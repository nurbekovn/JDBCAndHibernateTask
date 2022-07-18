package peaksoft.dao;
import peaksoft.model.User;
import java.util.List;
import peaksoft.util.Util;
import java.sql.*;
import java.util.ArrayList;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {
    }

    public void createUsersTable() {
        String sql = "Create table if not exists users(id serial primary key,name varchar,last_name varchar,age int)";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "drop table if exists users";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("table drop");
        } catch (SQLException e) {
            e.getMessage();
            System.out.println("Error !");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "Insert into users(name, last_name, age ) values(?,?,?)";
        try (Connection connection = Util.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users where id = ?";
        try (Connection connection = Util.connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             statement.setInt(1, (int) id);
             statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM user";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User();
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                user.setId(id);
                String last_name = resultSet.getString("last_name");
                user.setLastName(last_name);
                user.setName(name);
                user.setAge((byte) resultSet.getInt("age"));
                users.add(user);
                System.out.println(user.getId() + " - " + user.getName());
            }
            return users;
        } catch (Exception ew) {
            ew.getMessage();
            return null;
        }
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                System.out.println("id" + resultSet.getByte("id"));
                System.out.println("name" + resultSet.getString("name"));
                System.out.println("last_name" + resultSet.getString("lastName"));
                System.out.println("age" + resultSet.getInt("age"));
            }
        } catch (SQLException kg) {
            kg.getMessage();
        }
    }
}

//public class UserDaoJdbcImpl implements UserDao {
//
//    public UserDaoJdbcImpl() {
//
//    }
//
//    public void createUsersTable() {
//
//
//    }
//
//    public void dropUsersTable() {
//
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//
//    }
//
//    public void removeUserById(long id) {
//
//    }
//
//    public List<User> getAllUsers() {
//        return null;
//    }
//
//    public void cleanUsersTable() {
//
//    }
//}