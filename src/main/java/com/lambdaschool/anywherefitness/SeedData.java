package com.lambdaschool.anywherefitness;

import com.lambdaschool.anywherefitness.models.Course;
import com.lambdaschool.anywherefitness.models.ClientCourses;
import com.lambdaschool.anywherefitness.models.Instructor;
import com.lambdaschool.anywherefitness.models.Client;
import com.lambdaschool.anywherefitness.services.ClientService;
import com.lambdaschool.anywherefitness.services.InstructorService;
import com.lambdaschool.anywherefitness.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ConditionalOnProperty(
        prefix = "command.line.runner",
        value="enabled",
        havingValue="true",
        matchIfMissing = true
)
@Component
public class SeedData
        implements CommandLineRunner{

    @Autowired
    InstructorService instructorService;

    @Autowired
    CoursesService coursesService;


    @Autowired
    ClientService clientService;

    @Transactional
    @Override
    public void run(String[] args) throws Exception{
        instructorService.deleteAll();
        clientService.deleteAll();
        coursesService.deleteAll();

        Instructor testInstructor1 = new Instructor();
        testInstructor1.setPrimaryemail("testinstructor1@gmail.com");
        testInstructor1.setPassword("Test123!!");

        Instructor testInstructor2 = new Instructor();
        testInstructor1.setPrimaryemail("testinstructor2@gmail.com");
        testInstructor1.setPassword("Test123!!");
    }

}