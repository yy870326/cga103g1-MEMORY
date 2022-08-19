package com.auth_fun.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.auth.model.AuthVO;
import com.util.JdbcUtil;

public class AuthFunJdbcDAO implements I_AuthFunDAO {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "06210323";

	private static final String INSERT = "INSERT INTO auth_fun (fun_no,fun_name) VALUES (?,?)";
	private static final String UPDATE = "UPDATE auth_fun SET fun_no=? WHERE fun_name=?";
	private static final String DELETE = "DELETE FROM auth_fun WHERE fun_no=?";
	private static final String GET_ONE = "SELECT * FROM auth_fun WHERE fun_no=?";
	private static final String GET_ALL = "SELECT * FROM auth_fun";

	@Override
	public void insert(AuthFunVO authFunVO) {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(INSERT);

			ps.setInt(1, authFunVO.getFun_no());
			ps.setString(2, authFunVO.getFun_name());
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
	public void update(AuthFunVO authFunVO) {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(UPDATE);

			ps.setInt(1, authFunVO.getFun_no());
			ps.setString(2, authFunVO.getFun_name());
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
	public void delete(Integer fun_no) {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(DELETE);

			ps.setInt(1, fun_no);
			ps.executeUpdate();

			// Handle any driver errors
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
	public AuthFunVO findByPrimaryKey(Integer fun_no) {

		AuthFunVO authFunVO = null;
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
				authFunVO = new AuthFunVO();
				authFunVO.setFun_no(rs.getInt("fun_no"));
				authFunVO.setFun_name(rs.getString("fun_name"));
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
		return authFunVO;
	}

	@Override
	public List<AuthFunVO> getAll() {
		List<AuthFunVO> list = new ArrayList<AuthFunVO>();
		AuthFunVO authFunVO = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				authFunVO = new AuthFunVO();
				authFunVO.setFun_no(rs.getInt("fun_no"));
				authFunVO.setFun_name(rs.getString("fun_name"));
				list.add(authFunVO);
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
		AuthFunJdbcDAO dao = new AuthFunJdbcDAO();

		// 新增
//		AuthFunVO authFunVO1 = new AuthFunVO();
//		authFunVO1.setFun_no(10);
//		authFunVO1.setFun_name("留言狀態管理員");
//		dao.insert(authFunVO1);

		// 修改
//		AuthFunVO authFunVO2 = new AuthFunVO();
//		authFunVO2.setFun_no(8);
//		authFunVO2.setFun_name("打雜管理員");
//		dao.update(authFunVO2);

//	//刪除
//		dao.delete(10);

		// 查詢
		AuthFunVO authFunVO3 = dao.findByPrimaryKey(1);
		System.out.println(authFunVO3.getFun_no() + ",");
		System.out.println(authFunVO3.getFun_name() + ",");
		System.out.println("----------------------");

		// 查詢多筆資料
		List<AuthFunVO> list = dao.getAll();
		for (AuthFunVO aAuthFun : list) {
			System.out.println(aAuthFun.getFun_no() + ",");
			System.out.println(aAuthFun.getFun_name() + ",");
			System.out.println();
		}
	}
}
