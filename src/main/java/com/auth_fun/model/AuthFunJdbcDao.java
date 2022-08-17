package com.auth_fun.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;

public class AuthFunJdbcDao implements I_AuthFunDAO{
	
	private static final String INSERT = "INSERT INTO auth_fun (fun_no,fun_name) VALUES (?,?)";
	private static final String UPDATE = "UPDATE auth_fun SET fun_no=? WHERE fun_name=?";
	private static final String GET_ONE = "SELECT * FROM auth_fun WHERE fun_no=?";
	private static final String GET_ALL = "SELECT * FROM auth_fun";
	
	static { // 資料庫連線
		try {
			Class.forName(JdbcUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(AuthFunVO authFunVO) {
			try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
					PreparedStatement ps = con.prepareStatement(INSERT)) {
				
				ps.setInt(1,authFunVO.getFun_no());
				ps.setString(2,authFunVO.getFun_name());
				ps.executeUpdate();
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
	}

	@Override
	public void update(AuthFunVO authFunVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE)) {
			
			ps.setInt(1,authFunVO.getFun_no());
			ps.setString(2,authFunVO.getFun_name());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public AuthFunVO getOne(Integer fun_no) {
		ResultSet rs = null;
		AuthFunVO af = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ONE)) {
			ps.setInt(1,fun_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				af = new AuthFunVO();
				af.setFun_no(rs.getInt("fun_no"));
				af.setFun_name(rs.getString("fun_name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return af;
	}

	@Override
	public List<AuthFunVO> getAll() {
		List<AuthFunVO> afAll = new ArrayList<AuthFunVO>();
		ResultSet rs =null;
		AuthFunVO af =null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ALL)) {rs = ps.executeQuery();
				
				rs = ps.executeQuery();
				while (rs.next()) {
					af = new AuthFunVO();
					af.setFun_no(rs.getInt("fun_no"));
					af.setFun_name(rs.getString("fun_name"));
					afAll.add(af);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
	   }
	   return afAll;
	
	 }
}
