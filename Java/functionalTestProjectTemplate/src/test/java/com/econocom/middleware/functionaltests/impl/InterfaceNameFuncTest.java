package com.econocom.middleware.functionaltests.impl;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.econocom.middleware.functionaltests.SkipFlag;
import com.econocom.middleware.functionaltestsapi.FunctionalTestsApiApplication;
import com.econocom.middleware.functionaltestsapi.dto.FileDto.FileNameStructure;
import com.econocom.middleware.functionaltestsapi.dto.FtpDto;
import com.econocom.middleware.functionaltestsapi.dto.JdbcDto;
import com.econocom.middleware.functionaltestsapi.files.FileManagerService;
import com.econocom.middleware.functionaltestsapi.ftp.FtpManagerService;
import com.econocom.middleware.functionaltestsapi.jdbc.JdbcService;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Class handling non-regression test with Cucumber.
 * 
 * @author romain.chevallier@econocom.com
 * 
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = { "com.econocom.middleware.functionaltestsapi" })
@Import(FunctionalTestsApiApplication.class)
@SpringIntegrationTest
@TestPropertySource("/interfaceNameFuncTest.properties")
@ContextConfiguration(locations = { "classpath:META-INF/Spring/spring-context.xml" })
public class InterfaceNameFuncTest {
	/**
	 * private enum to store possible errors and their description.
	 */
	private enum InterfaceNameErrorCode {
		OK("OK"), ERR000("Unknown error"), REG("Regression found");

		private String description = "";

		InterfaceNameErrorCode(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return this.name();
		}

		public String toDeepString() {
			return this.description;
		}
	}
	/**
	 * Class logger used to log things to console. Please refer to our specification
	 * document about how to log properly.
	 */
	private final Logger log = LoggerFactory.getLogger(InterfaceNameFuncTest.class);
	/**
	 * Class global variables workaround to share these between test steps...
	 */
	private HashMap<String, File> bufMap;// variable to store tmp files so we can share between steps
	private static final SimpleDateFormat date = new SimpleDateFormat("ddMMyyyyHHmmSS");
	/**
	 * Autowired dependencies, please remove unnecessary imports
	 */
	/**
	 * Provide some methods for file/directory manipulations
	 */
	@Autowired
	private FileManagerService fManagerService;
	/**
	 * Allow FTP Connection with remote server for basic tasks
	 */
	@Autowired
	private FtpManagerService ftpManagerService;
	/**
	 * Allow JDBC connection to do database basic tasks
	 */
	@Autowired
	private JdbcService jdbcService;
	/**
	 * Remote servers communication Fields values, please remove unnecessary fields
	 */
	/**
	 * SFTP fields
	 */
	@Value("${sftp.inbound.host}")
	private String sftpHost;

	@Value("${sftp.inbound.port}")
	private int sftpPort;

	@Value("${sftp.inbound.user}")
	private String sftpUser;

	@Value("${sftp.inbound.password}")
	private String sftpPassword; // /!\ passwords must be set in interfaceNameFuncTest.properties files
	/**
	 * FTPS fields
	 */
	@Value("${ftps.inbound.host}")
	private String ftpsHost;

	@Value("${ftps.inbound.port}")
	private int ftpsPort;

	@Value("${ftps.inbound.user}")
	private String ftpsUser;

	@Value("${ftps.inbound.password}")
	private String ftpsPassword;// /!\ passwords must be set in interfaceNameFuncTest.properties files

	@Value("${ftps.err.path}")
	private String ftpsOutputErrorDirectory;
	/**
	 * FTP fields
	 */
	@Value("${ftp.inbound.host}")
	private String ftpHost;

	@Value("${ftp.inbound.port}")
	private int ftpPort;

	@Value("${ftp.inbound.user}")
	private String ftpUser;

	@Value("${ftp.inbound.password}")
	private String ftpPassword;// /!\ passwords must be set in interfaceNameFuncTest.properties files

	@Value("${ftp.in.path}")
	private String ftpInputDirectory;

	@Value("${local.in.path}")
	private String localInputDirectory;
	/**
	 * Db Params
	 */
	@Value("${db.connect.string}")
	private String dbConnectString;
	@Value("${db.user}")
	private String dbUser;
	@Value("${db.password}")
	private String dbPassword;
	@Value("${db.driver}")
	private String dbDriver;
	@Value("${db.select.u_ast_invoice}")
	private String dbSelectInvoice;
	@Value("${db.delete.mw.dbo}")
	private String dbDeleteString;
	/**
	 * Private methods. Essentially filling fields needed for remote servers
	 * communication. Please remove unnecessary ones and feel free to add methods
	 * for your tests.
	 */
	/**
	 * Method to fill the ftpDto Object with shared settings (Host name and
	 * credentials)
	 * 
	 * @param boolean secure
	 * @return FtpDto ftpDto
	 */
	private FtpDto fillFtpDto(boolean secure) {
		log.info("Creating new FtpDto and filling fields...");

		FtpDto ftpDto = new FtpDto();

		if (secure) {
			ftpDto.setFtpHost(ftpsHost);
			ftpDto.setFtpServerPort(ftpsPort);
			ftpDto.setFtpUser(ftpsUser);
			ftpDto.setFtpPasword(ftpsPassword);
			ftpDto.setIsFtpSecure(true);
		} else {
			ftpDto.setFtpHost(ftpHost);
			ftpDto.setFtpServerPort(ftpPort);
			ftpDto.setFtpUser(ftpUser);
			ftpDto.setFtpPasword(ftpPassword);
			ftpDto.setIsFtpSecure(false);
		}
		return ftpDto;
	}
	/**
	 * Method to fill the jdbcDto Object with shared settings
	 * 
	 * @return JdbcDto jdbcDto
	 */
	private JdbcDto fillJdbcDto() {
		log.info("Creating new JdbcDto and filling fields...");
		JdbcDto jdbcDto = new JdbcDto();
		jdbcDto.setDriver(dbDriver);
		jdbcDto.setLogin(dbUser);
		jdbcDto.setPassword(dbPassword);
		jdbcDto.setUrlConnection(dbConnectString);
		return jdbcDto;
	}
	/**
	 * This method prevent running tests if the ftp server is unreachable
	 * 
	 * @throws Exception
	 */
	public void the_ftp_server_is_up_and_reached() throws Exception {
		FTPClient ftp = null;

		try {
			ftp = ftpManagerService.openConnectionAndLoginFtpServer(fillFtpDto(false));
		} catch (Exception e) {
			log.error("Error reaching the ftp server : {} {}", e.getClass().toString().split(" ")[1], e.getMessage());
			SkipFlag.skipFlagRemoteServer = true;
		}
		SkipFlag.skipFlagRemoteServer = (ftp != null && ftp.getReplyCode() == 230) ? false : true;
	}
	/**
	 * This method prevent running tests if the ftps server is unreachable
	 * 
	 * @throws Exception
	 */
	public void the_ftps_server_is_up_and_reached() throws Exception {
		FTPClient ftps = null;
		try {
			ftps = ftpManagerService.openConnectionAndLoginFtpServer(fillFtpDto(true));
		} catch (Exception e) {
			log.error("Error reaching the ftps server : {} {}", e.getClass().toString().split(" ")[1], e.getMessage());
			SkipFlag.skipFlagRemoteServer = true;
		}
		SkipFlag.skipFlagRemoteServer = (ftps != null && ftps.getReplyCode() == 200) ? false : true;
	}
	/**
	 * This method prevent running tests if the Database is unreachable
	 */
	public void the_jdbc_service_can_reach_db() {
		try {
			SkipFlag.skipFlagRemoteServer = false;
			jdbcService.createConnection(fillJdbcDto());
		} catch (Exception e) {
			log.error("Problem with remote database: {}", e.getMessage());
			SkipFlag.skipFlagRemoteServer = true;
		}
	}
	/**
	 * void test : workaround for "No runnable method" error rise
	 * 
	 * @return true
	 */
	@Test
	public void voidTest() {
		assertTrue(true);
	}
	/**
	 * @Before Hooks used to skip tests if needed (ie. when checking the environment
	 *         return an error)
	 */
	@Before(value = "@Local", order = 0)
	public void checkEnvLocal() {
		log.info("--------------BEFORE----------------");
		log.info("Checking local environment...");
		if (SkipFlag.skipFlagLocal) {
			log.error("Problem with local environment, aborting test...");
			log.info("------------------------------------");
			Assume.assumeTrue(false);
		} else {
			log.info("Environment OK");
			log.info("------------------------------------");
			bufMap = new HashMap<>(); // We instantiate bufMap here to make sure it's empty and ready to use for
										// local test
		}
	}
	// If we are really checking local environment, we should add something

	/**
	 * ENGLISH TESTS Glue code for NonRegTest_en.feature file
	 */
	/**
	 * @Local Local test <br>
	 * 
	 *        This scenario is already implemented so you can have an insight of how
	 *        to bind Gherkin scenario to actual Java code (called "glue code")
	 * 
	 * @throws Exception
	 */
	@Given("^I open the \"([^\"]*)\" file stored in the \"([^\"]*)\" directory$")
	public void i_open_the_file_stored_in_the_directory(String fileName, String inputDirectory) throws Exception {

		String filePath = inputDirectory + "/" + fileName;
		ClassPathResource resource = new ClassPathResource(filePath);
		File f = resource.getFile();

		if (f.exists())
			bufMap.put("source.xml", f);// so we can access this file next step
		else
			log.error("error with input file");

		assertTrue("source.xml doesn't exist", bufMap.get("source.xml").exists());
	}
	@When("^I read the content$")
	public void i_read_the_content() throws Exception {

		List<String> tmpStringList = fManagerService.readContentFileAsStringList(bufMap.get("source.xml"));

		if (!tmpStringList.isEmpty()) {
			log.info(bufMap.get("source.xml").getName());
			Iterator<String> iterator = tmpStringList.iterator();
			while (iterator.hasNext()) {
				log.info(iterator.next());
			}
		}
		assertTrue("Input file is empty", !tmpStringList.isEmpty());
	}
	@Then("^my test is done$")
	public void my_test_is_done() throws Exception {
		log.info("TEST DONE");
		assertTrue("impossible error...", true);
	}
	/**
	 * @NonRegTest non-reg test <br>
	 * 
	 * @throws Exception
	 */
	/**
	 * "Before" Cucumber Hook to make sure test environment is OK...
	 *
	 * Scale it to your needs
	 *
	 * @throws Exception
	 */
	@Before(value = "@Non-regression-test", order = 0)
	public void checkEnvRemote() throws Exception {
		log.info("--------------BEFORE----------------");
		log.info("Checking DB connection...");
		the_jdbc_service_can_reach_db();
		if (!SkipFlag.skipFlagRemoteServer) {
			log.info("Environment DB OK");
			log.info("Checking FTPS connection...");
			the_ftps_server_is_up_and_reached(); // add condition because if one server connection fails it's enough to
													// stop the test
		}
		if (SkipFlag.skipFlagRemoteServer) {
			log.error("Problem with remote servers, aborting test...");
			log.info("------------------------------------");
			Assume.assumeTrue(false);
		} else {
			log.info("Environment FTPS OK");
			log.info("Environment OK");
			log.info("------------------------------------");
			bufMap = new HashMap<>(); // We instantiate bufMap here to make sure it's empty and ready to use for
										// non-reg test
		}
	}
	/**
	 * "Before" Cucumber Hook to make sure I/O repositories are empty...
	 * 
	 * Scale it to your needs
	 * 
	 * @throws Exception
	 */
	@Before(value = "@Non-regression-test", order = 1)
	public void cleanUpFTP() throws Exception {

		if (SkipFlag.skipFlagRemoteServer)
			Assume.assumeTrue(false);
		log.info("clean up INput files");

		FtpDto ftpDto = fillFtpDto(false);
		ftpDto.setFileNameStructure(FileNameStructure.END_WITH);
		ftpDto.setFtpFileName("*.txt"); // set here the extension that the input files should always have
		ftpDto.setFtpRemoteDirectory(ftpInputDirectory);

		try {
			ftpManagerService.deleteFtpFilesDirectory(ftpDto);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error deleting files in the input directory : {}", e.getMessage());
		}
	}
	@Given("^I take the reference input file named \"([^\"]*)\" from Source Repository$")
	public void i_take_the_reference_input_file_named_from_Source_Repository(String refInputFile) throws Exception {
		// TODO Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}
	@When("^I put it in the Source Connection$")
	public void i_put_it_in_the_Source_Connection() throws Exception {
		// TODO Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}
	@When("^I look in the Target Connection for the output file named \"([^\"]*)\" that should be provided within a maximum time of (\\d+) sec$")
	public void i_look_in_the_Target_Connection_for_the_output_file_named_that_should_be_provided_within_a_maximum_time_of_sec(
			String outputFileName, int maxTime) throws Exception {
		// TODO Write code here that turns the phrase above into concrete actions
		int timer = 5;// time(in sec) between two download tries
		throw new PendingException();
	}

	@When("^I take the reference output file named \"([^\"]*)\" from Target Repository$")
	public void i_take_the_reference_output_file_named_from_Target_Repository(String refOutputFile) throws Exception {
		// TODO Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	/**
	 * This won't work if you don't use the HashMap for sharing files between steps.
	 * Please transform it to your needs and try to keep the behavior which is:
	 * 			We compare interface output with reference output
	 * 				- the two outputs match -> no regression -> test OK
	 * 				- the two outputs differ -> regression -> rename output and prefix with error code -> store output on archive server
	 * 
	 * @throws Exception
	 */
	@Then("^they should be the same$")
	public void they_should_be_the_same() throws Exception {
		Boolean compare = false;
		Boolean regression = false;
		compare = fManagerService.compareFilesContent(fManagerService.convertFileToInputStream(bufMap.get("out")),
				fManagerService.convertFileToInputStream(bufMap.get("outRef")));
		regression = compare ? false : true;
		if (regression) {
			log.debug("Files are not equal, storing the interface output for analysis...");
			String p = FilenameUtils.getFullPath(bufMap.get("out").getAbsolutePath());
			log.debug(p);

			File tmp = bufMap.get("out");
			File renameTmp = new File(
					p + InterfaceNameErrorCode.REG.toString() + "_out_" + date.format(new Date()) + ".xml");
			Files.copy(tmp.toPath(), renameTmp.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
			bufMap.put("err", renameTmp);// I put it in the map so my @After hook delete it for me after test
			FtpDto ftpsDto = fillFtpDto(true);
			ftpsDto.setFile(FilenameUtils.getFullPath(bufMap.get("err").getAbsolutePath()),
					bufMap.get("err").getName());
			ftpsDto.setFtpRemoteDirectory(ftpsOutputErrorDirectory);
			if (log.isDebugEnabled()) {
				log.debug("Setting the remote directory to" + ftpsOutputErrorDirectory + "....");
			}
			try {
				ftpManagerService.uploadFile(ftpsDto);
			} catch (Exception e) {
				assertTrue("Error during upload : " + e.getMessage(), false);
			}
			assertTrue(InterfaceNameErrorCode.REG.toDeepString() + " : Files are not equal, output stored for analysis",
					false);
		}
		assertTrue(!regression);
	}
	/**
	 * "After" Cucumber Hook to make sure I/O repositories are empty...
	 * 
	 * Scale it to your needs
	 * 
	 * @throws Exception
	 */
	@After(value = "@Non-regression-test", order = 0)
	public void cleanUpAfterFtp() throws Exception {
		if (SkipFlag.skipFlagRemoteServer)
			Assume.assumeTrue(false);
		log.info("--------------AFTER----------------");
		log.info("clean up INput files");

		FtpDto ftpDto = fillFtpDto(false);
		ftpDto.setFileNameStructure(FileNameStructure.END_WITH);
		ftpDto.setFtpFileName("*.txt"); // set here the extension that the input files should always have
		ftpDto.setFtpRemoteDirectory(ftpInputDirectory);

		try {
			ftpManagerService.deleteFtpFilesDirectory(ftpDto);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error deleting files in the input directory : {}", e.getMessage());
		}

		/*
		 * Make sure that the files in the map don't persist
		 */
		log.info("Deletion of temp file(s)....");
		Iterator<String> i = bufMap.keySet().iterator();
		while (i.hasNext()) {
			String fileName = i.next();
			log.info("Deleting \"" + bufMap.get(fileName).getAbsolutePath() + "\" from computer...");
			if (bufMap.get(fileName).delete())
				log.info("Deleting " + fileName + " was successful.");
			else
				log.error("Error while deleting " + fileName);
		}
		log.info("-----------------------------------");
	}
}
