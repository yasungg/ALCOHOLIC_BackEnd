package com.kh.mini_masilrang.controller;


import com.kh.mini_masilrang.dao.ReviewDAO;
import com.kh.mini_masilrang.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ReviewController {

    // 해당 제품 리뷰 조회
    @GetMapping("/product/review")
    public ResponseEntity<List<ReviewVO>> productReview(@RequestParam int product) {
        ReviewDAO dao = new ReviewDAO();
        List<ReviewVO> list = dao.viewSelect(product);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 리뷰 작성

    @PostMapping("/product/insertReview")
    public ResponseEntity<String> insertReview(@RequestBody Map<String, Object> reviewData) {
        int user_no = (int) reviewData.get("user_no");

        String rev_content = (String) reviewData.get("rev_content");
        int product = Integer.parseInt((String) reviewData.get("product"));
        System.out.println(user_no);
        System.out.println(rev_content);

        ReviewDAO dao = new ReviewDAO();
        dao.insertReview(user_no, rev_content, product);

        return new ResponseEntity<>("true", HttpStatus.OK);
    }

    // 리뷰 수정
    @PostMapping("/updateReview")
    public ResponseEntity<String> updateReview(@RequestBody Map<String, Object> reviewData) {
        int rev_no = Integer.parseInt((String) reviewData.get("rev_no"));
        String rev_content = (String) reviewData.get("rev_content");

        ReviewDAO dao = new ReviewDAO();
        dao.updateReview(rev_no, rev_content);
        return new ResponseEntity<>("True", HttpStatus.OK);
    }

    @GetMapping("/deleteReview")
    public ResponseEntity<String> deleteReview(@RequestParam int rev_no) {
        ReviewDAO dao = new ReviewDAO();
        dao.deleteReview(rev_no);
        return new ResponseEntity<>("True", HttpStatus.OK);
    }
}
