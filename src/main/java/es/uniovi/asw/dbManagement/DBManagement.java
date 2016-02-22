package es.uniovi.asw.dbManagement;

import org.springframework.stereotype.Component;

import es.uniovi.asw.model.Voter;
import es.uniovi.asw.types.ChangePass;
import es.uniovi.asw.types.UserPass;

@Component
public interface DBManagement {

	public Voter save(Voter voter);

	public Voter getVoter(String email);

	public Voter getVoter(UserPass userPass);

	public Boolean changePassword(ChangePass changePass);
	
	public VoterRepository _getVoterRepository();

}