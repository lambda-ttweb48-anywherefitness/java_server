package com.lambdaschool.anywherefitness.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The entity allowing interaction with the clientcourses table.
 * The join table between course and students
 */
@Entity
@Table(name = "clientcourses")
@IdClass(ClientCoursesId.class)
public class ClientCourses
        extends Auditable
        implements Serializable
{
    /**
     * Foreign key into the course table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "courseid")
    @JsonIgnoreProperties("clients")
    private Course course;

    /**
     * Foreign key into the student table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "clientid")
    @JsonIgnoreProperties("courses")
    private Client client;

    public ClientCourses()
    {
    }

    /**
     * Given the params, create a new course student combination
     *
     * @param course  The course object of the course student combination
     * @param client The client object of the course student combination
     */
    public ClientCourses(
            Course course,
            Client client)
    {
        this.course = course;
        this.client = client;
    }

    /**
     * The getter for course
     *
     * @return the complete course object of this course student combination
     */
    public Course getCourse()
    {
        return course;
    }

    /**
     * The setter for course
     *
     * @param course change the course object associated with this course student combination to this one.
     */
    public void setCourse(Course course)
    {
        this.course = course;
    }

    /**
     * The getter for client
     *
     * @return the complete client object of this course client combination
     */
    public Client getClient()
    {
        return client;
    }

    /**
     * The setter for client
     *
     * @param client change the student object associated with this course student combination to this one.
     */
    public void setClient(Client client)
    {
        this.client = client;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        ClientCourses that = (ClientCourses) o;
        return ((this.client == null) ? 0 : this.client.getClientid()) == ((that.client == null) ? 0 : that.client.getClientid()) &&
                ((this.course == null) ? 0 : this.course.getCourseid()) == ((that.course == null) ? 0 : that.course.getCourseid());
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
