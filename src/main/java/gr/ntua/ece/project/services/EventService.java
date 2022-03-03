package gr.ntua.ece.project.services;

import gr.ntua.ece.project.entities.Event;
import gr.ntua.ece.project.entities.Parent;
import gr.ntua.ece.project.entities.Report;
import gr.ntua.ece.project.entities.Provider;
import gr.ntua.ece.project.forms.EventForm;
import gr.ntua.ece.project.forms.SearchForm;

import java.util.List;

public interface EventService {

    Event createEvent(EventForm eventForm, Provider provider);
    Event updateEvent(Event event);
    int updateAvailableTickets(int eventId, int tickets);
    Event findEventByTitle(String Title);
    Event findById(int id);
    List<Event> findByConstraints(SearchForm searchForm);

    int updateForTicket(Parent parent,Provider provider,Event event,int ticketAmount);
    void updateProviderWallet(Provider provider,Event event);
    void changeStatus (int eventId);
}
