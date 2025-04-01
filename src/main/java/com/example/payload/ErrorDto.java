package com.example.payload;

import java.util.Date;

public class ErrorDto {
    private String msg;
    private Date date;
    private String Description;

    public ErrorDto(String msg, Date date, String description) {
        this.msg = msg;
        this.date = date;
        Description = description;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
