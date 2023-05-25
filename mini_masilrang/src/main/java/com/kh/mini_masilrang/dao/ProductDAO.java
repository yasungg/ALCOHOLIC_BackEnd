package com.kh.mini_masilrang.dao;

import com.kh.mini_masilrang.common.Common;
import com.kh.mini_masilrang.vo.LikeProductVO;
import com.kh.mini_masilrang.vo.ProductVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pStmt = null;
    private ResultSet rs = null;

    //체크박스 선택 시 정보 받아오기
    public List<ProductVO> productFind(String checked) {
        List<ProductVO> list1 = new ArrayList<>();
        try {
            conn = Common.getConnection();

            String sql = "SELECT PRODUCT_NO, PRODUCT_NAME, CONTENT1, CONTENT2, PRODUCT_IMG, DESCRIPTION_IMG FROM PRODUCT WHERE GENRE = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, checked);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                ProductVO vo = new ProductVO();
                int product_no = rs.getInt("PRODUCT_NO");
                String product_name = rs.getString("PRODUCT_NAME");
                String content1 = rs.getString("CONTENT1");
                String content2 = rs.getString("CONTENT2");
                String product_img = rs.getString("PRODUCT_IMG");
                String description_img = rs.getString("DESCRIPTION_IMG");
                vo.setProduct_no(product_no);
                vo.setProduct_name(product_name);
                vo.setContent1(content1);
                vo.setContent2(content2);
                vo.setProduct_img(product_img);
                vo.setDescription_img(description_img);
                list1.add(vo);
            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list1;
    }

    //테마 선택 시 정보 받아오기
    public List<ProductVO> productThemeFind(String theme) {
        List<ProductVO> list2 = new ArrayList<>();
        try {
            conn = Common.getConnection();
            String themesql = "SELECT PRODUCT_NO, PRODUCT_NAME, CONTENT1, CONTENT2, PRODUCT_IMG FROM PRODUCT WHERE THEMA = ?";
            pStmt = conn.prepareStatement(themesql);
            pStmt.setString(1, theme);
            rs = pStmt.executeQuery();

            while (rs.next()) {
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
                list2.add(vo);
            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list2;
    }

    public List<ProductVO> ProductInfo(int product) {
        List<ProductVO> list = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pStmt = null;

        try {
            System.out.print("DB에 접속 중입니다.");
            conn = Common.getConnection();
            String query = "SELECT * FROM PRODUCT WHERE PRODUCT_NO = ?";
            pStmt = conn.prepareStatement(query);
            pStmt.setInt(1, product);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                ProductVO vo = new ProductVO();
                int product_no = rs.getInt("PRODUCT_NO");
                String product_name = rs.getString("PRODUCT_NAME");
                String content1 = rs.getString("CONTENT1");
                String content2 = rs.getString("CONTENT2");
                String genre = rs.getString("GENRE");
                String alcoholp = rs.getString("ALCOHOLP");
                String capacity = rs.getString("CAPACITY");
                String store_link = rs.getString("STORE_LINK");
                String product_img = rs.getString("PRODUCT_IMG");
                String description_img = rs.getString("DESCRIPTION_IMG");
                String thema = rs.getString("THEMA");

                vo.setProduct_no(product_no);
                vo.setProduct_name(product_name);
                vo.setContent1(content1);
                vo.setContent2(content2);
                vo.setGenre(genre);
                vo.setAlcoholp(alcoholp);
                vo.setCapacity(capacity);
                vo.setStore_link(store_link);
                vo.setProduct_img(product_img);
                vo.setDescription_img(description_img);
                vo.setThema(thema);
                list.add(vo);

            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 관심 상품 추가
    public boolean insertLikeProduct(int userNo, int productNo) {
        int result = 0;
        String sql = "INSERT INTO LIKEPRODUCT (USER_NO, USER_NAME, PRODUCT_NO, PRODUCT_NAME, PRODUCT_IMG, CONTENT1, CONTENT2)\n" +
                "SELECT M.USER_NO, M.USER_NAME, P.PRODUCT_NO, P.PRODUCT_NAME, P.PRODUCT_IMG, P.CONTENT1, P.CONTENT2\n" +
                "FROM MEMBER_INFO M\n" +
                "JOIN PRODUCT P ON M.USER_NO = ? AND P.PRODUCT_NO = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, userNo);
            pStmt.setInt(2, productNo);
            result = pStmt.executeUpdate();
            System.out.println("관심 상품 추가 결과 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }
    // 관심 상품 조회
    public List<LikeProductVO> likeProduct(int userNo) {
        List<LikeProductVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT PRODUCT_NO, PRODUCT_NAME, CONTENT1, CONTENT2, PRODUCT_IMG FROM LIKEPRODUCT WHERE USER_NO = " + "'" + userNo  + "'";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                LikeProductVO vo = new LikeProductVO();
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
        return list;
    }
    // 관심 상품 하트 표시
    public boolean heartCheck(int product_no, int userNum) {
        int result = 0;
        String sql = "SELECT * FROM LIKEPRODUCT WHERE USER_NO = ? AND PRODUCT_NO = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, userNum);
            pStmt.setInt(2, product_no);
            result = pStmt.executeUpdate();
            System.out.println("데이터베이스에서 정보 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }
    public boolean deleteLikeProduct(int productNo, int userNo) {
        int result = 0;
        String sql = "DELETE FROM LIKEPRODUCT WHERE USER_NO =? AND PRODUCT_NO = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, userNo);
            pStmt.setInt(2, productNo);
            result = pStmt.executeUpdate();
            System.out.println("관심 상품 삭제 결과 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }
}