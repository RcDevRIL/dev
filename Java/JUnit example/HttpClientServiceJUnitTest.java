package com.econocom.middleware.functionaltestsapi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.econocom.middleware.functionaltestsapi.dto.HttpDto;
import com.econocom.middleware.functionaltestsapi.http.HttpClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { FunctionalTestsApiApplication.class })
public class HttpClientServiceJUnitTest {
	private static final String URL=  "http://BACKEND.ADRESS/ENDPOINT_URI/";
	private static final String JSON= "{\r\n" + "	\"input\":{\r\n" + "		\"id\":22095\r\n" + "	}\r\n" + "}";

	@Autowired
	private HttpClientService httpClientService;

	@Test
	public void testQueryRequest() throws Exception {

		Map<String, String> headers = new HashMap<>();
		headers.put("Session-Token", "apikey !key");
		HttpDto result = httpClientService.restQuery(URL, true, "PUT", headers, JSON);
		assertNotNull(result);
		assertTrue(200 == result.getCode());
		assertTrue("application/json".equals(result.getContentType()));

	}
	
	@Test
	public void testNotAuthenticatedRequest() throws Exception {
		 Map<String, InputStream> result = httpClientService.jsonPostRequest(URL, JSON);
		 assertNull(result);
	}
	
	@Test
	public void testNotAuthenticatedQueryRequest() throws Exception {
		Map<String, String> headers = new HashMap<>();
		HttpDto result = httpClientService.restQuery(URL, "POST", headers, JSON);
		 assert(result.getCode()==401);
	}

}
