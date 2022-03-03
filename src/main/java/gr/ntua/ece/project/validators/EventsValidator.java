package gr.ntua.ece.project.validators;

import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.forms.EventForm;
import gr.ntua.ece.project.forms.UserForm;
import gr.ntua.ece.project.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EventsValidator implements Validator {
    @Autowired
    private EventService eventService;

    @Override
    public boolean supports(Class<?> aClass) {
        return EventForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EventForm eventForm =(EventForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Size.eventForm.TitleEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Size.eventForm.DescriptionEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "Size.eventForm.StreetEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "latitude", "Size.eventForm.geo");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "longtitude", "Size.eventForm.geo");


        if (eventForm.getDescription().length() < 6 ) {
            errors.rejectValue("description", "Size.eventForm.description");
        }
        if (eventService.findEventByTitle(eventForm.getTitle()) != null) {
            errors.rejectValue("title", "Duplicate.eventForm.title");
        }
    }
}
