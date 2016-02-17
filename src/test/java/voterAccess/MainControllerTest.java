package voterAccess;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.net.URL;

import javax.validation.constraints.AssertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.springframework.boot.test.IntegrationTest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

import junit.framework.Assert;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
	private MockMvc mvc;
	boolean porPantalla=false;
	

	

	@SuppressWarnings("static-access")
	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();		
		mvc = new MockMvcBuilders().webAppContextSetup(wac).build();
		
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
	
	/*
	 * 
	 * 
	 * BLOQUE DE TESTS DEDICADOS AL: Peticion, respuesta en Json. 
	 * 
	 * 
	 */

	@Test
	public void getLanding() throws Exception {
		String userURI = base.toString() + "/user";
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(response.hasBody(), equalTo(true));
		if (porPantalla) { System.out.println(response.getBody()); }
	}
	
	@Test	
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

	}

	@Test
	public void postUserUNknown() throws Exception {		
		mvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"emailnovalido666@mail.com\", \"password\": \"password\"}")
		).andExpect(status().isNotFound());		 
	}
	
	@Test
	public void postUserBadPass() throws Exception {		
		mvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"uo212486@mail.com\", \"password\": \"pas\"}")
		).andExpect(status().isNotFound());		 
	}
	
	@Test
	public void postUserInvalidMailFormat_at() throws Exception {		
		mvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"uo212486.com\", \"password\": \"password\"}")
		).andExpect(status().isNotFound());		 	
	}
	
	@Test
	public void postUserInvalidMailFormat_domain() throws Exception {		
		mvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"uo212486@mail\", \"password\": \"password\"}")
		).andExpect(status().isNotFound());			
	}
	
	@Test
	public void postUserInvalidMailFormatDomain_wrongPass() throws Exception {		
		mvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"login\":\"uo212486@mail\", \"password\": \"pa\"}")
		).andExpect(status().isNotFound());		
	}
	

	/*
	 * 
	 * 
	 * BLOQUE DE TESTS DEDICADOS AL: DOMINIO
	 * 
	 * 
	 */
	//UserPass
	@Test
	public void testUserPass() throws Exception {
		String login="loglog"; 
		String pass="paspas";
		UserPass up = new UserPass(login,pass);
		assertTrue(up.toString().equals("UserPass [login=" + login + ", password=" + pass + "]"));
	}	
	
	//UserInfo
	@Test
	public void testUserInfoVacio() throws Exception {

		UserInfo ui = new UserInfo();
		assertTrue(ui.hashCode()==923521);
		assertTrue(ui.toString().equals("UserInfo [name=null, "
				+ "nif=null, "
				+ "email=null, "
				+ "pollingStationCode=null]"));
	}
	
	@Test
	public void testUserInfoCompare() throws Exception {

		UserInfo ui1 = new UserInfo();
		UserInfo ui2 = new UserInfo();
		//compara 2 null
		assertTrue(ui1.equals(ui2));
		ui2 = new UserInfo("name", "nif", "email", "pollingStationCode");
		// compara un null con un bien formado
		assertFalse(ui1.equals(ui2));
		// comapra 2 bien formados
		ui1 = new UserInfo("name", "nif", "email", "pollingStationCode");
		assertTrue(ui1.equals(ui2));
		//compara dif nif
		ui1 = new UserInfo("pepe", "25", "email@dom.com", "1");
		ui2 = new UserInfo("pepe", "50", "email@dom.com", "1");
		assertFalse(ui1.equals(ui2));
		//compara dif nombre
		ui1 = new UserInfo("pepe", "25", "email@dom.com", "1");
		ui2 = new UserInfo("juan", "25", "email@dom.com", "1");
		assertFalse(ui1.equals(ui2));
		//compara dif colegio electoral
		ui1 = new UserInfo("pepe", "50", "email@dom.com", "8");
		ui2 = new UserInfo("pepe", "50", "email@dom.com", "1");
		assertFalse(ui1.equals(ui2));
		//compara dif correo
		ui1 = new UserInfo("pepe", "50", "mail@dom.com", "1");
		ui2 = new UserInfo("pepe", "50", "email@hotmail.com", "1");
		assertFalse(ui1.equals(ui2));
	}
}