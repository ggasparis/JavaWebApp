package gr.ntua.ece.project.services;

import gr.ntua.ece.project.entities.Event;
import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.forms.UserForm;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

public interface StorageService {
    public String saveImage(MultipartFile file, User user);
    public String saveImage(MultipartFile file, Event event);
    public Resource loadImage(String filename, User user);
    public Resource loadImage(String filename, Event event);

}
