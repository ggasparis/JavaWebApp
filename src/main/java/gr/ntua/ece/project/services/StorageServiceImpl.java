package gr.ntua.ece.project.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import gr.ntua.ece.project.entities.Event;
import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.forms.UserForm;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.context.annotation.PropertySource;



@Service
@Configuration
@PropertySource("classpath:application.properties")
public class StorageServiceImpl implements StorageService {

    @Value("${user.images.path}")
    private String userImagesPath;

    @Value("${event.images.path}")
    private String eventImagesPath;

    @Override
    public String saveImage(MultipartFile file, User user) {
        if (file == null || file.isEmpty())
            return null;
        try {
            File path = new File(userImagesPath + File.separator + user.getId());
            if (!path.exists()) {
                path.mkdirs();
            }

            File outputFile = new File(path.getAbsolutePath() + File.separator + file.getOriginalFilename());
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
            outputStream.write(file.getBytes(), 0, file.getBytes().length);
            outputStream.close();
            return "/user/" + user.getId() + "/image/" + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String saveImage(MultipartFile file, Event event) {
        if (file == null || file.isEmpty())
            return null;
        try {
            File path = new File(eventImagesPath + File.separator + event.getId());
            if (!path.exists()) {
                path.mkdirs();
            }
            File outputFile = new File(path.getAbsolutePath() + File.separator + file.getOriginalFilename());
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
            outputStream.write(file.getBytes(), 0, file.getBytes().length);
            outputStream.close();
            return "/event/" + event.getId() + "/images/" + file.getOriginalFilename();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Resource loadImage(String filename, User user) {
        Path path = Paths.get(userImagesPath + File.separator + user.getId() + File.separator + filename);
        try {
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Resource loadImage(String filename, Event event) {
        Path path = Paths.get(eventImagesPath + File.separator + event.getId() + File.separator + filename);
        try {
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
