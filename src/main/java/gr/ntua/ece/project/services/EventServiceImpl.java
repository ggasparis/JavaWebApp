package gr.ntua.ece.project.services;

import gr.ntua.ece.project.entities.*;
import gr.ntua.ece.project.forms.EventForm;
import gr.ntua.ece.project.forms.SearchForm;
import gr.ntua.ece.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import static gr.ntua.ece.project.specifications.EventSpecification.isValid;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventPhotosRepository eventPhotosRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    ProviderRepository providerRepository;

    @Override
    public Event createEvent(EventForm eventForm, Provider provider){
        Event event = new Event();
        event.setTitle(eventForm.getTitle());
        event.setCategoryId(categoryRepository.findByName(eventForm.getCategory()).getId());
        event.setDescription(eventForm.getDescription());
        event.setTags("");
        event.setAgeFrom(eventForm.getAgeFrom());
        event.setAgeTo(eventForm.getAgeTo());
        event.setLatitude(eventForm.getLatitude());
        event.setLongtitude(eventForm.getLongtitude());
        event.setPostalCode(eventForm.getPostalCode());
        event.setStreet(eventForm.getStreet());
        event.setStreetNumber(eventForm.getStreetNumber());
        event.setIsActive(1);
        event.setDateTime(Timestamp.valueOf(eventForm.getDate() + " " + eventForm.getTime() + ":00.000"));
        event.setTicketPrice(eventForm.getTicketPrice());
        event.setAvailableTickets(eventForm.getAvailableTickets());
        event.setTotalTickets(eventForm.getAvailableTickets());


        event.setProviderUserId(provider.getUserId());

        eventRepository.save(event);

        EventPhotos eventPhotos;
        String link;
        for (MultipartFile file : eventForm.getFile()) {
            System.out.println(file.getOriginalFilename());
            eventPhotos = new EventPhotos();
            eventPhotos.setEventByEventId(event);
            if (!file.isEmpty())
                link = storageService.saveImage(file, event);
            else
                link = servletContext.getContextPath() + "/resources/img/playground.png";
            eventPhotos.setLink(link);
            eventPhotosRepository.save(eventPhotos);
        }

        return event;

    }


    @Override
    public List<Event> findByConstraints(SearchForm searchForm){
        List<Event> events=eventRepository.findAll(isValid(searchForm));
        for (Iterator<Event> iter = events.listIterator(); iter.hasNext(); ) {
            Event event = iter.next();
            if (distance(event.getLatitude().doubleValue(),searchForm.getLatitude().doubleValue(),
                            event.getLongtitude().doubleValue(),searchForm.getLongtitude().doubleValue(),0,0)/1000>searchForm.getRadius().doubleValue()) {
                iter.remove();
                System.out.println(event.getTitle());
            }
        }
        return events;
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public int updateAvailableTickets(int eventId, int tickets) {
        return eventRepository.updateAvailableTickets(eventId, tickets);
    }

    @Override
    @Transactional
    public int updateForTicket(Parent parent,Provider provider,Event event ,int ticketAmount){
        if (ticketAmount>event.getAvailableTickets())
            return 0;
        if ((ticketAmount*(event.getTicketPrice().doubleValue()*100))>parent.getWalletPoints())
            return 0;
        parent.setWalletPoints(parent.getWalletPoints()-(int)(ticketAmount*(event.getTicketPrice().doubleValue()*100)));
        //provider.setWalletPoints(provider.getWalletPoints()+(int)(ticketAmount*(event.getTicketPrice().doubleValue()*100)));
        if (updateAvailableTickets(event.getId(),ticketAmount)==0) return 0;
        else
            return 1;
    }

    @Override
    @Transactional
    public void updateProviderWallet( Provider provider,Event event){
        int ticketAmount= event.getTotalTickets()-event.getAvailableTickets();
        provider.setWalletPoints(provider.getWalletPoints()+(int)(ticketAmount*(event.getTicketPrice().doubleValue()*100)));
        event.setIsActive(0);
    }

    @Override
    @Transactional
    public void changeStatus(int eventId){
        Event event=eventRepository.findById(eventId);
        if (event.getIsActive()==1)
            event.setIsActive(0);

        else
            event.setIsActive(1);
    }

    @Override
    public Event findEventByTitle(String Title) {

        return eventRepository.findEventByTitle(Title);
    }

    @Override
    public Event findById(int id) {

        return eventRepository.findById(id);
    }



    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
