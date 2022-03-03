package gr.ntua.ece.project.controllers;

import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    @Autowired
    private UserService userService;

    @GetMapping("/error")
    public void error(HttpServletResponse response) throws IOException {
        response.sendRedirect("errorView");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value="errorView")
    public String show_ants (Model model)  {

        String currentUserName=null;
        User user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user=userService.findByUsername(currentUserName);
            model.addAttribute("user",user);
            model.addAttribute("role", user.getRoleId() );
        }
        else {
            model.addAttribute("role", 0);
        }
        return "errorPage";
    }
}

