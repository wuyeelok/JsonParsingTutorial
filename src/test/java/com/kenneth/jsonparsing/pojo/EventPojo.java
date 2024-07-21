package com.kenneth.jsonparsing.pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class EventPojo {
    private String name;
    private Date date;
    private LocalDate dateNew;
}
