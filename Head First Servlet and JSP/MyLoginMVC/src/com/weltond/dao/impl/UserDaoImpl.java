package com.weltond.dao.impl;

import com.weltond.dao.UserDao;
import com.weltond.domain.User;
import com.weltond.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 * @author Weltond Ning
 * @Project MyLoginMVC
 * @Date 5/21/2019
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void addUser(User user) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement("INSERT INTO users(username, password, email, birthday) VALUES(?,?,?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(user.getBirthday());
            ps.setString(4, date);

            int i = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to Add Users!");
        } finally {
            DBUtils.closeAll(null, ps, connection);
        }
    }

    @Override
    public User findUser(User user) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user1 = null;

        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username=? and password=?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user1 = new User();
                user1.setId(resultSet.getInt(1));
                user1.setUsername(resultSet.getString(2));
                user1.setPassword(resultSet.getString(3));
                user1.setEmail(resultSet.getString(4));
                user1.setBirthday(resultSet.getDate(5));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(resultSet, preparedStatement, connection);
        }
        return user1;
    }

    @Override
    public boolean findUserByName(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username=?");
            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(resultSet, preparedStatement, connection);
        }
        return false;
    }
}
