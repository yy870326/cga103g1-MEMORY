package com.act_rp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;

public class ActRpJdbcDAO implements I_ActRpDAO {
	// 新增
	private final String INSERT = "INSERT INTO act_rp(mem_no,act_no,act_rp_reason,act_rp_content,act_rp_time,act_rp_status)  VALUES ( ?, ?, ?, ?, now(),?)";
//	// 刪除
//	private final String DELETE = "DELETE FROM act_rp WHERE act_rp_no = ?";
	// 修改
	private final String UPDATE = "update  act_rp  set emp_no=?  , act_rp_done_time= now(), act_rp_status=?, act_rp_note=? WHERE act_rp_no = ?";
	// 顯示
	private final String GETALL = "select * from act_rp ";

	static {
		try {
			Class.forName(JdbcUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(ActRpVO actRpVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(INSERT)){
				
				pstmt.setInt(1, actRpVO.getMem_no());
				pstmt.setInt(2, actRpVO.getAct_no());
				pstmt.setInt(3, actRpVO.getAct_rp_reason());
				pstmt.setString(4, actRpVO.getAct_rp_content());
				pstmt.setInt(5, actRpVO.getAct_rp_status());
				pstmt.executeUpdate();

			} catch (SQLException se) {
				se.printStackTrace();
			} 
	}

//	@Override
//	public void delete(ActRpVO ActRpVO) {
//
//	}

	@Override
	public void update(ActRpVO actRpVO) {
		try(Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);	//輸入在try內會自動關閉
				PreparedStatement pstmt = con.prepareStatement(UPDATE)) {
			
			pstmt.setInt(1, actRpVO.getEmp_no());
			pstmt.setInt(2, actRpVO.getAct_rp_status());
			pstmt.setString(3, actRpVO.getAct_rp_note());
			pstmt.setInt(4, actRpVO.getAct_rp_no());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}       
	}

	@Override
	public List<ActRpVO> getall() {
		List<ActRpVO> actrpALL = new ArrayList<>();
		ActRpVO ar = null;
		ResultSet rs = null;
		try(Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GETALL);) 
		{
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ar = new ActRpVO();
				ar.setAct_rp_no(rs.getInt("Act_rp_no"));
				ar.setMem_no(rs.getInt("Mem_no"));
				ar.setAct_no(rs.getInt("Act_no"));
				ar.setAct_rp_reason(rs.getInt("Act_rp_reason"));
				ar.setAct_rp_content(rs.getString("Act_rp_content"));
				ar.setAct_rp_time(rs.getDate("Act_rp_time"));
				ar.setEmp_no(rs.getInt("Emp_no"));
				ar.setAct_rp_done_time(rs.getDate("Act_rp_done_time"));
				ar.setAct_rp_status(rs.getInt("Act_rp_status"));
				ar.setAct_rp_note(rs.getString("Act_rp_note"));
				
				
				actrpALL.add(ar);
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
		return actrpALL;
	}

}
