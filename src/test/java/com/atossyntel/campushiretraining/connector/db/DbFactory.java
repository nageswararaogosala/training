/**
 * 
 */
package com.atossyntel.campushiretraining.connector.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author ngosal461
 *
 */
@Component
public class DbFactory {
	
	 private static final Logger LOG = LoggerFactory.getLogger(DbFactory.class);

	@Autowired
    @Qualifier("OracleDbImpl")
    private DbInterface oracleDbImpl;
	
	@Autowired
    private Environment env;
	
	 public DbInterface getInstance(String property) {
	        if (env.getProperty(property)!=null && env.getProperty(property).equalsIgnoreCase("true")) {
	            LOG.debug("couchbase call for property -> ...."+property);
	            //return (DbInterface) couchbaseDbImpl;
	            return null;
	        } else {
	            LOG.debug("Oracle call for property -> ...."+property);
	            return (DbInterface) oracleDbImpl;
	        }
	    }
}
