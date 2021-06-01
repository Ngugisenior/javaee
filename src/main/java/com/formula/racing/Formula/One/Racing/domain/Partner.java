package com.formula.racing.Formula.One.Racing.domain;

import javax.persistence.*;
import java.util.HashSet;

import java.util.Set;

@Entity
public class Partner extends AbstractEntity<Long>{
    public  static  final String TBL_NAME = "Partner";
    public static final String PAGE_TITLE ="Partners";
    private String name;

    @ManyToMany(mappedBy = "partner",fetch = FetchType.EAGER)
    private Set<Team> team = new HashSet<>();

    public Partner(String name) {
        this.name = name;
    }

    public Partner() {
    }

    public Partner(Long aLong) {
        super(aLong);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Team> getTeam() {
        return team;
    }

    public void setTeam(Set<Team> t) {
        this.team = t;
    }

    public void addTeam(Team t){
        team.add(t);
        t.getPartner().add(this);
    }
    public void removeTeam(Team t){
        team.remove(t);
        t.getPartner().remove(this);
    }
}
