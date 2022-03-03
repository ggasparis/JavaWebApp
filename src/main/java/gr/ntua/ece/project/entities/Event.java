package gr.ntua.ece.project.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import javax.validation.constraints.Min;


@Entity
@Table(name = "event")
public class Event {
    private int id;
    private int providerUserId;
    private int categoryId;
    private String title;
    private String description;
    private String tags;
    private int ageFrom;
    private int ageTo;
    private BigDecimal latitude;
    private BigDecimal longtitude;
    private String postalCode;
    private String street;
    private String streetNumber;
    private Timestamp dateTime;
    private BigDecimal ticketPrice;
    private Integer totalTickets;
    private Integer availableTickets;
    private Integer isActive;
    private Provider providerByProviderUserId;
    private Category categoryByCategoryId;
    private Collection<EventPhotos> eventPhotosById;
    private Collection<Report> reportsById;
    private Collection<Ticket> ticketsById;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "provider_user_id")
    public int getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(int providerUserId) {
        this.providerUserId = providerUserId;
    }

    @Basic
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "tags")
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Basic
    @Column(name = "age_from")
    public int getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(int ageFrom) {
        this.ageFrom = ageFrom;
    }

    @Basic
    @Column(name = "age_to")
    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }

    @Basic
    @Column(name = "latitude")
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longtitude")
    public BigDecimal getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(BigDecimal longtitude) {
        this.longtitude = longtitude;
    }

    @Basic
    @Column(name = "postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "street_number")
    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Basic
    @Column(name = "date_time")
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Basic
    @Column(name = "ticket_price")
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Basic
    @Column(name = "total_tickets")
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    @Basic
    @Column(name = "available_tickets")
    @Min(value = 0, message="The value must be positive")
    public Integer getAvailableTickets() { return availableTickets ; }

    public void setAvailableTickets(Integer availableTickets) {
        this.availableTickets = availableTickets;
    }

    @Basic
    @Column(name = "is_active")
    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (providerUserId != event.providerUserId) return false;
        if (categoryId != event.categoryId) return false;
        if (ageFrom != event.ageFrom) return false;
        if (ageTo != event.ageTo) return false;
        if (title != null ? !title.equals(event.title) : event.title != null) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        if (tags != null ? !tags.equals(event.tags) : event.tags != null) return false;
        if (latitude != null ? !latitude.equals(event.latitude) : event.latitude != null) return false;
        if (longtitude != null ? !longtitude.equals(event.longtitude) : event.longtitude != null) return false;
        if (postalCode != null ? !postalCode.equals(event.postalCode) : event.postalCode != null) return false;
        if (street != null ? !street.equals(event.street) : event.street != null) return false;
        if (streetNumber != null ? !streetNumber.equals(event.streetNumber) : event.streetNumber != null) return false;
        if (dateTime != null ? !dateTime.equals(event.dateTime) : event.dateTime != null) return false;
        if (ticketPrice != null ? !ticketPrice.equals(event.ticketPrice) : event.ticketPrice != null) return false;
        if (availableTickets != null ? !availableTickets.equals(event.availableTickets) : event.availableTickets != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + providerUserId;
        result = 31 * result + categoryId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + ageFrom;
        result = 31 * result + ageTo;
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longtitude != null ? longtitude.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (streetNumber != null ? streetNumber.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (ticketPrice != null ? ticketPrice.hashCode() : 0);
        result = 31 * result + (availableTickets != null ? availableTickets.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "provider_user_id", referencedColumnName = "user_id", nullable = false, updatable = false, insertable = false)
    public Provider getProviderByProviderUserId() {
        return providerByProviderUserId;
    }

    public void setProviderByProviderUserId(Provider providerByProviderUserId) {
        this.providerByProviderUserId = providerByProviderUserId;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public Category getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(Category categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    @OneToMany(mappedBy = "eventByEventId")
    public Collection<EventPhotos> getEventPhotosById() {
        return eventPhotosById;
    }

    public void setEventPhotosById(Collection<EventPhotos> eventPhotosById) {
        this.eventPhotosById = eventPhotosById;
    }

    @OneToMany(mappedBy = "eventByEventId")
    public Collection<Report> getReportsById() {
        return reportsById;
    }

    public void setReportsById(Collection<Report> reportsById) {
        this.reportsById = reportsById;
    }

    @OneToMany(mappedBy = "eventByEventId")
    public Collection<Ticket> getTicketsById() {
        return ticketsById;
    }

    public void setTicketsById(Collection<Ticket> ticketsById) {
        this.ticketsById = ticketsById;
    }
}
