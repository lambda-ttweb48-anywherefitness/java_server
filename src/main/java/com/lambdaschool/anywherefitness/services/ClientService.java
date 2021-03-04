package com.lambdaschool.anywherefitness.services;

import com.lambdaschool.anywherefitness.models.Client;

import java.util.List;

/**
 * The service that works with the Client Model
 */
public interface ClientService
{
    /**
     * Returns a list of all the Clients
     *
     * @return List of Clients. If no clients, empty list.
     */
    List<Client> findAll();

    /**
     * Returns the client with the given primary key.
     *
     * @param id The primary key (long) of the client you seek.
     * @return The given client or throws an exception if not found.
     */
    Client findClientById(long id);

    /**
     * Deletes the client record and its client and course combinations from the database based off of the provided primary key
     *
     * @param id The primary key (long) of the student you seek.
     */
    void delete(long id);

    void deleteAll();

    /**
     * Given a complete client object, saves that client object in the database.
     * If a primary key is provided, the record is completely replaced
     * If no primary key is provided, one is automatically generated and the record is added to the database.
     *
     * @param client the client object to be saved
     * @return the saved client object including any automatically generated fields
     */
    Client save(Client client);
}