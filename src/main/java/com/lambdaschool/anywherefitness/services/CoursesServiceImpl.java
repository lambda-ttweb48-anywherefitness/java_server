package com.lambdaschool.anywherefitness.services;

import com.lambdaschool.anywherefitness.models.Course;
import com.lambdaschool.anywherefitness.models.Instructor;
import com.lambdaschool.anywherefitness.models.Client;
import com.lambdaschool.anywherefitness.models.ClientCourses;
import com.lambdaschool.anywherefitness.repositories.CourseRepository;
import com.lambdaschool.anywherefitness.repositories.InstructorRepository;
import com.lambdaschool.anywherefitness.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the CoursesService
 */
@Service(value = "coursesService")
public class CoursesServiceImpl
        implements CoursesService
{
    /**
     * Connects this service to the Course table.
     */
    @Autowired
    private CourseRepository courserepos;

    /**
     * Connects this service to the Client table.
     */
    @Autowired
    private ClientRepository clientrepos;

    /**
     * Connects this service to the Instructor table.
     */
    @Autowired
    private InstructorRepository instructorrepos;

    @Override
    public List<Course> findAll()
    {
        List<Course> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        courserepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Course findCourseById(long id)
    {
        return courserepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course id " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        courserepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course id " + id + " not found!"));
        courserepos.deleteById(id);
    }

    @Transactional
    @Override
    public Course save(Course course)
    {
        Course newCourse = new Course();

        if (course.getCourseid() != 0)
        {
            Course oldCourse = courserepos.findById(course.getCourseid())
                    .orElseThrow(() -> new EntityNotFoundException("Course id " + course.getCourseid() + " not found!"));

            newCourse.setCourseid(course.getCourseid());
        }

        newCourse.setCoursename(course.getCoursename());
        Instructor newInstructor = instructorrepos.findById(course.getInstructor()
                .getInstructorid())
                .orElseThrow(() -> new EntityNotFoundException("Instructor id " + course.getInstructor()
                        .getInstructorid() + " not found!"));
        newCourse.setInstructor(newInstructor);

        newCourse.getClientCourses()
                .clear();
        for (ClientCourses cc : course.getClientCourses())
        {
            Client newClient = clientrepos.findById(cc.getClient().getClientid())
                    .orElseThrow(() -> new EntityNotFoundException("Instructor id " + cc.getClient()
                            .getClientid() + " not found!"));

            newCourse.getClientCourses()
                    .add(new ClientCourses(newCourse,
                            newClient));
        }

        return courserepos.save(newCourse);
    }

    @Override
    public void deleteAll(){
        courserepos.deleteAll();
    }
}