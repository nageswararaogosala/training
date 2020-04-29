/**
 * 
 */
package com.atossyntel.training.connector.db;

import java.util.List;

import com.atossyntel.training.model.Employee;

/**
 * @author ngosal461
 *
 */
public interface DbInterface {

	List<Employee> getEmployees();
	
	Employee getEmployee(String empId);

	Long saveEmployee(Employee emp);
}
