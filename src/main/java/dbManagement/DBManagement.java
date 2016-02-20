package dbManagement;

import org.springframework.beans.factory.annotation.Autowired;

import model.Voter;
import types.ChangePass;
import types.UserPass;

public class DBManagement {

	@Autowired
	private VoterRepository voterRepository;

	public DBManagement() {

	}
	
	public Voter save(Voter voter) {
		return voterRepository.save(voter);
	}
	
	Voter GetVoter(String email) {
		return voterRepository.findByEmail(email);
	}

	Voter GetVoter(UserPass userPass) {
		return voterRepository.findByEmailAndPassword(userPass.getLogin(), userPass.getPassword());
	}

	Boolean ChangePassword(ChangePass changePass) {
		Voter voter = voterRepository.findByEmail(changePass.getLogin());

		if (voter == null || !voter.getPassword().equals(changePass.getOldPassword()))
			return false;

		voter.setPassword(changePass.getNewPassword());
		voterRepository.save(voter);
		return true;
	}
}
