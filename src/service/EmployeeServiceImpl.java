package com.cg.service;

import java.util.List;

import com.cg.bean.Employee;
import com.cg.dao.EmployeeDao;
import com.cg.dao.EmployeeDaoImpl;
import com.cg.util.EmployeeException;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao eDao;

	public EmployeeServiceImpl() {
		eDao = new EmployeeDaoImpl();
	}

	@Override
	public boolean createEmp(Employee emp) throws EmployeeException {
		return eDao.createEmp(emp);
	}

	@Override
	public void closeConnection() throws EmployeeException {
		eDao.closeConnection();
	}

	@Override
	public void updateEmp(int id, String designation, long mobile) throws EmployeeException {
		 eDao.updateEmp(id,designation,mobile);
	}

	@Override
	public void deleteEmp(int id) throws EmployeeException {
		eDao.deleteEmp(id);
	}

	@Override
	public boolean confirmDelete(String opt,int id) throws EmployeeException {
		return eDao.confirmDelete(opt,id);
	}

	@Override
	public Employee showEmp(int id) throws EmployeeException {
		return eDao.showEmp(id);
	}

	@Override
	public boolean isValidName(String name) {
		return name.matches("^([A-Z])\\w*$");
	}

	@Override
	public boolean isValidMobile(long mobile) {
		return Long.toString(mobile).matches("^[7-9][0-9]{9}$");
	}
	
	@Override
	public List<Employee> searchName(String searchStr) throws EmployeeException {
		return eDao.searchName(searchStr);
	}

}
