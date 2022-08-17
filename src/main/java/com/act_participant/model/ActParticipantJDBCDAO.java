package com.act_participant.model;

import static com.util.JdbcUtil.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.act.model.ActVO;

public class ActParticipantJDBCDAO implements I_ActParticipantDAO {
	
	private static final String INSERT = "insert into act_participant (act_no, mem_no, enroll_time) "
			+ "values(?, ?, ?)";
	
	private static final String UPDATE = "update act_participant set enroll_status = ? where act_no = ? and mem_no = ?";
	
	private static final String GET_ALL = "select act_no, mem_no, enroll_time, enroll_status from act_participant";
	
	private static final String GET_ONE_OF_ACT_PARTICIPANT = "select act_no, mem_no, enroll_time, enroll_status from act_participant where act_no = ?";
	
	private static final String GET_APES = "select act_no, mem_no, enroll_time, enroll_status from act_participant where mem_no = ?";
	
	@Override
	public void insert(ActParticipantVO actParticipantVO) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT)) {
			ps.setInt(1, actParticipantVO.getAct_no());
			ps.setInt(2, actParticipantVO.getMem_no());
			ps.setObject(3, actParticipantVO.getEnroll_time());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}				
	}
	@Override
	public void update(ActParticipantVO actParticipantVO) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(UPDATE)) {
			ps.setInt(1, actParticipantVO.getEnroll_status());
			ps.setInt(2, actParticipantVO.getAct_no());
			ps.setInt(3, actParticipantVO.getMem_no());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}				
	}
	@Override
	public List<ActParticipantVO> getAll() {
		List<ActParticipantVO> actPartiList = new ArrayList<ActParticipantVO>();
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ALL)) {
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer memNo = rs1.getInt(2);
				LocalDateTime enrollTime = (LocalDateTime) rs1.getObject(3);
				Integer enrollStatus = rs1.getInt(4);
				ActParticipantVO actParticipant = 
						new ActParticipantVO(actNo,
								memNo, enrollTime, enrollStatus);
				actPartiList.add(actParticipant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return actPartiList;
	}
	@Override
	public List<ActParticipantVO> getOneOfAll(Integer actNo) {
		List<ActParticipantVO> actPartiList = new ArrayList<ActParticipantVO>();
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ONE_OF_ACT_PARTICIPANT)) {
			ps.setInt(1, actNo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer act_No = rs1.getInt(1);
				Integer memNo = rs1.getInt(2);
				LocalDateTime enrollTime = (LocalDateTime) rs1.getObject(3);
				Integer enrollStatus = rs1.getInt(4);
				ActParticipantVO actParticipant = 
						new ActParticipantVO(act_No,
								memNo, enrollTime, enrollStatus);
				actPartiList.add(actParticipant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		return actPartiList;
	}
	@Override
	public List<ActParticipantVO> getAPES(Integer memNo) {
		List<ActParticipantVO> actPartiList = new ArrayList<ActParticipantVO>();
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_APES)) {
			ps.setInt(1, memNo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer mem_No = rs1.getInt(2);
				LocalDateTime enrollTime = (LocalDateTime) rs1.getObject(3);
				Integer enrollStatus = rs1.getInt(4);
				ActParticipantVO actParticipant = 
						new ActParticipantVO(actNo,
								mem_No, enrollTime, enrollStatus);
				actPartiList.add(actParticipant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		return actPartiList;
	}
	
	public static void main(String[] args) {
		ActParticipantJDBCDAO actParticipantJDBCDAO = new ActParticipantJDBCDAO();
		
		// insert
		ActParticipantVO actParticipantVO = new ActParticipantVO();
		actParticipantVO.setAct_no(2);
		actParticipantVO.setMem_no(1);
		actParticipantVO.setEnroll_time(LocalDateTime.of(2022,8,18,14,00));
		actParticipantJDBCDAO.insert(actParticipantVO);
		
		// update
//		actParticipantVO.setEnroll_status(1);
//		actParticipantVO.setAct_no(3);
//		actParticipantVO.setMem_no(2);
//		actParticipantJDBCDAO.update(actParticipantVO);

//		getAll()
//		List<ActParticipantVO> actPartilist = actParticipantJDBCDAO.getAll();
//		System.out.println(actPartilist);
//		actPartilist.forEach(actParti -> System.out.println(actParti));
		
//		getAll(Integer, Integer)
//		List<ActParticipantVO> actPartilist = actParticipantJDBCDAO.getOneOfAll(3);
//		actPartilist.forEach(actParti -> System.out.println(actParti));	
		
//		getAPES()
//		List<ActParticipantVO> actPartilist = actParticipantJDBCDAO.getAPES(2);
//		actPartilist.forEach(actParti -> System.out.println(actParti));
		
	}
}
