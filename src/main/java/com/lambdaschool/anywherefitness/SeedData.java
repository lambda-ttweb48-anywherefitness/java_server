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
        testInstructor1.setUsername("Esteban Francis");
        testInstructor1.setPrimaryemail("testinstructor1@gmail.com");
        testInstructor1.setPassword("Test123!!");

        Instructor testInstructor2 = new Instructor();
        testInstructor2.setUsername("Kim Su Yin");
        testInstructor1.setPrimaryemail("testinstructor2@gmail.com");
        testInstructor1.setPassword("Test123!!");

        Course testCourse1 = new Course();
        testCourse1.setCoursename("Spin 3");
        testCourse1.setIntensity("insanity");
        testCourse1.setDuration("90min");
        testCourse1.setCourselimit(34);
        testCourse1.setCoursestart("04/13/2021");

        Course testCourse2 = new Course();
        testCourse2.setCoursename("Sculpt 2");
        testCourse2.setIntensity("yoga");
        testCourse2.setDuration("60min");
        testCourse2.setCourselimit(34);
        testCourse2.setCoursestart("04/23/2021");

        Client testClient1 = new Client();
        testClient1.setUsername("Jennifer Smith");
        testClient1.setPrimaryemail("testclient2@gmail.com");
        testClient1.setPassword("Test123!!");

        Client testClient2 = new Client();
        testClient2.setUsername("Billy Bob");
        testClient2.setPrimaryemail("testclient2@gmail.com");
        testClient2.setPassword("Test123!!");


    }

}