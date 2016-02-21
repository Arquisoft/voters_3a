package es.uniovi.asw.dbManagement;

import es.uniovi.asw.model.Voter;
import es.uniovi.asw.types.ChangePass;
import es.uniovi.asw.types.UserPass;

public interface DBManagement {

	Voter save(Voter voter);

	Voter GetVoter(String email);

	Voter GetVoter(UserPass userPass);

	Boolean ChangePassword(ChangePass changePass);

}