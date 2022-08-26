package com.tkt_img.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;







public class TktImgjdbcDAO implements I_TktImgDAO{
	

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "pao103098";
	
	private static final String INSERT_STMT = 
			"INSERT INTO tkt_img (tkt_no,tkt_img) VALUES ( ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT tkt_img_no,tkt_no,tkt_img FROM tkt_img order by tkt_img_no";
		private static final String GET_ONE_STMT = 
			"SELECT tkt_img_no,tkt_no,tkt_img FROM tkt_img where tkt_img_no = ?";
		private static final String DELETE = 
			"DELETE FROM tkt_img where tkt_img_no = ?";
		private static final String UPDATE = 
			"UPDATE tkt_img set  tkt_no=?, tkt_img=? where tkt_img_no = ?";
	@Override
	public void insert(TktImgVO tktimgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, tktimgVO.gettktNO());
			pstmt.setBytes(2, tktimgVO.gettktimg());
			
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
	public void update(TktImgVO tktimgno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, tktimgno.gettktNO());
			pstmt.setBytes(2, tktimgno.gettktimg());
			pstmt.setInt(3, tktimgno.gettktImgNO());

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
	public void delete(Integer TktImgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, TktImgVO);

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
	public TktImgVO findByPrimaryKey(Integer tktImgno) {
		TktImgVO tktimgVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tktImgno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				tktimgVo = new TktImgVO();
				tktimgVo.settktImgNO(rs.getInt("tkt_img_no"));
				tktimgVo.settktNO(rs.getInt("tkt_no"));
				tktimgVo.settktimg(rs.getBytes("tkt_img"));
				
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
		return tktimgVo;
	}

	@Override
	public List<TktImgVO> getAll() {
		List<TktImgVO> list = new ArrayList<TktImgVO>();
		TktImgVO tktimgVO = null;

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
				tktimgVO = new TktImgVO();
				tktimgVO.settktImgNO(rs.getInt("tkt_img_no"));
				tktimgVO.settktNO(rs.getInt("tkt_no"));
				tktimgVO.settktimg(rs.getBytes("tkt_img"));
				list.add(tktimgVO);
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
		TktImgjdbcDAO dao = new TktImgjdbcDAO();
		// 新增
//		TktImgVO tktimg01 = new TktImgVO();
//
//		tktimg01.settktNO(5);
//		tktimg01.settktimg(null);
//		dao.insert(tktimg01);
//		
		// 修改
//		TktImgVO tktimg02 = new TktImgVO();
//		tktimg02.settktImgNO(1);
//		tktimg02.settktNO(99);
//		tktimg02.settktimg(null);
//		dao.update(tktimg02);
//		
//		// 刪除
//		dao.delete(1);
//		
//		// 查詢
//		TktImgVO tktimg03 = dao.findByPrimaryKey(2);
//		System.out.println(tktimg03.gettktImgNO()+",");
//		System.out.println(tktimg03.gettktNO()+",");
//		System.out.println(tktimg03.gettktimg()+",");
//		System.out.println("---------------------");
//		// 查詢
		List<TktImgVO> list = dao.getAll();
		for(TktImgVO aTktImg : list) {
			System.out.println(aTktImg.gettktImgNO()+",");
			System.out.println(aTktImg.gettktNO()+",");
			System.out.println(aTktImg.gettktimg()+",");
		}
	}

}
