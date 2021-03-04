package com.lambdaschool.anywherefitness.repositories;

import com.lambdaschool.anywherefitness.models.Instructor;
import org.springframework.data.repository.CrudRepository;
import javax.validation.constraints.Email;

public interface InstructorRepository
        extends CrudRepository<Instructor, Long>
{
    Instructor findByEmailIgnoreCase(Email email);
}