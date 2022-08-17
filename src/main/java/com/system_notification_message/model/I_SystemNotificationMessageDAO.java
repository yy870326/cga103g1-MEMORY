package com.system_notification_message.model;

import java.util.List;


public interface I_SystemNotificationMessageDAO {
	//新增訊息
		public void insert(SystemNotificationMessageVO systemNotificationMessageVO);
		//下架訊息
		public void delete(SystemNotificationMessageVO systemNotificationMessageVO);
		//顯示訊息
		public List<SystemNotificationMessageVO> getall();
}
