package com.kh.mini_masilrang.controller;

import com.kh.mini_masilrang.dao.MemberDAO;
import com.kh.mini_masilrang.dao.ProductDAO;
import com.kh.mini_masilrang.vo.LikeProductVO;
import com.kh.mini_masilrang.vo.ProductVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {
    // 체크박스별 검색
    @GetMapping("/checked")
    public ResponseEntity<List<ProductVO>> productGet(@RequestParam String checked) {
        ProductDAO dao = new ProductDAO();
        List<ProductVO> list1 = dao.productFind(checked);
        return new ResponseEntity<>(list1, HttpStatus.OK);
    }

    @GetMapping("/theme")
    public ResponseEntity<List<ProductVO>> productThemeGet(@RequestParam String theme) {
        ProductDAO dao = new ProductDAO();
        List<ProductVO> list2 = dao.productThemeFind(theme);
        return new ResponseEntity<>(list2, HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductVO>> ProductInfo(@RequestParam int product) {
        ProductDAO dao = new ProductDAO();
        List<ProductVO> list = dao.ProductInfo(product);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 관심상품 추가
    @PostMapping("/insertLikeProduct")
    public ResponseEntity<Boolean> insertLikeProduct(@RequestBody Map<String, Integer> insertProduct) {
        int userNo = insertProduct.get("userNum");
        int productNo = insertProduct.get("productNum");
        ProductDAO dao = new ProductDAO();
        boolean isTrue = dao.insertLikeProduct(productNo, userNo);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    // 관심 상품 조회
    @GetMapping("/likeProduct")
    public ResponseEntity<List<LikeProductVO>> likeProductInfo(@RequestParam int userNo) {
        ProductDAO dao = new ProductDAO();
        List<LikeProductVO> list = dao.likeProduct(userNo);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 개인별 관심상품 등록 유지
    @GetMapping("/checkHeart")
    public ResponseEntity<Boolean> likeProductHeart(@RequestParam  Integer product_no, Integer userNum) {
        ProductDAO dao = new ProductDAO();
        boolean isTrue = dao.heartCheck(product_no, userNum);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);

    }
    // 관심 상품 삭제
    @PostMapping("/deleteHeart")
    public ResponseEntity<Boolean> deleteLikeProduct(@RequestBody Map<String, Integer> deleteProduct) {
        int userNo = deleteProduct.get("userNum");
        int productNo = deleteProduct.get("productNo");
        ProductDAO dao = new ProductDAO();
        boolean isTrue = dao.deleteLikeProduct(productNo, userNo);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }
}
