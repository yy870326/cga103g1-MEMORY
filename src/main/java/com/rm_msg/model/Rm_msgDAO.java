package com.rm_msg.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Rm_msgDAO implements I_Rm_msgDAO{
	private static DataSource ds = null;
	static {
		try{
			Context ctx = new InitialContext();
			ds =(DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(
				Exception e) {
			e.getStackTrace();
		}
	}

	
	@Override
	public void insert(Rm_msgVO rm_msgVO) {
		String sql = "INSERT INTO rm_msg(\r\n"
				+ "	\r\n"
				+ "	emp_no,\r\n"
				+ "    mem_no,\r\n"
				+ "    store_no,\r\n"
				+ "    rm_msg_reason,\r\n"
				+ "    rm_msg_date,\r\n"
				+ "    rm_msg_status,\r\n"
				+ "    rm_msg_donetime\r\n"
				+ ") \r\n"
				+ "VALUES(\r\n"
				+ "?,?,?,?,?,?,?\r\n"
				+ ");";
		
		try(
				Connection con = ds.getConnection();
					PreparedStatement ps = con.prepareStatement(sql);
				){
			ps.setInt(1, rm_msgVO.getEmp_no());
			ps.setInt(2, rm_msgVO.getMem_no());
			ps.setInt(3, rm_msgVO.getStore_no());
			ps.setString(4, rm_msgVO.getRm_msg_reason());
			ps.setDate(5, rm_msgVO.getRm_msg_date());
			ps.setInt(6, rm_msgVO.getRm_msg_status());
			ps.setDate(7, rm_msgVO.getRm_msg_donetime());
			
			ps.executeUpdate();
		}catch(
			Exception e	
				) {
			e.getStackTrace();
		}
	}
	@Override
	public void update(Rm_msgVO rm_msgVO) {
		String sql = "UPDATE rm_msg SET \r\n"
				+ "emp_no = ?,\r\n"
				+ "mem_no = ?,\r\n"
				+ "store_no = ?,\r\n"
				+ "rm_msg_reason = ?,\r\n"
				+ "rm_msg_date = ?,\r\n"
				+ "rm_msg_status = ?,\r\n"
				+ "rm_msg_donetime = ?\r\n"
				+ "WHERE \r\n"
				+ "rm_msg_no = ?;";
		
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
					ps.setInt(1, rm_msgVO.getEmp_no());
					ps.setInt(2, rm_msgVO.getMem_no());
					ps.setInt(3, rm_msgVO.getStore_no());
					ps.setString(4, rm_msgVO.getRm_msg_reason());
					ps.setDate(5, rm_msgVO.getRm_msg_date());
					ps.setInt(6, rm_msgVO.getRm_msg_status());
					ps.setDate(7, rm_msgVO.getRm_msg_donetime());
					ps.setInt(8, rm_msgVO.getRm_msg_no());
		}catch(Exception e ) {
			e.getStackTrace();
		}
		
	}

	@Override
	public void delete(Integer rm_msg_no) {
		String sql = "DELETE FROM rm_msg WHERE rm_msg_no = ?;";
		Rm_msgVO rm_msgVO = new Rm_msgVO();
		try(
				Connection con = ds.getConnection();
					PreparedStatement ps = con.prepareStatement(sql);
				){
			ps.setInt(1, rm_msgVO.getRm_msg_no());
			
			
			
		}catch(SQLException e) {
			e.getStackTrace();
		};
	}

	@Override
	public Rm_msgVO findByPrimaryKey(Integer rm_msg_no) {

		return null;
	}

	@Override
	public List<Rm_msgVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
