package gr.ntua.ece.project.controllers;

//import gr.ntua.ece.project.data.Place;
//import com.sun.deploy.security.SessionCertStore;
import com.itextpdf.text.pdf.codec.Base64;
import gr.ntua.ece.project.entities.Event;
import gr.ntua.ece.project.entities.Ticket;
import gr.ntua.ece.project.forms.EventForm;
import gr.ntua.ece.project.services.EventService;
import gr.ntua.ece.project.services.UserService;
import gr.ntua.ece.project.services.ReportService;
import gr.ntua.ece.project.services.TicketService;
import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.validators.UserValidator;
import gr.ntua.ece.project.entities.Parent;
import gr.ntua.ece.project.entities.Provider;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import java.util.Map;
import java.util.HashMap;
import org.springframework.web.servlet.view.document.AbstractPdfView;


@Controller
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private TicketService ticketService;

    @RequestMapping(value={"/event/{id:.+}"}, method = RequestMethod.GET)
    public String show_ants (Model model, @PathVariable int id)  {
        String currentUserName=null;
        User user;
        Event myEvent = eventService.findById(id);
        model.addAttribute("event", myEvent);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user=userService.findByUsername(currentUserName);
            int i=0;
            model.addAttribute("user",user);
            model.addAttribute("role", user.getRoleId() );
        }
        else
        model.addAttribute("role", 0 );

        return "EventPage";
    }


    @RequestMapping(value={"/report/{id:.+}"}, method = RequestMethod.GET)
    public String report(Model model, @PathVariable int id) {

        User user;
        String currentUserName=null;

        int myid = id;
        Event event = eventService.findById(myid);
        model.addAttribute("event", event);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user=userService.findByUsername(currentUserName);
            model.addAttribute("user",user);
            return "reportEvent";
        }
        else
            model.addAttribute("message", "you are not logged in currently");
        return "redirect:/login";
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String report(@RequestParam("eventId") int eventId, @RequestParam("comments") String comments) {

        Event event = eventService.findById(eventId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Parent parent = userService.findByUsername(authentication.getName()).getParentById();

        reportService.createReport(event, parent, comments);

        return "redirect:/parent";
    }

    @RequestMapping(value = "/book/{id:.+}", method = RequestMethod.GET)
    public String book(Model model, @PathVariable int id, @RequestParam(value = "message",required =false) String message){
        User user;
        String currentUserName=null;

        int myid = id;
        Event event = eventService.findById(myid);
        model.addAttribute("event", event);
        model.addAttribute("message", message);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user=userService.findByUsername(currentUserName);
            model.addAttribute("user",user);
            model.addAttribute("role", user.getRoleId() );
            return "bookEvent";
        }
        else
            model.addAttribute("message", "you are not logged in");
        return "redirect:/login";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String book(@RequestParam("eventId") int eventId, @RequestParam("numberOfTickets") int numberOfTickets, Model model) {

        Event event = eventService.findById(eventId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Parent parent = userService.findByUsername(authentication.getName()).getParentById();

        String currentUserName=null;
        User user;
        currentUserName = authentication.getName();
        user=userService.findByUsername(currentUserName);
        model.addAttribute("user",user);
        model.addAttribute("role", user.getRoleId() );



       // int success = eventService.updateAvailableTickets(eventId, numberOfTickets);
        //event.setAvailableTickets(event.getAvailableTickets() - numberOfTickets);
        //eventService.updateEvent(event);
        int success=eventService.updateForTicket(user.getParentById(),event.getProviderByProviderUserId(),event,numberOfTickets);

        Ticket ticket=null;
        if (success == 1) {
            ticket = ticketService.createTicket(event, parent, numberOfTickets);
            ticketService.sendEmail(event, parent, ticket);
        }

        if (ticket != null) {
            //return String.format("ticket/%d", ticket.getId());
            model.addAttribute("ticket", ticket);
            model.addAttribute("event", event);
            return "ticket";
        }
        else
            model.addAttribute("message", "Something went wrong. Please try again.");
        return String.format("redirect:/book/%d", eventId);
    }

    @RequestMapping(value = "/ticket",method = RequestMethod.GET)
    public String ticket(Model model, @RequestParam("ticket") Ticket ticket, @RequestParam("event") Event event){
        User user;
        String currentUserName=null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            user=userService.findByUsername(currentUserName);
            model.addAttribute("ticket", ticket);
            model.addAttribute("event",event);
            return "ticket";
        }
        else
            model.addAttribute("message", "you are not logged in currently");
        return "redirect:/login";
    }


    @RequestMapping(value = "/ticket{id:.+}.pdf", method = RequestMethod.GET)
    public void previewTicket(HttpServletResponse response, @PathVariable int id) throws IOException {
        Ticket ticket = ticketService.findById(id);
        Parent parent = ticket.getParentByParentUserId();
        Event event = ticket.getEventByEventId();
        User user;
        String currentUserName=null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user=userService.findByUsername(authentication.getName());
        if(user.getId()==parent.getUserId()) {

            String content = "Ticket booked via RozPanda\n\n\n\n\n" +
                    "Your ticket(s) for event: " + event.getTitle() + "\n\n Number of tickets: "
                    + ticket.getQuantity() + "\n\n Amount paid: " + String.valueOf(event.getTicketPrice().multiply(new BigDecimal(ticket.getQuantity())))
                    + "\n\n Ticket code: " + String.valueOf(ticket.getId())
                    + "\n\n\n\n Have fun!";

            ByteArrayOutputStream outputStream = null;
            outputStream = new ByteArrayOutputStream();


            try {
            ticketService.writePdf(outputStream, content);
                System.out.println("good");
            } catch(Exception ex) {
               System.out.println("fuck");
              ex.printStackTrace();
            }

            byte[] documentInBytes = outputStream.toByteArray();

            response.setContentType("application/pdf");
            response.setContentLength(documentInBytes.length);
            response.getOutputStream().write(documentInBytes);
        }
    }
}