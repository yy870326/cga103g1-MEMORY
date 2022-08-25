package com.rm_reserve.model;

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

public class RmReserveDAO implements I_RmReserveDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO RM_RESERVE(rm_type_no,rm_type_amount,rm_schedule_date,reservation_amount)VALUES(?,?,?,?)";
	private static final String UPDATE = "UPDATE RM_RESERVE SET rm_type_no=?,rm_type_amount=?, rm_schedule_date=?,reservation_amount=? WHERE serial_no=?";
	private static final String CANCEL = "SELECT * FROM RM_RESERVE WHERE serial_no=?";
	private static final String GET_ONE = "SELECT * FROM RM_RESERVE WHERE serial_no=?";
	private static final String GET_ALL = "SELECT * FROM RM_RESERVE";

	@Override
	public void insert(RmReserveVO rmreserveVO) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

			ps.setInt(1, rmreserveVO.getRm_type_no());
			ps.setInt(2, rmreserveVO.getRm_type_amount());
			ps.setDate(3, rmreserveVO.getRm_schedule_date());
			ps.setInt(4, rmreserveVO.getReservation_amount());
			ps.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public void update(RmReserveVO rmreserveVO) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);

			ps.setInt(1, rmreserveVO.getRm_type_no());
			ps.setInt(2, rmreserveVO.getRm_type_amount());
			ps.setDate(3, rmreserveVO.getRm_schedule_date());
			ps.setInt(4, rmreserveVO.getReservation_amount());
			ps.setInt(5, rmreserveVO.getSerial_no());
			ps.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public void chanel(RmReserveVO rmreserveVO) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(CANCEL);

			ps.setInt(1, rmreserveVO.getSerial_no());
			ps.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public RmReserveVO findByPrimaryKey(Integer serial_no) {

		RmReserveVO rmReserveVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE);

			ps.setInt(1, serial_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				rmReserveVO = new RmReserveVO();
				rmReserveVO.setRm_type_no(rs.getInt("rm_type_no"));
				rmReserveVO.setRm_type_amount(rs.getInt("rm_type_no"));
				rmReserveVO.setRm_schedule_date(rs.getDate("rm_schedule_date"));
				rmReserveVO.setReservation_amount(rs.getInt("reservation_amount"));
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rmReserveVO;
	}

	@Override
	public List<RmReserveVO> getAll() {
		List<RmReserveVO> list = new ArrayList<RmReserveVO>();
		RmReserveVO rmReserveVO = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);

			rs = ps.executeQuery();
			while (rs.next()) {
				rmReserveVO = new RmReserveVO();
				rmReserveVO.setSerial_no(rs.getInt("serial_NO"));
				rmReserveVO.setRm_type_no(rs.getInt("rm_type_no"));
				rmReserveVO.setRm_type_amount(rs.getInt("rm_type_amount"));
				rmReserveVO.setRm_schedule_date(rs.getDate("rm_schedule_date"));
				rmReserveVO.setReservation_amount(rs.getInt("reservation_amount"));
				list.add(rmReserveVO);
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}