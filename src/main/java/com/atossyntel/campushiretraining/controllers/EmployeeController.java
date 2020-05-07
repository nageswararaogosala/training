
/**
 * 
 */
package com.atossyntel.campushiretraining.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atossyntel.campushiretraining.connector.db.OracleDbImpl;
import com.atossyntel.campushiretraining.model.Employee;

/**
 * @author ngosal461
 *
 */
@RestController
public class EmployeeController {
	
	@Autowired
	private OracleDbImpl dbFactory;
	
	
	
	@RequestMapping(method={RequestMethod.GET}, value="/api/atossyntel/v1/employee", produces=MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Object> getp2pStatusWithoutRules() {
		  List<Employee> objectList = dbFactory.getEmployees();
		  return new ResponseEntity<Object>(objectList, HttpStatus.OK);
	  }

	@RequestMapping(method={RequestMethod.GET}, value="/api/atossyntel/v1/employee/{employeeId}", produces=MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Object> getEmployee(@PathVariable String employeeId) {
		 Employee emp = dbFactory.getEmployee(employeeId);
		  return new ResponseEntity<Object>(emp, HttpStatus.OK);
	  }
	
	@RequestMapping(method = {
			RequestMethod.PUT }, value = "/api/atossyntel/v1/employee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> saveEmployee(@RequestBody Employee request) {
		Long var = dbFactory.saveEmployee(request);
		return new ResponseEntity<Long>(var, HttpStatus.OK);
	}
}
