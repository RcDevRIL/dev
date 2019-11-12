package com.econocom.middleware.functionaltestsapi;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.econocom.middleware.functionaltestsapi.utils.Helper;

public class HelperJunitTest {
	
	@Test
	public void listMapToString() {
		Map<String,Object> map =  new HashMap<>();
		map.put("nom", "toto");
		map.put("prenom", "titi");
		List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
		listMap.add(map);
		String result = Helper.listMapToString(listMap );
		assertTrue(("nom;prenomtoto;titi").equals(result.replace("\n", "")));
	}

}
