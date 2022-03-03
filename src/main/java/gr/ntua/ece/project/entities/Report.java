package gr.ntua.ece.project.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Report {
    private int id;
//    private int parentUserId; menoyn gia reference argotera
//    private int eventId;
    private Timestamp dateTime;
    private String comments;
    private Parent parentByParentUserId;
    private Event eventByEventId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "parent_user_id")
//    public int getParentUserId() {
//        return parentUserId;
//    }
//
//    public void setParentUserId(int parentUserId) {
//        this.parentUserId = parentUserId;
//    }
//
//    @Basic
//    @Column(name = "event_id")
//    public int getEventId() {
//        return eventId;
//    }
//
//    public void setEventId(int eventId) {
//        this.eventId = eventId;
//    }

    @Basic
    @Column(name = "date_time")
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Basic
    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (id != report.id) return false;
        //if (parentUserId != report.parentUserId) return false;
        //if (eventId != report.eventId) return false;
        if (dateTime != null ? !dateTime.equals(report.dateTime) : report.dateTime != null) return false;
        if (comments != null ? !comments.equals(report.comments) : report.comments != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        //result = 31 * result + parentUserId;
       // result = 31 * result + eventId;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "parent_user_id", referencedColumnName = "user_id",nullable = false)
    public Parent getParentByParentUserId() {
        return parentByParentUserId;
    }

    public void setParentByParentUserId(Parent parentByParentUserId) {
        this.parentByParentUserId = parentByParentUserId;
    }

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    public Event getEventByEventId() {
        return eventByEventId;
    }

    public void setEventByEventId(Event eventByEventId) {
        this.eventByEventId = eventByEventId;
    }
}
