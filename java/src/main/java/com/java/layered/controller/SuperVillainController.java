package com.java.layered.controller;

import com.java.layered.repository.SuperVillainRepository;
import com.java.layered.service.SuperVillainService;

public class SuperVillainController {
    final SuperVillainService service;
    final SuperVillainRepository repository;
    SuperVillainController(SuperVillainService service, SuperVillainRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    public SuperVillainService service() {return service;}

    public SuperVillainRepository repository() {return repository;}
}
