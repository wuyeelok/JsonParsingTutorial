package com.kenneth.jsonparsing.pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookPojo {
    private String title;
    private boolean inPrint;
    private LocalDate publishDate;
}
