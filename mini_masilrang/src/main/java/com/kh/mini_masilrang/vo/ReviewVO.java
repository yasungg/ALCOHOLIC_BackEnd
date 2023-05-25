package com.kh.mini_masilrang.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ReviewVO {
    private int user_no;
    private String user_id;
    private int product_no;
    private int rev_no;
    private String rev_content;
    private String rev_img;
    private Date rev_date;
}
