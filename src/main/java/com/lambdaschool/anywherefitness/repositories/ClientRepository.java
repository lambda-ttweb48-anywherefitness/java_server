package com.lambdaschool.anywherefitness.repositories;

import com.lambdaschool.anywherefitness.models.Client;
import org.springframework.data.repository.CrudRepository;
import javax.validation.constraints.Email;


public interface ClientRepository
    extends CrudRepository<Client, Long>
{
    Client findByEmailIgnoreCase(Email email);
}