package nju.software.service.implement;

import nju.software.data.dao.UserMapper;
import nju.software.data.dataobject.User;
import nju.software.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.login(username , password);
    }

    @Override
    public int updateUserUrl(int id, String url) {
        return userMapper.updateUserUrl(id , url);
    }

    @Override
    public List<User> getUserList() {
        System.out.println("userService impl  ");
        return userMapper.getUserList();
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectByPrimaryKey(int i) {
        System.out.println("userService impl  selectByPrimaryKey");
        return userMapper.selectByPrimaryKey(i);
    }
}
