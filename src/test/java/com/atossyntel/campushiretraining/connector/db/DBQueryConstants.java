/**
 * 
 */
package com.atossyntel.campushiretraining.connector.db;

/**
 * @author ngosal461
 *
 */
public interface DBQueryConstants {	
	
	String SELECT_EMPLOYEES = "SELECT * FROM AMSOWNER.EMPLOYEE";
	
	String SELECT_EMPLOYEE = "SELECT * FROM AMSOWNER.EMPLOYEE WHERE EMPLOYEE_ID=? ";
	
	String INSERT_EMPLOYEE = "INSERT INTO AMSOWNER.EMPLOYEE (EMPLOYEE_ID,EMPLOYEE_NAME,MAIL,DESIGNATION,ROLE,LOCATION,TXN_DATE)"
			+ "VALUES(EMPLOYEE_ID_SEQ.NEXTVAL,?,?,?,?,?,SYSDATE)";
}
