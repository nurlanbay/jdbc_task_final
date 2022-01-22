package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    Util util = new Util();

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = util.connection();
             Statement statement1 = connection.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id serial primary key," +
                    "name varchar (50) not null," +
                    "last_name varchar(50) not null," +
                    "age int)";
            System.out.println("create table successfully");
            statement1.executeQuery(sql);

        } catch (Exception e) {
            System.out.println("it has a Users table");
            System.out.println();
        }

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE users";
        try (Connection connection = util.connection();
             Statement statement = connection.createStatement();) {
             statement.executeUpdate(sql);
                System.out.println("Drop table successfully");
                System.out.println();
        } catch (Exception e) {
            System.out.println("Сиз жок таблицаны очургону атасыз");
            System.out.println();
        }
    }

    public void saveUser(String name, String last_name, byte age) {
        String sql = " INSERT INTO users (name,last_name,age)VALUES(?,?,?) ";

        try (Connection connection = util.connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, last_name);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("Add user to Data Base");
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = (?)";
        Connection connection = util.connection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        int i = statement.executeUpdate();
        if (i > 0) {
            System.out.println("Delete user successfully");
            System.out.println();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        User user = new User();

        try {
            Connection connection = util.connection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users";
            ResultSet resul = statement.executeQuery(sql);
            while (resul.next()) {
                user.setId( resul.getLong(1));
                user.setName(resul.getString("name"));
                user.setLastName(resul.getString("last_name"));
                user.setAge( resul.getByte("age"));
                list.add(user);
            }
            System.out.println(list);

        } catch (Exception e) {
            System.out.println("There is no table");
        }
        return  list;
    }
    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try (Connection connection = util.connection();
             Statement statement = connection.createStatement()){
            int i = statement.executeUpdate(sql);

            if (i>0){
                System.out.println("Deleted All Rows In The Table Successfully...");
            }else {
                System.out.println("Table already empty");
            }

        } catch (SQLException e) {
            System.out.println("There is no table");
        }

    }
}