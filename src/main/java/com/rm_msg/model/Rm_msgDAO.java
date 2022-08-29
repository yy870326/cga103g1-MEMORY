package com.rm_msg.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		String sql = "SELECT "
				+ "emp_no,\r\n"
				+ "mem_no,\r\n"
				+ "store_no,\r\n"
				+ "rm_msg_reason,\r\n"
				+ "rm_msg_date,\r\n"
				+ "rm_msg_status,\r\n"
				+ "rm_msg_donetime \r\n"
				+ "FROM \r\n"
				+ "rm_msg \r\n"
				+ "WHERE \r\n"
				+ "rm_msg_no = ?;";
		Rm_msgVO rm = null;
		
		try(
			Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				){
			ps.setInt(1, rm_msg_no);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				rm = new Rm_msgVO();
				rm.setEmp_no(rs.getInt("emp_no"));
				rm.setMem_no(rs.getInt("mem_no"));
				rm.setStore_no(rs.getInt("store_no"));
				rm.setRm_msg_reason(rs.getString("rm_msg_reason"));
				rm.setRm_msg_date(rs.getDate("rm_msg_date"));
				rm.setRm_msg_status(rs.getInt("rm_msg_status"));
				rm.setRm_msg_donetime(rs.getDate("rm_msg_donetime"));
			}
		}catch(
		SQLException e) {
			e.getStackTrace();
		}
		return rm;
	}

	@Override
	public List<Rm_msgVO> getAll() {
		List<Rm_msgVO> list = new ArrayList<Rm_msgVO>();
		Rm_msgVO rm = new Rm_msgVO();
		String sql = "SELECT "
				+ "emp_no,\r\n"
				+ "mem_no,\r\n"
				+ "store_no,\r\n"
				+ "rm_msg_reason,\r\n"
				+ "rm_msg_date,\r\n"
				+ "rm_msg_status,\r\n"
				+ "rm_msg_donetime \r\n"
				+ "FROM \r\n"
				+ "rm_msg \r\n"
				+ "WHERE \r\n"
				+ "rm_msg_no = ?;";
		ResultSet rs = null;
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);)
		{   	rs = ps.executeQuery();
			while(rs.next()) {
				rm.setEmp_no(rs.getInt("emp_no"));
				rm.setMem_no(rs.getInt("mem_no"));
				rm.setStore_no(rs.getInt("store_no"));
				rm.setRm_msg_reason(rs.getString("rm_msg_reason"));
				rm.setRm_msg_date(rs.getDate("rm_msg_date"));
				rm.setRm_msg_status(rs.getInt("rm_msg_status"));
				rm.setRm_msg_donetime(rs.getDate("rm_msg_donetime"));
				list.add(rm);
				
			}
			
		}catch(
			SQLException e	) {
			e.getStackTrace();
		}
		return list;
	}
	
}
