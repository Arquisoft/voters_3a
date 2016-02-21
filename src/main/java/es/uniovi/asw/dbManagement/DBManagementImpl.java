package es.uniovi.asw.dbManagement;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityScan;

//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.types.ChangePass;
import es.uniovi.asw.types.UserPass;


//@Service
//@Transactional
@EntityScan("es.uniovi.asw.model")
public class DBManagementImpl implements DBManagement {

	@Autowired
	private VoterRepository voterRepository;

	public DBManagementImpl() {

	}
	
	@Override
	public Voter save(Voter voter) {
		return voterRepository.save(voter);
	}
	
	@Override
	public Voter GetVoter(String email) {
		return voterRepository.findByEmail(email);
	}

	@Override
	public Voter GetVoter(UserPass userPass) {
		return voterRepository.findByEmailAndPassword(userPass.getLogin(), userPass.getPassword());
	}

	@Override
	public Boolean ChangePassword(ChangePass changePass) {
		Voter voter = voterRepository.findByEmail(changePass.getLogin());

		if (voter == null || !voter.getPassword().equals(changePass.getOldPassword()))
			return false;

		voter.setPassword(changePass.getNewPassword());
		voterRepository.save(voter);
		return true;
	}
}
