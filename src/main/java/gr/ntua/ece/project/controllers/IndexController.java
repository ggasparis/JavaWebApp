package gr.ntua.ece.project.controllers;

//import gr.ntua.ece.project.data.Place;
import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.forms.SearchForm;
import gr.ntua.ece.project.services.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/","/index","/home"})
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
        model.addAttribute("SearchForm", new SearchForm());
        model.addAttribute("myLat", 37.975446);
        model.addAttribute("myLon", 23.734774);
        return "index";
    }
}