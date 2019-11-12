# Author: romain.chevallier@econocom.com

@InterfaceName
Feature: [Interface_name] [Interface_workflow_name] - Functional tests

	@Local
		Scenario: Local test
			Given I open the "source.xml" file stored in the "inputFiles" directory 
			When I read the content
			Then my test is done
			
	@Non-regression-test
		Scenario: Non-regression test of [Interface_name] on the [Interface_workflow_name] workflow
			Given I take the reference input file named "ref_source.xml" from Source Repository
			When I put it in the Source Connection
			And I look in the Target Connection for the output file named "response_request" that should be provided within a maximum time of 60 sec 
			And I take the reference output file named "ref_target.xml" from Target Repository
			Then they should be the same
