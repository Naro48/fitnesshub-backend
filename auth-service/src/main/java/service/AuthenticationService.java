package service;

import entity.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserCredentialsRepository;

@Service
public class AuthenticationService {
    @Autowired
    private UserCredentialsRepository userCredentialsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService service ;

    public String saveUserCredentials(UserCredentials userCredentials) {
        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword())) ;
        userCredentialsRepository.save(userCredentials);
        return "user added to the system";
    }

    public String generateToken(String username) {
        return service.generateToken(username);
    }

    public void validateToken(String token) {
        service.validateToken(token);
    }


}
