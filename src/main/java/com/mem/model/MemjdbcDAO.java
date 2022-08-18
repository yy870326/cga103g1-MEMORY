package com.mem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class MemjdbcDAO implements I_MemDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "pao103098";
	
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
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public void delete(Integer memno) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1,memno);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public MemVO findByPrimaryKey(Integer mem_no) {
			MemVO memvo = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, mem_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
				
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
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
		public static void main(String[] args) {


			MemjdbcDAO dao = new MemjdbcDAO();
//			// 新增
//			MemVO memVO01 = new MemVO();
//			memVO01.setMem_acc("Swwwwww");
//			memVO01.setMem_pwd("qwwwwww");
//			memVO01.setAcc_status(2);
//			memVO01.setMem_name("小");
//			memVO01.setMem_gender("F");
//			memVO01.setMem_email("@wwwww");
//			memVO01.setMem_mobile("09888");
//			memVO01.setMem_city("台北");
//			memVO01.setMem_dist("中和");
//			memVO01.setMem_addr("中和");
//			memVO01.setMem_reg_date(java.sql.Date.valueOf("2002-01-01"));
//			memVO01.setMem_pic(null);
//			memVO01.setMem_report_count(2);
//			memVO01.setMem_card("3333333");
//			dao.insert(memVO01);
			
			
//			// 修改
			MemVO memVO02 = new MemVO();
			memVO02.setMem_no(4);
			memVO02.setMem_acc("cccccc");
			memVO02.setMem_pwd("1111111");
			memVO02.setAcc_status(1);
			memVO02.setMem_name("水");
			memVO02.setMem_gender("F");
			memVO02.setMem_email("@wwwww");
			memVO02.setMem_mobile("09888666");
			memVO02.setMem_city("台北");
			memVO02.setMem_dist("中和");
			memVO02.setMem_addr("中和");
			memVO02.setMem_reg_date(java.sql.Date.valueOf("2005-01-01"));
			memVO02.setMem_pic(null);
			memVO02.setMem_report_count(3);
			memVO02.setMem_card("333334433344433");
			
			dao.update(memVO02);
//
//			// 刪除
			dao.delete(4);
//
//			// 查詢
//			MemVO memVO03 = dao.findByPrimaryKey(2);
//			System.out.println(memVO03.getMem_no()+",");
//			System.out.println(memVO03.getMem_acc()+",");
//			System.out.println(memVO03.getMem_pwd()+",");
//			System.out.println(memVO03.getAcc_status()+",");
//			System.out.println(memVO03.getMem_name()+",");
//			System.out.println(memVO03.getMem_gender()+",");
//			System.out.println(memVO03.getMem_email()+",");
//			System.out.println(memVO03.getMem_mobile()+",");
//			System.out.println(memVO03.getMem_city()+",");
//			System.out.println(memVO03.getMem_dist()+",");
//			System.out.println(memVO03.getMem_addr()+",");
//			System.out.println(memVO03.getMem_reg_date()+",");
//			System.out.println(memVO03.getMem_pic()+",");
//			System.out.println(memVO03.getMem_report_count()+",");
//			System.out.println(memVO03.getMem_card()+",");
			
//			// 查詢
//			List<MemVO> list = dao.getAll();
//			for(MemVO aMem : list) {
//				System.out.println(aMem.getMem_no()+",");
//				System.out.println(aMem.getMem_acc()+",");
//				System.out.println(aMem.getMem_pwd()+",");
//				System.out.println(aMem.getAcc_status()+",");
//				System.out.println(aMem.getMem_name()+",");
//				System.out.println(aMem.getMem_gender()+",");
//				System.out.println(aMem.getMem_email()+",");
//				System.out.println(aMem.getMem_mobile()+",");
//				System.out.println(aMem.getMem_city()+",");
//				System.out.println(aMem.getMem_dist()+",");
//				System.out.println(aMem.getMem_addr()+",");
//				System.out.println(aMem.getMem_reg_date()+",");
//				System.out.println(aMem.getMem_pic()+",");
//				System.out.println(aMem.getMem_report_count()+",");
//				System.out.println(aMem.getMem_card()+",");
//				
//			}
					
		}
		
}
