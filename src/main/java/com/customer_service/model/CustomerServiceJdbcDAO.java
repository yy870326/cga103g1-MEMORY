package com.customer_service.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;

public class CustomerServiceJdbcDAO implements I_CustomerServiceDAO {
	private static final String INSERT = "INSERT INTO Customer_Service(STORE_NO,EMP_NO,MEM_NO,MSG_CONTENT,MSG_DIRECTION,MSG_TIME)VALUES(?,?,?,?,?,now())";
	private static final String FIND = "select * from Customer_Service "
			+ "where (msg_direction =? and (store_no=? or  mem_no=?))";

	static {
		try {
			Class.forName(JdbcUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(CustomerServiceVO customerServiceVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(INSERT)) {
			Integer storeNo = customerServiceVO.getStore_no();
			if (storeNo == null) {
				pstmt.setNull(1, Types.INTEGER);
			} else {
				pstmt.setInt(1, customerServiceVO.getStore_no());
			}
			
			pstmt.setInt(2, customerServiceVO.getEmp_no());
			
			Integer memNo = customerServiceVO.getMem_no();
			if (memNo == null) {
				pstmt.setNull(3, Types.INTEGER);
			} else {
				pstmt.setInt(3, customerServiceVO.getMem_no());
			}
			
			pstmt.setString(4, customerServiceVO.getMsg_content());
			pstmt.setInt(5, customerServiceVO.getMsg_direction());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	@Override
	public List<CustomerServiceVO> findMsg(CustomerServiceVO customerServiceVO) {
		List<CustomerServiceVO> csMSG = new ArrayList<>();
		CustomerServiceVO cs = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD); // 輸入在try內會自動關閉
				PreparedStatement pstmt = con.prepareStatement(FIND);) {
			pstmt.setInt(1, customerServiceVO.getMsg_direction());
			Integer storeNo = customerServiceVO.getStore_no();
			if (storeNo == null) {
				pstmt.setNull(2, Types.INTEGER);
			} else {
				pstmt.setInt(2, customerServiceVO.getStore_no());
			}
			
			Integer memNo = customerServiceVO.getMem_no();
			if (memNo == null) {
				pstmt.setNull(3, Types.INTEGER);
			} else {
				pstmt.setInt(3, customerServiceVO.getMem_no());
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cs = new CustomerServiceVO();
				cs.setMsg_id(rs.getInt("msg_id"));
				cs.setStore_no(rs.getInt("Store_no"));
				cs.setEmp_no(rs.getInt("Emp_no"));
				cs.setMem_no(rs.getInt("Mem_no"));
				cs.setMsg_content(rs.getString("Msg_content"));
				cs.setMsg_direction(rs.getInt("Msg_direction"));
				cs.setMsg_time(rs.getDate("Msg_time"));
				csMSG.add(cs);
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
		return csMSG;
	}

}
