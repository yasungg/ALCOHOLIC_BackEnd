package com.kh.mini_masilrang.dao;


import com.kh.mini_masilrang.common.Common;
import com.kh.mini_masilrang.vo.ReviewVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pStmt = null;
    private ResultSet rs = null;

    // 해당 술 리뷰 조회
    public List<ReviewVO> viewSelect(int product) {
        List<ReviewVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            String sql = "SELECT * " +
                    "FROM REVIEW R " +
                    "JOIN PRODUCT P ON R.PRODUCT_NO = P.PRODUCT_NO " +
                    "JOIN MEMBER_INFO M ON R.USER_NO = M.USER_NO " +
                    "WHERE P.PRODUCT_NO = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, product);
            rs = pStmt.executeQuery();
            System.out.println(rs.next());
            while (rs.next()) {
                int rev_no = rs.getInt("REV_NO");
                int user_no = rs.getInt("USER_NO");
                String user_id = rs.getString("USER_ID");
                String rev_content = rs.getString("REV_CONTENT");
                String rev_img = rs.getString("REV_IMG");
                Date rev_date = rs.getDate("REV_DATE");
                int product_no = rs.getInt("PRODUCT_NO");

                ReviewVO vo = new ReviewVO();
                vo.setRev_no(rev_no);
                vo.setUser_no(user_no);
                vo.setUser_id(user_id);

                vo.setRev_content(rev_content);
                vo.setRev_img(rev_img);
                vo.setRev_date(rev_date);
                vo.setProduct_no(product_no);
                System.out.println(user_id);
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

    // 리뷰 작성
    public void insertReview(int user_no, String rev_content, String rev_img, int product) {
        String sql = "INSERT INTO REVIEW (REV_NO,USER_NO,USER_ID, REV_CONTENT, REV_IMG, REV_DATE, PRODUCT_NO) " +
                "VALUES(REV_NO.NEXTVAL,?,?,?,?, SYSDATE,?) ";
        String user_id = "";
        String idfindsql = "SELECT USER_ID FROM MEMBER_INFO WHERE USER_NO = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = Common.getConnection();
            pStmt = conn.prepareStatement(idfindsql);
            pStmt.setInt(1, user_no);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                user_id = rs.getString("USER_ID");
            }

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user_no);
            pstmt.setString(2, user_id);
            pstmt.setString(3, rev_content);
            pstmt.setString(4, rev_img);
            pstmt.setInt(5, product);
            pstmt.executeUpdate();
            System.out.println(user_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(pstmt);
            Common.close(conn);
        }
    }

    // 리뷰 수정
    public void updateReview(int rev_no, String rev_content, String rev_img) {
        String sql = "UPDATE REVIEW SET REV_CONTENT = ?, REV_IMG = ? WHERE REV_NO = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, rev_content);
            pstmt.setString(2, rev_img);
            pstmt.setInt(3, rev_no);
            pstmt.executeUpdate();

            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 리뷰 삭제
    public void deleteReview(int rev_no) {
        String sql = "DELETE FROM REVIEW WHERE REV_NO = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, rev_no);
            pstmt.executeUpdate();

            Common.close(pstmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 마이페이지에서 리뷰 조회
    public List<ReviewVO> myReview(int userNo) {
        List<ReviewVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            String sql = "SELECT R.REV_NO, R.USER_ID, R.REV_CONTENT, R.REV_IMG, R.REV_DATE, R.PRODUCT_NO " +
                    "FROM MEMBER_INFO M " +
                    "JOIN REVIEW R ON M.USER_NO = R.USER_NO " +
                    "WHERE M.USER_NO = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, userNo);
            rs = pStmt.executeQuery();
            System.out.println(rs.next());
            while (rs.next()) {
                ReviewVO vo = new ReviewVO();
                int rev_no = rs.getInt("REV_NO");
                String user_id = rs.getString("USER_ID");
                String rev_content = rs.getString("REV_CONTENT");
                String rev_img = rs.getString("REV_IMG");
                Date rev_date = rs.getDate("REV_DATE");
                int product_no = rs.getInt("PRODUCT_NO");

                vo.setRev_no(rev_no);
                vo.setUser_id(user_id);
                vo.setRev_content(rev_content);
                vo.setRev_img(rev_img);
                vo.setRev_date(rev_date);
                vo.setProduct_no(product_no);
                System.out.println(user_id);
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
    // 리뷰 사진 업데이트
    public boolean reviewImgUpdate(String rev_img, String rev_no) {
        int result = 0;
        String sql = "UPDATE REVIEW SET REV_IMG = ? WHERE REV_NO = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, rev_img);
            pStmt.setString(2, rev_no);
            result = pStmt.executeUpdate();
            System.out.println("profile 업데이트 결과: " + rev_no + " - " + rev_img);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }
}

