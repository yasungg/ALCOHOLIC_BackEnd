package com.kh.mini_masilrang.controller;

import com.kh.mini_masilrang.dao.MainDAO;
import com.kh.mini_masilrang.vo.ProductVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MainController {
    @GetMapping("/mainProduct")
    public ResponseEntity<List<ProductVO>> mainProductGet(@RequestParam String thema) {
        MainDAO dao = new MainDAO();
        List<ProductVO> list = dao.MainProduct(thema);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/mainProduct2")
    public ResponseEntity<List<ProductVO>> mainProductGet2(@RequestParam String thema) {
        MainDAO dao = new MainDAO();
        List<ProductVO> list = dao.MainProduct2(thema);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
