package com.mem_coup.model;

import static com.util.JdbcUtil.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemCoupJdbcDAO implements I_MemCoupDAO {

	private static final String INSERT = "INSERT INTO mem_coup (mem_no, coup_no, coup_state) VALUES (?, ?, ?);";
//	private static final String UPDATE = "UPDATE mem_coup SET coup_no = ? WHERE mem_no = ?;";
	private static final String GET_ONE = "SELECT mem_coup_no ,mem_no, coup_no, coup_state FROM mem_coup WHERE mem_coup_no = ?;";
	private static final String GET_ALL = "SELECT mem_coup_no ,mem_no, coup_no, coup_state FROM mem_coup ORDER BY mem_coup_no;";
	
	@Override
	public void insert(MemCoupVO memCoupVO) {

		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT)) {

			ps.setInt(1, memCoupVO.getMem_no());
			ps.setInt(2, memCoupVO.getCoup_no());
			ps.setInt(3, memCoupVO.getCoup_state());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//	@Override
//	public void update(MemCoupVO memCoupVO) {
//
//		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//				PreparedStatement ps = conn.prepareStatement(UPDATE)) {
//
//			ps.setInt(1, memCoupVO.getCoup_no());
//			ps.setInt(2, memCoupVO.getMem_no());
//
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

	@Override
	public MemCoupVO findByPrimaryKey(Integer mem_coup_no) {

		MemCoupVO memCoupVO = null;
		ResultSet rs = null;

		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ONE)) {

			ps.setInt(1, mem_coup_no);

			rs = ps.executeQuery();

			while (rs.next()) {
				memCoupVO = new MemCoupVO();

				memCoupVO.setMem_coup_no(rs.getInt("mem_coup_no"));
				memCoupVO.setMem_no(rs.getInt("mem_no"));
				memCoupVO.setCoup_no(rs.getInt("coup_no"));
				memCoupVO.setTkt_order_no(rs.getInt("tkt_order_no"));
				memCoupVO.setCoup_state(rs.getInt("coup_state"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return memCoupVO;
	}

	@Override
	public List<MemCoupVO> getAll() {

		List<MemCoupVO> list = new ArrayList<MemCoupVO>();
		MemCoupVO memCoupVO = null;
		ResultSet rs = null;

		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ALL)) {

			rs = ps.executeQuery();

			while (rs.next()) {
				memCoupVO = new MemCoupVO();

				memCoupVO.setMem_coup_no(rs.getInt("mem_coup_no"));
				memCoupVO.setMem_no(rs.getInt("mem_no"));
				memCoupVO.setCoup_no(rs.getInt("coup_no"));
				memCoupVO.setTkt_order_no(rs.getInt("tkt_order_no"));
				memCoupVO.setCoup_state(rs.getInt("coup_state"));
				
				list.add(memCoupVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void main(String[] args) {
		MemCoupJdbcDAO dao = new MemCoupJdbcDAO();

		// insert
//		MemCoupVO voInsert = new MemCoupVO();
//		
//		voInsert.setMem_no(1);
//		voInsert.setCoup_no(2);
//		voInsert.setCoup_state(0);
//		
//		dao.insert(voInsert);

		// update
//		MemCoupVO voUpdate = new MemCoupVO();
//		
//		voUpdate.setCoup_no(6);
//		voUpdate.setMem_no(1);
//		
//		dao.update(voUpdate);

		// findByPrimaryKey
		MemCoupVO voPk = dao.findByPrimaryKey(1);
		
		System.out.println(voPk.getMem_coup_no());
		System.out.println(voPk.getMem_no());
		System.out.println(voPk.getCoup_no());
		System.out.println(voPk.getTkt_order_no());
		System.out.println(voPk.getCoup_state());

		// getAll
		List<MemCoupVO> list = dao.getAll();
		
		for (MemCoupVO voAll : list) {
			System.out.println(voAll.getMem_coup_no());
			System.out.println(voAll.getMem_no());
			System.out.println(voAll.getCoup_no());
			System.out.println(voAll.getTkt_order_no());
			System.out.println(voAll.getCoup_state());
		}
		
	}

}
