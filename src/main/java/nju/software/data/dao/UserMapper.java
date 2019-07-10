package nju.software.data.dao;


import nju.software.data.dataobject.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);



//    自定义接口


    User login(String username , String password);


    int updateUserUrl(int id , String url);




    List<User> getUserList();
}