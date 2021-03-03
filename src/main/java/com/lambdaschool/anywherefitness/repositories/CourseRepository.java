package com.lambdaschool.anywherefitness.repositories;

import com.lambdaschool.anywherefitness.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository
        extends CrudRepository<Course, Long>
{
}