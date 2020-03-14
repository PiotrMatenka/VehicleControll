package wspa.vehicle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class AuthenticationController {
    @GetMapping("/user-login")
    @ResponseBody
    public Principal login (Principal user)
    {
        return user;
    }
}
