package com.harshil.userdaoplugin.dao;

import com.harshil.userdaoplugin.pojo.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private NamedParameterJdbcOperations jdbcOperations;

    @Autowired
    public void setJdbcOperations(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public void createUser(AppUser user) {
        String sql = "insert into app_user (username) values(:username)";
        Map<String, Object> paramMap = Collections.singletonMap("username", user.getUsername());

        jdbcOperations.update(sql, paramMap);
    }

    @Override
    public AppUser getUser(int userId) {
        String sql = "select id, username from app_user where id = :userId";
        Map<String, Object> paramMap = Collections.singletonMap("userId", userId);

        List<AppUser> fetchedUsers = jdbcOperations.query(sql, paramMap, new UserRowMapper());
        return fetchedUsers.get(0);
    }

    private class UserRowMapper implements RowMapper<AppUser> {
        @Override
        public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String username = rs.getString("username");

            return new AppUser(id, username);
        }
    }

}
