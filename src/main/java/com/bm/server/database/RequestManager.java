package com.bm.server.database;


import com.bm.server.model.Wniosek;
import com.google.inject.Singleton;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


@Singleton
public class RequestManager {

    private EntityManager entityManager;

    @Inject
    public RequestManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Wniosek> getAllRequests() {
        TypedQuery<Wniosek> typedQuery = entityManager.createQuery("SELECT a FROM Wniosek a", Wniosek.class);
        return typedQuery.getResultList();
    }

    public List<Wniosek> getByNameRequest(String nazwa) {
        TypedQuery<Wniosek> typedQuery = entityManager.createNamedQuery("Wniosek.findByNazwa", Wniosek.class)
                .setParameter("nazwa", nazwa);
        return typedQuery.getResultList();
    }

    public List<Wniosek> getByStateRequest(int stan) {
        TypedQuery<Wniosek> typedQuery = entityManager.createNamedQuery("Wniosek.findByStan", Wniosek.class)
                .setParameter("stan", stan);
        return typedQuery.getResultList();
    }


    public void addRequest(Wniosek wniosek) {
        Wniosek nowyWniosek = new Wniosek(1, wniosek.getNazwa(), wniosek.getTresc());
        entityManager.getTransaction().begin();
        entityManager.persist(nowyWniosek);
        entityManager.getTransaction().commit();
        wniosek.setId(nowyWniosek.getId());
    }

    public void editRequest(Wniosek wniosek) {
        entityManager.getTransaction().begin();
        entityManager.merge(wniosek);
        entityManager.getTransaction().commit();
    }

    public List<Wniosek> findRange(int[] range) {
        TypedQuery<Wniosek> typedQuery = entityManager.createQuery("SELECT a FROM Wniosek a ", Wniosek.class);
        return typedQuery.getResultList();

    }

    public Wniosek find(Object id) {
        return entityManager.find(Wniosek.class, id);
    }

    public void remove(Wniosek entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(entity));
        entityManager.getTransaction().commit();
    }


    public Object getByNameAndStateRequest(String nazwa, int stan) {
        TypedQuery<Wniosek> typedQuery = entityManager.createNamedQuery("Wniosek.findByNameAndStan", Wniosek.class)
                .setParameter("stan", stan)
                .setParameter("nazwa",nazwa);
        return typedQuery.getResultList();
    }
}
