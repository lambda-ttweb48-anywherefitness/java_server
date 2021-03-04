package com.lambdaschool.anywherefitness.services;

import com.lambdaschool.anywherefitness.exceptions.ResourceNotFoundException;
import com.lambdaschool.anywherefitness.models.Client;
import com.lambdaschool.anywherefitness.models.ClientCourses;
import com.lambdaschool.anywherefitness.models.Instructor;
import com.lambdaschool.anywherefitness.models.Course;
import com.lambdaschool.anywherefitness.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the InstructorService Interface
 */
@Service(value = "instructorservice")
public class InstructorServiceImpl
        implements InstructorService
{
    /**
     * Connects this service to the Instructor table.
     */
    @Autowired
    private InstructorRepository instructorrepos;

    /**
     * Connects this service to the course service.
     */
    @Autowired
    private CoursesService coursesService;

    @Override
    public List<Instructor> findAll()
    {
        List<Instructor> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        instructorrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Instructor findInstructorById(long id)
    {
        return instructorrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor id " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        instructorrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor id " + id + " not found!"));
        instructorrepos.deleteById(id);
    }

    @Transactional
    @Override
    public Instructor save(Instructor instructor)
    {
        Instructor newInstructor = new Instructor();

        if (instructor.getInstructorid() != 0)
        {
            Instructor oldInstructor = instructorrepos.findById(instructor.getInstructorid())
                    .orElseThrow(() -> new ResourceNotFoundException("Instructor id " + instructor.getInstructorid() + " not found!"));

            newInstructor.setInstructorid(instructor.getInstructorid());
        }

        newInstructor.setPrimaryemail(instructor.getPrimaryemail());

        newInstructor.getCourses()
                .clear();

        return instructorrepos.save(newInstructor);
    }
}
