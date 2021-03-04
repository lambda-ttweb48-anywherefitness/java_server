package com.lambdaschool.anywherefitness.controllers;

import com.lambdaschool.anywherefitness.models.Client;
import com.lambdaschool.anywherefitness.models.ClientCourses;
import com.lambdaschool.anywherefitness.repositories.ClientRepository;
import com.lambdaschool.anywherefitness.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/clients/{clientid}", produces = "application/json")
    public ResponseEntity<?> getClientById(@PathVariable long clientid)
    {
        Client client = clientService.findClientById(clientid);

        return new ResponseEntity<>(client,
                HttpStatus.OK);
    }

    @GetMapping(value = "", produces = {"application/json"})
    public ResponseEntity<?> getAllClients(){
        List<Client> clients = clientService.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping(value = "/client",consumes = "application/json")
    public ResponseEntity<?> addUser(@Valid @RequestBody Client newClient)throws
            URISyntaxException
    {
        newClient.setClientid(0);
        newClient = clientService.save(newClient);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newClient.getClientid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }
}