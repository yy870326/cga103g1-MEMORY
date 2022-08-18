package com.last_news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.JdbcUtil;
public class LastNewsJdbcDAO implements I_LastNewsDAO {
	// 新增訊息
	private final String INSERT = "INSERT INTO last_news(news,news_img,news_time)  VALUES (?, ?, NOW())";
	// 下架訊息
	private final String DELETE = "DELETE FROM last_news WHERE news_no = ?";
	// 顯示訊息
	private final String GETALL = "select * from last_news order by news_no desc";

	static {
		try {
			Class.forName("mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void insert(LastNewsVO lastNewsVO) {
		
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(INSERT)){
			
			pstmt.setString(1, lastNewsVO.getNews());
			pstmt.setBytes(2, lastNewsVO.getNews_img());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} 

	}

	@Override
	public void delete(LastNewsVO lastNewsVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(DELETE)){
				
				pstmt.setInt(1,lastNewsVO.getNews_no());
				pstmt.executeUpdate();

			} catch (SQLException se) {
				se.printStackTrace();
			} 

	}

	@Override
	public List<LastNewsVO> getall() {
		List<LastNewsVO> newsALL = new ArrayList<>();
		LastNewsVO ln = null;
		ResultSet rs = null;
		try(Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);	//輸入在try內會自動關閉
				PreparedStatement pstmt = con.prepareStatement(GETALL);) 
		{
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ln = new LastNewsVO();
				ln.setNews_no(rs.getInt("News_no"));
				ln.setNews(rs.getString("News"));
				ln.setNews_img(rs.getBytes("News_img"));
				ln.setNews_time(rs.getDate("News_time"));
				
				newsALL.add(ln);
			}
				
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return newsALL;
	}

}
