package com.tn.service;

import com.github.pagehelper.PageInfo;
import com.tn.entity.Users;
import com.tn.utils.UsersParam;

public interface UsersService {

    PageInfo<Users> getUsersByPage(UsersParam usersParam);
    boolean addUsers(Users users);
    boolean deleteUsers(Integer id);
    boolean deleteUsersList(Integer [] ids);
    boolean updateUsers(Users users);
    Users getUsersById(Integer id);
    boolean checkUserName( String name);
    Users loginUsers(String name,String password);
}
