package com.store.model;

import java.sql.Date;
import java.util.List;

import com.rm_order_list.model.Rm_order_listVO;

public class StoreService {
	private I_StoreDAO dao;
	
	public StoreService() {
		dao = new StoreDAO();
	}
	
	public StoreVO addStoreService(
	 String store_acc,
	 String store_pwd,
	 Integer acc_status,
	 String store_name,
	 String store_gui,
	 String store_rep,
	 String store_tel,
	 String store_fax,
	 String store_add,
	 String store_poc,
	 String store_con_phone,
	 String store_con_add,
	 String store_email,
	 Date store_reg_date,
	 String bank_account,
	 Integer store_tkt_rating_scoure,
	 Integer store_tkt_rating_count,
	 Integer store_tkt_rating,
	 Integer store_rm_rating_score,
	 Integer store_rm_rating_count,
	 Integer store_act_rating_score,
	 Integer store_act_rating_count,
	 Integer store_report_count
			) {
		StoreVO storeVO = new StoreVO();
		
		storeVO.setStore_acc(store_acc);
		storeVO.setStore_pwd(store_pwd);
		storeVO.setAcc_status(acc_status);
		storeVO.setStore_name(store_name);
		storeVO.setStore_gui(store_gui);
		storeVO.setStore_rep(store_rep);
		storeVO.setStore_tel(store_tel);
		storeVO.setStore_fax(store_fax);
		storeVO.setStore_add(store_add);
		storeVO.setStore_poc(store_poc);
		storeVO.setStore_con_phone(store_con_phone);
		storeVO.setStore_con_add(store_con_add);
		storeVO.setStore_email(store_email);
		storeVO.setStore_reg_date(store_reg_date);
		storeVO.setBank_account(bank_account);
		storeVO.setStore_tkt_rating(store_tkt_rating);
		storeVO.setStore_tkt_rating_count(store_tkt_rating_count);
		storeVO.setStore_tkt_rating_scoure(store_tkt_rating_scoure);
		storeVO.setStore_rm_rating_count(store_rm_rating_count);
		storeVO.setStore_rm_rating_score(store_rm_rating_score);
		storeVO.setStore_act_rating_count(store_act_rating_count);
		storeVO.setStore_act_rating_score(store_act_rating_score);
		storeVO.setStore_report_count(store_report_count);
		
		
		return dao.insert(storeVO);
	}
	
	public StoreVO updateStoreService (
			
			 Integer store_no,
			 String store_acc,
			 String store_pwd,
			 Integer acc_status,
			 String store_name,
			 String store_gui,
			 String store_rep,
			 String store_tel,
			 String store_fax,
			 String store_add,
			 String store_poc,
			 String store_con_phone,
			 String store_con_add,
			 String store_email,
			 Date store_reg_date,
			 String bank_account,
			 Integer store_tkt_rating_scoure,
			 Integer store_tkt_rating_count,
			 Integer store_tkt_rating,
			 Integer store_rm_rating_score,
			 Integer store_rm_rating_count,
			 Integer store_act_rating_score,
			 Integer store_act_rating_count,
			 Integer store_report_count
			) {
		
		StoreVO storeVO = new StoreVO();
		
		storeVO.setStore_no(store_no);
		storeVO.setStore_acc(store_acc);
		storeVO.setStore_pwd(store_pwd);
		storeVO.setAcc_status(acc_status);
		storeVO.setStore_name(store_name);
		storeVO.setStore_gui(store_gui);
		storeVO.setStore_rep(store_rep);
		storeVO.setStore_tel(store_tel);
		storeVO.setStore_fax(store_fax);
		storeVO.setStore_add(store_add);
		storeVO.setStore_poc(store_poc);
		storeVO.setStore_con_phone(store_con_phone);
		storeVO.setStore_con_add(store_con_add);
		storeVO.setStore_email(store_email);
		storeVO.setStore_reg_date(store_reg_date);
		storeVO.setBank_account(bank_account);
		storeVO.setStore_tkt_rating(store_tkt_rating);
		storeVO.setStore_tkt_rating_count(store_tkt_rating_count);
		storeVO.setStore_tkt_rating_scoure(store_tkt_rating_scoure);
		storeVO.setStore_rm_rating_count(store_rm_rating_count);
		storeVO.setStore_rm_rating_score(store_rm_rating_score);
		storeVO.setStore_act_rating_count(store_act_rating_count);
		storeVO.setStore_act_rating_score(store_act_rating_score);
		storeVO.setStore_report_count(store_report_count);
		dao.update(storeVO);
		
		return storeVO;
	} 
	
	public void deleteStoreService(Integer store_no) {
		dao.delete(store_no);
		
	}
	
	public StoreVO getOneStore(Integer store_no) {
		return dao.queryStore(store_no);
	}
	
	public List<StoreVO> getAllStore(){
		return dao.getAllStore();
	}
}
