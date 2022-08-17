package com.rm_pic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.util.JdbcUtil;

public class RmPicJdbcDAO implements I_RmPicDAO{
	
	private static final String INSERT = "INSERT INTO rm_pic(rm_type_no, rm_pic_img)VALUES(?,?)";
	private static final String UPDATE = "UPDATE rm_pic SET rm_type_no=?, rm_pic_img=? WHERE rm_pic_no=?";
	private static final String DELETE = "DELETE FROM rm_pic WHERE rm_pic_no=?";
	private static final String GET_ONE = "SELECT * FROM rm_pic WHERE rm_pic_no=?";
	private static final String GET_ALL = "SELECT * FROM rm_pic";
	
	static { // 資料庫連線
		try {
			Class.forName(JdbcUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(RmPicVO rmPicVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT)) {
			ps.setInt(1,rmPicVO.getRm_pic_no());
			ps.setBytes(2,rmPicVO.getRm_pic_img());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(RmPicVO rmPicVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE)) {
			ps.setInt(1,rmPicVO.getRm_type_no());
			ps.setBytes(2,rmPicVO.getRm_pic_img());
			ps.setInt(3,rmPicVO.getRm_pic_no());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer rm_pic_no) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(DELETE)) {
			 
			ps.setInt(1,rm_pic_no);
			ps.executeUpdate();
			  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public RmPicVO getOne(Integer rm_pic_no) {
		ResultSet rs = null;
		RmPicVO rp = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ONE)) {
			ps.setInt(1, rm_pic_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				rp = new RmPicVO();
				rp.setRm_pic_no(rs.getInt("rm_pic_no"));
				rp.setRm_type_no(rs.getInt("rm_type_no"));
				rp.setRm_pic_img(rs.getBytes("rm_pic_img"));
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
		return rp;
	}

	@Override
	public List<RmPicVO> getAll() {
		List<RmPicVO> rpAll = new ArrayList<RmPicVO>();
		ResultSet rs = null;
		RmPicVO rp = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ALL)) {
			rs = ps.executeQuery();
			while (rs.next()) {
			rp = new RmPicVO();
			rp.setRm_pic_no(rs.getInt("rm_pic_no"));
			rp.setRm_type_no(rs.getInt("rm_type_no"));
			rp.setRm_pic_img(rs.getBytes("rm_pic_img"));
			rpAll.add(rp);
			
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
		return rpAll;
	}
}
