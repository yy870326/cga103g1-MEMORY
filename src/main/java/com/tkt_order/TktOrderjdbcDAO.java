package com.tkt_order;

import java.util.*;

import javax.swing.plaf.synth.SynthScrollPaneUI;

import com.mem.model.MemVO;
import com.tkt_item.com.TktItemVO;

import java.sql.*;

public class TktOrderjdbcDAO implements I_TktOrderDAO{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "pao103098";

	private static final String INSERT_STMT = 
		"INSERT INTO tkt_order (tkt_order_no,mem_no,mem_coup_no,tkt_no,original_price,orderdate,ttl_price) VALUES (?, ?, ?, ?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT tkt_order_no,mem_no,mem_coup_no,tkt_no,original_price,orderdate,ttl_price FROM tkt_order order by tkt_order_no";
	private static final String GET_ONE_STMT = 
		"SELECT tkt_order_no,mem_no,mem_coup_no,tkt_no,original_price,orderdate,ttl_price FROM tkt_order where tkt_order_no = ?";
	private static final String DELETE = 
		"DELETE FROM tkt_order where tkt_order_no = ?";
	private static final String UPDATE = 
		"UPDATE tkt_order set mem_no=?, mem_coup_no=?, tkt_no=?, original_price=? ,orderdate=?,ttl_price=?  where tkt_order_no = ?";

	@Override
	public void insert(TktOrderVO tktOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
		pstmt.setInt(1, tktOrderVO.getTkt_order_no());
		pstmt.setInt(2, tktOrderVO.getMem_no());
		pstmt.setInt(3, tktOrderVO.getMem_coup_no());
		pstmt.setInt(4, tktOrderVO.getTkt_no());
		pstmt.setInt(5, tktOrderVO.getOriginal_price());
		pstmt.setDate(6, tktOrderVO.getOrderdate());
		pstmt.setInt(7, tktOrderVO.getTTL_PRICE());
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
	public void update(TktOrderVO tktOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setInt(1, tktOrderVO.getMem_no());
			pstmt.setInt(2, tktOrderVO.getMem_coup_no());
			pstmt.setInt(3, tktOrderVO.getTkt_no());
			pstmt.setInt(4, tktOrderVO.getOriginal_price());
			pstmt.setDate(5, tktOrderVO.getOrderdate());
			pstmt.setInt(6, tktOrderVO.getTTL_PRICE());
			pstmt.setInt(7, tktOrderVO.getTkt_order_no());
			
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
	public void delete(Integer tkt_order_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tkt_order_no);

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
	public TktOrderVO findByPrimaryKey(Integer tkt_order_no) {
		TktOrderVO tktOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tkt_order_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				tktOrderVO  = new TktOrderVO();
				tktOrderVO.setTkt_order_no(rs.getInt("tkt_order_no"));
				tktOrderVO.setMem_no(rs.getInt("mem_no"));
				tktOrderVO.setMem_coup_no(rs.getInt("mem_coup_no"));
				tktOrderVO.setTkt_no(rs.getInt("tkt_no"));
				tktOrderVO.setOriginal_price(rs.getInt("original_price"));
				tktOrderVO.setOrderdate(rs.getDate("orderdate"));
				tktOrderVO.setTTL_PRICE(rs.getInt("ttl_price"));
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
		return tktOrderVO;
	}

	@Override
	public List<TktOrderVO> getAll() {
		List<TktOrderVO> list = new ArrayList<TktOrderVO>();
		TktOrderVO tktOrderVO = null;

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
				tktOrderVO  = new TktOrderVO();
				tktOrderVO.setTkt_order_no(rs.getInt("tkt_order_no"));
				tktOrderVO.setMem_no(rs.getInt("mem_no"));
				tktOrderVO.setMem_coup_no(rs.getInt("mem_coup_no"));
				tktOrderVO.setTkt_no(rs.getInt("tkt_no"));
				tktOrderVO.setOriginal_price(rs.getInt("original_price"));
				tktOrderVO.setOrderdate(rs.getDate("orderdate"));
				tktOrderVO.setTTL_PRICE(rs.getInt("ttl_price"));
				list.add(tktOrderVO);
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
		TktOrderjdbcDAO	dao = new TktOrderjdbcDAO();
		//新增
//		TktOrderVO	tktOrderVO1 = new TktOrderVO();
//		tktOrderVO1.setTkt_order_no(5);
//		tktOrderVO1.setMem_no(2);
//		tktOrderVO1.setMem_coup_no(4);
//		tktOrderVO1.setTkt_no(4);
//		tktOrderVO1.setOriginal_price(4);
//		tktOrderVO1.setOrderdate(java.sql.Date.valueOf("2002-01-01"));
//		tktOrderVO1.setTTL_PRICE(9);
//		dao.insert(tktOrderVO1);
		//修改
//		TktOrderVO tktOrderVO2 = new TktOrderVO();
//		tktOrderVO2.setTkt_order_no(1);
//		tktOrderVO2.setMem_no(9);
//		tktOrderVO2.setMem_coup_no(9);
//		tktOrderVO2.setTkt_no(9);
//		tktOrderVO2.setOriginal_price(9);
//		tktOrderVO2.setOrderdate(java.sql.Date.valueOf("2002-01-01"));
//		tktOrderVO2.setTTL_PRICE(9);
//		dao.update(tktOrderVO2);
//		刪除
//		dao.delete(1);
		//查詢
//		TktOrderVO tktOrderVO3 = dao.findByPrimaryKey(3);
//		System.out.print(tktOrderVO3.getTkt_order_no()+",");
//		System.out.print(tktOrderVO3.getMem_no()+",");
//		System.out.print(tktOrderVO3.getMem_coup_no()+",");
//		System.out.print(tktOrderVO3.getTkt_no()+",");
//		System.out.print(tktOrderVO3.getOriginal_price()+",");
//		System.out.print(tktOrderVO3.getOrderdate()+",");
//		System.out.print(tktOrderVO3.getTTL_PRICE()+",");
		//查詢
		List<TktOrderVO> list = dao.getAll();
		for(TktOrderVO tktOrderVO4 :list) {
			System.out.print(tktOrderVO4.getTkt_order_no()+",");
			System.out.print(tktOrderVO4.getMem_no()+",");
			System.out.print(tktOrderVO4.getMem_coup_no()+",");
			System.out.print(tktOrderVO4.getTkt_no()+",");
			System.out.print(tktOrderVO4.getOriginal_price()+",");
			System.out.print(tktOrderVO4.getOrderdate()+",");
			System.out.print(tktOrderVO4.getTTL_PRICE()+",");	
		}
		
		
	}
}
