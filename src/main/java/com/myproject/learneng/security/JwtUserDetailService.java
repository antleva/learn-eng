package com.myproject.learneng.security;

import com.myproject.learneng.domain.User;
import com.myproject.learneng.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(JwtUserDetailService.class);

    private final UserService userService;

    @Autowired
    public JwtUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userService.findUserByName(username);
            if(user == null){
                logger.error("user with name "+username+" not found");
                throw new UsernameNotFoundException("user not found");
            }

            AuthenticatedUser authenticatedUser = AuthenticatedUserFactory.create(user);
            logger.info("load user by name");
        return authenticatedUser;
    }
}
