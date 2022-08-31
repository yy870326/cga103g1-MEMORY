package com.rm_order.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.rm_order_list.model.RmOrderListVO;

public class TestRmOrder {
	public static void main(String[] args) {
		I_RmOrderDAO irm = new RmOrderJdbcDAO();
		RmOrderVO rm = new RmOrderVO();
	
//	insert()
//	rm.setRm_order_no(1);
//	rm.setMem_no(1);
//	rm.setStore_no(1);
//	rm.setRm_order_status(1);
//	rm.setRm_charge(3500);
//	rm.setRm_review(5);
//	irm.insert(rm);
//	System.out.println("新增成功");
	
	
//  insertWithLists()
	rm.setMem_no(1);
	rm.setStore_no(1);
	rm.setRm_order_status(1);
	rm.setRm_charge(20000);
	rm.setStart_date(Date.valueOf("2022-09-28"));
	rm.setEnd_date(Date.valueOf("2022-09-30"));
	
	List<RmOrderListVO> testList = new ArrayList<RmOrderListVO>(); // 準備置入訂單明細數筆
	RmOrderListVO rol = new RmOrderListVO();   // 訂單明細POJO1
	rol.setRm_type_no(14);
	rol.setRm_amount(1);
	rol.setRm_price(4000);
	rol.setArrival_date(Date.valueOf("2022-09-28"));
	rol.setDeparture_date(Date.valueOf("2022-09-30"));
	rol.setRm_check_in("朱小妹");
	
	
	RmOrderListVO rol2 = new RmOrderListVO();   // 訂單明細POJO2
	rol2.setRm_type_no(19);
	rol2.setRm_amount(1);
	rol2.setRm_price(6000);
	rol2.setArrival_date(Date.valueOf("2022-09-28"));
	rol2.setDeparture_date(Date.valueOf("2022-09-30"));
	rol2.setRm_check_in("朱小弟");

	testList.add(rol);
	testList.add(rol2);
	
	irm.insertWithLists(rm, testList);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	update()
//	rm.setMem_no(1);
//	rm.setStore_no(1);
//	rm.setOrder_date(Date.valueOf("2022-10-31"));
//	rm.setRm_order_status(1);
//	rm.setRm_charge(3600);
//	rm.setRm_review(4);
//	rm.setRm_order_no(1);
//	irm.update(rm);
//	System.out.println("更新成功");
	
	
//	updateStatus()
//	rm.setRm_order_no(3);
//	rm.setRm_order_status(2);
//	rm.setRm_charge(0);
//	irm.updateStatus(rm);
//	System.out.println("更新成功");
	
	
//	getOne()
//	RmOrderVO rmone = irm.getOne(1);
//	System.out.println("訂單編號="+rmone.getRm_order_no());
//	System.out.println("會員編號="+rmone.getMem_no());
//	System.out.println("廠商編號="+rmone.getStore_no());
//	System.out.println("訂單日期="+rmone.getOrder_date());
//	System.out.println("訂單狀態="+rmone.getRm_order_status());
//	System.out.println("訂單總金額="+rmone.getRm_charge());
//	System.out.println("訂房評價="+rmone.getRm_review());
	
	
//	getAll()
//	List<RmOrderVO> list = irm.getAll();
//	for(RmOrderVO rms : list) {
//		System.out.println("訂單編號="+rms.getRm_order_no());
//		System.out.println("會員編號="+rms.getMem_no());
//		System.out.println("廠商編號="+rms.getStore_no());
//		System.out.println("訂單日期="+rms.getOrder_date());
//		System.out.println("訂單狀態="+rms.getRm_order_status());
//		System.out.println("訂單總金額="+rms.getRm_charge());
//		System.out.println("訂房評價="+rms.getRm_review());
//	}
//	System.out.println("成功查詢"+list.size()+"筆資料");
	}
}
