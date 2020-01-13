package by.sazanchuk.finalTask.entity;

import java.util.List;
import java.util.Objects;

/**
 * entity User
 */
public class User {
    private Integer id;
    private String login;
    private String password;
    private String name;
    private String email;
    private Integer phoneNumber;
    private String address;
    private Role role;

    /**
     * Instantiates a new User.
     */
    public User () {}

    /**
     * @param role role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * gets role
     *
     * @return role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param id the identity
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * gets identity
     *
     * @return identity
     */
    public Integer getId() {
        return id;
    }

    /**
     * gets login
     *
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * gets password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * gets name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * gets email
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * gets phone number
     *
     * @return phone number
     */
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * gets address
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                phoneNumber == user.phoneNumber &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(address, user.address) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, email, phoneNumber, address, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", role=" + role +
                '}';
    }
}
