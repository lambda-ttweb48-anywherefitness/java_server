package com.lambdaschool.anywherefitness.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ClientCoursesId implements Serializable
{
    /**
     * The id of the student
     */
    private long client;

    /**
     * The id of the course
     */
    private long course;

    /**
     * The default constructor
     */
    public ClientCoursesId()
    {
    }

    public long getClient() {
        return client;
    }

    public void setClient(long client) {
        this.client = client;
    }

    public long getCourse()
    {
        return course;
    }

    public void setCourse(long course)
    {
        this.course = course;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        // boolean temp = (o.getClass() instanceof Class);
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        ClientCoursesId that = (ClientCoursesId) o;
        return client == that.client &&
                course == that.course;
    }

    @Override
    public int hashCode()
    {
        return 37;
    }

}