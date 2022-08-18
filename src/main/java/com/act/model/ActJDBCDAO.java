package com.act.model;

import static com.util.JdbcUtil.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ActJDBCDAO implements I_ActDAO{
	
	private static final String INSERT = "insert into act( "
			+ "mem_no, act_type_no, act_title, "
			+ "act_content, "
			+ "act_current_count, act_min_count, act_max_count,  "
			+ "act_enroll_begin, act_enroll_end, act_start, act_end, "
			+ "act_loc, act_pl) "
			+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE = "update act set act_type_no = ?, act_title = ? "
			+ ", act_content = ?, act_current_count = ?, act_min_count = ? "
			+ ", act_max_count = ?, act_enroll_begin = ?, act_enroll_end= ? "
			+ ", act_start = ?, act_end = ?, act_loc = ? "
			+ ", act_pl = ? where act_no = ? and mem_no = ?";
	
	private static final String UPDATE_ACT_STATUS = "update act set act_status = ? where act_no = ?";

	private static final String UPDATE_ACT_RATE_AND_EVAL = "update act set act_rate_sum = ?, act_eval_sum = ? where act_no = ?";
	
	private static final String GET_ALL = "select act_no "
			+ ", mem_no, act_type_no, act_title, act_content "
			+ ", act_current_count, act_max_count, act_min_count "
			+ ", act_enroll_begin, act_enroll_end, act_start "
			+ ", act_end, act_loc, act_pl, act_rate_sum "
			+ ", act_eval_sum, act_status  "
			+ "from act";
	
	private static final String GET_HOST_ACT = "select act_no, mem_no, act_type_no, act_title "
			+ ", act_content, act_current_count, act_max_count "
			+ ", act_min_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum"
			+ ", act_eval_sum, act_status from act where mem_no = ?";
	
	private static final String FIND_ACT_BY_TYPE = "select act_no, act_type_no, mem_no, act_title "
			+ ", act_content, act_current_count, act_min_count "
			+ ", act_max_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum"
			+ ", act_eval_sum, act_status from act where act_type_no = ?";
	
	private static final String FIND_AEB = "select act_no, mem_no, act_type_no, act_title "
			+ ", act_content, act_current_count, act_max_count "
			+ ", act_min_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum"
			+ ", act_eval_sum, act_status from act where act_enroll_begin >= ?";
	
	private static final String FIND_AEE = "select act_no, mem_no, act_type_no, act_title "
			+ ", act_content, act_current_count, act_max_count "
			+ ", act_min_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum"
			+ ", act_eval_sum, act_status from act where act_enroll_end <= ?";
	
	private static final String FIND_AEBE = "select act_no, mem_no, act_type_no, act_title "
			+ ", act_content, act_current_count, act_max_count "
			+ ", act_min_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum"
			+ ", act_eval_sum, act_status from act where act_enroll_begin >= ? and act_enroll_end <= ? ";
	
	private static final String FIND_ASBE = "select act_no, mem_no, act_type_no, act_title "
			+ ", act_content, act_current_count, act_max_count "
			+ ", act_min_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum"
			+ ", act_eval_sum, act_status from act where act_start >= ? and act_end <= ? ";
	
	private static final String GET_ACT_EVAL_AVG = "select act_no, mem_no, act_type_no, act_title "
			+ ", act_content, act_current_count, act_max_count "
			+ ", act_min_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum "
			+ ", act_eval_sum, act_status from act where (act_rate_sum / act_eval_sum) >= ?";

	private static final String GET_ACT_LOC = "select act_no, mem_no, act_type_no, act_title "
			+ ", act_content, act_current_count, act_max_count "
			+ ", act_min_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, (act_rate_sum / act_eval_sum) as StarAvg "
			+ ", act_status from act where act_loc = ?";

	private static final String FIND_ACT_BY_MIN_COUNT = "select act_no "
			+ ", mem_no, act_type_no, act_title, act_content "
			+ ", act_current_count, act_max_count, act_min_count "
			+ ", act_enroll_begin, act_enroll_end, act_start "
			+ ", act_end, act_loc, act_pl, (act_rate_sum / act_eval_sum) as avgStar, act_status "
			+ " from act where act_min_count >= ?";
		
	private static final String FIND_ACT_BY_MAX_COUNT = "select act_no "
			+ ", mem_no, act_type_no, act_title, act_content "
			+ ", act_current_count, act_max_count, act_min_count "
			+ ", act_enroll_begin, act_enroll_end, act_start "
			+ ", act_end, act_loc, act_pl, (act_rate_sum / act_eval_sum) as avgStar, act_status "
			+ "from act where act_max_count <= ?";

	
	private static final String FIND_PERIOD_COUNT = "select act_no "
			+ ", mem_no, act_type_no, act_title, act_content "
			+ ", act_current_count, act_max_count, act_min_count "
			+ ", act_enroll_begin, act_enroll_end, act_start "
			+ ", act_end, act_loc, act_pl, (act_rate_sum / act_eval_sum) as avgStar, act_status "
			+ "from act where act_min_count >= ? and act_max_count <= ?";
	
	private static final String FIND_ACC =  "select act_no, act_type_no, mem_no, act_title "
			+ ", act_content, act_current_count, act_min_count "
			+ ", act_max_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum"
			+ ", act_eval_sum, act_status from act where act_current_count = ?";
	
	private static final String UPDATE_RATE_SUM = "update act set act_rate_sum = ? where act_no = ?";
	
	private static final String UPDATE_EVAL_SUM = "update act set act_eval_sum = ? where act_no = ?";
	
	// ========================================================================================== //
	
	
	@Override
	public void insert(ActVO actVO) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT)) {
			ps.setInt(1, actVO.getMen_no());
			ps.setInt(2, actVO.getAct_type_no());
			ps.setString(3, actVO.getAct_title());
			ps.setString(4, actVO.getAct_content());
			ps.setInt(5, actVO.getAct_current_count());
			ps.setInt(6, actVO.getAct_min_count());
			ps.setInt(7, actVO.getAct_max_count());
			ps.setObject(8, actVO.getAct_enroll_begin());
			ps.setObject(9, actVO.getAct_enroll_end());
			ps.setObject(10, actVO.getAct_start());			
			ps.setObject(11, actVO.getAct_end());
			ps.setInt(12, actVO.getAct_loc());			
			ps.setString(13, actVO.getAct_pl());
			ps.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void update(ActVO actVO) {
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(UPDATE)) {
			ps.setInt(1, actVO.getAct_type_no());
			ps.setString(2, actVO.getAct_title());
			ps.setString(3, actVO.getAct_content());
			ps.setInt(4, actVO.getAct_current_count());
			ps.setInt(5, actVO.getAct_min_count());
			ps.setInt(6, actVO.getAct_max_count());
			ps.setObject(7, actVO.getAct_enroll_begin());
			ps.setObject(8, actVO.getAct_enroll_end());
			ps.setObject(9, actVO.getAct_start());			
			ps.setObject(10, actVO.getAct_end());
			ps.setInt(11, actVO.getAct_loc());			
			ps.setString(12, actVO.getAct_pl());
			ps.setInt(13, actVO.getAct_no());
			ps.setInt(14, actVO.getMen_no());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	

	@Override
	public void updateActStatus(ActVO actVO) {
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(UPDATE_ACT_STATUS)) {
			ps.setInt(1, actVO.getAct_status());
			ps.setInt(2, actVO.getAct_no());			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	@Override
	public void updateRateEval(ActVO actVO) {
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(UPDATE_ACT_RATE_AND_EVAL)) {
			ps.setInt(1, actVO.getAct_rate_sum());
			ps.setInt(2, actVO.getEval_sum());
			ps.setInt(3, actVO.getAct_no());			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	

	@Override
	public List<ActVO> getAll() {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ALL);) {			
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer actTypeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime actStart = (LocalDateTime) rs1.getObject(11);
				LocalDateTime actEnd = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer actRateSum = rs1.getInt(15);
				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, actTypeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, actStart,
								actEnd, actLoc,
								actPl,actRateSum,
								actEvalSum,actStatus
								);
				acts.add(act);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}
	

	@Override
	public List<ActVO> getHostAct(Integer memNo) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_HOST_ACT);) {
			ps.setInt(1, memNo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer actTypeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime actStart = (LocalDateTime) rs1.getObject(11);
				LocalDateTime actEnd = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer actRateSum = rs1.getInt(15);
				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, actTypeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, actStart,
								actEnd, actLoc,
								actPl,actRateSum,
								actEvalSum,actStatus
								);
				acts.add(act);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}

	@Override
	public List<ActVO> findActByType(Integer actTypeNo) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(FIND_ACT_BY_TYPE)) {
			ps.setInt(1, actTypeNo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime actStart = (LocalDateTime) rs1.getObject(11);
				LocalDateTime actEnd = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer actRateSum = rs1.getInt(15);
				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, actStart,
								actEnd, actLoc,
								actPl,actRateSum,
								actEvalSum,actStatus
								);
				acts.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}

	@Override
	public List<ActVO> findActByAEB(LocalDateTime actEnrollBegin) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(FIND_AEB)) {
			ps.setObject(1, actEnrollBegin);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime act_EnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime actStart = (LocalDateTime) rs1.getObject(11);
				LocalDateTime actEnd = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer actRateSum = rs1.getInt(15);
				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								act_EnrollBegin,
								actEnrollEnd, actStart,
								actEnd, actLoc,
								actPl,actRateSum,
								actEvalSum,actStatus
								);
				acts.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}
	
	@Override
	public List<ActVO> findActByAEE(LocalDateTime actEnrollEnd) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(FIND_AEE)) {
			ps.setObject(1, actEnrollEnd);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime act_EnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime actStart = (LocalDateTime) rs1.getObject(11);
				LocalDateTime actEnd = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer actRateSum = rs1.getInt(15);
				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								act_EnrollEnd, actStart,
								actEnd, actLoc,
								actPl,actRateSum,
								actEvalSum,actStatus
								);
				acts.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}

	@Override
	public List<ActVO> findActByAEBE(LocalDateTime actEnrollBegin, LocalDateTime actEnrollEnd) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(FIND_AEBE)) {
			ps.setObject(1, actEnrollBegin);
			ps.setObject(2, actEnrollEnd);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime act_EnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime act_EnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime actStart = (LocalDateTime) rs1.getObject(11);
				LocalDateTime actEnd = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer actRateSum = rs1.getInt(15);
				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								act_EnrollBegin,
								act_EnrollEnd, actStart,
								actEnd, actLoc,
								actPl,actRateSum,
								actEvalSum,actStatus
								);
				acts.add(act);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}

	@Override
	public List<ActVO> findActByASBE(LocalDateTime actStart, LocalDateTime actEnd) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(FIND_ASBE)) {
				ps.setObject(1, actStart);
				ps.setObject(2, actEnd);
				ResultSet rs1 = ps.executeQuery();
				while (rs1.next()) {
					Integer actNo = rs1.getInt(1);
					Integer actMemNo = rs1.getInt(2);
					Integer act_typeNo = rs1.getInt(3);
					String actTitle = rs1.getString(4);
					String actContent = rs1.getString(5);
					Integer actCurrentCount = rs1.getInt(6);
					Integer actMinCount = rs1.getInt(7);
					Integer actMaxCount = rs1.getInt(8);
					LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
					LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
					LocalDateTime act_Start = (LocalDateTime) rs1.getObject(11);
					LocalDateTime act_End = (LocalDateTime) rs1.getObject(12);
					Integer actLoc = rs1.getInt(13);
					String actPl = rs1.getString(14);
					Integer actRateSum = rs1.getInt(15);
					Integer actEvalSum = rs1.getInt(16);
					Integer actStatus = rs1.getInt(17);
					ActVO act = 
							new ActVO(actNo,
									actMemNo, act_typeNo, actTitle,
									actContent, actCurrentCount,
									actMinCount, actMaxCount,
									actEnrollBegin,
									actEnrollEnd, act_Start,
									act_End, actLoc,
									actPl,actRateSum,
									actEvalSum,actStatus
									);
					acts.add(act);
					}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}

	@Override
	public List<ActVO> getActEvalAvg(Integer starAvg) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ACT_EVAL_AVG)) {
			ps.setInt(1, starAvg);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime act_Start = (LocalDateTime) rs1.getObject(11);
				LocalDateTime act_End = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer act_RateSum = rs1.getInt(15);
				Integer act_EvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, act_Start,
								act_End, actLoc,
								actPl,act_RateSum,
								act_EvalSum,actStatus);
				acts.add(act);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}

	@Override
	public List<ActVO> getActLoc(Integer actLoc) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ACT_LOC)) {
			ps.setInt(1, actLoc);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime act_Start = (LocalDateTime) rs1.getObject(11);
				LocalDateTime act_End = (LocalDateTime) rs1.getObject(12);
				Integer act_Loc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer starAvg = rs1.getInt(15);
//				Integer actRateSum = rs1.getInt(15);
//				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(16);				
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, act_Start,
								act_End, act_Loc,
								actPl, starAvg,actStatus);
				acts.add(act);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}
	
	@Override
	public List<ActVO> findActByMinCount(Integer minCount) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(FIND_ACT_BY_MIN_COUNT)) {
			ps.setInt(1, minCount);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime act_Start = (LocalDateTime) rs1.getObject(11);
				LocalDateTime act_End = (LocalDateTime) rs1.getObject(12);
				Integer act_Loc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer starAvg = rs1.getInt(15);
				Integer actStatus = rs1.getInt(16);				
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, act_Start,
								act_End, act_Loc,
								actPl, starAvg,actStatus);
				acts.add(act);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}
	
	@Override
	public List<ActVO> findActByMaxCount(Integer maxCount) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(FIND_ACT_BY_MAX_COUNT)) {
			ps.setInt(1, maxCount);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime act_Start = (LocalDateTime) rs1.getObject(11);
				LocalDateTime act_End = (LocalDateTime) rs1.getObject(12);
				Integer act_Loc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer starAvg = rs1.getInt(15);
				Integer actStatus = rs1.getInt(16);				
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, act_Start,
								act_End, act_Loc,
								actPl, starAvg,actStatus);
				acts.add(act);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return acts;
	}
	
	@Override
	public List<ActVO> findActByCurrentCount(Integer currentCount) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(FIND_ACC)) {
			ps.setInt(1, currentCount);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime act_Start = (LocalDateTime) rs1.getObject(11);
				LocalDateTime act_End = (LocalDateTime) rs1.getObject(12);
				Integer act_Loc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer starAvg = rs1.getInt(15);
				Integer actStatus = rs1.getInt(16);				
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, act_Start,
								act_End, act_Loc,
								actPl, starAvg,actStatus);
				acts.add(act);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return acts;
	}

	@Override
	public List<ActVO> findPeriodCount(Integer minCount, Integer maxCount) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(FIND_PERIOD_COUNT)) {
			ps.setObject(1, minCount);
			ps.setObject(2, maxCount);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime act_Start = (LocalDateTime) rs1.getObject(11);
				LocalDateTime act_End = (LocalDateTime) rs1.getObject(12);
				Integer act_Loc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer starAvg = rs1.getInt(15);
				Integer actStatus = rs1.getInt(16);				
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, act_Start,
								act_End, act_Loc,
								actPl, starAvg,actStatus);
				acts.add(act);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return acts;
	}

	
	@Override
	public void updateRateSum(ActVO actVO) {
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(UPDATE_RATE_SUM)) {
			ps.setInt(1, actVO.getAct_rate_sum());
			ps.setInt(2, actVO.getAct_no());			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	
	@Override
	public void updateEvalSum(ActVO actVO) {
		try(Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement ps = conn.prepareStatement(UPDATE_EVAL_SUM)) {
			ps.setInt(1, actVO.getEval_sum());
			ps.setInt(2, actVO.getAct_no());			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	// ====== Main ======
		
	
	
	public static void main(String[] args) {
			ActJDBCDAO actJBCDDAO = new ActJDBCDAO();
			
			// insert
			ActVO actVO1 = new ActVO();
//			actVO1.setMen_no(1);
//			actVO1.setAct_type_no(2);
//			actVO1.setAct_title("羽球團");
//			actVO1.setAct_content("歡迎新手加入，一起運動趣!");
//			actVO1.setAct_current_count(1);
//			actVO1.setAct_min_count(4);
//			actVO1.setAct_max_count(100);
//			actVO1.setAct_enroll_begin(LocalDateTime.of(2022,10,10,14,00,00));
//			actVO1.setAct_enroll_end(LocalDateTime.of(2022,10,18,14,00,00));
//			actVO1.setAct_start(LocalDateTime.of(2022,10,20,14,00,00));
//			actVO1.setAct_end(LocalDateTime.of(2022,10,20,14,00,00));
//			actVO1.setAct_loc(0);
//			actVO1.setAct_pl("中山區 中山國小 羽球場");
//			actJBCDDAO.insert(actVO1);
			
			// update
	//		actVO1.setAct_type_no(2);
	//		actVO1.setAct_title("Tibame 羽球團");
	//		actVO1.setAct_content("歡迎新手加入，一起運動趣喔!");
	//		actVO1.setAct_current_count(3);
	//		actVO1.setAct_min_count(6);
	//		actVO1.setAct_max_count(50);
	//		actVO1.setAct_enroll_begin(LocalDateTime.of(2022,10,11,12,00,00));
	//		actVO1.setAct_enroll_end(LocalDateTime.of(2022,10,19,12,00,00));
	//		actVO1.setAct_start(LocalDateTime.of(2022,10,21,18,00,00));
	//		actVO1.setAct_end(LocalDateTime.of(2022,10,21,18,00,00));
	//		actVO1.setAct_loc(0);
	//		actVO1.setAct_pl("中山區 中山國小 裡面的 羽球場");
	//		actVO1.setAct_no(8);
	//		actVO1.setMen_no(1);
	//		actJBCDDAO.update(actVO1);
			
			// updateActStatus
//			actVO1.setAct_status(1);
//			actVO1.setAct_no(8);
//			actJBCDDAO.updateActStatus(actVO1);
	
			//updateRateEval(ActVO)
//			actVO1.setAct_rate_sum(20);
//			actVO1.setEval_sum(4);
//			actVO1.setAct_no(8);
//			actJBCDDAO.updateRateEval(actVO1);
					
			// getAll()
//			List<ActVO> acts = actJBCDDAO.getAll();
//			System.out.println(acts);
//			acts.forEach(act -> System.out.println(act));
//				
			// getHostAct(Integer)
//			List<ActVO> acts = actJBCDDAO.getHostAct(3);
//			acts.forEach(System.out::println);

			// findActByType(Integer)
//			List<ActVO> acts = actJBCDDAO.findActByType(2);
//			acts.forEach(System.out::println);
			
			// findActByAEB(LocalDateTime)
//			List<ActVO> acts = actJBCDDAO.findActByAEB(LocalDateTime.of(
//					2022,8, 15,11,00));
//			acts.forEach(System.out::println);
			
			// findActByAEE(LocalDateTime)
//			List<ActVO> acts = actJBCDDAO.findActByAEB(LocalDateTime.of(
//					2022,10, 11,12,00));
//			acts.forEach(System.out::println);
			
			// findActByAEBE(LocalDateTime, LocalDateTime)
//			List<ActVO> acts = actJBCDDAO.findActByAEBE(LocalDateTime.of(
//			2022,10, 11,11,00),
//					LocalDateTime.of(
//					2022,10, 20,12,00));
//			acts.forEach(System.out::println);
	
	
			// findActByASBE(LocalDateTime, LocalDateTime)
//				List<ActVO> acts = actJBCDDAO.findActByASBE(LocalDateTime.of(
//			2022,10, 20,11,00),
//			LocalDateTime.of(
//			2022,10, 22,12,00));
//				acts.forEach(System.out::println);
			
			// getActEvalAvg(Integer, Integer)
			List<ActVO> acts = actJBCDDAO.getActEvalAvg(3);
			acts.forEach(System.out::println);
							
			// getActLoc(Integer)
//			List<ActVO> acts = actJBCDDAO.getActLoc(0);
//			acts.forEach(System.out::println);
						
			// findActByMinCount(Integer)
//			List<ActVO> acts = actJBCDDAO.findActByMinCount(3);
//			acts.forEach(System.out::println);
			
			// findActByMaxCount(Integer)
//			List<ActVO> acts = actJBCDDAO.findActByMaxCount(6);
//			acts.forEach(System.out::println);
			
			// findActByCurrentCount
//			List<ActVO> acts = actJBCDDAO.findActByCurrentCount(7);
//			acts.forEach(System.out::println);
			
			// findPeriodCount(Integer, Integer)
//			List<ActVO> acts = actJBCDDAO.findPeriodCount(4,10);
//			acts.forEach(System.out::println);
			
			// updateRateSum(ActVO)
//			actVO1.setAct_rate_sum(30);
//			actVO1.setAct_no(1);
//			actJBCDDAO.updateRateSum(actVO1);
//			
			// updateEvalSum(ActVO)
//			actVO1.setEval_sum(6);
//			actVO1.setAct_no(1);
//			actJBCDDAO.updateEvalSum(actVO1);
		}

	



}
