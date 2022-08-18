package com.act_reply.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;

public class ActReplyJdbcDAO implements I_ActReplyDAO {
	// 新增
	private final String INSERT = "INSERT INTO act_reply(act_no,reply_content,mem_no,reply_time)  VALUES (?, ?, ?,now())";
	// 刪除
	private final String DELETE = "DELETE FROM act_reply WHERE act_reply_no = ?";
	// 顯示
	private final String GETALL = "select * from act_reply ";

	static {
		try {
			Class.forName(JdbcUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(ActReplyVO actReplyVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(INSERT)){
				
				pstmt.setInt(1, actReplyVO.getAct_no());
				pstmt.setString(2, actReplyVO.getReply_content());
				pstmt.setInt(3, actReplyVO.getMem_no());
				pstmt.executeUpdate();

			} catch (SQLException se) {
				se.printStackTrace();
			} 
	}

	@Override
	public void delete(ActReplyVO actReplyVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(DELETE)){
				
				pstmt.setInt(1,actReplyVO.getAct_reply_no());
				pstmt.executeUpdate();

			} catch (SQLException se) {
				se.printStackTrace();
			} 

	}

	@Override
	public List<ActReplyVO> getall(ActReplyVO actReplyVO) {
		List<ActReplyVO> actreplyALL = new ArrayList<>();
		ActReplyVO ar = null;
		ResultSet rs = null;
		try(Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);	//輸入在try內會自動關閉
				PreparedStatement pstmt = con.prepareStatement(GETALL);) 
		{
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ar = new ActReplyVO();
				ar.setAct_reply_no(rs.getInt("Act_reply_no"));
				ar.setAct_no(rs.getInt("Act_no"));
				ar.setReply_content(rs.getString("reply_content"));
				ar.setMem_no(rs.getInt("mem_no"));
				ar.setReply_time(rs.getDate("reply_time"));
				
				actreplyALL.add(ar);
			}
				
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return actreplyALL;
	}

}
