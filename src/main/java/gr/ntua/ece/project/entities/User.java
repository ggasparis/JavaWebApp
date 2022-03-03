package gr.ntua.ece.project.entities;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private int roleId;
    private int isBanned;
    private Administrator administratorById;
    private Parent parentById;
    private Provider providerById;

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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "is_banned")
    public int getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(int isBanned) {
        this.isBanned = isBanned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (roleId != user.roleId) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + roleId;
        return result;
    }

    @OneToOne(mappedBy = "userByUserId", cascade=CascadeType.ALL)
    public Administrator getAdministratorById() {
        return administratorById;
    }

    public void setAdministratorById(Administrator administratorById) {
        this.administratorById = administratorById;
    }

    @OneToOne(mappedBy = "userByUserId", cascade=CascadeType.ALL)
    public Parent getParentById() {
        return parentById;
    }

    public void setParentById(Parent parentById) {
        this.parentById = parentById;
    }

    @OneToOne(mappedBy = "userByUserId", cascade=CascadeType.ALL)
    public Provider getProviderById() {
        return providerById;
    }

    public void setProviderById(Provider providerById) {
        this.providerById = providerById;
    }

}
