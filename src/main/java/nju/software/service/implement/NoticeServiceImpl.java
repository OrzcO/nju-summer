package nju.software.service.implement;

import nju.software.data.dao.NoticeMapper;
import nju.software.data.dataobject.Notice;
import nju.software.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service(value = "noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Resource(name = "noticeMapper")
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> getNoticeList() {
        return noticeMapper.getNoticeList();
    }
}
