package com.rm_order.model;

import java.awt.PrintGraphics;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Date;

import com.rm_order_list.model.RmOrderListDAO;
import com.rm_order_list.model.RmOrderListVO;

public class RmOrderDAO implements I_RmOrderDAO {
	private static final String INSERT = "INSERT INTO rm_order(mem_no, store_no, order_date, rm_order_status, rm_charge, start_date, end_date)VALUES(?,?,NOW(),?,?,?,?)";
	private static final String UPDATE = "UPDATE rm_order SET mem_no=?, store_no=?, order_date=?, rm_order_status=?, rm_charge=?, rm_review=?, start_date=?, end_date=? WHERE rm_order_no=?";
	private static final String UPDATESTATUS = "UPDATE rm_order SET rm_order_status=?, rm_charge=? WHERE rm_order_no=?";
	private static final String CHECKIN = "UPDATE rm_order SET rm_order_status=0 WHERE rm_order_no=?";
	private static final String GET_ONE = "SELECT * FROM rm_order WHERE rm_order_no=?";
	private static final String GET_ALL = "SELECT * FROM rm_order ORDER BY rm_order_no DESC";
	private static final String GET_ALL_BY_STORE = "SELECT * FROM rm_order WHERE store_no=? ORDER BY store_no DESC";
	private static final String GET_ALL_STATUS = "SELECT * FROM rm_order WHERE rm_order_status = ? ORDER BY rm_order_no DESC";
	private static final String GET_STORE_STATUS = "SELECT * FROM rm_order WHERE store_no = ? AND rm_order_status = ?";
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
	public RmOrderVO insert(RmOrderVO rmOrderVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);

			ps.setInt(1, rmOrderVO.getMem_no());
			ps.setInt(2, rmOrderVO.getStore_no());
			ps.setInt(3, rmOrderVO.getRm_order_status());
			ps.setInt(4, rmOrderVO.getRm_charge());		
			ps.setInt(5, rmOrderVO.getRm_order_no());
			ps.setDate(6, rmOrderVO.getStart_date());
			ps.setDate(7, rmOrderVO.getEnd_date());
			ps.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rmOrderVO;
	}

	@Override
	public void insertWithLists(RmOrderVO rmOrderVO, List<RmOrderListVO> list) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			String cols[] = {"rm_order_no"};
			ps = con.prepareStatement(INSERT, cols);
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
						RmOrderListDAO dao = new RmOrderListDAO();
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
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void update(RmOrderVO rmOrderVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);

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

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void updateStatus(RmOrderVO rmOrderVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATESTATUS);

			ps.setInt(1, rmOrderVO.getRm_order_status());
			ps.setInt(2, rmOrderVO.getRm_charge());
			ps.setInt(3, rmOrderVO.getRm_order_no());

			ps.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void checkIn(Integer rm_order_no) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(CHECKIN);
			
			ps.setInt(1, rm_order_no);
			
			ps.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public RmOrderVO getOne(Integer rm_order_no) {
		ResultSet rs = null;
		RmOrderVO rm = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ONE);

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
				rm.setStart_date(rs.getDate("start_date"));
				rm.setEnd_date(rs.getDate("end_date"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
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
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL);

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
				rm.setStart_date(rs.getDate("start_date"));
				rm.setEnd_date(rs.getDate("end_date"));

				rmAll.add(rm);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rmAll;
	}
	public List<RmOrderVO> getAllByStore(Integer store_no) {
		List<RmOrderVO> rmAll = new ArrayList<>();
		ResultSet rs = null;
		RmOrderVO rm = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL_BY_STORE);
			ps.setInt(1, store_no);
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
				rm.setStart_date(rs.getDate("start_date"));
				rm.setEnd_date(rs.getDate("end_date"));
				rmAll.add(rm);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rmAll;
	}
	public List<RmOrderVO> getAllStatus(Integer rm_order_status) {
		List<RmOrderVO> rmAll = new ArrayList<>();
		ResultSet rs = null;
		RmOrderVO rm = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_ALL_STATUS);
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
				rm.setStart_date(rs.getDate("start_date"));
				rm.setEnd_date(rs.getDate("end_date"));
				rmAll.add(rm);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rmAll;
	}
	public List<RmOrderVO> getStoreStatus(Integer store_no, Integer rm_order_status) {
		List<RmOrderVO> rmAll = new ArrayList<>();
		ResultSet rs = null;
		RmOrderVO rm = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GET_STORE_STATUS);
			ps.setInt(1, store_no);
			ps.setInt(2, rm_order_status);
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
				rm.setStart_date(rs.getDate("start_date"));
				rm.setEnd_date(rs.getDate("end_date"));
				rmAll.add(rm);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rmAll;
	}
}
