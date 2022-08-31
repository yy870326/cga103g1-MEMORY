package com.rm_order_list.model;

import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.util.JdbcUtil;

import java.sql.*;

public class RmOrderListJdbcDAO implements I_RmOrderListDAO {
	
	private static final String INSERT = "INSERT INTO rm_order_list (rm_type_no, rm_order_no, rm_amount, rm_price, arrival_date"
			+ ", departure_date, rm_check_in) VALUES (?,?,?,?,?,?,?);";
	private static final String UPDATE = "UPDATE rm_order_list SET rm_type_no = ?, rm_order_no = ?, rm_amount = ?, rm_price = ?"
			+ ", arrival_date = ?, departure_date = ?, rm_check_in = ? WHERE rm_order_list_no = ?;";
	private static final String CHANGE = "UPDATE rm_order_list SET rm_type_no = ?, rm_amount = ?, rm_price = ?"
			+ ", arrival_date = ?, departure_date = ?, rm_check_in = ? WHERE rm_order_list_no = ?;";
	private static final String DELETE = "DELETE FROM rm_order_list WHERE rm_order_list_no = ?;";
	private static final String GET_ONE = "SELECT rm_type_no, rm_order_no, rm_amount, rm_price, arrival_date, departure_date"
			+ ", rm_check_in FROM rm_order_list WHERE rm_order_list_no = ? ";
	private static final String GET_ALL = "SELECT * FROM rm_order_list";
	private static final String GET_ALL_BY_ORDNO = "SELECT * FROM rm_order_list WHERE rm_order_no = ?";
	private static final String GET_ALL_BY_RMTYPE = "SELECT * FROM rm_order_list WHERE rm_type_no = ?";
	private static DataSource ds = null;
	static { // 資料庫連線
		try {
			Class.forName(JdbcUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	public RmOrderListVO insert(RmOrderListVO rmOrderListVO) {
		
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT)) {

			ps.setInt(1, rmOrderListVO.getRm_type_no());
			ps.setInt(2, rmOrderListVO.getRm_order_no());
			ps.setInt(3, rmOrderListVO.getRm_amount());
			ps.setInt(4, rmOrderListVO.getRm_price());
			ps.setDate(5, rmOrderListVO.getArrival_date());
			ps.setDate(6, rmOrderListVO.getDeparture_date());
			ps.setString(7, rmOrderListVO.getRm_check_in());
			
			ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return rmOrderListVO;
	}
		
	@Override
	public void update(RmOrderListVO rmOrderListVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE)) {

					ps.setInt(1, rmOrderListVO.getRm_type_no());
					ps.setInt(2, rmOrderListVO.getRm_order_no());
					ps.setInt(3, rmOrderListVO.getRm_amount());
					ps.setInt(4, rmOrderListVO.getRm_price());
					ps.setDate(5, rmOrderListVO.getArrival_date());
					ps.setDate(6, rmOrderListVO.getDeparture_date());
					ps.setString(7, rmOrderListVO.getRm_check_in());
					ps.setInt(8, rmOrderListVO.getRm_order_list_no());
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}
	
	@Override
	public void changeROL(RmOrderListVO rmOrderListVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE)) {
		
			ps.setInt(1, rmOrderListVO.getRm_type_no());
			ps.setInt(2, rmOrderListVO.getRm_amount());
			ps.setInt(3, rmOrderListVO.getRm_price());
			ps.setDate(4, rmOrderListVO.getArrival_date());
			ps.setDate(5, rmOrderListVO.getDeparture_date());
			ps.setString(6, rmOrderListVO.getRm_check_in());
			ps.setInt(7, rmOrderListVO.getRm_order_list_no());
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public void delete(Integer rm_order_list_no) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(DELETE)) {
			
			ps.setInt(1, rm_order_list_no);
			ps.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public RmOrderListVO findByPrimaryKey(Integer rm_order_list_no) {
		ResultSet rs = null;
		RmOrderListVO rm = null;
		
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ONE)) {
			
				
			ps.setInt(1, rm_order_list_no);
			rs = ps.executeQuery();
				while(rs.next()) {
					rm = new RmOrderListVO();
					rm.setRm_type_no(rs.getInt("rm_type_no"));
					rm.setRm_order_no(rs.getInt("rm_order_no"));
					rm.setRm_amount(rs.getInt("rm_amount"));
					rm.setRm_price(rs.getInt("rm_price"));
					rm.setArrival_date(rs.getDate("arrival_date"));
					rm.setDeparture_date(rs.getDate("departure_date"));
					rm.setRm_check_in(rs.getString("rm_check_in"));
					
				}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return rm;
	}

	@Override
	public List<RmOrderListVO> getAll() {
		List<RmOrderListVO> rmAll = new ArrayList<>();
		ResultSet rs = null;
		RmOrderListVO rm = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ALL)) {

			rs = ps.executeQuery();
		
		while(rs.next()) {
			rm = new RmOrderListVO();
			rm.setRm_type_no(rs.getInt("rm_type_no"));
			rm.setRm_order_no(rs.getInt("rm_order_no"));
			rm.setRm_amount(rs.getInt("rm_amount"));
			rm.setRm_price(rs.getInt("rm_price"));
			rm.setArrival_date(rs.getDate("arrival_date"));
			rm.setDeparture_date(rs.getDate("departure_date"));
			rm.setRm_check_in(rs.getString("rm_check_in"));
			rmAll.add(rm);
		}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return rmAll;
	}



	@Override
	public List<RmOrderListVO> getAllByRmOrderNo(Integer rm_order_no) {
		
		List<RmOrderListVO> list = new ArrayList<>();
		RmOrderListVO rmOrderListVO = null;
		ResultSet rs = null;

		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ALL_BY_ORDNO)) {
			
			ps.setInt(1, rm_order_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				rmOrderListVO = new RmOrderListVO();
				rmOrderListVO.setRm_order_list_no(rs.getInt("rm_order_list_no"));
				rmOrderListVO.setRm_type_no(rs.getInt("rm_type_no"));
				rmOrderListVO.setRm_order_no(rs.getInt("rm_order_no"));
				rmOrderListVO.setRm_amount(rs.getInt("rm_amount"));
				rmOrderListVO.setRm_price(rs.getInt("rm_price"));
				if (rs.getDate("arrival_date") != null) {
					rmOrderListVO.setArrival_date(rs.getDate("arrival_date"));
				}
				if (rs.getDate("departure_date") != null) {
					rmOrderListVO.setDeparture_date(rs.getDate("departure_date"));
				}
				rmOrderListVO.setRm_check_in(rs.getString("rm_check_in"));
				list.add(rmOrderListVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}
	
	@Override
	public List<RmOrderListVO> getAllByRmTypeNo(Integer rm_type_no) {
		
		List<RmOrderListVO> list = new ArrayList<>();
		RmOrderListVO rmOrderListVO = null;
		ResultSet rs = null;
		
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ALL_BY_RMTYPE)) {
			
			ps.setInt(1, rm_type_no);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				rmOrderListVO = new RmOrderListVO();
				rmOrderListVO.setRm_order_list_no(rs.getInt("rm_order_list_no"));
				rmOrderListVO.setRm_type_no(rs.getInt("rm_type_no"));
				rmOrderListVO.setRm_order_no(rs.getInt("rm_order_no"));
				rmOrderListVO.setRm_amount(rs.getInt("rm_amount"));
				rmOrderListVO.setRm_price(rs.getInt("rm_price"));
				if (rs.getDate("arrival_date") != null) {
					rmOrderListVO.setArrival_date(rs.getDate("arrival_date"));
				}
				if (rs.getDate("departure_date") != null) {
					rmOrderListVO.setDeparture_date(rs.getDate("departure_date"));
				}
				rmOrderListVO.setRm_check_in(rs.getString("rm_check_in"));
				list.add(rmOrderListVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}

	@Override
	public void insert2(RmOrderListVO rmOrderListVO, Connection con) {
		
		try {
			PreparedStatement ps = con.prepareStatement(INSERT);
			ps.setInt(1, rmOrderListVO.getRm_type_no());
			ps.setInt(2, rmOrderListVO.getRm_order_no());
			ps.setInt(3, rmOrderListVO.getRm_amount());
			ps.setInt(4, rmOrderListVO.getRm_price());
			ps.setDate(5, rmOrderListVO.getArrival_date());
			ps.setDate(6, rmOrderListVO.getDeparture_date());
			ps.setString(7, rmOrderListVO.getRm_check_in());
			
			Statement stmt=	con.createStatement();
			stmt.executeUpdate("set auto_increment_increment=1;");   //自增主鍵-遞增
			
			ps.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
	}

}
