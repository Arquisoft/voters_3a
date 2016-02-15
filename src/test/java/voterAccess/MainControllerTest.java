package voterAccess;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

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

	private URL base;
	private RestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();
	}

	@Test
	public void getLanding() throws Exception {
		String userURI = base.toString() + "/user";
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(response.hasBody(), equalTo(true));
	}

	@Test
	public void getUser() throws Exception {
		String userURI = base.toString() + "/user";

		UserInfo expected = new UserInfo("David", "1234546789J", "uo212486", "123A");
		UserPass requestData = new UserPass("uo212486", "password");

		// retorna error 406. Puede que falte el accept aplication/json, no se.
		ResponseEntity<UserInfo> response = template.postForEntity(userURI, requestData, UserInfo.class);

		assertThat(response.getStatusCode(), equalTo(Status.OK));
		assertThat(response.getBody(), equalTo(expected));
	}

}