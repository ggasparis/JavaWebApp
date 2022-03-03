package gr.ntua.ece.project.controllers;

import gr.ntua.ece.project.entities.Parent;
import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.forms.SearchForm;
import gr.ntua.ece.project.forms.UserForm;
import gr.ntua.ece.project.repositories.UserRepository;
import gr.ntua.ece.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ParentController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/parent", method = RequestMethod.GET)
    public String simpleParent(Model model) {
        User user;
        String currentUserName = null;
        model.addAttribute("SearchForm", new SearchForm());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user = userService.findByUsername(currentUserName);
            model.addAttribute("user", user);
            model.addAttribute("myLat", user.getParentById().getLatitude());
            model.addAttribute("myLon", user.getParentById().getLongtitude());
            return "parent";
        } else
            model.addAttribute("message", "you are not logged in currently");
        return "redirect:/login";
    }


    @RequestMapping(value = "/parentProfile", method = RequestMethod.GET)
    public String parentProfile(Model model) {
        User user;
        String currentUserName = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user = userService.findByUsername(currentUserName);
            model.addAttribute("user", user);
            return "parentProfile";
        } else
            model.addAttribute("message", "you are not logged in currently");
        return "redirect:/login";
    }

    @RequestMapping(value = "/parent/mytickets", method = RequestMethod.GET)
    public String pastTickets(Model model) {
        User user;
        String currentUserName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user = userService.findByUsername(currentUserName);
            model.addAttribute("user", user);
            return "pastTickets";
        } else
            model.addAttribute("message", "you are not logged in currently");
        return "redirect:/login";
    }


    @RequestMapping(value = "/editParentProfile", method = RequestMethod.GET)
    public String editProfile(Model model) {
        User user;
        String currentUserName = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user = userService.findByUsername(currentUserName);
            model.addAttribute("user", user);
            model.addAttribute("userForm", new UserForm());
            return "editParentProfile";
        } else
            model.addAttribute("message", "you are not logged in currently");
        return "redirect:/login";
    }

    @RequestMapping(value = "/editParentProfile", method = RequestMethod.POST)
    public String editProfile(@ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "editParentProfile";
        }
        System.out.println("userform file: " + userForm.getFile());
        userService.updateParent(userForm);

        return "redirect:/parentProfile";
    }

    @RequestMapping(value = "/buyPoints", method = RequestMethod.GET)
    public String buyPoints(Model model) {
        User user;
        String currentUserName = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        currentUserName = authentication.getName();
        user = userService.findByUsername(currentUserName);
        model.addAttribute("user", user);

        if(user.getRoleId()!=2)
            return "error";

        return "buyPoints";
    }

    @RequestMapping(value = "/addCard", method = RequestMethod.GET)
    public String addCard(Model model) {
        User user;
        String currentUserName = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        currentUserName = authentication.getName();
        user = userService.findByUsername(currentUserName);
        model.addAttribute("user", user);

        if(user.getRoleId()!=2)
            return "error";

        return "addCard";
    }


    @RequestMapping(value = "/addPoints", method = RequestMethod.GET)
    public String addPoints(Model model, @RequestParam("flag") int valid) {

        User user;
        String currentUserName = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        currentUserName = authentication.getName();
        user = userService.findByUsername(currentUserName);
        model.addAttribute("user", user);
        return "addPoints";
    }


    @RequestMapping(value = "/successBuy", method = RequestMethod.GET)
    public String something(Model model ,@RequestParam("option") int option) {


        Parent parent;
        String currentUserName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            parent=userService.findByUsername(currentUserName).getParentById();
            userService.updateWalletParent(parent,option);
            return "redirect:/index";
        } else
            model.addAttribute("message", "you are not logged in currently");
        return "redirect:/login";


    }
}




