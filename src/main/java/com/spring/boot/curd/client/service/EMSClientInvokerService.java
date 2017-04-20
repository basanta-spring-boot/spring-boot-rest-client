package com.spring.boot.curd.client.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.curd.client.pojo.Employee;
import com.spring.boot.curd.client.pojo.InputRequest;
import com.spring.boot.curd.client.pojo.Response;
import com.spring.boot.curd.client.util.RuleUtils;

@Service
public class EMSClientInvokerService {

	private Logger log = LoggerFactory.getLogger(EMSClientInvokerService.class);

	@Autowired
	private RuleUtils ruleUtils;

	public Response addEmployee(InputRequest request) {
		Response response = new Response();
		String value = null;
		List<Employee> employees = request.getEmployees();
		try {
			for (Employee employee : employees) {
				response = ruleUtils.add(employee);
			}
			value = response.getStatus() + "" + employees.size();
			response.setStatus(value);
		} catch (Exception e) {
			log.info("Client Invoking Fail :" + e.getMessage());
		}
		return response;
	}

	public List<Employee> getAllEmployees() {
		List<Employee> employees = null;
		try {
			employees = ruleUtils.fetchAllEmployees();
		} catch (IOException e) {
			log.info("Client Invoking Failed :" + e.getLocalizedMessage());
		}
		return employees;
	}

	public Employee getEmployee(int id) {
		return ruleUtils.getEmployee(id);
	}

	public Response delete(int id) {
		return ruleUtils.deleteEmployee(id);
	}
}
