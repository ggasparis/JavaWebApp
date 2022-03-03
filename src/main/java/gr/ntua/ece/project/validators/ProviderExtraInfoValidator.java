package gr.ntua.ece.project.validators;


import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.forms.EventForm;
import gr.ntua.ece.project.forms.UserForm;
import gr.ntua.ece.project.services.EventService;
import gr.ntua.ece.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

// already covered somewhat by front end but needs extra checking for amf more than 9=database constraint failure
@Component
public class ProviderExtraInfoValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserForm userForm = (UserForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (userForm.getUsername().length() < 6 || userForm.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(userForm.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (userForm.getPassword().length() < 8 || userForm.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!userForm.getPassword_confirm().equals(userForm.getPassword())) {
            errors.rejectValue("password_confirm", "Diff.userForm.password_confirm");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Size.userForm.description");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "afm", "Size.userForm.afm");
        if (userForm.getAfm().length() != 9 ) {
            errors.rejectValue("afm", "Size.userForm.afm");
        }


    }
}

