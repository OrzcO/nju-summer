package nju.software.web.controller;

import nju.software.data.dataobject.Task;
import nju.software.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/task")
@Controller
public class TaskController {

    @Resource(name = "taskService")
    private TaskService taskService;

    @ResponseBody
    @RequestMapping("/getTaskList.do")
    public List<Task> getTaskList() {
        return taskService.getTaskList();
    }

}
