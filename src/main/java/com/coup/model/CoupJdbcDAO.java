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

	private static final String INSERT_COUP = "INSERT INTO coup (coup_name, introduce, discount, startdate, enddate, `status`) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String UPDATE_COUP = "UPDATE coup SET discount = ? WHERE coup_name = ?;";
	private static final String GET_ONE = "SELECT coup_no, coup_name, introduce, discount, startdate, enddate, `status` FROM coup WHERE coup_no = ?;";
	private static final String GET_ALL = "SELECT coup_no, coup_name, introduce, discount, startdate, enddate, `status` FROM coup ORDER BY coup_no;";

	@Override
	public void insert(CoupVO coupVO) {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT_COUP)) {

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
				PreparedStatement ps = conn.prepareStatement(UPDATE_COUP)) {

			ps.setInt(1, coupVO.getDiscount());
			ps.setString(2, coupVO.getCoup_name());

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
		CoupJdbcDAO dao = new CoupJdbcDAO();

		// insert
		CoupVO voInsert = new CoupVO();
		voInsert.setCoup_name("學生暑假優惠券");
		voInsert.setIntroduce("學生暑期旅遊折扣 160");
		voInsert.setDiscount(160);
		voInsert.setStartdate(java.sql.Date.valueOf("2022-08-01"));
		voInsert.setEnddate(java.sql.Date.valueOf("2022-08-31"));
		voInsert.setStatus(0);
		
		dao.insert(voInsert);

		// update
		CoupVO voUpdate = new CoupVO();
		
		voUpdate.setDiscount(160);
		voUpdate.setCoup_name("學生暑假優惠券");
		
		dao.update(voUpdate);
		
		// findByPrimaryKey
		CoupVO voPk = dao.findByPrimaryKey(5);
		System.out.println(voPk.getCoup_no());
		System.out.println(voPk.getCoup_name());
		System.out.println(voPk.getIntroduce());
		System.out.println(voPk.getDiscount());
		System.out.println(voPk.getStartdate());
		System.out.println(voPk.getEnddate());
		System.out.println(voPk.getStatus());
		
		// getAll
		List<CoupVO> list = dao.getAll();
		
		for (CoupVO voAll : list) {
			System.out.println(voAll.getCoup_no());
			System.out.println(voAll.getCoup_name());
			System.out.println(voAll.getIntroduce());
			System.out.println(voAll.getDiscount());
			System.out.println(voAll.getStartdate());
			System.out.println(voAll.getEnddate());
			System.out.println(voAll.getStatus());
		}
	}
}
