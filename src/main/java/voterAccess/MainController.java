package voterAccess;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import types.ChangePass;
import types.UserInfo;
import types.UserPass;

@Controller
@RestController
public class MainController {
	
	public static UserInfo usuario1 = new UserInfo("David","1234546789J","uo212486", "123A");
	public static UserPass pass1 = new UserPass("uo212486", "password");
	

    /*@RequestMapping("/")
    public String landing() {
        return "User Management Service";
    }*/
    
    /*@RequestMapping("/error")
    public String errorPage() {
        return "Error";
        http://stackoverflow.com/questions/25356781/spring-boot-remove-whitelabel-error-page
    }*/
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public UserInfo GetVoterInfo(@RequestBody @Valid final UserPass message) {
        return new UserInfo("David","1234546789J","uo212486", "123A");
    }
    
    /*@RequestMapping("/ChangePassword")
    public UserInfo user() {
        return new UserInfo("pepe",0);
    }*/
    
    //Metodos condicionales para probar que se meta bien los datos
    
    @RequestMapping(value = "/user2", method = RequestMethod.POST)
    public UserInfo GetVoterInfo2(@RequestBody @Valid final UserPass message) {
    	if(message.getLogin().split("@")[0].equals(pass1.getLogin()) &&  message.getPassword().equals(pass1.getPassword()))
    		return usuario1;
    	return null;
    }
    
    @RequestMapping(value = "/ChangePassword", method = RequestMethod.POST)
    public UserPass user(@RequestBody @Valid final ChangePass message) {
    	if(message.getLogin().split("@")[0].equals(pass1.getLogin()) &&  message.getOldPassword().equals(pass1.getPassword()))
    		return pass1;
    	return null;
    }
}