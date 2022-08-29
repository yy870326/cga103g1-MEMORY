package com.auth.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;

public class AuthJdbcDAO implements I_AuthDAO {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "06210323";

	private static final String INSERT = "INSERT INTO auth(fun_no,emp_no)VALUES (?,?)";
	private static final String UPDATE = "UPDATE auth SET emp_no=? where fun_no=?";
	private static final String CANCEL = "UPDATE * FROM auth WHERE fun_no=?";
	private static final String GET_ONE = "SELECT * FROM auth WHERE fun_no=?";
	private static final String GET_ALL = "SELECT * FROM auth";

	@Override
	public void insert(AuthVO authVO) {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(INSERT);

			ps.setInt(1, authVO.getFun_no());
			ps.setInt(2, authVO.getEmp_no());
			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
	public void update(AuthVO authVO) {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(UPDATE);

			ps.setInt(1, authVO.getFun_no());
			ps.setInt(2, authVO.getEmp_no());
			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
	public void cancel(AuthVO authVO) {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(CANCEL);

			ps.setInt(1, authVO.getFun_no());

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
	public AuthVO findByPrimaryKey(Integer fun_no) {

		AuthVO authVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ONE);

			ps.setInt(1, fun_no);

			rs = ps.executeQuery();

			while (rs.next()) {
				authVO = new AuthVO();
				authVO.setFun_no(rs.getInt("fun_no"));
				authVO.setEmp_no(rs.getInt("emp_no"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
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
		return authVO;
	}
	

	@Override
	public AuthVO findByFk(Integer emp_no) {
		return null;
	}

	@Override
	public List<AuthVO> getAllAuth() {
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				authVO = new AuthVO();
				authVO.setFun_no(rs.getInt("fun_no"));
				authVO.setEmp_no(rs.getInt("emp_no"));
				list.add(authVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
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
		AuthJdbcDAO dao = new AuthJdbcDAO();

		// 新增
//		AuthVO authVO1 = new AuthVO();
//		authVO1.setFun_no(7);
//		authVO1.setEmp_no(5);
//		dao.insert(authVO1);
//		System.out.println("新增成功");

		// 修改
//		AuthVO authVO2 = new AuthVO();
//		authVO2.setEmp_no(5);
//		authVO2.setFun_no(1);
//		dao.update(authVO2);
//		System.out.println("修改成功");
//
//		// 查詢單一筆資料
//		AuthVO authVO3 = dao.findByPrimaryKey(5);
//		System.out.print(authVO3.getFun_no() + ",");
//		System.out.println(authVO3.getEmp_no());
//		System.out.println("----------------------");
//
//		// 查詢多筆資料
		List<AuthVO> list = dao.getAllAuth();
		for (AuthVO aAuth : list) {
			System.out.print(aAuth.getFun_no() + ",");
			System.out.println(aAuth.getEmp_no());
			System.out.println();
		}
	}

}