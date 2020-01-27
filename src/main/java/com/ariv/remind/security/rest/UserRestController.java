package com.ariv.remind.security.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariv.remind.resource.ResponseData;
import com.ariv.remind.security.model.User;
import com.ariv.remind.security.rest.dto.RegisterDto;
import com.ariv.remind.security.service.UserService;

@RestController
@RequestMapping("/v1/api")
public class UserRestController {

   private final UserService userService;

   public UserRestController(UserService userService) {
      this.userService = userService;
   }

   @GetMapping("/user")
   public ResponseEntity<User> getActualUser() {
      return ResponseEntity.ok(userService.getUserWithAuthorities().get());
   }
   
   @PostMapping("/register")
   public ResponseData registerUser(@RequestBody RegisterDto registerDto) {
      return userService.registerUser(registerDto);
   }
}
