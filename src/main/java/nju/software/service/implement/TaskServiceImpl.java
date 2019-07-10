package nju.software.service.implement;

import nju.software.data.dao.TaskMapper;
import nju.software.data.dataobject.Task;
import nju.software.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "taskService")
public class TaskServiceImpl implements TaskService {

    @Resource(name = "taskMapper")
    private TaskMapper taskMapper;


    @Override
    public List<Task> getTaskList() {
        return taskMapper.getTaskList();
    }
}
