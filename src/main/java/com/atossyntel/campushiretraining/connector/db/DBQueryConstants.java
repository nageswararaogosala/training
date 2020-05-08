/**
 * 
 */
package com.atossyntel.campushiretraining.connector.db;

/**
 * @author ngosal461
 *
 */
public interface DBQueryConstants {	
	
	String SELECT_EMPLOYEES = "SELECT * FROM sakila.EMPLOYEE";
	
	String SELECT_EMPLOYEE = "SELECT * FROM sakila.EMPLOYEE WHERE EMPLOYEE_ID=? ";
	
	String INSERT_EMPLOYEE = "INSERT INTO sakila.EMPLOYEE (EMPLOYEE_ID,EMPLOYEE_NAME,MAIL,DESIGNATION,ROLE,LOCATION)"
			+ "VALUES(?,?,?,?,?,?)";
}
