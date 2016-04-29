package com.bm.server.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = "Historiazmian.findAll", query = "SELECT h FROM Historiazmian h"),
        @NamedQuery(name = "Historiazmian.findById", query = "SELECT h FROM Historiazmian h WHERE h.id = :id"),
        @NamedQuery(name = "Historiazmian.findByIdWniosek", query = "SELECT h FROM Historiazmian h WHERE h.idWniosek = :idWniosek"),
        @NamedQuery(name = "Historiazmian.findByTypZmiany", query = "SELECT h FROM Historiazmian h WHERE h.typZmiany = :typZmiany"),
        @NamedQuery(name = "Historiazmian.findByOpis", query = "SELECT h FROM Historiazmian h WHERE h.opis = :opis")})
public class Historiazmian implements Serializable {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_wniosek")
    private Integer idWniosek;
    @Column(name = "typ_zmiany")
    private String typZmiany;
    @Column(name = "opis")
    private String opis;

    public Historiazmian() {
    }

    public Historiazmian(Integer id) {
        this.id = id;
    }

    public Historiazmian(int id_wniosku, String stan, String opis) {
        setIdWniosek(id_wniosku);
        setTypZmiany(stan);
        setOpis(opis);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdWniosek() {
        return idWniosek;
    }

    public void setIdWniosek(Integer idWniosek) {
        this.idWniosek = idWniosek;
    }

    public String getTypZmiany() {
        return typZmiany;
    }

    public void setTypZmiany(String typZmiany) {
        this.typZmiany = typZmiany;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historiazmian)) {
            return false;
        }
        Historiazmian other = (Historiazmian) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Historiazmian[ id=" + id + " ]";
    }

    public Historiazmian(int idWniosek, String opis) {

        setOpis(opis);
        setIdWniosek(idWniosek);
    }

}
