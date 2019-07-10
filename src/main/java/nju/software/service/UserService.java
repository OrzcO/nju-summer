package nju.software.service;

import nju.software.data.dataobject.User;

import java.util.List;

public interface UserService {

    List<User> getUserList();

    User selectByPrimaryKey(int i);
}
