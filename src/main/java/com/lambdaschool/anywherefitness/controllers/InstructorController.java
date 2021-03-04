package com.lambdaschool.anywherefitness.controllers;

import com.lambdaschool.anywherefitness.models.Instructor;

import com.lambdaschool.anywherefitness.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @GetMapping(value = "/instructors/{instructorid}", produces = "application/json")
    public ResponseEntity<?> getInstructorById(@PathVariable long instructorid)
    {
        Instructor instructor = instructorService.findInstructorById(instructorid);

        return new ResponseEntity<>(instructor,
                HttpStatus.OK);
    }

    @GetMapping(value = "", produces = {"application/json"})
    public ResponseEntity<?> getAllInstructors(){
        List<Instructor> instructors = instructorService.findAll();
        return new ResponseEntity<>(instructors, HttpStatus.OK);
    }

    @PostMapping(value = "/instructors",consumes = "application/json")
    public ResponseEntity<?> addUser(@Valid @RequestBody Instructor newInstructor)throws
            URISyntaxException
    {
        newInstructor.setInstructorid(0);
        newInstructor = instructorService.save(newInstructor);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{instructorid}")
                .buildAndExpand(newInstructor.getInstructorid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }
}