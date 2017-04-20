package com.spring.boot.curd.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.spring.boot.curd.client.pojo.Employee;
import com.spring.boot.curd.client.pojo.InputRequest;
import com.spring.boot.curd.client.pojo.Response;
import com.spring.boot.curd.client.service.EMSClientInvokerService;

@RestController
public class EMSController {
	@Autowired
	private EMSClientInvokerService service;

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public Response addEmployee(@RequestBody InputRequest request) {
		return service.addEmployee(request);
	}

	@RequestMapping(value = "/getEmployees", method = RequestMethod.GET)
	public List<Employee> fetchAllEmployees() {
		return service.getAllEmployees();
	}

	@RequestMapping(value = "/getEmployee/{id}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable("id") int id) {
		return service.getEmployee(id);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Response deleteEmployee(@PathVariable("id") int id) {
		return service.delete(id);
	}
}
