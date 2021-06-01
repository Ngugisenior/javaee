package com.formula.racing.Formula.One.Racing.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Objects;


@MappedSuperclass
public class AbstractEntity<ID extends Serializable> implements Serializable {

    public static final String FLD_ID = "id";
    public static final String FLD_VERSION = "version";

    @Id
    @Column(name = FLD_ID,unique = true, updatable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name = FLD_ID)
    private ID id;

    @Version
    private Integer version;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;


    public AbstractEntity(){}
    public AbstractEntity(ID id){
        this.id = id;
    }


    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return Objects.equals(id, that.id) && version.equals(that.version) && created.equals(that.created) && updated.equals(that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
