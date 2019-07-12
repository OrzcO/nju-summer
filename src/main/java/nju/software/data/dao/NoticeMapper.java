package nju.software.data.dao;

import nju.software.data.dataobject.Notice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "noticeMapper")
public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);


    List<Notice> getNoticeList();


}