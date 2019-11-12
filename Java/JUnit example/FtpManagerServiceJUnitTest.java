package com.econocom.middleware.functionaltestsapi;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.econocom.middleware.functionaltestsapi.dto.FileDto.FileNameStructure;
import com.econocom.middleware.functionaltestsapi.dto.FtpDto;
import com.econocom.middleware.functionaltestsapi.ftp.FtpManagerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FunctionalTestsApiApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FtpManagerServiceJUnitTest {
	
	@Autowired
	FtpManagerService ftpManagerService;
	
	
	private FtpDto fillftpDto() {
		FtpDto ftpDto = new FtpDto();
		
		ftpDto.setFtpHost("ftp.server.com");
		ftpDto.setFtpPasword("FTP_PASSWORD");
		ftpDto.setFtpUser("ECO_DEV0048");
		ftpDto.setFtpRemoteDirectory("/FtpDirectory/TEST/");
		ftpDto.setFtpServerPort(21);
		ftpDto.setIsFtpSecure(false);
		return ftpDto;
	}
	
	@Test
	public void testAOpenConnection () throws IOException {
		FTPClient conn = ftpManagerService.openConnectionAndLoginFtpServer(fillftpDto());
		assertTrue(conn != null);
	}
	
	
	/**
	 * Test upload
	 * @throws IOException
	 */
	@Test
	public void testBUploadFileIntoFtpDirectory () throws IOException {
		
		ClassPathResource resource = new ClassPathResource("/inputFiles/request_03012019163074.xml");
		ClassPathResource resource2 = new ClassPathResource("/inputFiles/request_03012019163088.xml");
		File file = resource.getFile();
		File file2 = resource2.getFile();
		
		
		FtpDto ftpDto = fillftpDto();
		ftpDto.setFtpRemoteDirectory("/FtpDirectory/TEST/");
		ftpDto.setFile(file);
		
		FtpDto ftpDto2 = fillftpDto();
		ftpDto2.setFtpRemoteDirectory("/FtpDirectory/TEST/");
		ftpDto2.setFile(file2);
		
		
		
		try {
			ftpManagerService.uploadFile(ftpDto);
			ftpManagerService.uploadFile(ftpDto2);
			assertTrue(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}

		assertTrue(true);
		
	}
	
	
	/**
	 * Test download
	 */
	@Test
	public void testCDowloadFileFromSftpDirectory () {
		
		
		FtpDto ftpDto = fillftpDto();
							   
		ftpDto.setFtpFileName("request_03012019163074.xml");
		ftpDto.setFtpRemoteDirectory("/FtpDirectory/TEST/");
		
		try {
			Optional<InputStream> downloadAndStoreSingleFile = ftpManagerService.downloadRemoteFile(ftpDto);
			assertTrue (downloadAndStoreSingleFile.isPresent()) ;
			
			
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testCDowloadMotifFileFromSftpDirectory () {
		
		
		FtpDto ftpDto = fillftpDto();
							   
		ftpDto.setFtpFileName("request*.xml");
		ftpDto.setFtpRemoteDirectory("/FtpDirectory/TEST/");
		ftpDto.setFileNameStructure(FileNameStructure.STATRT_WITH);
		
		try {
			Optional<InputStream> downloadAndStoreSingleFile = ftpManagerService.downloadRemoteFile(ftpDto);
			assertTrue (downloadAndStoreSingleFile.isPresent()) ;
			
			
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testDowloandCron () {
		try {
			FtpDto fillSftpDto = fillftpDto();
			fillSftpDto.setFtpFileName("request_03012019163074.xml");
			fillSftpDto.setFtpRemoteDirectory("/FtpDirectory/TEST/");
			
			Optional<InputStream> downloadFileCron = ftpManagerService.downloadFileCron(fillSftpDto, 3, 30);
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
	public void testFDeleteSftpFile () {
		try {
			FtpDto fillSftpDto = fillftpDto();
			fillSftpDto.setFtpFileName("request_03012019163074.xml");
			fillSftpDto.setFtpRemoteDirectory("/FtpDirectory/TEST/");
			assertTrue(ftpManagerService.deleteFtpFile(fillSftpDto));
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	/**
	 * list files directory
	 */
	@Test
	public void testEListSftpFile () {
		try {
			FtpDto fillSftpDto = fillftpDto();
//			fillSftpDto.setFtpFileName("/request_03012019163088.xml");
			fillSftpDto.setFtpRemoteDirectory("/FtpDirectory/TEST/");
			List<String> listFtpDirectoryFiles = ftpManagerService.listFtpDirectoryFiles(fillSftpDto);
			assertTrue(listFtpDirectoryFiles.size() != 0);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	/**
	 * list files directory
	 */
	@Test
	public void deleteGFtpFilesDirectory () {
		try {
			FtpDto fillSftpDto = fillftpDto();
			// fillSftpDto.setFtpRemoteDirectory("/FtpDirectory/TEST/");
			fillSftpDto.setFileNameStructure(FileNameStructure.END_WITH);
			fillSftpDto.setFtpFileName("*.xml");
			
			
			
			boolean delete = ftpManagerService.deleteFtpFilesDirectory(fillSftpDto);
			assertTrue(delete);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@After
	public void disconnect() throws IOException {
		ftpManagerService.close();
	}
	
}
