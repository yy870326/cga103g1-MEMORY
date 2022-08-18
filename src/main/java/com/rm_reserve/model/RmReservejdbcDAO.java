package com.rm_reserve.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;

public class RmReservejdbcDAO implements I_RmReserveDAO{
	
	
	private static final String INSERT=
			"INSERT INTO RM_RESERVE(rm_type_no,rm_type_amout,rm_schedule_date,reservation_amount)VALUES(?,?,?,?)";
	private static final String UPDATE=
			"UPDATE RM_RESERVE SET rm_type_no=?,rm_type_amout=?, rm_schedule_date=?,reservation_amount=? WHERE serial_no=?";
	private static final String CANCEL= 
			"SELECT * FROM RM_RESERVE WHERE serial_no=?";
	private static final String GET_ONE=
			"SELECT * FROM RM_RESERVE WHERE serial_no=?";
	private static final String GET_ALL=
			"SELECT * FROM RM_RESERVE";
	
	static {
		try {
			Class.forName(JdbcUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void  insert(RmReserveVO rmreserveVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT)) {
				
			ps.setInt(1, rmreserveVO.getRm_type_no());
			ps.setInt(2, rmreserveVO.getRm_type_amout());
			ps.setDate(3, rmreserveVO.getRm_schedule_date());
			ps.setInt(4, rmreserveVO.getReservation_amount());
			
			ps.executeUpdate();
			
	        } catch(SQLException e) {
				e.printStackTrace();
			}
}
	
	@Override
	public void update(RmReserveVO rmreserveVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE)) {
				
				ps.setInt(1, rmreserveVO.getRm_type_no());
				ps.setInt(2, rmreserveVO.getRm_type_amout());
				ps.setDate(3, rmreserveVO.getRm_schedule_date());
				ps.setInt(4, rmreserveVO.getReservation_amount());
				ps.setInt(5, rmreserveVO.getSerial_no());
				
				ps.executeUpdate();
						
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void chanel(RmReserveVO rmreserveVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(CANCEL)) {
				
				ps.setInt(1, rmreserveVO.getSerial_no());
				ps.executeUpdate();
						
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public RmReserveVO getOne(Integer serial_no) {
		ResultSet rs =null;
		RmReserveVO rsv = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ONE)) {
				
				ps.setInt(1, serial_no);
				rs = ps.executeQuery();			
				while (rs.next()) {
					rsv = new RmReserveVO();
					rsv.setRm_type_no(rs.getInt("rm_type_no"));
					rsv.setRm_type_amout(rs.getInt("rm_type_no"));
					rsv.setRm_schedule_date(rs.getDate("rm_schedule_date"));
					rsv.setReservation_amount(rs.getInt("reservation_amount"));
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) {
				try {
					rs.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rsv;
}


	@Override
	public List<RmReserveVO> getAll() {
		List<RmReserveVO> rsvAll = new ArrayList<RmReserveVO>();
		ResultSet rs = null;
		RmReserveVO rsv = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ALL)) {
			
			rs = ps.executeQuery();
			while(rs.next()) {
				rsv = new RmReserveVO();
				rsv.setSerial_no(rs.getInt("serial_NO"));
				rsv.setRm_type_no(rs.getInt("rm_type_no"));
				rsv.setRm_type_amout(rs.getInt("rm_type_amout"));
				rsv.setRm_schedule_date(rs.getDate("rm_schedule_date"));
				rsv.setReservation_amount(rs.getInt("reservation_amount"));
				rsvAll.add(rsv);			
			}		
		}catch (SQLException e) {
			e.printStackTrace();
	   }finally {
		   if (rs !=null) {
			   try {
				   rs.close();
			   }catch (SQLException e) {
				e.printStackTrace();
			}
		  }
	   }
		return rsvAll;
	}	
}
  
	 