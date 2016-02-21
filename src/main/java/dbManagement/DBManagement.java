package dbManagement;

import model.Voter;
import types.ChangePass;
import types.UserPass;

public interface DBManagement {

	Voter save(Voter voter);

	Voter GetVoter(String email);

	Voter GetVoter(UserPass userPass);

	Boolean ChangePassword(ChangePass changePass);

}