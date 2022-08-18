package com.last_news.model;

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

public class LastNewsDAO implements I_LastNewsDAO {
	// 新增訊息
	private final String INSERT = "INSERT INTO last_news(news,news_img,news_time)  VALUES (?, ?, NOW())";
	// 下架訊息
	private final String DELETE = "DELETE FROM last_news WHERE news_no = ?";
	// 顯示訊息
	private final String GETALL = "select * from last_news order by news_no desc";

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
	public void insert(LastNewsVO lastNewsVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

			ps.setString(1, lastNewsVO.getNews());
			ps.setBytes(2, lastNewsVO.getNews_img());
			ps.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	@Override
	public void delete(LastNewsVO lastNewsVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(DELETE);

			ps.setInt(1, lastNewsVO.getNews_no());
			ps.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	@Override
	public List<LastNewsVO> getall() {
		List<LastNewsVO> newsALL = new ArrayList<>();
		LastNewsVO ln = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				ln = new LastNewsVO();
				ln.setNews_no(rs.getInt("News_no"));
				ln.setNews(rs.getString("News"));
				ln.setNews_img(rs.getBytes("News_img"));
				ln.setNews_time(rs.getDate("News_time"));

				newsALL.add(ln);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
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
