package service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.UserCredentialsRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInt {

    private final UserCredentialsRepository userCredentialsRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return (UserDetails) userCredentialsRepository.findByName(username).orElseThrow(
                        ()-> new UsernameNotFoundException("User not found "));

            }
        };
    }
}
