package com.lambdaschool.anywherefitness.repositories;

import com.lambdaschool.anywherefitness.models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository
    extends CrudRepository<Client, Long>
{
}