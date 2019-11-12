package com.econocom.middleware.functionaltestsapi;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.econocom.middleware.functionaltestsapi.dto.FtpDto;
import com.econocom.middleware.functionaltestsapi.ftp.FtpManagerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FunctionalTestsApiApplication.class})
public class FtpsManagerServiceJUnitTest {
	
	@Autowired
	FtpManagerService ftpManagerService;
	
	private FtpDto fillFtpDto() {
		FtpDto ftpsDto = new FtpDto();
		
		ftpsDto.setFtpHost("FTPS.SERVER.ADRESS");
		ftpsDto.setFtpUser("Jboss-DEV");
		ftpsDto.setFtpPasword("FTPS_PASSWORD");
		ftpsDto.setFtpRemoteDirectory("/FtpDirectory/ATLAS_CATALOG/");
		ftpsDto.setFtpServerPort(21);
		ftpsDto.setIsFtpSecure(true);
		return ftpsDto;
	}
	
	@Test
	public void testOpenConnection () throws IOException {
		FTPClient conn = ftpManagerService.openConnectionAndLoginFtpServer(fillFtpDto());
		assertTrue(conn != null && conn.isConnected());
	}
	
	
	/**
	 * Test upload
	 * @throws IOException
	 */
	@Test
	public void testUploadFileIntoFtpsDirectory () throws IOException {
		
		ClassPathResource resource = new ClassPathResource("/inputFiles/request_03012019163074.xml");
		File file = resource.getFile();
		
		FtpDto ftpsDto = fillFtpDto();
		ftpsDto.setFile(file);
		
		try {
			ftpManagerService.uploadFile(ftpsDto);
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
	public void testDowloadFileFromSftpsDirectory () {
		
		
		FtpDto ftpsDto = fillFtpDto();
							   
		ftpsDto.setFtpFileName("request_03012019163074.xml");
		ftpsDto.setFtpRemoteDirectory("/FtpDirectory/ATLAS_CATALOG/");
		
		try {
			Optional<InputStream> downloadAndStoreSingleFile = ftpManagerService.downloadRemoteFile(ftpsDto);
			assertTrue(downloadAndStoreSingleFile.isPresent());	
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void testDowloandCron () {
		try {
			FtpDto fillSftpDto = fillFtpDto();
			fillSftpDto.setFtpFileName("request_03012019163074.xml");
			fillSftpDto.setFtpRemoteDirectory("/FtpDirectory/ATLAS_CATALOG/");
			
			Optional<InputStream> downloadFileCron = ftpManagerService.downloadFileCron(fillSftpDto, 5, 60);
			assertTrue (downloadFileCron.isPresent());
		} catch (InterruptedException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	/**
	 * Delete file from sftp remote directory
	 */
	@Test
	public void testDeleteSftpFile () {
		try {
			FtpDto fillSftpDto = fillFtpDto();
			fillSftpDto.setFtpFileName("request_03012019163074.xml");
			fillSftpDto.setFtpRemoteDirectory("/FtpDirectory/ATLAS_CATALOG/");
			assertTrue(ftpManagerService.deleteFtpFile(fillSftpDto));
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


}
