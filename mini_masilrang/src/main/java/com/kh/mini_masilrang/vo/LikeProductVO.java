package com.kh.mini_masilrang.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeProductVO {
    private int user_no; // 사용자 고유 번호
    private String user_name; // 사용자 이름
    private int product_no; // 제품고유번호
    private String product_name; // 제품 이름
    private String product_img; // 이미지 링크
    private String content1; // 설명1
    private String content2; // 설명 2
}
