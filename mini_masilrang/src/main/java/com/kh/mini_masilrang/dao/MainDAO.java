package com.kh.mini_masilrang.dao;

import com.kh.mini_masilrang.common.Common;
import com.kh.mini_masilrang.vo.ProductVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class MainDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;
    public List<ProductVO> MainProduct(String thema) {
        List<ProductVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT PRODUCT_NO, PRODUCT_NAME, CONTENT1, CONTENT2, PRODUCT_IMG FROM PRODUCT WHERE THEMA = '어버이날'" ;
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                ProductVO vo = new ProductVO();
                int product_no = rs.getInt("PRODUCT_NO");
                String product_name = rs.getString("PRODUCT_NAME");
                String content1 = rs.getString("CONTENT1");
                String content2 = rs.getString("CONTENT2");
                String product_img = rs.getString("PRODUCT_IMG");
                vo.setProduct_no(product_no);
                vo.setProduct_name(product_name);
                vo.setContent1(content1);
                vo.setContent2(content2);
                vo.setProduct_img(product_img);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 리스트를 섞음
        Collections.shuffle(list);

        // 리스트에서 앞에서부터 4개의 요소를 선택
        List<ProductVO> randomList = list.subList(0, 4);
        return randomList;
    }
    public List<ProductVO> MainProduct2(String thema) {
        List<ProductVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT PRODUCT_NO, PRODUCT_NAME, CONTENT1, CONTENT2, PRODUCT_IMG FROM PRODUCT WHERE THEMA = '봄소풍'" ;
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                ProductVO vo = new ProductVO();
                int product_no = rs.getInt("PRODUCT_NO");
                String product_name = rs.getString("PRODUCT_NAME");
                String content1 = rs.getString("CONTENT1");
                String content2 = rs.getString("CONTENT2");
                String product_img = rs.getString("PRODUCT_IMG");
                vo.setProduct_no(product_no);
                vo.setProduct_name(product_name);
                vo.setContent1(content1);
                vo.setContent2(content2);
                vo.setProduct_img(product_img);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 리스트를 섞음
        Collections.shuffle(list);

        // 리스트에서 앞에서부터 4개의 요소를 선택
        List<ProductVO> randomList2 = list.subList(0, 4);
        return randomList2;
    }
}
