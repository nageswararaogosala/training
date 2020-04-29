/**
 * 
 */
package com.atossyntel.training.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import com.atossyntel.training.connector.db.EmployeeDBJDBCTemplate;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author ngosal461
 *
 */
@Configuration
public class PropertiesConfiguration {

	private static final Logger log = LoggerFactory.getLogger(PropertiesConfiguration.class);
	
	@Autowired
    private Environment env;
	
	
	@Bean(name = "EmployeeDao")
	public EmployeeDBJDBCTemplate EmployeeDao() {
		EmployeeDBJDBCTemplate obj = new EmployeeDBJDBCTemplate();
		obj.setDataSource(employeeDataSource());
	     try{
		 obj.getJdbcTemplate().execute("select 1 from dual");
		 log.info("EmployeeDao Database sucessfully connected. ");
	}catch (Exception e) {
			log.error(" Error while connecting to EmployeeDao.", e);
			e.printStackTrace();
		}
		return obj;
	}
	
	@Bean(name = { "core.commons.employee.DataSource" })
	@ConfigurationProperties(prefix = "datasource.employee")
	@Primary
	public DataSource employeeDataSource() {
		HikariDataSource ds = new HikariDataSource();		
		
		ds.setDriverClassName(env.getProperty("datasource.employee.driverClassName"));			
		ds.setUsername(env.getProperty("datasource.employee.username"));
		ds.setPassword(env.getProperty("datasource.employee.password"));
		
		if(env.getProperty("datasource.employee.max-active")!=null)		
		ds.setMaximumPoolSize(Integer.valueOf(env.getProperty("datasource.employee.max-active")));
		
		if(env.getProperty(env.getProperty("datasource.employee.min-idle"))!=null)
		ds.setMinimumIdle(Integer.valueOf(env.getProperty("datasource.employee.min-idle")));
		
		ds.setConnectionInitSql(env.getProperty("datasource.employee.validation-query"));
		ds.setJdbcUrl(env.getProperty("datasource.employee.url"));		
		ds.addDataSourceProperty("v$session.machine", env.getProperty("datasource.employee.machine"));
		ds.addDataSourceProperty("v$session.program", env.getProperty("datasource.employee.program"));
		ds.addDataSourceProperty("v$session.osuser", env.getProperty("datasource.employee.osuser"));		
		//The following jdbc timeouts will enable applications clear stale or hung database connections due to network issues.
		ds.addDataSourceProperty("oracle.jdbc.ReadTimeout", env.getProperty("datasource.employee.read-timeout"));
		ds.addDataSourceProperty("oracle.net.CONNECT_TIMEOUT", env.getProperty("datasource.employee.connection-timeout"));
		return ds;
	}
}
