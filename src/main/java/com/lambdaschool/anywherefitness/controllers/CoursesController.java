package com.lambdaschool.anywherefitness.controllers;

import com.lambdaschool.anywherefitness.models.ClientCourses;
import com.lambdaschool.anywherefitness.models.Client;
import com.lambdaschool.anywherefitness.models.Course;
import com.lambdaschool.anywherefitness.repositories.ClientRepository;
import com.lambdaschool.anywherefitness.repositories.CourseRepository;
import com.lambdaschool.anywherefitness.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CoursesController {
    @Autowired
    private CoursesService courseserv;

    @Autowired
    private ClientRepository clientrepos;

    @GetMapping(value="", produces = {"application/json"})
    public ResponseEntity<?> getAllCourses(){
        List<Course> courselist = courseserv.findAll();

        return new ResponseEntity<>(courselist, HttpStatus.OK);
    }

    @GetMapping(value="/courses/{courseid}", produces = {"application/json"})
    public ResponseEntity<?> getCourseById(@PathVariable long courseid){
        Course course = courseserv.findCourseById(courseid);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping(value="/courses", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewCourse(@RequestBody Course newcourse){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client workingClient = clientrepos.findByUsername(authentication.getName());

        newcourse.setCourseid(0);
        newcourse.setClientCourses((Set<ClientCourses>) workingClient);
        Course saved = courseserv.save(newcourse);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping(value="/courses/{courseid}")
    public ResponseEntity<?> putUpdateCourse(@RequestBody Course updatecourse, @PathVariable long courseid){
        Course updatedcourse = courseserv.save(updatecourse);
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @DeleteMapping(value = "/courses/{courseid}")
    public ResponseEntity<?> deleteCourseById(@PathVariable long courseid){
        courseserv.delete(courseid);
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }
}