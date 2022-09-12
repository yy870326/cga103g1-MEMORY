package com.coup.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.coup.model.CoupService;
import com.coup.model.CoupVO;
import com.mem_coup.model.MemCoupService;
import com.mem_coup.model.MemCoupVO;

//
@WebListener
public class CoupListener implements ServletContextListener {
	Timer timer = null;

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		timer.cancel();
	}

//	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
	
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// 優惠券超過時間過期狀態下架
				CoupService coupSrv = new CoupService();
				
				List<CoupVO> list = coupSrv.getAll();

				for (int i = 0; i < list.size(); i++) {
					CoupVO coupVO = new CoupVO();
					// 每一張優惠券抓出來檢查有沒有過期
					coupVO = list.get(i);

					java.util.Date today = new java.util.Date();// 現在時間
					java.util.Date enddate = coupVO.getEnddate(); // 優惠券過期時間
					java.util.Date realEndDay = new Date(today.getTime() - (1000 * 60 * 60 * 24));
					

					// 如果現在時間超過過期時間就下架
					if (realEndDay.after(enddate)) {
						coupSrv.updateCoupStatus(coupVO.getCoup_no());
//						System.out.println(coupVO.getCoup_no() + "優惠券類型下架"); 
					}
				}
				// 會員優惠券超過時間狀態過期
				MemCoupService memCoupSrv = new MemCoupService();
				
				List<MemCoupVO> listMemCoup = memCoupSrv.getAll();
				
				for(int i = 0; i < listMemCoup.size(); i++) {
					MemCoupVO memCouponVO = new MemCoupVO();
					// 會員擁有的優惠券一個一個抓出來檢查有沒有過期
					memCouponVO = listMemCoup.get(i);
					
					java.util.Date today = new java.util.Date(); //現在時間
					java.util.Date enddate = memCouponVO.getCoupVO().getEnddate(); // 先找優惠券再找
					java.util.Date realEndDay = new Date(today.getTime() - (1000 * 60 * 60 * 24));
//					System.out.println(enddate);
					
					// 如果截止日小於今天代表過期，更改優惠券狀態為 2
					 if (realEndDay.after(enddate)) {
						 memCoupSrv.changeState(memCouponVO.getMem_coup_no(), 2); // 會員優惠券狀態 2 過期
//						 System.out.println(memCouponVO.getMem_coup_no() + "我的優惠券過期");
			         }
					
				}
				
			}
		};

		Calendar cal = new GregorianCalendar(2022, Calendar.SEPTEMBER, 11, 0, 0, 0);// 9/11號 凌晨0點開始
		timer.scheduleAtFixedRate(task, cal.getTime(), 24 * 60 * 60 * 1000);// 從上面指定時間開始，並在每天執行一次
//		
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.HOUR_OF_DAY, 0); // 每天 0 
//		calendar.set(Calendar.MINUTE, 0);
//		calendar.set(Calendar.SECOND, 0);
//		// java.util Date 第一次執行動作的時間是凌晨 0 點
//		Date date = calendar.getTime();

	}
	

//	
//	
}
