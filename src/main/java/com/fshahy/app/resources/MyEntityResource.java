package com.fshahy.app.resources;

import java.util.List;

import com.fshahy.app.models.MyEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;;

@Path("myentity")
public class MyEntityResource {
    @PersistenceContext(unitName = "pu1")
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MyEntity> getAllEntities() {
        return entityManager.createNamedQuery("selectAll", MyEntity.class).getResultList();
    }
}
