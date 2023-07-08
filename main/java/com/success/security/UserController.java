package com.success.security;

import org.springframework.web.bind.annotation.GetMapping;

import com.success.dto.UserDTO;
import com.success.service.UserService;

public class UserController {
	
	private  UserService userService;

    @GetMapping
    public Iterable<UserDTO> getUsers(){
       return userService.getUsers();
   }

}
