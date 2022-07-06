package self.learning.learningspringboot.service.implementation;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        if(userName.equals("marzuk16")){
            return new User("marzuk!6", "M4rzuk!6", new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("user not found !!");
        }
    }
    
}
