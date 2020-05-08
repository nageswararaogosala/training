/**
 * 
 */
package com.atossyntel.campushiretraining.connector.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.atossyntel.campushiretraining.model.Employee;

/**
 * @author ngosal461
 *
 */
@Component
public class EmployeeDBConnector {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeDBConnector.class);

	@Autowired
	private EmployeeDBJDBCTemplate empDBJdbcTemplate;
	
	public List<Employee> getEmpDetails() throws DataAccessException {
		LOG.debug("Executing SQL:[" + DBQueryConstants.SELECT_EMPLOYEES + "] ");
		return empDBJdbcTemplate.getJdbcTemplate().query(DBQueryConstants.SELECT_EMPLOYEES,
				new Object[] {}, new ResultSetExtractor<List<Employee>>() {
					@Override
					public List<Employee> extractData(ResultSet rs) {
						List<Employee> list = new ArrayList<Employee>();
						try {
							while (rs.next()) {
								Employee emp = new Employee();
								emp.setDesignation(rs.getString("DESIGNATION"));																
								emp.setEmployee_id(rs.getString("EMPLOYEE_ID"));
								emp.setEmployee_name(rs.getString("EMPLOYEE_NAME"));	
								emp.setLocation(rs.getString("LOCATION"));
								emp.setMail(rs.getString("MAIL"));		
								emp.setRole(rs.getString("ROLE"));
								list.add(emp);
							}
						} catch (EmptyResultDataAccessException e) {
							LOG.info(" >>>>>>>>>>>>No data found >>>>>>>>>>>>" + e);
							return null;
						} catch (Exception e) {
							LOG.warn(">>>>>>>>>>Error while calling oracle table EMPLOYEE >>>>>>>>>>>" + e);
							return null;
						}
						return list;
					}
				});
	}
	
	public Employee getEmpDetails(String empId) throws DataAccessException {
		LOG.debug("Executing SQL:[" + DBQueryConstants.SELECT_EMPLOYEE + "] ");
		return empDBJdbcTemplate.getJdbcTemplate().query(DBQueryConstants.SELECT_EMPLOYEE,
				new Object[] {empId}, new ResultSetExtractor<Employee>() {
					@Override
					public Employee extractData(ResultSet rs) {	
						Employee emp = null;
						try {
							while (rs.next()) {
								emp = new Employee();
								emp.setDesignation(rs.getString("DESIGNATION"));																
								emp.setEmployee_id(rs.getString("EMPLOYEE_ID"));
								emp.setEmployee_name(rs.getString("EMPLOYEE_NAME"));	
								emp.setLocation(rs.getString("LOCATION"));
								emp.setMail(rs.getString("MAIL"));		
								emp.setRole(rs.getString("ROLE"));								
							}
						} catch (EmptyResultDataAccessException e) {
							LOG.info(" >>>>>>>>>>>>No data found >>>>>>>>>>>>" + e);
							return null;
						} catch (Exception e) {
							LOG.warn(">>>>>>>>>>Error while calling oracle table EMPLOYEE >>>>>>>>>>>" + e);
							return null;
						}
						return emp;
					}
				});
	}
	
	public Long saveEmployee(Employee emp)throws DataAccessException {
		LOG.debug("Executing SQL:[" + DBQueryConstants.INSERT_EMPLOYEE + "] ");
		Long id = null;
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(DBQueryConstants.INSERT_EMPLOYEE,
						//new String[] { "EMPLOYEE_ID" });
						new String[] {});
				
				ps.setString(1, emp.getEmployee_id());
				ps.setString(2, emp.getEmployee_name());
				ps.setString(3,emp.getMail());
				ps.setString(4, emp.getDesignation());
				ps.setString(5, emp.getRole());
				ps.setString(6, emp.getLocation());
				return ps;
			}
		};

		// get generated key
		final KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		LOG.debug("Executing SQL:[" + DBQueryConstants.INSERT_EMPLOYEE + "] with params:"
				+ emp.getEmployee_id());
		//int count =  empDBJdbcTemplate.getJdbcTemplate().update(psc, generatedKeyHolder);
		int count =  empDBJdbcTemplate.getJdbcTemplate().update(psc);
		//id = generatedKeyHolder.getKey().longValue();
		if(count==0){
			LOG.debug("No records are inserted in EMPLOYEE table-> " + count);
		}
		else{
			LOG.debug("Number of records inserted in EMPLOYEE table-> " + count);
			LOG.debug("The following record inserted in EMPLOYEE table-> " + id);
		}		
		return id;
	}
	
	private Calendar getCurrentDateEST() {
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		return currentDate;
	}
	
	
}
