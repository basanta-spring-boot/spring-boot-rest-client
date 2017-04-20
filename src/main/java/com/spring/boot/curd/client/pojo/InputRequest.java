package com.spring.boot.curd.client.pojo;

import java.util.List;

public class InputRequest {

	private List<Employee> employees;

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
