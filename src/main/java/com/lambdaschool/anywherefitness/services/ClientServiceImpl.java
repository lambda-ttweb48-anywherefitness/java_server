package com.lambdaschool.anywherefitness.services;

import com.lambdaschool.anywherefitness.exceptions.ResourceNotFoundException;
import com.lambdaschool.anywherefitness.models.Course;
import com.lambdaschool.anywherefitness.models.Client;
import com.lambdaschool.anywherefitness.models.ClientCourses;
import com.lambdaschool.anywherefitness.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the ClientService Interface
 */
@Service(value = "clientservice")
public class ClientServiceImpl
        implements ClientService
{
        /**
         * Connects this service to the Client table.
         */
        @Autowired
        private ClientRepository clientrepos;

        /**
         * Connects this service to the course service.
         */
        @Autowired
        private CoursesService coursesService;

        @Override
        public List<Client> findAll()
        {
            List<Client> list = new ArrayList<>();
            /*
             * findAll returns an iterator set.
             * iterate over the iterator set and add each element to an array list.
             */
            clientrepos.findAll()
                    .iterator()
                    .forEachRemaining(list::add);
            return list;
        }

        @Override
        public Client findClientById(long id)
        {
            return clientrepos.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Client id " + id + " not found!"));
        }

        @Transactional
        @Override
        public void delete(long id)
        {
            clientrepos.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Client id " + id + " not found!"));
            clientrepos.deleteById(id);
        }

        @Transactional
        @Override
        public Client save(Client client)
        {
            Client newClient = new Client();

            if (client.getClientid() != 0)
            {
                Client oldClient = clientrepos.findById(client.getClientid())
                        .orElseThrow(() -> new ResourceNotFoundException("Client id " + client.getClientid() + " not found!"));

                newClient.setClientid(client.getClientid());
            }

            newClient.setPrimaryemail(client.getPrimaryemail());

            newClient.getCourses()
                    .clear();
            for (ClientCourses cc : client.getCourses())
            {
                Course newCourse = coursesService.findCourseById(cc.getCourse()
                        .getCourseid());

                newClient.getCourses()
                        .add(new ClientCourses(newCourse,
                                newClient))
                ;
            }

            return clientrepos.save(newClient);
        }

    @Override
    public void deleteAll(){
        clientrepos.deleteAll();
    }

    @Override
    public Client findClientByEmail(Email email)
    {
        Client ce = clientrepos.findByEmailIgnoreCase(email);

        if (ce != null)
        {
            return ce;
        } else
        {
            throw new ResourceNotFoundException("Client unavailable. This resource might have been moved or deleted.");
        }
    }
}
