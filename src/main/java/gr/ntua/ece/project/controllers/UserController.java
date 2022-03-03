package gr.ntua.ece.project.controllers;


import gr.ntua.ece.project.entities.Parent;
import gr.ntua.ece.project.entities.Ticket;
import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.forms.LoginForm;
import gr.ntua.ece.project.forms.UserForm;
import gr.ntua.ece.project.services.SecurityService;
import gr.ntua.ece.project.services.UserService;
import gr.ntua.ece.project.validators.ProviderExtraInfoValidator;
import gr.ntua.ece.project.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    ProviderExtraInfoValidator providerExtraInfoValidator;


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("userForm", new UserForm());

        return "register";
    }


    @RequestMapping(value = "/user/pastTickets", method = RequestMethod.GET)
    public String show_ants (Model model)  {
        String currentUserName=null;
        User user;
        Ticket ticket;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user=userService.findByUsername(currentUserName);
            model.addAttribute("user",user);
            model.addAttribute("role", user.getRoleId() );
        }
        else
            model.addAttribute("role", 0 );
        return "pastTickets";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("userForm") UserForm userForm, @RequestParam("role") String role, @RequestParam("file") MultipartFile file, BindingResult bindingResult, Model model) {


        if (role.equals("provider"))
            providerExtraInfoValidator.validate(userForm, bindingResult); //periexei ola ta pedia ..kserw einai kako :D

        else
            userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        System.out.println(userForm.getFile().getContentType() + " " + userForm.getFile().getOriginalFilename());
        if(role.equals("parent"))
            userService.makeParent(userForm);
        else
            userService.makeProvider(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPassword_confirm());

        if(role.equals("parent"))
            return "redirect:/parent";
        else return "redirect:/provider";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login( Model model, String error, String logout,@RequestParam(value = "message",required =false) String message) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        model.addAttribute("message" ,message);
        return "login";
    }
}