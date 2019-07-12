package nju.software.web.controller;


import nju.software.data.dataobject.Notice;
import nju.software.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/notice")
@Controller()
public class NoticeController {

    @Resource(name = "noticeService")
    private NoticeService noticeService;

    @RequestMapping("/getNoticeList.do")
    @ResponseBody
    public List<Notice> getNoticeList() {
        return noticeService.getNoticeList();
    }
}
