package com.harshil.userdaoplugin.dao;

import com.harshil.userdaoplugin.pojo.AppUser;

public interface UserDao {

    void createUser(AppUser user);

    AppUser getUser(int id);

}
