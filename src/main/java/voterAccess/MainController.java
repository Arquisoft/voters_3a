package voterAccess;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import types.ChangePass;
import types.UserInfo;
import types.UserPass;
import exceptions.*;

@Controller
@RestController
public class MainController {

	public static UserInfo usuario1 = new UserInfo("David", "1234546789J", "uo212486", "123A");
	public static UserPass pass1 = new UserPass("uo212486", "password");

	/*
	 * @RequestMapping("/") public String landing() { return
	 * "User Management Service"; }
	 */
	
	// http://stackoverflow.com/questions/25356781/spring-boot-remove-whitelabel-error-page
	/*
	 * @RequestMapping("/error") public String errorPage() { return "Error"; }
	 */

	// Probar con {"login":"uo212486@uniovi.es","password":"password"}
	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserInfo> GetVoterInfo(@RequestBody @Valid final UserPass userPass) {
		if (userPass.getLogin().split("@")[0].equals(pass1.getLogin())
				&& userPass.getPassword().equals(pass1.getPassword()))
			return new ResponseEntity<UserInfo>(usuario1, HttpStatus.OK);
		return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
		// throw new UserNotFoundException(userPass);
	}

	@RequestMapping(value = "/ChangePassword", method = RequestMethod.POST)
	public UserPass user(@RequestBody @Valid final ChangePass message) {
		if (message.getLogin().split("@")[0].equals(pass1.getLogin())
				&& message.getOldPassword().equals(pass1.getPassword())) {
			pass1 = new UserPass(message.getLogin(), message.getNewPassword());
			return pass1;
		}
		return null;
	}
}