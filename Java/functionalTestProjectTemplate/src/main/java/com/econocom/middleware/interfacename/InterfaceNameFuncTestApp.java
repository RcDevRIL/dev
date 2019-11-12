package com.econocom.middleware.interfacename;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

/**
 * Main class of Spring app
 * 
 * 	(Not used for now, but may be useful for some projects)
 * 
 * @author romain.chevallier@econocom.com
 *
 */
@IntegrationComponentScan
@EnableIntegration
@Configuration
public class InterfaceNameFuncTestApp {

	public static void main(String[] args) {
		//not used for now, but may be useful for some projects. Feel free to delete the "main" package if this is only a Cucumber project.
	}

}
