package com.allacsta.latihan.controller.oauth;


import com.allacsta.latihan.repository.oauth.UserRepository;
import com.allacsta.latihan.service.oauth.Oauth2UserDetailsService;
import com.allacsta.latihan.service.register.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    private Oauth2UserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @GetMapping("/detail-profile")
    public ResponseEntity<Map> detailProfile(
            Principal principal
    ) {
        Map map = userService.getDetailProfile(principal);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

}
