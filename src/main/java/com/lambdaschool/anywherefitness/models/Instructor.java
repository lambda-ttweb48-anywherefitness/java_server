package com.lambdaschool.anywherefitness.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

/**
 * The entity allowing interaction with the instructors table
 */
@Entity
@Table(name = "instructors")
public class Instructor
        extends Auditable
{
    /**
     * The primary key (long) of the instructor table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long instructorid;

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
     * List of courses associated with this instructor. Does not get saved in the database directly.
     * Forms a one to many relationship with courses. One instructor to many courses.
     */
    @OneToMany(mappedBy = "instructor",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "instructor",
            allowSetters = true)
    private List<Course> courses = new ArrayList<>();

    /**
     * Default Constructor used primarily by the JPA.
     */
    public Instructor()
    {
    }

    /**
     * Given the name of instructor, add them
     *
     * @param primaryemail The primaryemail (String) for the new instructor
     */
    public Instructor(
            String primaryemail,
            String password)
    {
        setPassword(password);
    }

    /**
     * Getter for the instructor id
     *
     * @return The primary key (long) for this instructor
     */
    public long getInstructorid()
    {
        return instructorid;
    }

    /**
     * Setter for the instructor id
     *
     * @param instructorid The new primary key (long) for this instructor
     */
    public void setInstructorid(long instructorid)
    {
        this.instructorid = instructorid;
    }

    /**
     * the instructor email
     *
     * @return the instructor email
     */
    public String getPrimaryemail() {
        return primaryemail;
    }

    /**
     * Setter for the instructor name
     *
     * @param primaryemail the new instructor name (String) for this instructor
     */
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
     * Getter for courses
     *
     * @return The list of courses this instructor is teaching
     */
    public List<Course> getCourses()
    {
        return courses;
    }

    /**
     * Setter for courses
     *
     * @param courses The new list of courses this instructor is teaching
     */
    public void setCourses(List<Course> courses)
    {
        this.courses = courses;
    }
}
