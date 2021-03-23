package com.cg.service;

import java.util.List;

import com.cg.bean.Employee;
import com.cg.util.EmployeeException;

public interface EmployeeService {

	boolean createEmp(Employee emp) throws EmployeeException;

	void closeConnection() throws EmployeeException;

	void updateEmp(int id, String designation, long mobile) throws EmployeeException;

	void deleteEmp(int id) throws EmployeeException;

	boolean confirmDelete(String opt,int id) throws EmployeeException;

	Employee showEmp(int id) throws EmployeeException;

	boolean isValidName(String name);

	boolean isValidMobile(long mobile);

	List<Employee> searchName(String searchStr) throws EmployeeException;


}
