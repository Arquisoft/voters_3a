package voterAccess;

import model.Voter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.net.URL;
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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import dbManagement.DBManagement;
import dbManagement.VoterRepository;
import model.Voter;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import types.ChangePass;
import types.UserInfo;
import types.UserPass;
import voterAccess.Application;
import dbManagement.VoterRepository;;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@ComponentScan(basePackages = { "dbManagement.VoterRepository" })
public class DBManagementTest {

	@Autowired
	private VoterRepository voterRepository;
		
    @Test
    public void shouldWireRepository() {
        assertNotNull(voterRepository);
    }
	
	/*
	@Test
	public void save() {
		DBManagement db = new DBManagement();
		db.save(new Voter("Antonio", "ant@uni.com", "pass", "1234566K", 412));
		
		Voter voter1 = new Voter("Antonio", "ant@uni.com", "pass", "1234566K", 412);
		Voter voter2 = db.GetVoter("ant@uni.com");
		assertTrue(voter1.equals(voter2));
	}*/

}