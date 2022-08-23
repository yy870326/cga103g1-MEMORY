package com.emp.model;

import java.util.List;

public class EmpService {
	private I_EmpDAO dao;

	public EmpService() {
		dao = new EmpDAO();
	}


	public EmpVO addEmp(String emp_acc, String emp_pwd, String emp_name, String emp_email, Boolean emp_state) {
		EmpVO empVO = new EmpVO();
		empVO.setEmp_acc(emp_acc);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_email(emp_email);
		empVO.setEmp_state(emp_state);
		
		dao.insert(empVO);
		return empVO;
	}


	public EmpVO updateEmp(String emp_acc, String emp_pwd, String emp_name, String emp_email, Boolean emp_state, Integer emp_no) {
		EmpVO empVO = new EmpVO();
		empVO.setEmp_acc(emp_acc);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_email(emp_email);
		empVO.setEmp_state(emp_state);
		empVO.setEmp_no(emp_no);
		dao.update(empVO);
		return empVO;
	}


	public EmpVO getOneEmp(Integer emp_no) {
		
		return dao.getOne(emp_no);
	}


	public List<EmpVO> getAll() {
	
		return dao.getAll();
	}


	public EmpVO login(String emp_acc, String emp_pwd) {
		
		return dao.login(emp_acc, emp_pwd);
	}
}
