package com.system_notification_message.model;

import java.util.List;

import com.last_news.model.LastNewsVO;


public interface I_SystemNotificationMessageDAO {
		//新增訊息
		public void insert(SystemNotificationMessageVO systemNotificationMessageVO);
		//下架訊息
		public void delete( Integer msg_no);
		//顯示訊息
		public List<SystemNotificationMessageVO> getall();
		//顯示圖片
		public  SystemNotificationMessageVO getpic(Integer msg_no);
		//取得一筆資料
		public SystemNotificationMessageVO getone(Integer msg_no);
		
		
		public SystemNotificationMessageVO  getlast();
}
