package dbManagement;

import model.Voter;

public class DBManagement {

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
