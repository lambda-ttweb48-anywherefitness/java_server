package com.lambdaschool.anywherefitness.services;

import com.lambdaschool.anywherefitness.models.Instructor;

import java.util.List;

/**
 * The service that works with the Instructor Model
 */
public interface InstructorService
{
    /**
     * Returns a list of all the Instructors
     *
     * @return List of Instructors. If no instructors, empty list.
     */
    List<Instructor> findAll();

    /**
     * Returns the instructor with the given primary key.
     *
     * @param id The primary key (long) of the instructor you seek.
     * @return The given instructor or throws an exception if not found.
     */
    Instructor findInstructorById(long id);

    /**
     * Deletes the instructor record and its course combinations from the database based off of the provided primary key
     *
     * @param id The primary key (long) of the student you seek.
     */
    void delete(long id);

    void deleteAll();

    /**
     * Given a complete instructor object, saves that object in the database.
     * If a primary key is provided, the record is completely replaced
     * If no primary key is provided, one is automatically generated and the record is added to the database.
     *
     * @param instructor the client object to be saved
     * @return the saved client object including any automatically generated fields
     */
    Instructor save(Instructor instructor);
}