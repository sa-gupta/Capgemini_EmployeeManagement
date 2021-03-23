package com.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.bean.Employee;
import com.cg.util.DBUtil;
import com.cg.util.EmpMessages;
import com.cg.util.EmployeeException;

public class EmployeeDaoImpl implements EmployeeDao {

	Logger log = Logger.getLogger(EmployeeDaoImpl.class);
	private Connection conn;
	public EmployeeDaoImpl() {
	}

	@Override
	public boolean createEmp(Employee emp) throws EmployeeException {
		String query = "insert into emp values (?,?,?,?)";

		try {
			conn = DBUtil.createConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, emp.getId());
			stmt.setString(2, emp.getName());
			stmt.setString(3, emp.getDesig());
			stmt.setLong(4, emp.getMobile());
			int rows = stmt.executeUpdate();
			if (rows > 0) {
				log.info("New Employee created: ID= " + emp.getId() + ", NAME= " + emp.getName() + ", Designation= "
						+ emp.getDesig() + ", MOBILE= " + emp.getMobile());
				return true;
			}
		} catch (SQLException e) {
			log.error(EmpMessages.INSERT_ERROR);
			throw new EmployeeException(EmpMessages.INSERT_ERROR);
		}
		return false;
	}

	@Override
	public void closeConnection() throws EmployeeException {
		DBUtil.closeConnection();

	}

	@Override
	public void updateEmp(int id, String desig, long mobile) throws EmployeeException {
		String query = "update emp set desig=?,mobile=? where id=?";

		try {
			conn = DBUtil.createConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, desig);
			stmt.setLong(2, mobile);
			stmt.setInt(3, id);
			int rows = stmt.executeUpdate();
			if (rows > 0) {
				log.info("Record with ID : " + id + " Updated Designation: " + desig + " and Mob.No: " + mobile);
			} else
				log.error("Update not possible due to Unavailabilty of ID");
			throw new EmployeeException(EmpMessages.UPDATE_ERROR);
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}

	}

	@Override
	public void deleteEmp(int id) throws EmployeeException {
		String query = "delete from emp where id=?";
		conn = DBUtil.createConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			int rows = stmt.executeUpdate();
			if (rows > 0) {

			} else {
				log.error("Delete not possible due to Unavailabilty of ID");
				throw new EmployeeException(EmpMessages.DELETE_ERROR);
			}
		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}

	}

	@Override
	public boolean confirmDelete(String opt, int id) throws EmployeeException {
		try {
			if (opt.equals("y") || opt.equals("Y")) {
				conn.commit();
				log.info("Record deleted with ID : " + id);
				return true;
			} else
				conn.rollback();
		} catch (SQLException e) {
			throw new EmployeeException(EmpMessages.DB_ERROR);
		}

		return false;
	}

	@Override
	public Employee showEmp(int id) throws EmployeeException {
		String query = "select * from emp where id=?";
		Employee emp = null;
		conn = DBUtil.createConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				emp = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getLong(4));
			}
		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
		return emp;
	}

	@Override
	public List<Employee> searchName(String searchStr) throws EmployeeException {
		String query = "select * from emp where name like ? order by name";
		List<Employee> resList = new ArrayList<>();
		conn = DBUtil.createConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);

			stmt.setString(1, searchStr + "%");
			ResultSet rs = stmt.executeQuery();
//			System.out.println(rs.next());
			while (rs.next()) {
				resList.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getLong(4)));
			}
//			System.out.println(resList);
		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}

		return resList;
	}

}
