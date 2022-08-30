package com.rm_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rm_order_list.model.RmOrderListDAO;
import com.rm_order_list.model.RmOrderListJdbcDAO;
import com.rm_order_list.model.RmOrderListVO;
import com.util.JdbcUtil;

public class RmOrderJdbcDAO implements I_RmOrderDAO {

	private static final String INSERT = "INSERT INTO rm_order(mem_no, store_no, order_date, rm_order_status, rm_charge, start_date, end_date)VALUES(?,?,NOW(),?,?,?,?)";
	private static final String UPDATE = "UPDATE rm_order SET mem_no=?, store_no=?, order_date=?, rm_order_status=?, rm_charge=?, rm_review=?, start_date=?, end_date=? WHERE rm_order_no=?";
	private static final String UPDATESTATUS = "UPDATE rm_order SET rm_order_status=?, rm_charge=? WHERE rm_order_no=?";
	private static final String CHECKIN = "UPDATE rm_order SET rm_order_status=0 WHERE rm_order_no=?";
	private static final String GET_ONE = "SELECT * FROM rm_order WHERE rm_order_no=?";
	private static final String GET_ALL = "SELECT * FROM rm_order ORDER BY rm_order_no DESC";
	private static final String GET_ALL_BY_STORE = "SELECT * FROM rm_order WHERE store_no=? ORDER BY store_no DESC";
	private static final String GET_ALL_STATUS = "SELECT * FROM rm_order WHERE rm_order_status = ? ORDER BY rm_order_no DESC";
	private static final String GET_STORE_STATUS = "SELECT * FROM rm_order WHERE store_no = ? AND rm_order_status = ?";

	static { // 資料庫連線
		try {
			Class.forName(JdbcUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public RmOrderVO insert(RmOrderVO rmOrderVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT)) {

			ps.setInt(1, rmOrderVO.getMem_no());
			ps.setInt(2, rmOrderVO.getStore_no());
			ps.setInt(3, rmOrderVO.getRm_order_status());
			ps.setInt(4, rmOrderVO.getRm_charge());
			ps.setDate(5, rmOrderVO.getStart_date());
			ps.setDate(6, rmOrderVO.getEnd_date());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rmOrderVO;
	}

	@Override
	public void insertWithLists(RmOrderVO rmOrderVO, List<RmOrderListVO> list) {
		
		String cols[] = {"rm_order_no"};
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT, cols)) {

			con.setAutoCommit(false);

			ps.setInt(1, rmOrderVO.getMem_no());
			ps.setInt(2, rmOrderVO.getStore_no());
			ps.setInt(3, rmOrderVO.getRm_order_status());
			ps.setInt(4, rmOrderVO.getRm_charge());
			ps.setDate(5, rmOrderVO.getStart_date());
			ps.setDate(6, rmOrderVO.getEnd_date());
			ps.executeUpdate();

			String next_rmorderno = null;
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				next_rmorderno = rs.getString(1);
				System.out.println("自增主鍵值= " + next_rmorderno +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
						RmOrderListJdbcDAO dao = new RmOrderListJdbcDAO();
						System.out.println("list.size()-A="+list.size());
						for (RmOrderListVO aRm : list) {
							aRm.setRm_order_no(new Integer(next_rmorderno)) ;
							dao.insert2(aRm,con);
						}

						// 2●設定於 pstm.executeUpdate()之後
						con.commit();
						con.setAutoCommit(true);
						System.out.println("list.size()-B="+list.size());
						System.out.println("新增訂單編號" + next_rmorderno + "時,共有訂單明細" + list.size()
								+ "項同時被新增");
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}
	
	@Override
	public void update(RmOrderVO rmOrderVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE)) {

			ps.setInt(1, rmOrderVO.getMem_no());
			ps.setInt(2, rmOrderVO.getStore_no());
			ps.setObject(3, rmOrderVO.getOrder_date());
			ps.setInt(4, rmOrderVO.getRm_order_status());
			ps.setInt(5, rmOrderVO.getRm_charge());
			ps.setInt(6, rmOrderVO.getRm_review());
			ps.setInt(7, rmOrderVO.getRm_order_no());
			ps.setDate(8, rmOrderVO.getStart_date());
			ps.setDate(9, rmOrderVO.getEnd_date());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateStatus(RmOrderVO rmOrderVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATESTATUS)) {

			ps.setInt(1, rmOrderVO.getRm_order_status());
			ps.setInt(2, rmOrderVO.getRm_charge());
			ps.setInt(3, rmOrderVO.getRm_order_no());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public RmOrderVO getOne(Integer rm_order_no) {
		ResultSet rs = null;
		RmOrderVO rm = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ONE)) {

			ps.setInt(1, rm_order_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				rm = new RmOrderVO();
				rm.setRm_order_no(rs.getInt("rm_order_no"));
				rm.setMem_no(rs.getInt("mem_no"));
				rm.setStore_no(rs.getInt("store_no"));
				rm.setOrder_date(rs.getDate("order_date"));
				rm.setRm_order_status(rs.getInt("rm_order_status"));
				rm.setRm_charge(rs.getInt("rm_charge"));
				rm.setRm_review(rs.getInt("rm_review"));
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
		return rm;
	}

	@Override
	public List<RmOrderVO> getAll() {
		List<RmOrderVO> rmAll = new ArrayList<>();
		ResultSet rs = null;
		RmOrderVO rm = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ALL)) {

			rs = ps.executeQuery();
			while (rs.next()) {
				rm = new RmOrderVO();
				rm.setRm_order_no(rs.getInt("rm_order_no"));
				rm.setMem_no(rs.getInt("mem_no"));
				rm.setStore_no(rs.getInt("store_no"));
				rm.setOrder_date(rs.getDate("order_date"));
				rm.setRm_order_status(rs.getInt("rm_order_status"));
				rm.setRm_charge(rs.getInt("rm_charge"));
				rm.setRm_review(rs.getInt("rm_review"));

				rmAll.add(rm);
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
		return rmAll;
	}
	public List<RmOrderVO> getAllStatus(Integer rm_order_status) {
		List<RmOrderVO> rmAll = new ArrayList<>();
		ResultSet rs = null;
		RmOrderVO rm = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ALL_STATUS)){
			ps.setInt(1, rm_order_status);
			rs = ps.executeQuery();
			while (rs.next()) {
				rm = new RmOrderVO();
				rm.setRm_order_no(rs.getInt("rm_order_no"));
				rm.setMem_no(rs.getInt("mem_no"));
				rm.setStore_no(rs.getInt("store_no"));
				rm.setOrder_date(rs.getDate("order_date"));
				rm.setRm_order_status(rs.getInt("rm_order_status"));
				rm.setRm_charge(rs.getInt("rm_charge"));
				rm.setRm_review(rs.getInt("rm_review"));
				
				rmAll.add(rm);
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
		return rmAll;
	}

	@Override
	public void checkIn(Integer rm_order_no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RmOrderVO> getAllByStore(Integer store_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RmOrderVO> getStoreStatus(Integer store_no, Integer rm_order_status) {
		// TODO Auto-generated method stub
		return null;
	}

}
