package gr.ntua.ece.project.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name = "parent")
public class Parent {
    private int userId;
    private int walletPoints;
    private String name;
    private String surname;
    private String postalCode;
    private String street;
    private String streetNumber;
    private String phone;
    private String photoLink;
    private BigDecimal latitude;
    private BigDecimal longtitude;
    private User userByUserId;
    private Collection<Report> reportsByUserId;
    private Collection<Ticket> ticketsByUserId;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "wallet_points")
    public int getWalletPoints() {
        return walletPoints;
    }

    public void setWalletPoints(int walletPoints) {
        this.walletPoints = walletPoints;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "photo_link")
    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parent parent = (Parent) o;

        if (userId != parent.userId) return false;
        if (walletPoints != parent.walletPoints) return false;
        if (name != null ? !name.equals(parent.name) : parent.name != null) return false;
        if (surname != null ? !surname.equals(parent.surname) : parent.surname != null) return false;
        if (postalCode != null ? !postalCode.equals(parent.postalCode) : parent.postalCode != null) return false;
        if (street != null ? !street.equals(parent.street) : parent.street != null) return false;
        if (streetNumber != null ? !streetNumber.equals(parent.streetNumber) : parent.streetNumber != null)
            return false;
        if (phone != null ? !phone.equals(parent.phone) : parent.phone != null) return false;
        if (photoLink != null ? !photoLink.equals(parent.photoLink) : parent.photoLink != null) return false;
        if (latitude != null ? !latitude.equals(parent.latitude) : parent.latitude != null) return false;
        if (longtitude != null ? !longtitude.equals(parent.longtitude) : parent.longtitude != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + walletPoints;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (streetNumber != null ? streetNumber.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (photoLink != null ? photoLink.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longtitude != null ? longtitude.hashCode() : 0);
        return result;
    }

    @OneToOne
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "parentByParentUserId")
    public Collection<Report> getReportsByUserId() {
        return reportsByUserId;
    }

    public void setReportsByUserId(Collection<Report> reportsByUserId) {
        this.reportsByUserId = reportsByUserId;
    }

    @OneToMany(mappedBy = "parentByParentUserId")
    public Collection<Ticket> getTicketsByUserId() {
        return ticketsByUserId;
    }

    public void setTicketsByUserId(Collection<Ticket> ticketsByUserId) {
        this.ticketsByUserId = ticketsByUserId;
    }
}
