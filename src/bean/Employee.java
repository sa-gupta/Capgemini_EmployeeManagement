package com.cg.bean;

public class Employee {
	private int id;
	private String name;
	private String desig;
	private long mobile;
	
	
	public Employee(int id, String name, String desig, long mobile) {
		super();
		this.id = id;
		this.name = name;
		this.desig = desig;
		this.mobile = mobile;
	}
	public Employee() {
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesig() {
		return desig;
	}
	public void setDesig(String desig) {
		this.desig = desig;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", desig=" + desig + ", mobile=" + mobile + "]";
	}	
}

