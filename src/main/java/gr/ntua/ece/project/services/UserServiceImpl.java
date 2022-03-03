package gr.ntua.ece.project.services;


import gr.ntua.ece.project.entities.Event;
import gr.ntua.ece.project.entities.Parent;
import gr.ntua.ece.project.entities.Provider;
import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.forms.UserForm;
import gr.ntua.ece.project.repositories.ParentRepository;
import gr.ntua.ece.project.repositories.ProviderRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import gr.ntua.ece.project.repositories.RoleRepository;
import gr.ntua.ece.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static gr.ntua.ece.project.specifications.UserSpecification.isAdministrator;
import static gr.ntua.ece.project.specifications.UserSpecification.isParent;
import static gr.ntua.ece.project.specifications.UserSpecification.isProvider;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    byte negative = 0;

    @Override
    public Parent makeParent(UserForm userForm) {
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setIsBanned((byte) 0 );
        user.setRoleId(2);
        user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        userRepository.save(user);

        String photoLink;
        if (!userForm.getFile().isEmpty())
            photoLink = storageService.saveImage(userForm.getFile(), user);
        else
            photoLink = servletContext.getContextPath() + "/resources/img/avatar.png";

        Parent parent= new Parent();
        parent.setUserId(user.getId());
        parent.setName(userForm.getName());
        parent.setSurname(userForm.getSurname());
        parent.setPhone(userForm.getPhone());
        parent.setPostalCode(userForm.getPostalCode());
        parent.setStreet(userForm.getStreet());
        parent.setStreetNumber(userForm.getStreetNumber());
        parent.setPhotoLink(photoLink);
        //to be changed ,not in form
        parent.setLatitude(userForm.getLatitude());
        parent.setLongtitude(userForm.getLongtitude());
        parent.setWalletPoints(0);
        parentRepository.save(parent);
        return parent;
    }

    @Override
    public Provider makeProvider(UserForm userForm) {
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setIsBanned((byte) 0 );
        user.setRoleId(3);
        user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        userRepository.save(user);

        String photoLink;
        if (!userForm.getFile().isEmpty())
            photoLink = storageService.saveImage(userForm.getFile(), user);
        else
            photoLink = servletContext.getContextPath() + "/resources/img/avatar.png";

        Provider provider= new Provider();
        provider.setUserId(user.getId());
        provider.setName(userForm.getName());
        provider.setSurname(userForm.getSurname());
        provider.setAfm(userForm.getAfm());
        provider.setCompanyName(userForm.getCompanyName());
        provider.setDescription(userForm.getDescription());
        provider.setPhone(userForm.getPhone());
        provider.setPostalCode(userForm.getPostalCode());
        provider.setStreet(userForm.getStreet());
        provider.setStreetNumber(userForm.getStreetNumber());
        provider.setPhotoLink(photoLink);


        Date date = Date.from(ZonedDateTime.now().minusMonths(3).toInstant());
        provider.setSubscriptionExpiryDate(new java.sql.Date(date.getTime()));



        providerRepository.save(provider);

        return provider;
    }
    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(int id){
        return userRepository.findById(id);
    }


    @Override
    public List<User> findAllByRole(String role) {
        if (role.equals("parent"))
            return userRepository.findAll(isParent());
        else if (role.equals("provider"))
            return userRepository.findAll(isProvider());
        else if (role.equals("administrator"))
            return userRepository.findAll(isAdministrator());
        else if (role.equals("not_approved"))
            return userRepository.findAllByProviderById_IsApproved(0);
        else if (role.equals("all"))
            return userRepository.findAll();
        return null;

    }

    @Override
    public Parent updateParent(UserForm userForm) {
        User user=findByUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
//        user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        userRepository.save(user);

        Parent parent = user.getParentById();
        parent.setName(userForm.getName());
        parent.setSurname(userForm.getSurname());
        parent.setPhone(userForm.getPhone());
        parent.setPostalCode(userForm.getPostalCode());
        parent.setStreet(userForm.getStreet());
        parent.setStreetNumber(userForm.getStreetNumber());
        if (!userForm.getFile().isEmpty()) {
            String photoLink = storageService.saveImage(userForm.getFile(), user);
            parent.setPhotoLink(photoLink);
        }
        parentRepository.save(parent);

        return parent;
    }


    @Override
    public Provider updateProvider(UserForm userForm) {
        User user=findByUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
//        user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        userRepository.save(user);

        Provider provider = user.getProviderById();
        provider.setName(userForm.getName());
        provider.setSurname(userForm.getSurname());
        provider.setPhone(userForm.getPhone());
        provider.setPostalCode(userForm.getPostalCode());
        provider.setStreet(userForm.getStreet());
        provider.setStreetNumber(userForm.getStreetNumber());
        provider.setAfm(userForm.getAfm());
        provider.setCompanyName(userForm.getCompanyName());
        provider.setDescription(userForm.getDescription());
        if (!userForm.getFile().isEmpty()) {
            String photoLink = storageService.saveImage(userForm.getFile(), user);
            provider.setPhotoLink(photoLink);
        }
        providerRepository.save(provider);

        return provider;

    }

    @Override
    public User approveProvider(String username) {

        User user = findByUsername(username);
        Provider provider = user.getProviderById();
        provider.setIsApproved(1);
        providerRepository.save(provider);

        return user;
    }

    @Override
    public User banUser(String username) {

        User user = findByUsername(username);
        user.setIsBanned(1);
        if (user.getRoleId() ==3){
            Provider provider=user.getProviderById();
            for (Event event : provider.getEventsByUserId() ){
                event.setIsActive(0);
                }
        }
        userRepository.save(user);

        return user;
    }

    @Override
    public User unbanUser(String username) {

        User user = findByUsername(username);
        user.setIsBanned(0);
        userRepository.save(user);

        return user;
    }


    @Override
    @Transactional
    public void updateWalletParent(Parent parent,int amount){
        parent.setWalletPoints(parent.getWalletPoints()+amount);
    }

    @Override
    @Transactional
    public void updateProviderSub(Provider provider){
        if (provider.getSubscriptionExpiryDate().before(new java.util.Date())) {
            Date date = Date.from(ZonedDateTime.now().plusMonths(3).toInstant());
            provider.setSubscriptionExpiryDate(new java.sql.Date(date.getTime()));
        }
        else {
            Calendar c = Calendar.getInstance();
            c.setTime(provider.getSubscriptionExpiryDate());
            c.add(Calendar.DATE, 90);
            java.sql.Date myDate= new java.sql.Date(c.getTimeInMillis());
            provider.setSubscriptionExpiryDate(myDate);
        }
    }
}
