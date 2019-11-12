package com.econocom.middleware.functionaltests;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Test runner that trigger cucumber features test execution
 * 
 * @author romain.chevallier@econocom.com
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"classpath:cucumber/features/NonRegTest_en.feature",}, 
		glue = "com.econocom.middleware.functionaltests.impl", 
		monochrome = true, 
		plugin = {
				"pretty", "html:target/interfaceName-func_tests-report",
				"junit:target/interfaceName-func_tests-report.xml",
				"json:target/interfaceName-func_tests-report.json"}, 
		tags = {"@InterfaceName"}) /*skipped tests = "not @tag"
								 * done tests = "@tag", 
								 * if you want to perform ALL test scenarios, please use the @Middleware tag
								 * 
								 * https://cucumber.io/docs/cucumber/api/#tag-expressions
								 */
public class InterfaceNameFuncTestRunnerTest {}
