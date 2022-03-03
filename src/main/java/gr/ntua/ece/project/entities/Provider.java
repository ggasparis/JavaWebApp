package gr.ntua.ece.project.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "provider")
public class Provider {
    private int userId;
    private int wallet_points;
    private String companyName;
    private String description;
    private String afm;
    private int isApproved;
    private String name;
    private String surname;
    private String postalCode;
    private String street;
    private String streetNumber;
    private String phone;
    private String photoLink;
    private Date subscriptionExpiryDate;
    private Date registrationDate;
    private Collection<Event> eventsByUserId;
    private User userByUserId;

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
        return wallet_points;
    }

    public void setWalletPoints(int walletPoints) {
        this.wallet_points = walletPoints;
    }

    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "AFM")
    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    @Basic
    @Column(name = "is_approved")
    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
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
    @Column(name = "subscription_expiry_date")
    public Date getSubscriptionExpiryDate() {
        return subscriptionExpiryDate;
    }

    public void setSubscriptionExpiryDate(Date subscriptionExpiryDate) {
        this.subscriptionExpiryDate = subscriptionExpiryDate;
    }

    @Basic
    @Column(name = "registration_date")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Provider provider = (Provider) o;

        if (userId != provider.userId) return false;
        if (isApproved != provider.isApproved) return false;
        if (companyName != null ? !companyName.equals(provider.companyName) : provider.companyName != null)
            return false;
        if (description != null ? !description.equals(provider.description) : provider.description != null)
            return false;
        if (afm != null ? !afm.equals(provider.afm) : provider.afm != null) return false;
        if (name != null ? !name.equals(provider.name) : provider.name != null) return false;
        if (surname != null ? !surname.equals(provider.surname) : provider.surname != null) return false;
        if (postalCode != null ? !postalCode.equals(provider.postalCode) : provider.postalCode != null) return false;
        if (street != null ? !street.equals(provider.street) : provider.street != null) return false;
        if (streetNumber != null ? !streetNumber.equals(provider.streetNumber) : provider.streetNumber != null)
            return false;
        if (phone != null ? !phone.equals(provider.phone) : provider.phone != null) return false;
        if (photoLink != null ? !photoLink.equals(provider.photoLink) : provider.photoLink != null) return false;
        if (subscriptionExpiryDate != null ? !subscriptionExpiryDate.equals(provider.subscriptionExpiryDate) : provider.subscriptionExpiryDate != null)
            return false;
        if (registrationDate != null ? !registrationDate.equals(provider.registrationDate) : provider.registrationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (afm != null ? afm.hashCode() : 0);
        result = 31 * result + (int) isApproved;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (streetNumber != null ? streetNumber.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (photoLink != null ? photoLink.hashCode() : 0);
        result = 31 * result + (subscriptionExpiryDate != null ? subscriptionExpiryDate.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "providerByProviderUserId")
    public Collection<Event> getEventsByUserId() {
        return eventsByUserId;
    }

    public void setEventsByUserId(Collection<Event> eventsByUserId) {
        this.eventsByUserId = eventsByUserId;
    }

    @OneToOne
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
