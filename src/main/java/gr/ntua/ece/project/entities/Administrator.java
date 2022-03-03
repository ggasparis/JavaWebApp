package gr.ntua.ece.project.entities;

import javax.persistence.*;

@Entity
public class Administrator {
    private int userId;
    private byte canApprove;
    private byte canAlterRights;
    private byte canAlterRoles;
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
    @Column(name = "can_approve")
    public byte getCanApprove() {
        return canApprove;
    }

    public void setCanApprove(byte canApprove) {
        this.canApprove = canApprove;
    }

    @Basic
    @Column(name = "can_alter_rights")
    public byte getCanAlterRights() {
        return canAlterRights;
    }

    public void setCanAlterRights(byte canAlterRights) {
        this.canAlterRights = canAlterRights;
    }

    @Basic
    @Column(name = "can_alter_roles")
    public byte getCanAlterRoles() {
        return canAlterRoles;
    }

    public void setCanAlterRoles(byte canAlterRoles) {
        this.canAlterRoles = canAlterRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Administrator that = (Administrator) o;

        if (userId != that.userId) return false;
        if (canApprove != that.canApprove) return false;
        if (canAlterRights != that.canAlterRights) return false;
        if (canAlterRoles != that.canAlterRoles) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (int) canApprove;
        result = 31 * result + (int) canAlterRights;
        result = 31 * result + (int) canAlterRoles;
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
}
