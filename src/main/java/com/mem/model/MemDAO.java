package com.mem.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemDAO implements I_MemDAO{
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cga103g1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
			"INSERT INTO mem (mem_acc,mem_pwd,acc_status,mem_name,mem_gender,mem_email,mem_mobile,mem_city,mem_dist,mem_addr,mem_reg_date,mem_pic,mem_report_count,mem_card) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT mem_no,mem_acc,mem_pwd,acc_status,mem_name,mem_gender,mem_email,mem_mobile,mem_city,mem_dist,mem_addr,mem_reg_date,mem_pic,mem_report_count,mem_card FROM mem order by mem_no";
		private static final String GET_ONE_STMT = 
			"SELECT mem_no,mem_acc,mem_pwd,acc_status,mem_name,mem_gender,mem_email,mem_mobile,mem_city,mem_dist,mem_addr,mem_reg_date,mem_pic,mem_report_count,mem_card FROM mem where mem_no = ?";
		private static final String DELETE = 
			"DELETE FROM mem where mem_no = ?";
		private static final String UPDATE = 
			"UPDATE mem set mem_acc =?,mem_pwd=?,acc_status=?,mem_name=?,mem_gender=?,mem_email=?,mem_mobile=?,mem_city=?,mem_dist=?,mem_addr=?,mem_reg_date=?,mem_pic=?,mem_report_count=?,mem_card=? where mem_no = ?";
	@Override
	public void insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getMem_acc());
			pstmt.setString(2, memVO.getMem_pwd());
			pstmt.setInt(3, memVO.getAcc_status());
			pstmt.setString(4, memVO.getMem_name());
			pstmt.setString(5,memVO.getMem_gender());
			pstmt.setString(6, memVO.getMem_email());
			pstmt.setString(7, memVO.getMem_mobile());
			pstmt.setString(8, memVO.getMem_city());
			pstmt.setString(9, memVO.getMem_dist());
			pstmt.setString(10, memVO.getMem_addr());
			pstmt.setDate(11, memVO.getMem_reg_date());
			pstmt.setBytes(12, memVO.getMem_pic());
			pstmt.setInt(13, memVO.getMem_report_count());
			pstmt.setString(14, memVO.getMem_card());
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
	}

	@Override
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMem_acc());
			pstmt.setString(2, memVO.getMem_pwd());
			pstmt.setInt(3, memVO.getAcc_status());
			pstmt.setString(4, memVO.getMem_name());
			pstmt.setString(5,memVO.getMem_gender());
			pstmt.setString(6, memVO.getMem_email());
			pstmt.setString(7, memVO.getMem_mobile());
			pstmt.setString(8, memVO.getMem_city());
			pstmt.setString(9, memVO.getMem_dist());
			pstmt.setString(10, memVO.getMem_addr());
			pstmt.setDate(11, memVO.getMem_reg_date());
			pstmt.setBytes(12, memVO.getMem_pic());
			pstmt.setInt(13, memVO.getMem_report_count());
			pstmt.setString(14, memVO.getMem_card());
			pstmt.setInt(15, memVO.getMem_no());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
	}

	@Override
	public void delete(Integer memno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public MemVO findByPrimaryKey(Integer memno) {
		MemVO memvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memvo = new MemVO();
				memvo.setMem_no(rs.getInt("mem_no"));
				memvo.setMem_acc(rs.getString("mem_acc"));
				memvo.setMem_pwd(rs.getString("mem_pwd"));
				memvo.setAcc_status(rs.getInt("acc_status"));
				memvo.setMem_name(rs.getString("mem_name"));
				memvo.setMem_gender(rs.getString("mem_gender"));
				memvo.setMem_email(rs.getString("mem_email"));
				memvo.setMem_mobile(rs.getString("mem_mobile"));
				memvo.setMem_city(rs.getString("mem_city"));
				memvo.setMem_dist(rs.getString("mem_dist"));
				memvo.setMem_addr(rs.getString("mem_addr"));
				memvo.setMem_reg_date(rs.getDate("mem_reg_date"));
				memvo.setMem_pic(rs.getBytes("mem_pic"));
				memvo.setMem_report_count(rs.getInt("mem_report_count"));
				memvo.setMem_card(rs.getString("mem_card"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memvo;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMem_no(rs.getInt("mem_no"));
				memVO.setMem_acc(rs.getString("mem_acc"));
				memVO.setMem_pwd(rs.getString("mem_pwd"));
				memVO.setAcc_status(rs.getInt("acc_status"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_gender(rs.getString("mem_name"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_mobile(rs.getString("mem_mobile"));
				memVO.setMem_city(rs.getString("mem_city"));
				memVO.setMem_dist(rs.getString("mem_dist"));
				memVO.setMem_addr(rs.getString("mem_addr"));
				memVO.setMem_reg_date(rs.getDate("mem_reg_date"));
				memVO.setMem_pic(rs.getBytes("mem_pic"));
				memVO.setMem_report_count(rs.getInt("mem_report_count"));
				memVO.setMem_card(rs.getString("mem_card"));
				list.add(memVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
}
