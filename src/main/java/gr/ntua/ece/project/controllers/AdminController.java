package gr.ntua.ece.project.controllers;

import gr.ntua.ece.project.entities.*;
import gr.ntua.ece.project.repositories.ReportRepository;
import gr.ntua.ece.project.services.EventService;
import gr.ntua.ece.project.services.SecurityService;
import gr.ntua.ece.project.services.UserService;
import gr.ntua.ece.project.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model,SecurityContextHolderAwareRequestWrapper request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
                String userRole = authority.getAuthority();
                if (userRole.equals("administrator"))
                    return "/admin";

            }
            return "redirect:/index";
        } else
            return "redirect:/login";

    }


    @RequestMapping(value = "/showUsers", method = RequestMethod.GET)
    public String showUsers(Model model, @RequestParam(value = "role", required=false) String[] role, HttpServletRequest request) {

        List<User> userList;

        HttpSession session = request.getSession();

        if(role != null){
            session.setAttribute("role", role);
        }
        else role = (String[]) session.getAttribute("role");
        if (role==null)
            return "redirect:/admin";
        if (role.length==2)
            userList=userService.findAllByRole("all");
        else
            userList=userService.findAllByRole(role[0]);

        model.addAttribute("userList",userList);

        return "showUsers";
    }

    @RequestMapping(value = "/showPending", method = RequestMethod.GET)
    public String showPending(Model model) {

        List<User> userList = userService.findAllByRole("not_approved");

        model.addAttribute("userList",userList);

        return "showPending";
    }

    @RequestMapping(value = "/approveProvider", method = RequestMethod.GET)
    public String approve(Model model,  @RequestParam("username") String username) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            userService.approveProvider(username);
            return "redirect:/showPending";
        }
        else
            model.addAttribute("message","you are not logged in currently");
        return "redirect:/login";
    }

    @RequestMapping(value = "/banUser", method = RequestMethod.GET)
    public String ban(Model model,  @RequestParam("username") String username) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            userService.banUser(username);
            return "redirect:/showUsers";
        }
        else
            model.addAttribute("message","you are not logged in currently");
        return "redirect:/login";
    }

    @RequestMapping(value = "/unbanUser", method = RequestMethod.GET)
    public String unban(Model model,  @RequestParam("username") String username) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            userService.unbanUser(username);
            return "redirect:/showUsers";
        }
        else
            model.addAttribute("message","you are not logged in currently");
        return "redirect:/login";
    }

    @RequestMapping(value = "/publicUserProfile", method = RequestMethod.GET)
    public String publicProfile(Model model,  @RequestParam("username") String username) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            User user = userService.findByUsername(authentication.getName());
            model.addAttribute("user", user);

            User viewuser = userService.findByUsername(username);
            model.addAttribute("viewuser", viewuser);

            if (viewuser.getRoleId() == 2)
                return "publicParentProfile";
            else return "publicProviderProfile";
        }
        else
            model.addAttribute("message","you are not logged in currently");
        return "redirect:/login";
    }

    @Autowired
    private ReportRepository reportRepository;

    @RequestMapping(value = "/admin/showReports", method = RequestMethod.GET)
    public String showReports(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
                String userRole = authority.getAuthority();
                if (userRole.equals("administrator")) {
                    List<Report> reportList=reportRepository.findAll();
                    model.addAttribute("reportList", reportList);
                    return "/showReports";
                }

            }
            return "redirect:/index";

        }

        else
            return "redirect:/login";
    }

    @RequestMapping(value={"/admin/deleteReport/{id:.+}"}, method = RequestMethod.GET)
    public String report(Model model, @PathVariable int id) {

        reportRepository.delete(reportRepository.findById(id));
        return "redirect:/admin";
    }


    @RequestMapping(value={"/admin/changestatus/event/{id:.+}"}, method = RequestMethod.GET)
    public String show_ants (Model model, @PathVariable int id)  {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
                String userRole = authority.getAuthority();
                if (userRole.equals("administrator")) {
                    eventService.changeStatus(id);


                    return "redirect:/admin";
                }
            }
            return "redirect:/index";
        } else
            return "redirect:/login";
    }
}
