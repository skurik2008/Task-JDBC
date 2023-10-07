package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection cn = Util.getConnection(); Statement st = cn.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users (id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastname VARCHAR(50), age TINYINT UNSIGNED)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection cn = Util.getConnection(); Statement st = cn.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection cn = Util.getConnection()) {
             try (PreparedStatement pst = cn.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)")) {
                 cn.setAutoCommit(false);
                 pst.setString(1, name);
                 pst.setString(2, lastName);
                 pst.setInt(3, age);
                 pst.executeUpdate();
                 cn.commit();
             } catch (SQLException e) {
                 cn.rollback();
                 throw e;
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection cn = Util.getConnection(); Statement st = cn.createStatement()) {
                st.executeUpdate(String.format("DELETE FROM users WHERE id = %d", id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection cn = Util.getConnection()) {
            try (Statement st = cn.createStatement()) {
                ResultSet rs = st.executeQuery("SELECT * FROM users");
                while (rs.next()) {
                    User user = new User(rs.getString("name"),
                            rs.getString("lastname"), rs.getByte("age"));
                    list.add(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection cn = Util.getConnection()) {
            try (Statement st = cn.createStatement()) {
                st.executeUpdate("DELETE FROM users");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
