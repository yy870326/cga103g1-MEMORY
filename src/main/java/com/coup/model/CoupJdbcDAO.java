package com.coup.model;

import static com.util.JdbcUtil.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoupJdbcDAO implements I_CoupDAO {

	private static final String INSERT = "INSERT INTO coup (coup_name, introduce, discount, startdate, enddate, `status`) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE coup SET coup_name = ?, introduce = ?, discount = ?, startdate = ?, enddate = ?, `status` = ? WHERE coup_no = ?;";
	private static final String UPDATE_STATUS = "UPDATE coup SET status = ? WHERE enddate = ?;";
	private static final String GET_ONE = "SELECT coup_no, coup_name, introduce, discount, startdate, enddate, `status` FROM coup WHERE coup_no = ?;";
	private static final String GET_ALL = "SELECT coup_no, coup_name, introduce, discount, startdate, enddate, `status` FROM coup ORDER BY coup_no;";

	@Override
	public void insert(CoupVO coupVO) {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT)) {

			ps.setString(1, coupVO.getCoup_name());
			ps.setString(2, coupVO.getIntroduce());
			ps.setInt(3, coupVO.getDiscount());
			ps.setObject(4, coupVO.getStartdate());
			ps.setObject(5, coupVO.getEnddate());
			ps.setInt(6, coupVO.getStatus());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(CoupVO coupVO) {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(UPDATE)) {
			
			ps.setString(1, coupVO.getCoup_name());
			ps.setString(2, coupVO.getIntroduce());
			ps.setInt(3, coupVO.getDiscount());
			ps.setObject(4, coupVO.getStartdate());
			ps.setObject(5, coupVO.getEnddate());
			ps.setInt(6, coupVO.getStatus());
			ps.setInt(7, coupVO.getCoup_no());
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateStatus(CoupVO coupVO) {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(UPDATE_STATUS)) {

			ps.setInt(1, coupVO.getStatus());
			ps.setDate(2, coupVO.getEnddate());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public CoupVO findByPrimaryKey(Integer coup_no) {
		CoupVO coupVO = null;
		ResultSet rs = null;

		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ONE)) {

			ps.setInt(1, coup_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				coupVO = new CoupVO();

				coupVO.setCoup_no(rs.getInt("coup_no"));
				coupVO.setCoup_name(rs.getString("coup_name"));
				coupVO.setIntroduce(rs.getString("introduce"));
				coupVO.setDiscount(rs.getInt("discount"));
				coupVO.setStartdate(rs.getDate("startdate"));
				coupVO.setEnddate(rs.getDate("enddate"));
				coupVO.setStatus(rs.getInt("status"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return coupVO;
	}

	@Override
	public List<CoupVO> getAll() {
		List<CoupVO> list = new ArrayList<CoupVO>();
		CoupVO coupVO = null;
		ResultSet rs = null;

		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ALL)) {

			rs = ps.executeQuery();

			while (rs.next()) {
				coupVO = new CoupVO();

				coupVO.setCoup_no(rs.getInt("coup_no"));
				coupVO.setCoup_name(rs.getString("coup_name"));
				coupVO.setIntroduce(rs.getString("introduce"));
				coupVO.setDiscount(rs.getInt("discount"));
				coupVO.setStartdate(rs.getDate("startdate"));
				coupVO.setEnddate(rs.getDate("enddate"));
				coupVO.setStatus(rs.getInt("status"));

				list.add(coupVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	};

	public static void main(String[] args) {
//		CoupJdbcDAO dao = new CoupJdbcDAO();

		// insert
//		CoupVO voInsert = new CoupVO();
//		voInsert.setCoup_name("歡慶週年優惠券");
//		voInsert.setIntroduce("歡慶週年旅遊折扣 168");
//		voInsert.setDiscount(168);
//		voInsert.setStartdate(java.sql.Date.valueOf("2022-09-01"));
//		voInsert.setEnddate(java.sql.Date.valueOf("2022-09-15"));
//		voInsert.setStatus(0);
//
//		dao.insert(voInsert);

		// update
//		CoupVO voUpdate = new CoupVO();
//		
//		voUpdate.setCoup_name("88節優惠");
//		voUpdate.setIntroduce("88節檔期限時折扣288");
//		voUpdate.setDiscount(288);
//		voUpdate.setStartdate(java.sql.Date.valueOf("2022-08-03"));
//		voUpdate.setEnddate(java.sql.Date.valueOf("2022-08-20"));
//		voUpdate.setStatus(1);
//		voUpdate.setCoup_no(1);
//
//		dao.update(voUpdate);

		// update status
//		CoupVO voUpdateStatus = new CoupVO();
//
//		voUpdateStatus.setStatus(1);
//		voUpdateStatus.setEnddate(java.sql.Date.valueOf("2022-08-10"));
//
//		dao.updateStatus(voUpdateStatus);

		// findByPrimaryKey
//		CoupVO voPk = dao.findByPrimaryKey(5);
//		System.out.println(voPk.getCoup_no());
//		System.out.println(voPk.getCoup_name());
//		System.out.println(voPk.getIntroduce());
//		System.out.println(voPk.getDiscount());
//		System.out.println(voPk.getStartdate());
//		System.out.println(voPk.getEnddate());
//		System.out.println(voPk.getStatus());

		// getAll
//		List<CoupVO> list = dao.getAll();
//
//		for (CoupVO voAll : list) {
//			System.out.println(voAll.getCoup_no());
//			System.out.println(voAll.getCoup_name());
//			System.out.println(voAll.getIntroduce());
//			System.out.println(voAll.getDiscount());
//			System.out.println(voAll.getStartdate());
//			System.out.println(voAll.getEnddate());
//			System.out.println(voAll.getStatus());
//		}
		
	}
}
