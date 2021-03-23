package com.cg.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.bean.Employee;
import com.cg.service.EmployeeService;
import com.cg.service.EmployeeServiceImpl;
import com.cg.util.EmployeeException;

public class EmployeeMain {
	private EmployeeService eService;

	public EmployeeMain() {
		eService = new EmployeeServiceImpl();
	}

	private void execute() {
		int choice;
		Scanner scan = new Scanner(System.in);
		while (true) {
			choice = getChoice(scan);
			switch (choice) {
			case 1:
				System.out.println("Create Employee");

				try {
					System.out.println("Enter <ID> <Name> <Designation> <Mobile>");
					int id = scan.nextInt();
					String name = scan.next();
					String desig = scan.next();
					long mobile = scan.nextLong();
					if (eService.isValidName(name)) {
						if (eService.isValidMobile(mobile)) {
							Employee emp = new Employee(id, name, desig, mobile);
							eService.createEmp(emp);
							System.out.println("Data Stored Successfully");
						} else
							System.out.println("Mobile no. should be of 10 digit and must start with 9,8,7");
					} else
						System.out.println("Name should start with capital letter.");

				} catch (EmployeeException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Input not correct");
					scan.nextLine();
				}
				break;
			case 2:
				System.out.println("Update Employee");
//				boolean success = false;
				try {
					System.out.println("Enter <ID> <Designation> <Mobile>");
					int id = scan.nextInt();
					String desig = scan.next();
					long mobile = scan.nextLong();
					if (eService.isValidMobile(mobile)) {
						eService.updateEmp(id, desig, mobile);
						System.out.println("Data Updated Successfully");
					} else
						System.out.println("Mobile no. should be of 10 digit and must start with 9,8,7");
				} catch (EmployeeException e) {
//					e.printStackTrace();
//					System.out.println("Failed to update data");
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Input not correct");
					scan.nextLine();
				}
				break;
			case 3:
				System.out.println("Delete Employee");
				System.out.println("Enter <ID>");
				try {
					int id = scan.nextInt();
					eService.deleteEmp(id);
					System.out.println("Do you really want to delete.  y/n ");
					if (eService.confirmDelete(scan.next(),id)) {
						System.out.println("Row deleted successfully");
					} else
						System.out.println("No deletion occured");
				} catch (InputMismatchException e) {
					System.out.println("Enter correct input only");
					scan.nextLine();
				} catch (EmployeeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Display Employee");
				System.out.println("Enter <ID>");
				Employee emp;
				try {
					emp = eService.showEmp(scan.nextInt());
					System.out.println("<ID>    <NAME>        <DESIGNATION>          <MOBILE>");
					System.out.printf("%-8s%-14s%-23s%s\n", emp.getId(), emp.getName(), emp.getDesig(),
							emp.getMobile());
				} catch (NullPointerException e) {
					System.out.println("Given ID doesn't exist.");
				} catch (InputMismatchException e) {
					System.out.println("ID should only be a number");
					scan.nextLine();
				} catch (EmployeeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("Search Employee by <Name>");
				System.out.println("Enter any no. of character.");
				try {
					String key = scan.next();
					if(eService.isValidName(key)) {
						List<Employee> resList = eService.searchName(key);
						System.out.println("<ID>    <NAME>        <DESIGNATION>          <MOBILE>");
						for (Employee employee : resList) {
							System.out.printf("%-8s%-14s%-23s%s\n", employee.getId(), employee.getName(), employee.getDesig(),
									employee.getMobile());
						}
						System.out.println();
					}else
						System.out.println("First Character must be Capital.");
				}catch (InputMismatchException e) {
					System.out.println("wrong input");
					scan.nextLine();
				}catch(EmployeeException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 0:
				System.out.println("Exiting System, Thank You ");
				try {
					eService.closeConnection();
				} catch (EmployeeException e) {
					System.out.println(e.getMessage());
				}

				scan.close();
				System.exit(0);
				break;
			default:
				System.out.println("Enter numbers from 0 to 4 Only !");
				break;
			}

		}

	}

	private int getChoice(Scanner scan) {
		int choice = -1;
		System.out.println("------Employee Management------");
		System.out.println("  1. Create Employee");
		System.out.println("  2. Update Employee");
		System.out.println("  3. Delete Employee");
		System.out.println("  4. Display Employee");
		System.out.println("  5. Search Employee");
		System.out.println("  0. Exit");
		System.out.println("-------------------------------");
		System.out.println("Enter your choice");
		try {
			choice = scan.nextInt();
		} catch (InputMismatchException e) {
			scan.nextLine();
		}

		return choice;
	}

	public static void main(String[] args) {
		new EmployeeMain().execute();
	}
}
