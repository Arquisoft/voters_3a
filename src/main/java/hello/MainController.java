package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping("/")
    public String landing() {
        return "User Management Service";
    }
    
    /*@RequestMapping("/error")
    public String errorPage() {
        return "Error";
        http://stackoverflow.com/questions/25356781/spring-boot-remove-whitelabel-error-page
    }*/
    
    // https://spring.io/guides/gs/handling-form-submission/
    @RequestMapping(value = "/GetVoterInfo", method = RequestMethod.POST)
    public String GetVoterInfo() {
        return "Que pasa wey!";
        //http://stackoverflow.com/questions/28649561/spring-boot-post-http-request
    }
    
    @RequestMapping("/ChangePassword")
    public UserInfo user() {
        return new UserInfo("pepe",0);
    }
}