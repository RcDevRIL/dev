package com.econocom.middleware.functionaltestsapi;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.econocom.middleware.functionaltestsapi.dto.FileDto.FileNameStructure;
import com.econocom.middleware.functionaltestsapi.dto.SftpDto;
import com.econocom.middleware.functionaltestsapi.ftp.SftpManagerService;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FunctionalTestsApiApplication.class})
public class SftpManagerServiceJUnitTest {
	
	@Autowired
	SftpManagerService sftpManagerService;
	
	 @Before
     public void setUp() throws IOException {
		 testUploadFileIntoSftpDirectory ();
     }
	
	private SftpDto fillSftpDto() {
		SftpDto sftpDto = new SftpDto();
		
		sftpDto.setSftpHost("sftp.server.adress");
		sftpDto.setSftpPasword("SFTP_PASSWORD");
		sftpDto.setSftpUser("SAP-JBOSSFUSE");
		sftpDto.setSftpRemoteDirectory("/DEV/functionalTestApi/in/");
		return sftpDto;
	}
	

	private void testUploadFileIntoSftpDirectory () throws IOException {
		
		ClassPathResource resource = new ClassPathResource("/inputFiles/request_03012019163074.xml");
		File file = resource.getFile();
		
		SftpDto sftpDto = fillSftpDto();
		sftpDto.setFile(file);
		
		try {
			sftpManagerService.uploadToSftpFiles(sftpDto);
		} catch (Exception e) {
			assertTrue(false);
		}

		assertTrue(true);
		
	}
	
	
	/**
	 * Test download
	 */
	@Test
	public void testDowloadFileFromSftpDirectory () {
		
		
		SftpDto sftpDto = fillSftpDto();
		sftpDto.setSftpFileName("request_03012019163074.xml");
		
		try {
			Optional<InputStream> downloadAndStoreSingleFile = sftpManagerService.downloadSingleFile(sftpDto);
			if (downloadAndStoreSingleFile.isPresent()) {
				assertTrue(true);	
			} else {
				assertTrue(false);
			}
			
			
		} catch (SftpException | IOException | JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testDowloadMotifFileFromSftpDirectory () {
		SftpDto sftpDto = fillSftpDto();
		sftpDto.setFileNameStructure(FileNameStructure.STATRT_WITH);
		sftpDto.setSftpFileName("request*.xml");
		try {
			Optional<InputStream> downloadAndStoreSingleFile = sftpManagerService.downloadSingleFile(sftpDto);
			assertTrue(downloadAndStoreSingleFile.get()!=null);
			
		} catch (SftpException | IOException | JSchException e) {			
			e.printStackTrace();
			assert(false);
		}
		
	}
	
	@Test
	public void testDowloadMotifHistoryFileFromSftpDirectory () {
		SftpDto sftpDto = fillSftpDto();
		sftpDto.setModificationDate(new Date());
		sftpDto.setFileNameStructure(FileNameStructure.STATRT_WITH);
		sftpDto.setSftpFileName("request*.xml");
		try {
			Set<String> setFiles = new HashSet<>();
			setFiles.add("file.xml");
			Optional<InputStream> downloadAndStoreSingleFile = sftpManagerService.downloadSingleFile(sftpDto, setFiles );
			assertTrue(downloadAndStoreSingleFile.get()!=null);
			
		} catch (SftpException | IOException | JSchException e) {			
			e.printStackTrace();
			assert(false);
		}
	}
	

	@Test
	public void testDowloandCron () {
		try {
			SftpDto fillSftpDto = fillSftpDto();
			fillSftpDto.setSftpFileName("request_03012019163074.xml");
			fillSftpDto.setFileNameStructure(FileNameStructure.FULL_NAME);
			Optional<InputStream> downloadFileCron = sftpManagerService.downloadFileCron(fillSftpDto, 3, 30);
			if (downloadFileCron.isPresent()) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	/**
	 * Delete file from sftp remote directory
	 */
	@Test
	public void testSftpDeleteSftpFile () {
		try {
			SftpDto fillSftpDto = fillSftpDto();
			fillSftpDto.setSftpFileName("request_03012019163074.xml");
			sftpManagerService.deleteSftpFile(fillSftpDto);
			assertTrue(true);
		} catch (Exception e) {e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	/**
	 * Test upload
	 * @throws IOException
	 */
	@Test
	public void testSftpDeleteDirectoryFiles () throws IOException {
		
//		ClassPathResource resource = new ClassPathResource("/inputFiles/request_03012019163074.xml");
//		File file = resource.getFile();
		
		SftpDto sftpDto = fillSftpDto();
		sftpDto.setSftpRemoteDirectory("/DEV/functionalTestApi/in/");
		
		try {
			sftpManagerService.deleteSftpRemoteDirectoryFiles(sftpDto);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}

		assertTrue(true);
		
	}
	


}
