package com.lambdaschool.anywherefitness.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * The entity allowing interaction with the courses table
 */
@Entity
@Table(name = "courses")
public class Course
        extends Auditable
{
    /**
     * Primary key (long) for this course
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseid;

    /**
     * Name (String) of this Course. Cannot be null and must be unique
     */
    @Column(nullable = true,
            unique = true)
    private String coursename;
    private String coursestart;
    private String duration;
    private int courselimit;
    private String intensity;
    private String location;

    /**
     * The instructor object (Instructor) of this course
     * <br>
     * Forms a Many to one relationship between course and instructor.
     * An instructor has many courses!
     */
    @OneToOne
    @JoinColumn(name = "instructorid",
            nullable = false)
    @JsonIgnoreProperties(value = "courses",
            allowSetters = true)
    private Instructor instructor;

    /**
     * Part of the join relationship between course and client
     * connects course to a course student combination
     */
    @OneToMany(mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "course",
            allowSetters = true)
    private Set<ClientCourses> clients = new HashSet<>();

    /**
     * Getter for primary key of this course
     *
     * @return the primary key (long) for this course
     */
    public long getCourseid()
    {
        return courseid;
    }

    /**
     * Setter for the primary key of this course
     *
     * @param courseid the new primary key (long) for this course
     */
    public void setCourseid(long courseid)
    {
        this.courseid = courseid;
    }

    /**
     * Getter for the name of this course
     *
     * @return The name (String) of this course
     */
    public String getCoursename()
    {
        return coursename;
    }

    /**
     * Setter for the name of this course
     *
     * @param coursename the new name (String) for this course
     */
    public void setCoursename(String coursename)
    {
        this.coursename = coursename;
    }

    public int getCourselimit() {
        return courselimit;
    }

    public void setCourselimit(int courselimit) {
        this.courselimit = courselimit;
    }

    public String getCoursestart() {
        return coursestart;
    }

    public void setCoursestart(String coursestart) {
        this.coursestart = coursestart;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Getter for the course client combinations for this course
     *
     * @return A list of course client combinations for this course
     */
    public Set<ClientCourses> getClientCourses()
    {
        return clients;
    }

    /**
     * Setter for the course client combinations for this course
     *
     * @param clients A new list of course client combinations associated with course
     */
    public void setClientCourses(Set<ClientCourses> clients)
    {
        this.clients = clients;
    }

    /**
     * Getter for the instructor assigned to this course
     *
     * @return the full instructor object assigned to this course.
     */
    public Instructor getInstructor()
    {
        return instructor;
    }

    /**
     * Setter for instructor
     *
     * @param instructor the new instructor for this course
     */
    public void setInstructor(Instructor instructor)
    {
        this.instructor = instructor;
    }
}