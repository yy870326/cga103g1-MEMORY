package com.store.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class StoreDAO implements I_StoreDAO{
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
	public void insert(StoreVO storeVO) {
		 
		String sql = "INSERT INTO store (\r\n"
				+ " \r\n"
				+ " store_acc, \r\n"
				+ " store_pwd,\r\n"
				+ " acc_status,\r\n"
				+ " store_name,\r\n"
				+ " store_gui,\r\n"
				+ " store_rep,\r\n"
				+ " store_tel,\r\n"
				+ " store_fax,\r\n"
				+ " store_add,\r\n"
				+ " store_poc,\r\n"
				+ " store_con_phone,\r\n"
				+ " store_con_add,\r\n"
				+ " store_email,\r\n"
				+ " store_reg_date,\r\n"
				+ " bank_account,\r\n"
				+ " store_tkt_rating_score,\r\n"
				+ " store_tkt_rating_count,\r\n"
				+ " store_tkt_rating,\r\n"
				+ " store_rm_rating_score,\r\n"
				+ " store_rm_rating_count,\r\n"
				+ " store_act_rating_score, \r\n"
				+ " store_act_rating_count,\r\n"
				+ " store_report_count \r\n"
				+ ") \r\n"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, storeVO.getStore_acc());
			ps.setString(2, storeVO.getStore_pwd());
			ps.setInt(3,storeVO.getAcc_status());
			ps.setString(4,storeVO.getStore_name());
			ps.setString(5,storeVO.getStore_gui());
			ps.setString(6,storeVO.getStore_rep());
			ps.setString(7,storeVO.getStore_tel());
			ps.setString(8,storeVO.getStore_fax());
			ps.setString(9,storeVO.getStore_add());
			ps.setString(10,storeVO.getStore_poc());
			ps.setString(11,storeVO.getStore_con_phone());
			ps.setString(12,storeVO.getStore_con_add());
			ps.setString(13,storeVO.getStore_email());
			ps.setDate(14,storeVO.getStore_reg_date());
			ps.setString(15,storeVO.getBank_account());
			ps.setInt(16,storeVO.getStore_tkt_rating_scoure());
			ps.setInt(17,storeVO.getStore_tkt_rating_count());
			ps.setInt(18,storeVO.getStore_tkt_rating());
			ps.setInt(19,storeVO.getStore_rm_rating_score());
			ps.setInt(20,storeVO.getStore_rm_rating_count());
			ps.setInt(21,storeVO.getStore_act_rating_score());
			ps.setInt(22,storeVO.getStore_act_rating_count());
			ps.setInt(23,storeVO.getStore_report_count());
			
			ps.executeUpdate();
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(StoreVO storeVO) {
		String sql = "UPDATE store SET \r\n"
				+ " store_acc = ?, \r\n"
				+ " store_pwd = ?,\r\n"
				+ " acc_status = ?,\r\n"
				+ " store_name = ?,\r\n"
				+ " store_gui = ?,\r\n"
				+ " store_rep = ?,\r\n"
				+ " store_tel = ?,\r\n"
				+ " store_fax = ?,\r\n"
				+ " store_add = ?,\r\n"
				+ " store_poc = ?,\r\n"
				+ " store_con_phone = ?,\r\n"
				+ " store_con_add = ?,\r\n"
				+ " store_email = ?,\r\n"
				+ " store_reg_date = ?,\r\n"
				+ " bank_account = ?,\r\n"
				+ " store_tkt_rating_score = ?,\r\n"
				+ " store_tkt_rating_count = ?,\r\n"
				+ " store_tkt_rating = ?,\r\n"
				+ " store_rm_rating_score = ?,\r\n"
				+ " store_rm_rating_count = ?,\r\n"
				+ " store_act_rating_score = ?, \r\n"
				+ " store_act_rating_count = ?,\r\n"
				+ " store_report_count = ?\r\n"
				+ " WHERE \r\n"
				+ " store_no = ?;";
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
					ps.setString(1, storeVO.getStore_acc());
					ps.setString(2, storeVO.getStore_pwd());
					ps.setInt(3,storeVO.getAcc_status());
					ps.setString(4,storeVO.getStore_name());
					ps.setString(5,storeVO.getStore_gui());
					ps.setString(6,storeVO.getStore_rep());
					ps.setString(7,storeVO.getStore_tel());
					ps.setString(8,storeVO.getStore_fax());
					ps.setString(9,storeVO.getStore_add());
					ps.setString(10,storeVO.getStore_poc());
					ps.setString(11,storeVO.getStore_con_phone());
					ps.setString(12,storeVO.getStore_con_add());
					ps.setString(13,storeVO.getStore_email());
					ps.setDate(14,storeVO.getStore_reg_date());
					ps.setString(15,storeVO.getBank_account());
					ps.setInt(16,storeVO.getStore_tkt_rating_scoure());
					ps.setInt(17,storeVO.getStore_tkt_rating_count());
					ps.setInt(18,storeVO.getStore_tkt_rating());
					ps.setInt(19,storeVO.getStore_rm_rating_score());
					ps.setInt(20,storeVO.getStore_rm_rating_count());
					ps.setInt(21,storeVO.getStore_act_rating_score());
					ps.setInt(22,storeVO.getStore_act_rating_count());
					ps.setInt(23,storeVO.getStore_report_count());
					ps.setInt(24, storeVO.getStore_no());
					
					ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		};

		
	}

	@Override
	public void delete(Integer store_no) {
		String sql = "DELETE FROM store WHERE store_no = ?";
			try(Connection con = ds.getConnection();){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, store_no);
			
			ps.executeUpdate();
		}catch(Exception e) {
			e.getStackTrace();
		};
		
	}

	@Override
	public StoreVO queryStore(Integer store_no) {
		String sql = "SELECT * FROM store WHERE store_no = ? ORDER BY store_no;";
		StoreVO	storeVO = new StoreVO();
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
								){
			ps.setInt(1, store_no);
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_acc(rs.getString("store_acc"));
				storeVO.setStore_pwd(rs.getString("store_pwd"));
				storeVO.setAcc_status(rs.getByte("acc_status"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_gui(rs.getString("store_gui"));
				storeVO.setStore_rep(rs.getString("store_rep"));
				storeVO.setStore_tel(rs.getString("store_tel"));
				storeVO.setStore_fax(rs.getString("store_fax"));
				storeVO.setStore_add(rs.getString("store_add"));
				storeVO.setStore_poc(rs.getString("store_poc"));
				storeVO.setStore_con_phone(rs.getString("store_con_phone"));
				storeVO.setStore_con_add(rs.getString("store_con_add"));
				storeVO.setStore_email(rs.getString("store_email"));
				storeVO.setStore_reg_date(rs.getDate("store_reg_date"));
				storeVO.setBank_account(rs.getString("bank_account"));
				storeVO.setStore_tkt_rating_scoure(rs.getInt("store_tkt_rating_scoure"));
				storeVO.setStore_tkt_rating_count(rs.getInt("store_tkt_rating_count"));
				storeVO.setStore_tkt_rating(rs.getInt("store_tkt_rating"));
				storeVO.setStore_rm_rating_score(rs.getInt("store_rm_rating_score"));
				storeVO.setStore_rm_rating_count(rs.getInt("store_rm_rating_count"));
				storeVO.setStore_act_rating_score(rs.getInt("store_act_rating_score"));
				storeVO.setStore_act_rating_count(rs.getInt("store_act_rating_count"));
				storeVO.setStore_report_count(rs.getInt("store_report_count"));
			}
		}catch(Exception e) {
					e.getStackTrace();
				}
		
		return storeVO;
	}

	@Override
	public List<StoreVO> getAll() {
		String sql = "SELECT * FROM store ORDER BY store_no;";
		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = new StoreVO();
		 
		
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				
				){
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_acc(rs.getString("store_acc"));
				storeVO.setStore_pwd(rs.getString("store_pwd"));
				storeVO.setAcc_status(rs.getByte("acc_status"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_gui(rs.getString("store_gui"));
				storeVO.setStore_rep(rs.getString("store_rep"));
				storeVO.setStore_tel(rs.getString("store_tel"));
				storeVO.setStore_fax(rs.getString("store_fax"));
				storeVO.setStore_add(rs.getString("store_add"));
				storeVO.setStore_poc(rs.getString("store_poc"));
				storeVO.setStore_con_phone(rs.getString("store_con_phone"));
				storeVO.setStore_con_add(rs.getString("store_con_add"));
				storeVO.setStore_email(rs.getString("store_email"));
				storeVO.setStore_reg_date(rs.getDate("store_reg_date"));
				storeVO.setBank_account(rs.getString("bank_account"));
				storeVO.setStore_tkt_rating_scoure(rs.getInt("store_tkt_rating_scoure"));
				storeVO.setStore_tkt_rating_count(rs.getInt("store_tkt_rating_count"));
				storeVO.setStore_tkt_rating(rs.getInt("store_tkt_rating"));
				storeVO.setStore_rm_rating_score(rs.getInt("store_rm_rating_score"));
				storeVO.setStore_rm_rating_count(rs.getInt("store_rm_rating_count"));
				storeVO.setStore_act_rating_score(rs.getInt("store_act_rating_score"));
				storeVO.setStore_act_rating_count(rs.getInt("store_act_rating_count"));
				storeVO.setStore_report_count(rs.getInt("store_report_count"));
				
			}
			
		}catch(Exception e) {
					e.getStackTrace();
				}
		
		return list;
	}
	
}
