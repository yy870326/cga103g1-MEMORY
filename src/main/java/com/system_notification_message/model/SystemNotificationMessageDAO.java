package com.system_notification_message.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SystemNotificationMessageDAO implements I_SystemNotificationMessageDAO {
	// 新增訊息
	private final String INSERT = "INSERT INTO system_notfication_message(msg,msg_img,msg_time,emp_no,emp_read)  VALUES (?, ?, NOW(),?, ?)";
	// 下架訊息
	private final String DELETE = "DELETE FROM system_notfication_message WHERE msg_no = ?";
	// 顯示訊息
	private final String GETALL = "select * from system_notfication_message order by msg_no desc";

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(SystemNotificationMessageVO systemNotificationMessageVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);
			
			ps.setString(1, systemNotificationMessageVO.getMsg());
			ps.setBytes(2, systemNotificationMessageVO.getMsg_img());
			ps.setInt(3, systemNotificationMessageVO.getEmp_no());
			ps.setInt(4, systemNotificationMessageVO.getEmp_read());
			ps.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	@Override
	public void delete(SystemNotificationMessageVO systemNotificationMessageVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(DELETE);
			
			ps.setInt(1, systemNotificationMessageVO.getMsg_no());
			ps.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	@Override
	public List<SystemNotificationMessageVO> getall() {
		List<SystemNotificationMessageVO> snmALL = new ArrayList<>();
		SystemNotificationMessageVO snm = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETALL);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				snm = new SystemNotificationMessageVO();
				snm.setMsg_no(rs.getInt("msg_no"));
				snm.setMsg(rs.getString("msg"));
				snm.setMsg_img(rs.getBytes("msg_img"));
				snm.setMsg_time(rs.getDate("msg_time"));
				snm.setEmp_no(rs.getInt("emp_no"));
				snm.setEmp_read(rs.getInt("emp_read"));

				snmALL.add(snm);
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
		return snmALL;
	}
}
