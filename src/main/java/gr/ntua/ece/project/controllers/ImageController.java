package gr.ntua.ece.project.controllers;

import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.entities.Event;
import gr.ntua.ece.project.services.EventService;
import gr.ntua.ece.project.services.SecurityService;
import gr.ntua.ece.project.services.StorageService;
import gr.ntua.ece.project.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class ImageController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value="/user/{userId}/image/{filename:.+}", method = RequestMethod.GET)
    public ResponseEntity<Resource> serveUserImage(@PathVariable String filename, @PathVariable int userId) {
        User user;
        //String currentUserName=null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            //currentUserName = authentication.getName();
           // user = userService.findByUsername(currentUserName);
            user= userService.findById(userId);
            Resource file = storageService.loadImage(filename, user);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }
        return null;
    }

    @RequestMapping(value="/event/{eventId}/images/{filename:.+}", method = RequestMethod.GET)
    public ResponseEntity<Resource> serveEventImage(@PathVariable int eventId, @PathVariable String filename) {
        Event event = eventService.findById(eventId);
        Resource file = storageService.loadImage(filename, event);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);

    }
}
