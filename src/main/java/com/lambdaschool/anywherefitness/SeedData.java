package com.lambdaschool.anywherefitness;

import com.lambdaschool.anywherefitness.models.Course;
import com.lambdaschool.anywherefitness.models.ClientCourses;
import com.lambdaschool.anywherefitness.models.Instructor;
import com.lambdaschool.anywherefitness.models.Client;
import com.lambdaschool.anywherefitness.services.ClientService;
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
    ClientService clientService;

    @Autowired
    CoursesService coursesService;

}