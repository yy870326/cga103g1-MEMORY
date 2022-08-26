package com.tkt_item.com;

import java.util.*;
import java.sql.*;

public class TktItemjdbcDAO implements I_TktItemDAO{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "pao103098";

	private static final String INSERT_STMT = 
		"INSERT INTO tkt_item (tkt_no,tkt_order_no,amount,qrcode,tkt_ori_price) VALUES (?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT tkt_no,tkt_order_no,amount,qrcode,tkt_ori_price FROM tkt_item order by tkt_no";
	private static final String GET_ONE_STMT = 
		"SELECT tkt_no,tkt_order_no,amount,qrcode,tkt_ori_price FROM tkt_item where tkt_no = ?";
	private static final String DELETE = 
		"DELETE FROM tkt_item where tkt_no = ?";
	private static final String UPDATE = 
		"UPDATE tkt_item set tkt_order_no=?, amount=?, qrcode=?, tkt_ori_price=?  where tkt_no = ?";
	@Override
	public void insert(TktItemVO tktitemVO) {
		
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setInt(1, tktitemVO.getTkt_no());
				pstmt.setInt(2, tktitemVO.getTKT_ORDER_NO());
				pstmt.setInt(3, tktitemVO.getAmount());
				pstmt.setBytes(4, tktitemVO.getQrcode());
				pstmt.setInt(5, tktitemVO.getTkt_ori_price());
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
	public void update(TktItemVO tktitemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, tktitemVO.getTKT_ORDER_NO());
			pstmt.setInt(2, tktitemVO.getAmount());
			pstmt.setBytes(3, tktitemVO.getQrcode());
			pstmt.setInt(4, tktitemVO.getTKT_ORDER_NO());
			pstmt.setInt(5, tktitemVO.getTkt_no());
			
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
	public void delete(Integer tktno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tktno);

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
	public TktItemVO findByPrimaryKey(Integer tktno) {
		TktItemVO tktItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tktno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
			tktItemVO = new TktItemVO();
			tktItemVO.setTkt_no(rs.getInt("tkt_no"));
			tktItemVO.setTKT_ORDER_NO(rs.getInt("tkt_order_no"));
			tktItemVO.setAmount(rs.getInt("amount"));
			tktItemVO.setQrcode(rs.getBytes("qrcode"));
			tktItemVO.setTkt_ori_price(rs.getInt("tkt_ori_price"));
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
		return tktItemVO;
	}
	@Override
	public List<TktItemVO> getAll() {
		List<TktItemVO> list = new ArrayList<TktItemVO>();
		TktItemVO tktItemVO = null;

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
				 tktItemVO = new TktItemVO();
				tktItemVO.setTkt_no(rs.getInt("tkt_no"));
				tktItemVO.setTKT_ORDER_NO(rs.getInt("tkt_order_no"));
				tktItemVO.setAmount(rs.getInt("amount"));
				tktItemVO.setQrcode(rs.getBytes("qrcode"));
				tktItemVO.setTkt_ori_price(rs.getInt("tkt_ori_price"));
				list.add(tktItemVO);
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
		TktItemjdbcDAO dao = new TktItemjdbcDAO();
		//新增
//		TktItemVO tktkItemVO1 = new TktItemVO();
//		tktkItemVO1.setTkt_no(4);
//		tktkItemVO1.setTKT_ORDER_NO(9);
//		tktkItemVO1.setAmount(9);
//		tktkItemVO1.setQrcode(null);
//		tktkItemVO1.setTkt_ori_price(9);
//		dao.insert(tktkItemVO1);
//		
//		//修改
//		TktItemVO tktItemVO2 = new TktItemVO();
//		tktItemVO2.setTkt_no(3);
//		tktItemVO2.setTKT_ORDER_NO(8);
//		tktItemVO2.setAmount(9);
//		tktItemVO2.setQrcode(null);
//		tktItemVO2.setTkt_ori_price(9);
//		dao.update(tktItemVO2);
//		
		//刪除
//		dao.delete(1);
		//查詢
//		TktItemVO tktItemVO3 = dao.findByPrimaryKey(1);
//		System.out.println(tktItemVO3.getTkt_no()+"+");
//		System.out.println(tktItemVO3.getTKT_ORDER_NO()+"+");
//		System.out.println(tktItemVO3.getAmount()+"+");
//		System.out.println(tktItemVO3.getQrcode()+"+");
//		System.out.println(tktItemVO3.getTkt_ori_price()+"+");
//		System.out.println("------------------------------");
		
//		List<TktItemVO> list = dao.getAll();
//		for(TktItemVO aTkt : list) {
//			System.out.println(aTkt.getTkt_no()+",");
//			System.out.println(aTkt.getTKT_ORDER_NO()+",");
//			System.out.println(aTkt.getAmount()+",");
//			System.out.println(aTkt.getQrcode()+",");
//			System.out.println(aTkt.getTkt_ori_price()+",");
//		}
		
	}
	
}
