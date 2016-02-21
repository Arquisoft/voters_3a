package es.uniovi.asw.dbManagement;

import org.springframework.beans.factory.annotation.Autowired;

import es.uniovi.asw.model.Voter;
import es.uniovi.asw.types.ChangePass;
import es.uniovi.asw.types.UserPass;

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
	public Voter getVoter(String email) {
		return voterRepository.findByEmail(email);
	}

	@Override
	public Voter getVoter(UserPass userPass) {
		return voterRepository.findByEmailAndPassword(userPass.getLogin(), userPass.getPassword());
	}

	@Override
	public Boolean changePassword(ChangePass changePass) {
		Voter voter = voterRepository.findByEmail(changePass.getLogin());

		if (voter == null || !voter.getPassword().equals(changePass.getOldPassword()))
			return false;

		voter.setPassword(changePass.getNewPassword());
		voterRepository.save(voter);
		return true;
	}
}
