package com.lambdaschool.anywherefitness.repositories;

import com.lambdaschool.anywherefitness.models.Instructor;
import org.springframework.data.repository.CrudRepository;

public interface InstructorRepository
        extends CrudRepository<Instructor, Long>
{
}