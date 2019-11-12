package com.econocom.middleware.functionaltestsapi;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.econocom.middleware.functionaltestsapi.dto.JdbcDto;
import com.econocom.middleware.functionaltestsapi.jdbc.JdbcService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { FunctionalTestsApiApplication.class })
public class JdbcServiceImplJUnitTest {
	@Autowired
	private JdbcService jdbcServiceImpl;

	@Before
	public void createConnection() throws ClassNotFoundException, SQLException {
		JdbcDto jdbcDto = new JdbcDto();
		jdbcDto.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		jdbcDto.setLogin("JBOSS_WRITE_DEV");
		jdbcDto.setPassword("DATABASE_PASSWORD");
		jdbcDto.setUrlConnection("jdbc:sqlserver://DATABASE_ADRESS:1433;databaseName=MiddlewareDEV");
		jdbcServiceImpl.createConnection(jdbcDto);
	}

	@After
	public void closeConnection() {
		try {
			jdbcServiceImpl.close();
			assert (true);
		} catch (SQLException e) {
			assert (false);
			e.printStackTrace();
		}
	}

	@Test
	public void testGetResultFromQuery() {
		try {
			List<Map<String, Object>> result = jdbcServiceImpl.getResultFromQuery("select * from someTable");
			assertTrue(!result.isEmpty());
		} catch (SQLException e) {
			assert (false);
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateFromQuery() {
		try {
			jdbcServiceImpl.updateFromQuery(
					"update someTable set Type='C' where someField=1 AND someFieldID=10000000 and someFieldID2=10000000");
			assert (true);
		} catch (SQLException e) {
			assert (false);
			e.printStackTrace();
		}
	}

}
