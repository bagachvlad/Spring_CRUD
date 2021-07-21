package ua.springrest.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class User {

    public static final String ID_FIELD = "id";
    public static final String EMAIL_FIELD = "email";
    public static final String USERNAME_FIELD = "username";
    public static final String PASSWORD_FIELD = "password";
    public static final String FIRST_NAME_FIELD = "first_name";
    public static final String LAST_NAME_FIELD = "last_name";
    public static final String PHONE_NUMBER_FIELD = "phone_number";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            updatable = false ,
            nullable = false ,
            columnDefinition = "BIGINT"
    )
    private Long id;
    @Column(
            nullable = false ,
            unique = true ,
            columnDefinition = "VARCHAR(36)"
    )
    private String username;
    @Column(
            nullable = false ,
            columnDefinition = "VARCHAR(125)"
    )
    private String password;
    @Column(
            nullable = false ,
            columnDefinition = "VARCHAR(36)"
    )
    private String firstName;
    @Column(
            nullable = false ,
            columnDefinition = "VARCHAR(36)"
    )
    private String lastName;
    @Column(
            nullable = false ,
            unique = true ,
            columnDefinition = "VARCHAR(36)"
    )
    private String email;
    @Column(
            nullable = false,
            unique = true ,
            columnDefinition = "VARCHAR(36)"
    )
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private State state;

    public User(Long id, String username, String password, String firstName, String lastName, String email, String phoneNumber, Role role, State state) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.state = state;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId()) && getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword()) && getFirstName().equals(user.getFirstName()) && getLastName().equals(user.getLastName()) && getEmail().equals(user.getEmail()) && getPhoneNumber().equals(user.getPhoneNumber()) && getRole() == user.getRole() && getState() == user.getState();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getFirstName(), getLastName(), getEmail(), getPhoneNumber(), getRole(), getState());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", state=" + state +
                '}';
    }
}
