package gr.ntua.ece.project.controllers;


import gr.ntua.ece.project.entities.Event;
import gr.ntua.ece.project.entities.Provider;
import gr.ntua.ece.project.entities.Ticket;
import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.forms.EventForm;
import gr.ntua.ece.project.forms.UserForm;
import gr.ntua.ece.project.repositories.EventRepository;
import gr.ntua.ece.project.repositories.ReportRepository;
import gr.ntua.ece.project.repositories.TicketRepository;
import gr.ntua.ece.project.services.EventService;
import gr.ntua.ece.project.services.UserService;
import gr.ntua.ece.project.validators.EventsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static gr.ntua.ece.project.specifications.EventSpecification.doneMonthly;
import static gr.ntua.ece.project.specifications.EventSpecification.monthlyAndBeyond;
import static gr.ntua.ece.project.specifications.TicketSpecification.isMonthly;


@Controller
public class ProviderController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventsValidator eventsValidator;

    @RequestMapping(value = "/provider", method = RequestMethod.GET)
    public String simpleProvider (Model model) {
        User user;
        String currentUserName=null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user=userService.findByUsername(currentUserName);
            model.addAttribute("user",user);
            List<Event> events = (List<Event>) user.getProviderById().getEventsByUserId();
            model.addAttribute("events", events);
            model.addAttribute("eventForm", new EventForm());
            return "/provider";
        }
        else {
            model.addAttribute("message", "you are not logged in currently");
            return "redirect:/login";
        }
    }


    @RequestMapping(value = "/provider", method = RequestMethod.POST)
    public String simpleProvider(@ModelAttribute("eventForm") EventForm eventForm, BindingResult bindingResult, Model model) {
        String currentUserName=null;
        User user;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            user = userService.findByUsername(authentication.getName());

            eventsValidator.validate(eventForm, bindingResult);

            if (bindingResult.hasErrors()) {
                currentUserName = authentication.getName();
                user=userService.findByUsername(currentUserName);
                model.addAttribute("user",user);
                List<Event> events = (List<Event>) user.getProviderById().getEventsByUserId();
                model.addAttribute("events", events);
                model.addAttribute("eventForm", eventForm);
                return "provider";
            }

            eventService.createEvent(eventForm, user.getProviderById());
            return "redirect:/provider";
        }
        else {
            model.addAttribute("message", "you are not logged in currently");
            return "redirect:/login";
        }
    }

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @RequestMapping(value = "/provider/showMonthlyReports", method = RequestMethod.GET)
    public String daMonth( Model model) {
        User user;
        List<Ticket> tickets;
        List<Integer> amounts = new ArrayList<Integer>();
        int sum;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            user = userService.findByUsername(authentication.getName());
            Provider provider = user.getProviderById();
            List<Event> events =eventRepository.findAll(monthlyAndBeyond(provider));
            for (Event event :events){
                sum=0;
                tickets=ticketRepository.findAll(isMonthly(event));
                for (Ticket ticket :tickets)
                    sum=sum+ticket.getQuantity();
                amounts.add(sum);
            }
            model.addAttribute("eventsMonthly",events);
            model.addAttribute("amounts",amounts);
            String currentUserName = authentication.getName();
            user=userService.findByUsername(currentUserName);
            model.addAttribute("user", user);
            events =eventRepository.findAll(doneMonthly(provider));
            model.addAttribute("eventsDoneMonthly",events);
            return "/monthlyReports";
        }
        else {
            model.addAttribute("message", "you are not logged in currently");
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/provider/subcscribe", method = RequestMethod.GET)
    public String noOneWillNoticeImGay( Model model) {
        User user;
        String currentUserName=null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            int i=0;
            currentUserName = authentication.getName();
            user=userService.findByUsername(currentUserName);
            model.addAttribute("user",user);
            model.addAttribute("userForm", new UserForm());
            return "subscription";
        }
        else
            model.addAttribute("message","you are not logged in currently");
        return "redirect:/login";
    }


    @RequestMapping(value = "/provider/updateWallet/{id:.+}", method = RequestMethod.GET)
    public String wheresMyMoneyBitch( Model model,@PathVariable int id) {
        Event event=eventService.findById(id);
        eventService.updateProviderWallet(event.getProviderByProviderUserId(),event);
        return "redirect:/provider/showMonthlyReports";
    }

    @RequestMapping(value = "/providerProfile", method = RequestMethod.GET)
    public String providerProfile (Model model) {
        User user;
        String currentUserName=null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user=userService.findByUsername(currentUserName);
            model.addAttribute("user",user);
            return "providerProfile";
        }
        else
            model.addAttribute("message","you are not logged in currently");
        return "redirect:/login";
    }

    @RequestMapping(value = "/editProviderProfile", method = RequestMethod.GET)
    public String editProfile(Model model) {
        User user;
        String currentUserName=null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user=userService.findByUsername(currentUserName);
            model.addAttribute("user",user);
            model.addAttribute("userForm", new UserForm());
            return "editProviderProfile";
        }
        else
            model.addAttribute("message","you are not logged in currently");
        return "redirect:/login";
    }

    @RequestMapping(value = "/editProviderProfile", method = RequestMethod.POST)
    public String editProfile(@ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "editProviderProfile";
        }

        userService.updateProvider(userForm);

        return "redirect:/providerProfile";
    }
    @RequestMapping(value = "/provider/fml", method = RequestMethod.GET)
    public String FuckMyLife(Model model) {

        int flag=1;

        model.addAttribute("flag", flag);

        return "redirect:/provider/therealdeal";
    }
    @RequestMapping(value = "/provider/therealdeal", method = RequestMethod.GET)
    public String DAGAIMZZ1(Model model) {

        User user;
        String currentUserName=null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user=userService.findByUsername(currentUserName);
            userService.updateProviderSub(user.getProviderById());
            return "redirect:/index";
        }
        else
            model.addAttribute("message","you are not logged in currently");
        return "redirect:/login";
    }

}
