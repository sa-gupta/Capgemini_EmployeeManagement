package com.cg.dao;

import java.util.List;

import com.cg.bean.Employee;
import com.cg.util.EmployeeException;

public interface EmployeeDao {

	boolean createEmp(Employee emp) throws EmployeeException;

	void closeConnection() throws EmployeeException;

	void updateEmp(int id, String designation, long mobile) throws EmployeeException;

	void deleteEmp(int id) throws EmployeeException;

	boolean confirmDelete(String opt,int id) throws EmployeeException;

	Employee showEmp(int id) throws EmployeeException;

	List<Employee> searchName(String searchStr) throws EmployeeException;


}
