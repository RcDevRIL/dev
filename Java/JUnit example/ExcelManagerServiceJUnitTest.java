package com.econocom.middleware.functionaltestsapi;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.econocom.middleware.functionaltestsapi.files.excel.ExcelManagerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FunctionalTestsApiApplication.class})
public class ExcelManagerServiceJUnitTest {

	
	@Autowired
	private ExcelManagerService excelManagerService;
	
	@Test
	public void testGetRowContent() throws IOException {
		ClassPathResource r = new ClassPathResource("/inputFiles/ref_source_500lignes.xlsx");
		File f = r.getFile();
		
		String row = excelManagerService.getRow(50, f);
		System.out.println(row);
		
		assertTrue(row.length()>0);
	}
}
