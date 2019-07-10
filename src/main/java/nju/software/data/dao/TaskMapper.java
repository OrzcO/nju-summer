package nju.software.data.dao;

import nju.software.data.dataobject.Task;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "taskMapper")
public interface TaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);




//    自定义
    List<Task> getTaskList();
}