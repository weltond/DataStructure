package com.weltond.dao.impl;

import com.weltond.dao.UserDao;
import com.weltond.domain.User;
import com.weltond.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
