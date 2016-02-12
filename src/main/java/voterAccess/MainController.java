package hello;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import types.UserInfo;
import types.UserPass;

@Controller
@RestController
public class MainController {

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
}