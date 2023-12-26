package com.fshahy.app.resources;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Path;

@Path("myentity")
public class MyEntityResource {
    @PersistenceContext
    private EntityManager entityManager;
}
