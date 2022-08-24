package com.rm_type.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.util.JdbcUtil;

public class RmTypeDAO implements I_RmTypeDAO {
	private static final String INSERT = "INSERT INTO rm_type(store_no, rm_name, rm_total, rm_people, rm_price"
			+ ", rm_area, rm_intro, rm_update)VALUES(?,?,?,?,?,?,?,0)";
	
	private static final String UPDATE = "UPDATE rm_type SET rm_name=?, rm_total=?, rm_people=?, rm_price=?"
			+ ", rm_area=?, rm_intro=?, rm_rate_sum=?, rm_eval_sum=?, rm_update=? WHERE rm_type_no=?";
	
	private static final String CHANGE_STATE = "UPDATE rm_type SET rm_update=? WHERE rm_type_no=?";
	private static final String GET_ONE = "SELECT * FROM rm_type WHERE rm_type_no=?";
	private static final String GET_ALL = "SELECT * FROM rm_type";
	private static final String GET_ALL_BY_STORE = "SELECT * FROM rm_type WHERE store_no=?";
	private static final String GET_ALL_RSV = "SELECT * FROM rm_type WHERE rm_update=1";
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
	public RmTypeVO insert(RmTypeVO rmtypeVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);
			
			ps.setInt(1, rmtypeVO.getStore_no());
			ps.setString(2, rmtypeVO.getRm_name());
			ps.setInt(3, rmtypeVO.getRm_total());
			ps.setInt(4, rmtypeVO.getRm_people());
			ps.setInt(5, rmtypeVO.getRm_price());
			ps.setInt(6, rmtypeVO.getRm_area());
			ps.setString(7, rmtypeVO.getRm_intro());
			ps.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rmtypeVO;
	}

	@Override
	public RmTypeVO update(RmTypeVO rmtypeVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);

			ps.setString(1, rmtypeVO.getRm_name());
			ps.setInt(2, rmtypeVO.getRm_total());
			ps.setInt(3, rmtypeVO.getRm_people());
			ps.setInt(4, rmtypeVO.getRm_price());
			ps.setInt(5, rmtypeVO.getRm_area());
			ps.setString(6, rmtypeVO.getRm_intro());
			ps.setInt(7, rmtypeVO.getRm_rate_sum());
			ps.setInt(8, rmtypeVO.getRm_eval_sum());
			ps.setBoolean(9, rmtypeVO.getRm_update());
			ps.setInt(10, rmtypeVO.getRm_type_no());
			ps.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rmtypeVO;
	}

	@Override
	public void changeState(Integer rm_type_no, Boolean rm_update) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(CHANGE_STATE);

			ps.setBoolean(1, rm_update);
			ps.setInt(2, rm_type_no);
			ps.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public RmTypeVO getOne(Integer rm_type_no) {
		ResultSet rs = null;
		RmTypeVO rm = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE);

			ps.setInt(1, rm_type_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				rm = new RmTypeVO();
				rm.setStore_no(rs.getInt("store_no"));
				rm.setRm_name(rs.getString("rm_name"));
				rm.setRm_total(rs.getInt("rm_total"));
				rm.setRm_people(rs.getInt("rm_people"));
				rm.setRm_price(rs.getInt("rm_price"));
				rm.setRm_area(rs.getInt("rm_area"));
				rm.setRm_intro(rs.getString("rm_intro"));
				rm.setRm_rate_sum(rs.getInt("rm_rate_sum"));
				rm.setRm_eval_sum(rs.getInt("rm_eval_sum"));
				rm.setRm_update(rs.getBoolean("rm_update"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rm;
	}

	@Override
	public List<RmTypeVO> getAll() {
		List<RmTypeVO> rmAll = new ArrayList<>();
		ResultSet rs = null;
		RmTypeVO rm = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);

			rs = ps.executeQuery();
			while (rs.next()) {
				rm = new RmTypeVO();
				rm.setRm_type_no(rs.getInt("rm_type_no"));
				rm.setStore_no(rs.getInt("store_no"));
				rm.setRm_name(rs.getString("rm_name"));
				rm.setRm_total(rs.getInt("rm_total"));
				rm.setRm_people(rs.getInt("rm_people"));
				rm.setRm_price(rs.getInt("rm_price"));
				rm.setRm_area(rs.getInt("rm_area"));
				rm.setRm_intro(rs.getString("rm_intro"));
				rm.setRm_rate_sum(rs.getInt("rm_rate_sum"));
				rm.setRm_eval_sum(rs.getInt("rm_eval_sum"));
				rm.setRm_update(rs.getBoolean("rm_update"));

				rmAll.add(rm);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rmAll;
	}

	@Override
	public List<RmTypeVO> getAllRsv() {
		List<RmTypeVO> rmAllRsv = new ArrayList<>();
		ResultSet rs = null;
		RmTypeVO rm = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL_RSV);

			rs = ps.executeQuery();
			while (rs.next()) {
				rm = new RmTypeVO();
				rm.setRm_type_no(rs.getInt("rm_type_no"));
				rm.setStore_no(rs.getInt("store_no"));
				rm.setRm_name(rs.getString("rm_name"));
				rm.setRm_total(rs.getInt("rm_total"));
				rm.setRm_people(rs.getInt("rm_people"));
				rm.setRm_price(rs.getInt("rm_price"));
				rm.setRm_area(rs.getInt("rm_area"));
				rm.setRm_intro(rs.getString("rm_intro"));
				rm.setRm_rate_sum(rs.getInt("rm_rate_sum"));
				rm.setRm_eval_sum(rs.getInt("rm_eval_sum"));
				rm.setRm_update(rs.getBoolean("rm_update"));

				rmAllRsv.add(rm);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rmAllRsv;
	}

	@Override
	public List<RmTypeVO> getAllByStoreNo(Integer store_no) {
		List<RmTypeVO> rmAll = new ArrayList<>();
		ResultSet rs = null;
		RmTypeVO rm = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL_BY_STORE);

			ps.setInt(1, store_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				rm = new RmTypeVO();
				rm.setRm_type_no(rs.getInt("rm_type_no"));
				rm.setStore_no(rs.getInt("store_no"));
				rm.setRm_name(rs.getString("rm_name"));
				rm.setRm_total(rs.getInt("rm_total"));
				rm.setRm_people(rs.getInt("rm_people"));
				rm.setRm_price(rs.getInt("rm_price"));
				rm.setRm_area(rs.getInt("rm_area"));
				rm.setRm_intro(rs.getString("rm_intro"));
				rm.setRm_rate_sum(rs.getInt("rm_rate_sum"));
				rm.setRm_eval_sum(rs.getInt("rm_eval_sum"));
				rm.setRm_update(rs.getBoolean("rm_update"));
				rmAll.add(rm);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rmAll;
	}
}
