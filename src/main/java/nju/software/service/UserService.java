package nju.software.service;

import nju.software.data.dataobject.User;

import java.util.List;

public interface UserService {

    User login(String username , String password);


    List<User> getUserList();


    int updateUserUrl(int id , String url);

    int updateUser(User user);

    User selectByPrimaryKey(int i);
}
