package com.spring.boot.curd.client.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.curd.client.commons.Constants;
import com.spring.boot.curd.client.pojo.Employee;
import com.spring.boot.curd.client.pojo.Response;

@Component
@PropertySource(value = "classpath:ems_api.properties")
public class RuleUtils {

	
	@Autowired
	private Environment environment;

	private RestTemplate template;

	ObjectMapper mapper = null;
	HttpHeaders headers = null;

	@PostConstruct
	public void init() {
		template = new RestTemplate();
		mapper = new ObjectMapper();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		template.getMessageConverters().add(
				new MappingJackson2HttpMessageConverter());
		template.getMessageConverters().add(new StringHttpMessageConverter());
	}

	public Response add(Employee employee) throws IOException {
		String URL = environment.getProperty(Constants.ADD_EMPLOYEE);
		Response response = null;
		String input = mapper.writeValueAsString(employee);
		HttpEntity<String> entity = new HttpEntity<String>(input, headers);
		String results = template.postForObject(URL, entity, String.class);
		response = mapper.readValue(results, Response.class);
		return response;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> fetchAllEmployees() throws JsonParseException,
			JsonMappingException, IOException {
		String URL = environment.getProperty(Constants.GET_ALL_EMPLOYEE);
		List<Employee> employees = null;
		String result = template.getForObject(URL, String.class);
		employees = mapper.readValue(result, ArrayList.class);
		return employees;
	}

	public Employee getEmployee(int id) {
		Employee employee = null;
		String URL = environment.getProperty(Constants.GET_EMPLOYEE_BY_ID)
				+ "/{id}";
		Map<String, String> params = new HashMap<>();
		params.put("id", String.valueOf(id));
		employee = template.getForObject(URL, Employee.class, params);
		return employee;
	}

	public Response deleteEmployee(int id) {
		String URL = environment.getProperty(Constants.DELETE_EMPLOYEE)
				+ "/{id}";
		Map<String, String> params = new HashMap<>();
		params.put("id", String.valueOf(id));
		template.delete(URL, params);
		return new Response(true, "Deleted Successfully");
	}
}
