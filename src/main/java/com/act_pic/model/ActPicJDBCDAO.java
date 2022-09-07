package com.act_pic.model;

import static com.util.JdbcUtil.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.act.model.ActVO;
import com.act_participant.model.ActParticipantVO;

public class ActPicJDBCDAO implements I_ActPicDAO {

	private static final String INSERT = "INSERT INTO act_pic(act_no, act_pic_file) VALUES (?, ?)";
	
	private static final String UPDATE = "update act_pic set act_pic_file = ? where act_pic_no = ? and act_no = ?";
	
	private static final String GET_ONE_OF_ACT_PIC = "select act_pic_no, act_no, act_pic_file from act_pic where act_pic_no = ? and act_no = ?";
	
	private static final String GET_ALL = "select act_pic_no, act_no, act_pic_file from act_pic";
	
	@Override
	public void insert(ActPicVO actPicVO) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT);
				FileInputStream fis = new FileInputStream("src/main/webapp/backend/act/桌遊.jpg");
				BufferedInputStream bis = new BufferedInputStream(fis)) {
				ps.setInt(1, actPicVO.getAct_no());
				byte[] actPic = bis.readAllBytes();
				ps.setBytes(2, actPic);
				ps.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
	
	@Override
	public void update(ActPicVO actPicVO) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(UPDATE);
				FileInputStream fis = new FileInputStream("src/main/webapp/backend/act/Bar.jpg");
				BufferedInputStream bis = new BufferedInputStream(fis)) {
			byte[] actPic = bis.readAllBytes();
			ps.setBytes(1, actPic);
			ps.setInt(2, actPicVO.getAct_pic_no());
			ps.setInt(3, actPicVO.getAct_no());
			ps.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public List<ActPicVO> findActPic(Integer actNo) {
		List<ActPicVO> list = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ONE_OF_ACT_PIC);
				FileOutputStream fos = new FileOutputStream("src/main/webapp/backend/act/LoadAndSaveImg.jpg");
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {
//			ps.setInt(1, actPicNo);
			ps.setInt(1, actNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer actPic_No = rs.getInt(1);
				Integer act_No = rs.getInt(2);
				byte[] actPicFile = rs.getBytes(3);
				bos.write(actPicFile);
				ActPicVO actPicVO = 
						new ActPicVO(actPic_No,
								act_No, actPicFile);
				list.add(actPicVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return list;
	}
	
	@Override
	public List<ActPicVO> getAll() {
		List<ActPicVO> list = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ALL);
				FileOutputStream fos = new FileOutputStream("src/main/webapp/backend/act/LoadAndSaveImg2.jpg");
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer actPic_No = rs.getInt(1);
				Integer act_No = rs.getInt(2);
				byte[] actPicFile = rs.getBytes(3);
				bos.write(actPicFile);
				ActPicVO actPicVO = 
						new ActPicVO(actPic_No,
								act_No, actPicFile);
				list.add(actPicVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return list;
	}
	
	public static void main(String[] args) {
//		System.out.println(System.getProperty("user.dir"));
		
		ActPicJDBCDAO actPicJDBCDAO = new ActPicJDBCDAO();
		
		// insert
		ActPicVO actPicVO = new ActPicVO();
//		actPicVO.setAct_no(2);
//		actPicJDBCDAO.insert(actPicVO);
		
		// update
//		actPicVO.setAct_pic_no(3);
//		actPicVO.setAct_no(3);
//		actPicJDBCDAO.update(actPicVO);
		
		// getOne
//		List<ActPicVO> actPicList = actPicJDBCDAO.findActPic(3);
//		actPicList.forEach(System.out::println);

		// getAll
//		List<ActPicVO> actPicList = actPicJDBCDAO.getAll();
//		actPicList.forEach(System.out::println);
	
	}

}
