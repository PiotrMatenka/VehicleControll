package wspa.vehicle.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import wspa.vehicle.model.User;
import wspa.vehicle.model.dto.UserDto;
import wspa.vehicle.repositories.UserRepository;
import wspa.vehicle.services.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;



@RestController
public class AuthenticationController {
  private UserRepository userRepository;
  @Autowired
  public AuthenticationController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @RequestMapping("/user-login")
  @ResponseBody
    public Principal login(Principal user)
    {
      return user;
    }
    @RequestMapping("/user")
    public ResponseEntity<User> loggedUser(Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        User user = userRepository.findByEmail(loggedUsername);
        return ResponseEntity.ok(user);
    }




}









