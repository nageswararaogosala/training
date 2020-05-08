/**
 * 
 */
package com.atossyntel.campushiretraining.connector.db;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atossyntel.campushiretraining.model.Employee;

/**
 * @author ngosal461
 *
 */
@Component("OracleDbImpl")
public class OracleDbImpl implements DbInterface {

    private static final Logger LOG = LoggerFactory.getLogger(OracleDbImpl.class);
    
    @Autowired
    private EmployeeDBConnector empDBConnector;

	@Override
	public List<Employee> getEmployees() {
		LOG.debug(" getEmployees ");
		return empDBConnector.getEmpDetails();
	}
	
	@Override
	public Employee getEmployee(String empId) {
		LOG.debug(" getEmployee ");
		return empDBConnector.getEmpDetails(empId);
	}
	
	@Override
	public Long saveEmployee(Employee emp) {
		LOG.debug(" saveEmployee ");
		return empDBConnector.saveEmployee(emp);
	}

}
