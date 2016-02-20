package dbManagement;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import model.Voter;
import types.ChangePass;
import types.UserPass;


@Service
@Transactional
public class DBManagement {

	//@Autowired
	private VoterRepository voterRepository;

	public DBManagement() {

	}
	
	public Voter save(Voter voter) {
		return voterRepository.save(voter);
	}
	
	public Voter GetVoter(String email) {
		return voterRepository.findByEmail(email);
	}

	public Voter GetVoter(UserPass userPass) {
		return voterRepository.findByEmailAndPassword(userPass.getLogin(), userPass.getPassword());
	}

	public Boolean ChangePassword(ChangePass changePass) {
		Voter voter = voterRepository.findByEmail(changePass.getLogin());

		if (voter == null || !voter.getPassword().equals(changePass.getOldPassword()))
			return false;

		voter.setPassword(changePass.getNewPassword());
		voterRepository.save(voter);
		return true;
	}
}
