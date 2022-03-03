package gr.ntua.ece.project.controllers;

//import gr.ntua.ece.project.data.Place;
import gr.ntua.ece.project.entities.Event;
import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.forms.SearchForm;
import gr.ntua.ece.project.forms.UserForm;
import gr.ntua.ece.project.repositories.EventRepository;
import gr.ntua.ece.project.services.EventService;
import gr.ntua.ece.project.services.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ResultsController {

    @Autowired
    EventService eventService;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public String show_results (@ModelAttribute("SearchForm") SearchForm searchForm,Model model)  {
            System.out.println(searchForm.getDateFrom());
            System.out.println(searchForm.getDateTo());
            List<Event> events = eventService.findByConstraints(searchForm);
            User user;
        String currentUserName=null;
        model.addAttribute("SearchForm", searchForm);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user=userService.findByUsername(currentUserName);
            model.addAttribute("user",user);
            model.addAttribute("events",events);
            model.addAttribute("role", user.getRoleId() );
            return "searchResults";
        }
        else
            model.addAttribute("events",events);
            model.addAttribute("role", 0 );
            //model.addAttribute("message","you are not logged in currently");
        return "searchResults";
    }
}