package com.google.hackathon.hackapp.controller;

import java.util.logging.Logger;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.google.hackathon.hackapp.model.Member;

// The @Stateful annotation eliminates the need for manual transaction demarcation
@Stateful
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MemberLogin {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    public boolean login(String email, String password) throws Exception {

        try {
            log.info("Checking " + email + " " + password);
	    List results = em.createQuery(
            "SELECT c FROM Member c WHERE c.email = :email AND c.password = :password")
	    .setParameter("email", email)
            .setParameter("password", password)
            .getResultList();
	    return !results.isEmpty();
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            log.info("Exception:" + t.getMessage());
            throw ((Exception) t);
        }

    }

}
