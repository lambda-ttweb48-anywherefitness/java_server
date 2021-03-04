package com.lambdaschool.anywherefitness.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

/**
 * The Entity allowing interaction with the clients table
 */
@Entity
@Table(name = "clients")
public class Client
        extends Auditable
{
    /**
     * The primary key (long) of the students table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long clientid;

    @Column(nullable = false,
            unique = true)
    private String username;

    /**
     * The username (String). Cannot be null and must be unique
     */
    @Column(nullable = false,
            unique = true)
    @Email
    private String primaryemail;

    /**
     * The password (String) for this user. Cannot be null. Never get displayed
     */
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    /**
     * Part of the join relationship between student and course
     * connects students to the student course combination
     */
    @OneToMany(mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "student",
            allowSetters = true)
    private Set<ClientCourses> courses = new HashSet<>();

    /**
     * Default constructor used primarily by the JPA.
     */
    public Client()
    {
    }

    public Client(
            String username,
            String primaryemail,
            String password)
    {
        this.username = username;
        this.primaryemail = primaryemail;
        setPassword(password);
    }

    /**
     * Getter for the Client Id
     *
     * @return the client id, primary key, (long) of this student
     */
    public long getClientid() {
        return clientid;
    }

    /**
     * Setter for Client id
     *
     * @param clientid the new primary key (long) for this student
     */
    public void setClientid(long clientid) {
        this.clientid = clientid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrimaryemail() {
        return primaryemail;
    }

    public void setPrimaryemail(String primaryemail) {
        this.primaryemail = primaryemail;
    }

    /**
     * Getter for the password
     *
     * @return the password (String) of the user
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Setter for password to be used internally, after the password has already been encrypted
     *
     * @param password the new password (String) for the user. Comes in encrypted and stays that way
     */
    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    /**
     * @param password the new password (String) for this user. Comes in plain text and goes out encrypted
     */
    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }
    /**
     * Getter for courses associated with this student
     *
     * @return list of student courses combinations associated with this student
     */
    public Set<ClientCourses> getCourses()
    {
        return courses;
    }

    /**
     * Setter for courses associated with this student
     *
     * @param courses the new list of student courses combinations associated with this student
     */
    public void setCourses(Set<ClientCourses> courses)
    {
        this.courses = courses;
    }
}