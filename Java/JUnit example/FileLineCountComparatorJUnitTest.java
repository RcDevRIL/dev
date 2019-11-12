package com.econocom.middleware.functionaltestsapi;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.econocom.middleware.functionaltestsapi.comparators.FileLineCountComparator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { FunctionalTestsApiApplication.class })
public class FileLineCountComparatorJUnitTest {
	
	@Autowired
	private FileLineCountComparator fileLineCountComparator;
	@Test
	public void compareFile() throws IOException {
		ClassPathResource resource = new ClassPathResource("/inputFiles/request_03012019163074.xml");
		ClassPathResource resource2 = new ClassPathResource("/inputFiles/request_03012019163074.xml");
		Boolean result = fileLineCountComparator.compareFiles(resource.getFile(), resource2.getFile());
		assertTrue(result);
	}

}
