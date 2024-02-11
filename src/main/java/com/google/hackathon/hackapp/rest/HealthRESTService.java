package com.google.hackathon.hackapp.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.hackathon.hackapp.model.Member;

/**
 * This class produces a RESTful service to read the contents of the members table.
 */
@Path("/health")
@RequestScoped
public class HealthRESTService {
    @Inject
    private EntityManager em;

    @GET
    @Produces("text/plain")
    public String health() {
        // Use @SupressWarnings to force IDE to ignore warnings about "genericizing" the results of
        // this query
        @SuppressWarnings("unchecked")
        // We recommend centralizing inline queries such as this one into @NamedQuery annotations on
        // the @Entity class
        // as described in the named query blueprint:
        // https://blueprints.dev.java.net/bpcatalog/ee5/persistence/namedquery.html
        final Object result = em.createQuery("select 1 from Member").getSingleResult();
        return "OK";
    }
}
