package com.econocom.middleware.functionaltestsapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.econocom.middleware.functionaltestsapi.dto.CompatarorTypeEnum;
import com.econocom.middleware.functionaltestsapi.dto.FileDto;
import com.econocom.middleware.functionaltestsapi.files.FileManagerService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { FunctionalTestsApiApplication.class })
public class FileManagerServiceJUnitTest {

	@Autowired
	private FileManagerService fileManagerService;

	@Before
	public void createDirectory() throws IOException {
		String testFile = "/tmp/test/test2";
		if (!Files.exists(Paths.get(testFile))) {
			Files.createDirectories(Paths.get(testFile));
			Files.createFile(Paths.get(testFile + "test.txt"));
		}
	}

	private FileDto fillFileDto() {

		FileDto dto = new FileDto();

		dto.setFileName("testStoreContentInLocalFile.xml");
		dto.setLocalStoragePath("/tmp/test/api");
		dto.setInputStream(new ByteArrayInputStream("this is a dummy text test".getBytes(StandardCharsets.UTF_8)));

		return dto;
	}

	@Test
	public void testStoreContentInLocalFileZZ() {

		FileDto filledFileDto = fillFileDto();
		fileManagerService.storeLocalFile(filledFileDto);
		assertTrue(filledFileDto.getFile().exists());

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStoreErrorContentInLocalFileZZ() {

		FileDto filledFileDto = null;
		fileManagerService.storeLocalFile(filledFileDto);

	}

	@Test
	public void testDeleteFiles() {
		
		FileDto filledFileDto = fillFileDto();
		
		fileManagerService.deleteLocalDirectoryFiles(new File(filledFileDto.getFile().getParent()));
		assertTrue(true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDeleteFilesError() {

		File dirToDelete = null;
		fileManagerService.deleteLocalDirectoryFiles(dirToDelete);
		assertTrue(true);
	}

	@Test
	public void testReadFileAsStringList() throws IOException {
		ClassPathResource resource = new ClassPathResource("/inputFiles/request_03012019163074.xml");
		File file = resource.getFile();

		List<String> readContentFileAsStringList = fileManagerService.readContentFileAsStringList(file);
		assertTrue(readContentFileAsStringList.size() > 0);

	}

	@Test
	public void testReadFileAsString() throws IOException {
		ClassPathResource resource = new ClassPathResource("/inputFiles/request_03012019163074.xml");
		File file = resource.getFile();

		String readContentFileAsStringList = fileManagerService.readContentFileAsString(file, true);
		assertTrue(readContentFileAsStringList.length() > 0);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testCompareFiles_NoComparaisonTypeDefined() throws IOException {
		ClassPathResource resource = new ClassPathResource("/inputFiles/request_03012019163074.xml");
		File file1 = resource.getFile();

		ClassPathResource resource2 = new ClassPathResource("/inputFiles/request_03012019163075.xml");
		File file2 = resource2.getFile();
		Boolean readContentFileAsStringList = fileManagerService.compareFilesContent(file1, file2, null);
		assertTrue(readContentFileAsStringList);
	}

	@Test
	public void testCompareFiles_Similar() throws IOException {
		ClassPathResource resource = new ClassPathResource("/inputFiles/request_03012019163075.xml");
		File file1 = resource.getFile();

		ClassPathResource resource2 = new ClassPathResource("/inputFiles/request_03012019163075.xml");
		File file2 = resource2.getFile();

		Boolean readContentFileAsStringList = fileManagerService.compareFilesContent(file1, file2,
				CompatarorTypeEnum.STRING_COMPARATOR);
		assertTrue(readContentFileAsStringList);

	}

	@Test
	public void testCompareFiles_differents() throws IOException {
		ClassPathResource resource = new ClassPathResource("/inputFiles/request_03012019163074.xml");
		File file1 = resource.getFile();

		ClassPathResource resource2 = new ClassPathResource("/inputFiles/request_03012019163076.xml");
		File file2 = resource2.getFile();

		Boolean readContentFileAsStringList = fileManagerService.compareFilesContent(file1, file2,
				CompatarorTypeEnum.STRING_COMPARATOR);
		assertFalse(readContentFileAsStringList);

	}
	@Test
	public void testComparecsv() throws IOException {
		ClassPathResource resource = new ClassPathResource("/inputFiles/GRtest.csv");
		
		ClassPathResource resource2 = new ClassPathResource("/inputFiles/GRtest.csv");

		String csv1 = getStringFromStream(resource.getInputStream());
		String csv2 = getStringFromStream(resource2.getInputStream());
		
		String result =  fileManagerService.compareCsv(csv1, csv2, ";", true);
		assertEquals("", result);

	}
	@Test
	public void testNotSimilarComparecsv() throws IOException {
		ClassPathResource resource = new ClassPathResource("/inputFiles/GRtest.csv");
		
		ClassPathResource resource2 = new ClassPathResource("/inputFiles/GRtest2.csv");

		String csv1 = getStringFromStream(resource.getInputStream());
		String csv2 = getStringFromStream(resource2.getInputStream());
		
		String result =  fileManagerService.compareCsv(csv1, csv2, ";", true);
		assertTrue(result.length()>0);

	}

	@Test
	public void removeTagInFile_tag_exist() throws IOException {
		ClassPathResource resource = new ClassPathResource("/inputFiles/request_03012019163074.xml");
		File file = resource.getFile();
		fileManagerService.removeTagInFile(file, "Guid");
		assertNotNull(file);
	}

	@Test 
	public void compareFileEquals() throws Exception {
		ClassPathResource resource1 = new ClassPathResource("/inputFiles/GRTest.csv");
		ClassPathResource resource2 = new ClassPathResource("/inputFiles/GRTest.csv");
		 Boolean result = fileManagerService.compareCsvFile(resource1.getFile(), resource2.getFile());
		 assertTrue(result);

	}

	@Test
	public void deleteFolder() throws Exception {
		boolean result = fileManagerService.deleteFolder("/tmp/test");
		assertTrue(result);
	}

	@Test
	public void createFileFromString() throws Exception {
		String test = "Je suis le test";
		File result = fileManagerService.convertStringToFile(test, "test", ".txt");
		System.out.println(result.getAbsolutePath());
		System.out.println(result.getName());

		assertTrue(result.exists());
		result.delete();
	}
	
	@Test
	public void testValideFile() {
		boolean result = fileManagerService.isValidFileName("test.xml");
		assertTrue(result);
	}
	@Test
	public void testInValideFile() {
		boolean result = fileManagerService.isValidFileName("..");
		assertFalse(result);
	}
	
	@Test
	public void testCompareFilesContent() {
		try {
			ClassPathResource resource = new ClassPathResource("/inputFiles/request_03012019163075.xml");
			File file1 = resource.getFile();

			ClassPathResource resource2 = new ClassPathResource("/inputFiles/request_03012019163075.xml");
			File file2 = resource2.getFile();
			InputStream in1 = fileManagerService.convertFileToInputStream(file1);
			InputStream in2 = fileManagerService.convertFileToInputStream(file2);
			Boolean readContentFileAsStringList;

			readContentFileAsStringList = fileManagerService.compareFilesContent(in1, in2);
			assertTrue(readContentFileAsStringList);
		} catch (IOException e) {
			assert (false);
			e.printStackTrace();
		}

	}
	@Test(expected = IllegalArgumentException.class)
	public void testConvertFileToInputStream() throws FileNotFoundException {
		fileManagerService.convertFileToInputStream(null );
	}
	@Test
	public void testCreateTempFileFromStringContent() throws IOException {
		try {
		File result = fileManagerService.createTempFileFromStringContent("content", "test", ".txt");
		
		assert Pattern.matches("test.*.txt",result.getName());
		
		} catch (IOException e) {
			assert (false);
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testConvertInputStreamToFile() {
		ClassPathResource resource = new ClassPathResource("/inputFiles/request_03012019163075.xml");
		File result;
		try {
			result = fileManagerService.convertInputStreamToFile(resource.getInputStream(), "test", ".xml");
			assert (Pattern.matches("test.*.xml",result.getName()));
		} catch (IOException e) {
			assert (false);
			e.printStackTrace();
		}

	}

	
	protected String getStringFromStream (InputStream myStream) throws IOException {
		StringWriter writer1 = new StringWriter();
		IOUtils.copy(myStream,writer1, "UTF-8");
		String s = writer1.toString();
		 if (s.startsWith("\uFEFF")) {
		        s = s.substring(1);
		    }
		    return s;
	}
	
	
	
}
