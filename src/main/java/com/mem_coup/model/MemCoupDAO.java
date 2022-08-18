package com.mem_coup.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemCoupDAO implements I_MemCoupDAO{
	private static final String INSERT = "INSERT INTO mem_coup (mem_no, coup_no, coup_state) VALUES (?, ?, ?);";
	private static final String UPDATE = "UPDATE mem_coup SET coup_no = ? WHERE mem_no = ?;";
	private static final String GET_ONE = "SELECT mem_coup_no ,mem_no, coup_no, coup_state FROM mem_coup WHERE mem_coup_no = ?;";
	private static final String GET_ALL = "SELECT mem_coup_no ,mem_no, coup_no, coup_state FROM mem_coup ORDER BY mem_coup_no;";

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
	public void insert(MemCoupVO memCoupVO) {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

			ps.setInt(1, memCoupVO.getMem_no());
			ps.setInt(2, memCoupVO.getCoup_no());
			ps.setInt(3, memCoupVO.getCoup_state());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(MemCoupVO memCoupVO) {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);

			ps.setInt(1, memCoupVO.getCoup_no());
			ps.setInt(2, memCoupVO.getMem_no());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public MemCoupVO findByPrimaryKey(Integer mem_coup_no) {

		MemCoupVO memCoupVO = null;
		ResultSet rs = null;
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE);

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
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);

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
}

