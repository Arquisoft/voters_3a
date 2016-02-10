package hello;


import org.springframework.web.bind.annotation.RequestMapping;
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
    
    @RequestMapping("/GetVoterInfo")
    public String GetVoterInfo() {
        return "Que pasa wey!";
    }
    
    @RequestMapping("/ChangePassword")
    public UserInfo user() {
        return new UserInfo("pepe",0);
    }
}