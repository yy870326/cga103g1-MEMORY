package com.system_notification_message.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;

public class SystemNotificationMessageJdbcDAO implements I_SystemNotificationMessageDAO {
	// 新增訊息
	private final String INSERT = "INSERT INTO system_notfication_message(msg,msg_img,msg_time,emp_no,emp_read)  VALUES (?, ?, NOW(),?, ?)";
	// 下架訊息
	private final String DELETE = "DELETE FROM system_notfication_message WHERE msg_no = ?";
	// 顯示訊息
	private final String GETALL = "select * from system_notfication_message order by msg_no desc";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(SystemNotificationMessageVO systemNotificationMessageVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(INSERT)) {

			pstmt.setString(1, systemNotificationMessageVO.getMsg());
			pstmt.setBytes(2, systemNotificationMessageVO.getMsg_img());
			pstmt.setInt(3, systemNotificationMessageVO.getEmp_no());
			pstmt.setInt(4, systemNotificationMessageVO.getEmp_read());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	@Override
	public void delete(SystemNotificationMessageVO systemNotificationMessageVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(DELETE)){
				
				pstmt.setInt(1,systemNotificationMessageVO.getMsg_no());
				pstmt.executeUpdate();

			} catch (SQLException se) {
				se.printStackTrace();
			} 
	}

	@Override
	public List<SystemNotificationMessageVO> getall() {
		List<SystemNotificationMessageVO> snmALL = new ArrayList<>();
		SystemNotificationMessageVO snm = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD); // 輸入在try內會自動關閉
				PreparedStatement pstmt = con.prepareStatement(GETALL);) {
			rs = pstmt.executeQuery();
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
