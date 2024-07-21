package com.kenneth.jsonparsing.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorPojo {
    private String authorName;
    private List<BookPojo> books;
}
