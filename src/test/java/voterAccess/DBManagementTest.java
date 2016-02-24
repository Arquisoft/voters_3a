package voterAccess;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import es.uniovi.asw.Application;
import es.uniovi.asw.dbManagement.DBManagement;
import es.uniovi.asw.dbManagement.DBManagementImpl;
import es.uniovi.asw.dbManagement.VoterRepository;
import es.uniovi.asw.model.Voter;;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class DBManagementTest {

	DBManagement db = new DBManagementImpl();

	@Autowired
	VoterRepository voterRepository;
	
    @Test
    public void shouldWireRepositoryLocal() {
    	assertNotNull(voterRepository);
    }
    
    
    @Test
    public void shouldWireRepository() {
        assertNotNull(db._getVoterRepository());
    }
    
    @Test
	public void getVoterByEmail() {
		Voter voter1 = new Voter("Antonio", "uo212486@uniovi.es", "password", "123456789K", 123);
		Voter voter2 = db.getVoter("uo212486@uniovi.es");
		assertTrue(voter1.equals(voter2));
	}

	//public void getVoterByUserPass() {}

	
    @Test
	public void get() {
		db.save(new Voter("Antonio", "antonio@uniovi.com", "pass", "1234566K", 412));
		Voter voter1 = new Voter("Antonio", "antonio@uniovi.com", "pass", "1234566K", 412);
		Voter voter2 = db.getVoter("ant@uni.com");
		assertTrue(voter1.equals(voter2));
	}
    
    //public Boolean changePassword(ChangePass changePass);
    
	@Test
	public void save() {
		db.save(new Voter("Antonio", "antonio@uniovi.com", "pass", "1234566K", 412));
		Voter voter1 = new Voter("Antonio", "antonio@uniovi.com", "pass", "1234566K", 412);
		Voter voter2 = db.getVoter("ant@uni.com");
		assertTrue(voter1.equals(voter2));
	}

}