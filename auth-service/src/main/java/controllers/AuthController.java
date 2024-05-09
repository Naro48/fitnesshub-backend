package controllers;

import entity.UserCredentials;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public String addNewUser(UserCredentials userCredentials) {
        return authenticationService.saveUserCredentials(userCredentials);
    }


    @GetMapping("/token")
    public String getToken(UserCredentials userCredentials){
        return authenticationService.generateToken(userCredentials.getUsername());
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token){
        authenticationService.validateToken(token);
        return "Token is valid" ;
    }


}
