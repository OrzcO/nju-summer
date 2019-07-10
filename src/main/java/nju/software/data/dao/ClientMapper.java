package nju.software.data.dao;

import nju.software.data.dataobject.Client;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ClientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Client record);

    int insertSelective(Client record);

    Client selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);




//    自定义
    List<Client> selectAll();

    List<Client> search(String text);
}