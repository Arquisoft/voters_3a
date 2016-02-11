package types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserInfo {
	
	private static final Logger log = LoggerFactory.getLogger(UserInfo.class);

    private String name;
    private String nif;
    private String email;
    private String pollingStationCode;
    
    public UserInfo(){}
    
	public UserInfo(String name, String nif, String email, String pollingStationCode) {
		this.name = name;
		this.nif = nif;
		this.email = email;
		this.pollingStationCode = pollingStationCode;
		log.info(this.toString());
	}

	public String getName() {
		return name;
	}

	public String getNif() {
		return nif;
	}

	public String getEmail() {
		return email;
	}

	public String getPollingStationCode() {
		return pollingStationCode;
	}

	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", nif=" + nif + ", email=" + email + ", pollingStationCode="
				+ pollingStationCode + "]";
	}

}