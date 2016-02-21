package voterAccess;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import es.uniovi.asw.Application;
import es.uniovi.asw.dbManagement.VoterRepository;;

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