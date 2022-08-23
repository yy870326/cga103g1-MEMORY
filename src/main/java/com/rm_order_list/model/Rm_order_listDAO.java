package com.rm_order_list.model;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Rm_order_listDAO implements I_Rm_order_listDAO {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(Exception e) {
			e.getStackTrace();
		}
		
	}
	@Override
	public void insert(Rm_order_listVO rm_order_list) {
		String sql = "INSERT INTO rm_order_list (\r\n"
				+ " rm_type_no,\r\n"
				+ " rm_order_no,\r\n"
				+ " rm_amount,\r\n"
				+ " rm_price,\r\n"
				+ " arrival_date,\r\n"
				+ " departure_date,\r\n"
				+ " rm_check_in\r\n"
				+ ") VALUES (\r\n"
				+ "   ?,?,?,?,?,?,?\r\n"
				+ ");";
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, rm_order_list.getRm_type_no());
			ps.setInt(2,rm_order_list.getRm_order_no());
			ps.setInt(3,rm_order_list.getRm_amount());
			ps.setInt(4,rm_order_list.getRm_price());
			ps.setDate(5,rm_order_list.getArrival_date());
			ps.setDate(6,	rm_order_list.getDeparture_date());
			ps.setString(7,rm_order_list.getRm_check_in());
			
			
			ps.executeUpdate();
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
	}
		
	

	@Override
	public void update(Rm_order_listVO 	rm_order_list) {
		String sql = "UPDATE rm_order_list SET \r\n"
				+ " rm_type_no = ?,\r\n"
				+ " rm_order_no = ?,\r\n"
				+ " rm_amount = ?,\r\n"
				+ " rm_price = ?,\r\n"
				+ " arrival_date = ?,\r\n"
				+ " departure_date = ?,\r\n"
				+ " rm_check_in = ?\r\n"
				+ " WHERE \r\n"
				+ " rm_order_list_no = ?;";
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
					ps.setInt(1, rm_order_list.getRm_type_no());
					ps.setInt(2, rm_order_list.getRm_type_no());
					ps.setInt(3, rm_order_list.getRm_type_no());
					ps.setInt(4, rm_order_list.getRm_type_no());
					ps.setDate(5, rm_order_list.getArrival_date());
					ps.setDate(6, rm_order_list.getDeparture_date());
					ps.setString(7, rm_order_list.getRm_check_in());
					ps.setInt(8, rm_order_list.getRm_type_no());
					
					ps.executeUpdate();
			
		}catch(Exception e) {
					e.addSuppressed(e);
				}
	}

	@Override
	public void delete(Integer rm_order_list_no) {
		String sql = "DELETE FROM rm_order_list WHERE rm_order_list_no = ?;";
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				){
			ps.setInt(1, rm_order_list_no);
			ps.executeUpdate();
		}catch(Exception e) { 
			e.getStackTrace();
		}
	
	}

	@Override
	public Rm_order_listVO findByPrimaryKey(Integer rm_order_list_no) {
		String sql = "SELECT \r\n"
				+ "rm_type_no,\r\n"
				+ "rm_order_no,\r\n"
				+ "rm_amount,\r\n"
				+ "rm_price,\r\n"
				+ "arrival_date,\r\n"
				+ "departure_date,\r\n"
				+ "rm_check_in \r\n"
				+ "FROM \r\n"
				+ "rm_order_list \r\n"
				+ "WHERE \r\n"
				+ "rm_order_list_no = ? \r\n";
		Rm_order_listVO rol = null;
				
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
				ps.setInt(1, rm_order_list_no);
				ResultSet rs = ps.executeQuery();
				
				
				while(rs.next()) {
					rol = new Rm_order_listVO();
					rol.setRm_type_no(rs.getInt("rm_type_no"));
					rol.setRm_order_no(rs.getInt("rm_order_no"));
					rol.setRm_amount(rs.getInt("rm_amount"));
					rol.setRm_price(rs.getInt("rm_price"));
					rol.setArrival_date(rs.getDate("arrival_date"));
					rol.setDeparture_date(rs.getDate("departure_date"));
					rol.setRm_check_inl(rs.getString("rm_check_in"));
					
				}
		}catch(
				Exception e) {
					e.printStackTrace();
				}

		return rol;
	}

	@Override
	public List<Rm_order_listVO> getAll() {
		List<Rm_order_listVO> list = new ArrayList<Rm_order_listVO>();
		Rm_order_listVO rol =null;
		
		String sql = "SELECT \r\n"
				+ "rm_type_no,\r\n"
				+ "rm_order_no,\r\n"
				+ "rm_amount,\r\n"
				+ "rm_price,\r\n"
				+ "arrival_date,\r\n"
				+ "departure_date,\r\n"
				+ "rm_check_in \r\n"
				+ "FROM \r\n"
				+ "rm_order_list \r\n"
				+ "ORDER BY rm_order_list_no;";
		ResultSet rs = null;
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);)
		{	rs = ps.executeQuery();
		
		while(rs.next()) {
			rol = new Rm_order_listVO();
			rol.setRm_type_no(rs.getInt("rm_type_no"));
			rol.setRm_order_no(rs.getInt("rm_order_no"));
			rol.setRm_amount(rs.getInt("rm_amount"));
			rol.setRm_price(rs.getInt("rm_price"));
			rol.setArrival_date(rs.getDate("arrival_date"));
			rol.setDeparture_date(rs.getDate("departure_date"));
			rol.setRm_check_inl(rs.getString("rm_check_in"));
		}
			
		}catch(Exception e) {
			e.getStackTrace();
		}
		
		return list;
	}

}
