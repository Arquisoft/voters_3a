package dbManagement;

import org.springframework.beans.factory.annotation.Autowired;

import model.Voter;

public class DBManagement {

	@Autowired
	private VoterRepository voterRepository;
	
	public DBManagement() {
		
	}

	Voter GetVoter(Voter voter) {
		return voterRepository.findByEmail(voter.getEmail());
	}

	void ChangePassword(Voter voter) {
		voterRepository.save(voter);
	}
}
