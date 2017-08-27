package com.company.project;

import com.company.common.model.StatusCode;
import com.company.common.response.ObjectResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectApplicationTests {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
	}
	@Test
	public void testUserController() throws Exception{
		Map map=new HashMap<>();
		map.put("pageIdex",1);
		map.put("pageSize",2);
		ObjectResponse objectResponse=this.restTemplate.postForObject(this.base.toString()+"user/list", null,ObjectResponse.class,map);
		Assert.assertEquals(objectResponse.getCode(), StatusCode.Success);
	}
}
