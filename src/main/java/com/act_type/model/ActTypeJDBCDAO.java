package com.act_type.model;

import static com.util.JdbcUtil.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.act_participant.model.ActParticipantVO;

public class ActTypeJDBCDAO implements I_ActTypeDAO {

	private static final String INSERT = "insert into act_type(act_type_name) values(?)";
		
	private static final String GET_ALL = "select act_type_no, act_type_name from act_type";
	
	@Override
	public void insert(ActTypeVO actTypeVO) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT)) {
			ps.setString(1, actTypeVO.getAct_type_name());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public List<ActTypeVO> getAll() {
		List<ActTypeVO> actTypelist = new ArrayList<ActTypeVO>();
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ALL)) {
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actTypeNo = rs1.getInt(1);
				String actTypeName = rs1.getString(2);
				ActTypeVO actTypesName = 
						new ActTypeVO(actTypeNo, actTypeName);
				actTypelist.add(actTypesName);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actTypelist;
	}


	
	public static void main(String[] args) {
		
		ActTypeJDBCDAO actTypeJDBCDAO = new ActTypeJDBCDAO();
		
		// insert
		ActTypeVO actTypeVO = new ActTypeVO();
//		actTypeVO.setAct_type_name("社會服務活動");
//		actTypeJDBCDAO.insert(actTypeVO);
		
		// getAll
//		List<ActTypeVO> list = actTypeJDBCDAO.getAll();
//		list.forEach(System.out::println);
		
		
	}
}
