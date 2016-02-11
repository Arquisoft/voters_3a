package types;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "UserPass")
public class UserPass {
	private String login;
	private String password;
	
	public UserPass() {}
	
	public UserPass(String login, String password) {
		this.login = login;
		this.password = password;
	}

	@XmlElement
	public String getLogin() {
		return login;
	}
	
	@XmlElement
	public String getPassword() {
		return password;
	}

	
	
	
}