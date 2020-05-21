package com.karim.security;


import com.karim.database.UserRepo;
import com.karim.model.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.karim.security.MyUserDetails;

@Service
public class UserDetail implements UserDetailsService {

    @Autowired
    private UserRepo repo ;

    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Override
    public UserDetails loadUserByUsername(String username ) throws UsernameNotFoundException {
        User user = repo.findByEmail(username);
        if (user == null) {
            user = repo.findByPhone(username);
            if (user != null) {
                return new MyUserDetails(user);
            }
            user = repo.findByPhone(username);
            if (user == null) {
                return null;
            }else{
                return new MyUserDetails(user);
            }
        } else {
            return new MyUserDetails(user);
        }

    }

}
