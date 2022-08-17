package com.tkt.model;

import static com.util.JdbcUtil.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TktJdbcDAO implements I_TktDAO {

	private static final String INSERT = "INSERT INTO tkt (tkt_name, original_amount, price, tkt_startdate, tkt_enddate, `locate`,  instruction, address, notice, howuse, canxpolicy, tkt_status, sold_amount, kind) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE tkt SET price = ? WHERE tkt_name = ?;";
	private static final String GET_ONE = "SELECT tkt_no ,tkt_name, original_amount, price, tkt_startdate, tkt_enddate, `locate`, "
			+ " instruction, address, notice, howuse, canxpolicy, tkt_status, sold_amount, kind FROM tkt WHERE tkt_no = ?;";
	private static final String GET_ALL = "SELECT tkt_no ,tkt_name, original_amount, price, tkt_startdate, tkt_enddate, `locate`, "
			+ " instruction, address, notice, howuse, canxpolicy, tkt_status, sold_amount, kind FROM tkt ORDER BY tkt_no;";

	@Override
	public void insert(TktVO tktVO) {

		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT)) {

			ps.setString(1, tktVO.getTkt_name());
			ps.setInt(2, tktVO.getOriginal_amount());
			ps.setInt(3, tktVO.getPrice());
			ps.setObject(4, tktVO.getTkt_startdate());
			ps.setObject(5, tktVO.getTkt_enddate());
			ps.setString(6, tktVO.getLocate());
			ps.setString(7, tktVO.getInstruction());
			ps.setString(8, tktVO.getAddress());
			ps.setString(9, tktVO.getNotice());
			ps.setString(10, tktVO.getHowuse());
			ps.setString(11, tktVO.getCanxpolicy());
			ps.setInt(12, tktVO.getTkt_status());
			ps.setInt(13, tktVO.getSold_amount());
			ps.setInt(14, tktVO.getKind());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(TktVO tktVO) {

		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(UPDATE)) {

			ps.setInt(1, tktVO.getPrice());
			ps.setString(2, tktVO.getTkt_name());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public TktVO findByPrimaryKey(Integer tkt_no) {

		TktVO tktVO = null;
		ResultSet rs = null;

		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ONE)) {

			ps.setInt(1, tkt_no);
			rs = ps.executeQuery();

			while (rs.next()) {
				tktVO = new TktVO();

				tktVO.setTkt_no(rs.getInt("tkt_no"));
				tktVO.setTkt_name(rs.getString("tkt_name"));
				tktVO.setOriginal_amount(rs.getInt("original_amount"));
				tktVO.setPrice(rs.getInt("price"));
				tktVO.setTkt_startdate(rs.getDate("tkt_startdate"));
				tktVO.setTkt_enddate(rs.getDate("tkt_enddate"));
				tktVO.setLocate(rs.getString("locate"));
				tktVO.setInstruction(rs.getString("instruction"));
				tktVO.setAddress(rs.getString("address"));
				tktVO.setNotice(rs.getString("notice"));
				tktVO.setHowuse(rs.getString("howuse"));
				tktVO.setCanxpolicy(rs.getString("canxpolicy"));
				tktVO.setTkt_status(rs.getInt("tkt_status"));
				tktVO.setSold_amount(rs.getInt("sold_amount"));
				tktVO.setKind(rs.getInt("kind"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tktVO;
	}

	@Override
	public List<TktVO> getAll() {

		List<TktVO> list = new ArrayList<TktVO>();
		TktVO tktVO = null;
		ResultSet rs = null;

		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ALL)) {
			
			rs = ps.executeQuery();

			while (rs.next()) {
				tktVO = new TktVO();

				tktVO.setTkt_no(rs.getInt("tkt_no"));
				tktVO.setTkt_name(rs.getString("tkt_name"));
				tktVO.setOriginal_amount(rs.getInt("original_amount"));
				tktVO.setPrice(rs.getInt("price"));
				tktVO.setTkt_startdate(rs.getDate("tkt_startdate"));
				tktVO.setTkt_enddate(rs.getDate("tkt_enddate"));
				tktVO.setLocate(rs.getString("locate"));
				tktVO.setInstruction(rs.getString("instruction"));
				tktVO.setAddress(rs.getString("address"));
				tktVO.setNotice(rs.getString("notice"));
				tktVO.setHowuse(rs.getString("howuse"));
				tktVO.setCanxpolicy(rs.getString("canxpolicy"));
				tktVO.setTkt_status(rs.getInt("tkt_status"));
				tktVO.setSold_amount(rs.getInt("sold_amount"));
				tktVO.setKind(rs.getInt("kind"));
				
				list.add(tktVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void main(String[] args) {
		TktJdbcDAO dao = new TktJdbcDAO();

		// insert
		TktVO voInsert = new TktVO();
		
		voInsert.setTkt_name("義大遊樂世界");
		voInsert.setOriginal_amount(999);
		voInsert.setPrice(666);
		voInsert.setTkt_startdate(java.sql.Date.valueOf("2022-08-01"));
		voInsert.setTkt_enddate(java.sql.Date.valueOf("2022-12-31"));
		voInsert.setLocate("高雄");
		voInsert.setInstruction("義大遊樂世界一日遊門票");
		voInsert.setAddress("高雄市大樹區學城路一段10號");
		voInsert.setNotice("請在指定日期前使用");
		voInsert.setHowuse("抵達義大遊樂世界門口出示 QR Code");
		voInsert.setCanxpolicy("請洽客服系統");
		voInsert.setTkt_status(1);
		voInsert.setSold_amount(0);
		voInsert.setKind(1);
		
		dao.insert(voInsert);

		// update
		TktVO voUpdate = new TktVO();
		
		voUpdate.setPrice(777);
		voUpdate.setTkt_name("義大遊樂世界");
		
		dao.update(voUpdate);

		// findByPrimaryKey
		TktVO voPk = dao.findByPrimaryKey(3);
		
		System.out.println(voPk.getTkt_no());
		System.out.println(voPk.getTkt_name());
		System.out.println(voPk.getOriginal_amount());
		System.out.println(voPk.getPrice());
		System.out.println(voPk.getTkt_startdate());
		System.out.println(voPk.getTkt_enddate());
		System.out.println(voPk.getLocate());
		System.out.println(voPk.getInstruction());
		System.out.println(voPk.getAddress());
		System.out.println(voPk.getNotice());
		System.out.println(voPk.getHowuse());
		System.out.println(voPk.getCanxpolicy());
		System.out.println(voPk.getTkt_status());
		System.out.println(voPk.getSold_amount());
		System.out.println(voPk.getKind());

		// getAll
		List<TktVO> list = dao.getAll();
		
		for (TktVO voAll : list) {
			System.out.println(voAll.getTkt_no());
			System.out.println(voAll.getTkt_name());
			System.out.println(voAll.getOriginal_amount());
			System.out.println(voAll.getPrice());
			System.out.println(voAll.getTkt_startdate());
			System.out.println(voAll.getTkt_enddate());
			System.out.println(voAll.getLocate());
			System.out.println(voAll.getInstruction());
			System.out.println(voAll.getAddress());
			System.out.println(voAll.getNotice());
			System.out.println(voAll.getHowuse());
			System.out.println(voAll.getCanxpolicy());
			System.out.println(voAll.getTkt_status());
			System.out.println(voAll.getSold_amount());
			System.out.println(voAll.getKind());
		}
		
	}

}
