package nju.software.data.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Task {
    private Integer id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm" , timezone = "GMT+8")
    private Date begin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm" , timezone = "GMT+8")
    private Date finish;

    private String belong;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong == null ? null : belong.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}