package voterAccess;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import types.UserInfo;
import types.UserPass;
import voterAccess.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class MainControllerTest {

	@Value("${local.server.port}")
	private int port;
	@Autowired WebApplicationContext wac;
	private URL base;
	private RestTemplate template;
	boolean porPantalla=false;
	

	

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();		
		mvc = new MockMvcBuilders().webAppContextSetup(wac).build();
		if (porPantalla) { printScreen() ; }
	}
	
	/*
	@Test
	public void getUser() throws Exception {
		String userURI = base.toString() + "/user";

		UserInfo expected = new UserInfo("David", "1234546789J", "uo212486", "123A");
		UserPass requestData = new UserPass("uo212486", "password");

		// retorna error 406. Puede que falte el accept aplication/json, no se.
		ResponseEntity<UserInfo> response = template.postForEntity(userURI, requestData, UserInfo.class);

		assertThat(response.getStatusCode(), equalTo(Status.OK));
		assertThat(response.getBody(), equalTo(expected));
	}*/

	@Test
	public void getLanding() throws Exception {
		String userURI = base.toString() + "/user";
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(response.hasBody(), equalTo(true));
		if (porPantalla) { System.out.println(response.getBody()); }
	}

		
	public void printScreen() throws Exception {
		MvcResult m = (mvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
			
				.content("{\"login\":\"uo212486@mail.com\", \"password\": \"password\"}")
		).andReturn());
		System.out.println("RESULTADO JSON OBTENIDO: "+ m.getResponse().getContentAsString());
	}
	
	@Test
	public void postUserOK() throws Exception {
		mvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"uo212486@mail.com\", \"password\": \"password\"}"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void postUserKnown() throws Exception {
		
		mvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"uo212486@mail.com\", \"password\": \"password\"}")
		).andExpect(status().isOk())
		 .andExpect(jsonPath("name", equalTo("David")))
		 .andExpect(jsonPath("pollingStationCode", equalTo("123A")))
		 //.andExpect(jsonPath("pollingStationCode").value("123A"))
		;
	}
	@Test
	public void postUserUNknown() throws Exception {		
		mvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"emailnovalido666@mail.com\", \"password\": \"password\"}")
		).andExpect(status().isNotFound());		 
		;
	}
	
	private MockMvc mvc;

}