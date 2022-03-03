package gr.ntua.ece.project.services;

import gr.ntua.ece.project.entities.Event;
import gr.ntua.ece.project.entities.Parent;
import gr.ntua.ece.project.entities.Ticket;
import gr.ntua.ece.project.entities.Provider;
import gr.ntua.ece.project.forms.EventForm;
import gr.ntua.ece.project.forms.SearchForm;

import java.io.OutputStream;
import java.util.List;

public interface TicketService {

    Ticket createTicket(Event event, Parent parent, int quantity);
    Ticket findById(int id);
    void sendEmail(Event event, Parent parent, Ticket ticket);
    void writePdf(OutputStream outputStream, String content) throws Exception;
}
