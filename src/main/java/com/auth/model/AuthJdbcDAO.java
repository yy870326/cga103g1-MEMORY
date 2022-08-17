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


public class AuthJdbcDAO implements I_AuthDAO{
	
	private static final String INSERT = "INSERT INTO auth(fun_no,emp_no)VALUES (?,?)";
	private static final String UPDATE = "UPDATE auth SET fun_no=? , emp_no=? where emp_no=?";
	private static final String CANCEL=  "SELECT * FROM auth WHERE fun_no=?";
	private static final String GET_ONE= "SELECT * FROM auth WHERE fun_no=?";
	private static final String GET_ALL="SELECT * FROM auth";
	
	static {
		try {
			Class.forName(JdbcUtil.DRIVER);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(AuthVO authVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT)) {
			ps.setInt(1,authVO.getFun_no());
			ps.setInt(2,authVO.getEmp_no());
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(AuthVO authVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE)) {
			ps.setInt(1,authVO.getFun_no());
			ps.setInt(2,authVO.getEmp_no());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			}
	}

	@Override
	public void cancel(AuthVO authVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(CANCEL)) {
			ps.setInt(1,authVO.getFun_no());
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public AuthVO getOne(Integer fun_no) {
		ResultSet rs = null;
		AuthVO av = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ONE)) {
			ps.setInt(1,fun_no);
			rs = ps.executeQuery();
			while(rs.next()) {
				av = new AuthVO();
				av.setFun_no(rs.getInt("fun_no"));
				av.setEmp_no(rs.getInt("emp_no"));
			}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
			   if(rs !=null) {
				   try {
					   rs.close();
			     }catch(SQLException e) {
			    	e.printStackTrace();
			     } 
			}
		}
		return av;
	}

	@Override
	public List<AuthVO> getAll() {
		List<AuthVO> avAll = new ArrayList<AuthVO>();
		ResultSet rs = null;
		AuthVO av = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ALL)) {
			
		rs = ps.executeQuery();
		while(rs.next()) {
			av = new AuthVO();
			av.setFun_no(rs.getInt("fun_no"));
			av.setEmp_no(rs.getInt("emp_no"));
			avAll.add(av);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return avAll;
	}
}
	