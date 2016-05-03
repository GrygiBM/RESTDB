package com.bm.server.database;


import com.bm.server.model.Historiazmian;
import com.google.inject.Singleton;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Singleton
public class HistoryModificationsManager {

    private EntityManager entityManager;

    @Inject
    public HistoryModificationsManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Historiazmian> getAllHistory() {
        TypedQuery<Historiazmian> typedQuery = entityManager.createQuery("SELECT a FROM Historiazmian a", Historiazmian.class);
        return typedQuery.getResultList();
    }


    public void addHistory(Historiazmian historiazmian) {
        entityManager.persist(historiazmian);
    }

    public void addHistory(int id_wniosku, String opis) {

        entityManager.persist(new Historiazmian(id_wniosku, opis));
    }

    public void addHistory(int id_wniosku, String stan, String opis) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        opis =  opis.concat(" " + dateFormat.format(date).toUpperCase());
        entityManager.persist(new Historiazmian(id_wniosku, stan, opis));
    }


    public void editHistory(Historiazmian historiazmian) {
        entityManager.merge(historiazmian);
    }

    public List<Historiazmian> findRange(int[] range) {

        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Historiazmian.class));
        javax.persistence.Query q = entityManager.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public Historiazmian find(Object id) {
        return entityManager.find(Historiazmian.class, id);
    }

    public void remove(Historiazmian entity) {

        entityManager.remove(entityManager.merge(entity));
    }

    public List<Historiazmian> getByRequest(int idWniosek) {
        TypedQuery<Historiazmian> typedQuery = entityManager.createNamedQuery("Historiazmian.findByIdWniosek", Historiazmian.class)
                .setParameter("idWniosek", idWniosek);
        return typedQuery.getResultList();
    }
}
