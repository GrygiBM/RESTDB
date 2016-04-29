package com.bm.server.database;


import com.bm.server.model.Wniosek;
import com.google.inject.Singleton;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


@Singleton
public class WniosekManager {

    private EntityManager entityManager;

    @Inject
    public WniosekManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Wniosek> getAllWnioski() {
        TypedQuery<Wniosek> typedQuery = entityManager.createQuery("SELECT a FROM Wniosek a", Wniosek.class);
        return typedQuery.getResultList();
    }

    public List<Wniosek> getByNameWniosek(String nazwa) {
        TypedQuery<Wniosek> typedQuery = entityManager.createNamedQuery("Wniosek.findByNazwa", Wniosek.class)
                .setParameter("nazwa", nazwa);
        return typedQuery.getResultList();
    }

    public List<Wniosek> getByStanWniosek(int stan) {
        TypedQuery<Wniosek> typedQuery = entityManager.createNamedQuery("Wniosek.findByStan", Wniosek.class)
                .setParameter("stan", stan);
        return typedQuery.getResultList();
    }


    public void addWniosek(Wniosek wniosek) {
        Wniosek nowyWniosek = new Wniosek(1, wniosek.getNazwa(), wniosek.getTresc());
        entityManager.getTransaction().begin();
        entityManager.persist(nowyWniosek);
        entityManager.getTransaction().commit();
        wniosek.setId(nowyWniosek.getId());
    }

    public void editWniosek(Wniosek wniosek) {
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


    public Object getByNameAndStanWniosek(String nazwa, int stan) {
        TypedQuery<Wniosek> typedQuery = entityManager.createNamedQuery("Wniosek.findByNameAndStan", Wniosek.class)
                .setParameter("stan", stan)
                .setParameter("nazwa",nazwa);
        return typedQuery.getResultList();
    }
}
