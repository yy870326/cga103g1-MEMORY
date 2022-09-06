package com.rm_pic.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RmPicTest {

	public static void main(String[] args) throws IOException {
		I_RmPicDAO dao  = new RmPicJdbcDAO();
		
		// 新增
		RmPicVO rmPic = new RmPicVO();
		rmPic.setRm_type_no(1);
		byte[] pic = getPictureByteArray("/Users/ritaliu/Desktop/roomtype/1Taitung/4.jpeg");
		rmPic.setRm_pic(pic);
		dao.insert(rmPic);
		System.err.println("新增成功");
		
		// 刪除
//		dao.delete(24);
		
		// 查詢一筆 用PK
//		RoomImgVO roomimg = dao.getOne(4);
//		System.out.print(roomimg.getImg_no() + ",");
//		System.out.print(roomimg.getType_no() + ",");
//		System.out.println(roomimg.getType_img());
//		byte[] photo = roomimg.getType_img();
//		try {
//			readPicture(photo);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// 查詢單個房型的所有圖片
//		List<RoomImgVO> list = dao.getAllByType(2);
//		for (RoomImgVO roomimg : list) {
//			System.out.print(roomimg.getImg_no() + ",");
//			System.out.print(roomimg.getType_no() + ",");
//			System.out.println(roomimg.getType_img());
//			byte[] photo = roomimg.getType_img();
//			try {
//				readPicture(photo);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	//	將照片顯示出來
	static int count = 11;
	public static void readPicture(byte[] bytes) throws IOException {
		String imgurl = "/Users/ritaliu/Desktop/roomtype/1Taitung/"+count+".jpg";
		FileOutputStream fos = new FileOutputStream(imgurl);
		fos.write(bytes);
		fos.flush();
		fos.close();
		count++;
	}
	
	//  新增照片 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
