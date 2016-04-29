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
        @NamedQuery(name = "Wniosek.findAll", query = "SELECT w FROM Wniosek w"),
        @NamedQuery(name = "Wniosek.findById", query = "SELECT w FROM Wniosek w WHERE w.id = :id"),
        @NamedQuery(name = "Wniosek.findByStan", query = "SELECT w FROM Wniosek w WHERE w.stan = :stan"),
        @NamedQuery(name = "Wniosek.findByNazwa", query = "SELECT w FROM Wniosek w WHERE w.nazwa = :nazwa"),
        @NamedQuery(name = "Wniosek.findByTresc", query = "SELECT w FROM Wniosek w WHERE w.tresc = :tresc"),
        @NamedQuery(name = "Wniosek.findByNumer", query = "SELECT w FROM Wniosek w WHERE w.numer = :numer"),
        @NamedQuery(name = "Wniosek.findByInfo", query = "SELECT w FROM Wniosek w WHERE w.info = :info"),
        @NamedQuery(name = "Wniosek.findByNameAndStan", query = "SELECT w FROM Wniosek w WHERE w.stan = :stan and w.nazwa = :nazwa")
})
public class Wniosek implements Serializable {


    @Id
    @GeneratedValue
    private Integer id;
    @Basic(optional = false)
    @Column(name = "stan")
    private int stan;
    @Basic(optional = false)
    @Column(name = "nazwa")
    private String nazwa;
    @Column(name = "tresc")
    private String tresc;
    @Column(name = "numer")
    private Integer numer;
    @Column(name = "info")
    private String info;

    public Wniosek() {
    }

    public Wniosek(Integer id) {
        this.id = id;
    }

    public Wniosek(int stan, String nazwa,String tresc) {
        this.stan = stan;
        this.nazwa = nazwa;
        this.tresc = tresc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStan() {
        return stan;
    }

    public void setStan(int stan) {
        this.stan = stan;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Integer getNumer() {
        return numer;
    }

    public void setNumer(Integer numer) {
        this.numer = numer;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
        if (!(object instanceof Wniosek)) {
            return false;
        }
        Wniosek other = (Wniosek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Wniosek[ id=" + id + " ]";
    }

}
