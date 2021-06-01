package com.formula.racing.Formula.One.Racing.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.*;


//@Data
@Entity
public class Team extends AbstractEntity<Long> {
    public  static  final String TBL_NAME = "Team";
    private String team_name;
    private String power_unit;
    private int world_championships;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Person> personSet = new HashSet<Person>();

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "partner"
//            //joinColumns = @JoinColumn(name = "team_id",referencedColumnName = "id"),
//            //inverseJoinColumns = @JoinColumn(name = "partner_id", referencedColumnName = "id")
//    )
    private Set<Partner> partner = new HashSet<>();

    public Team(Long aLong) {
        super(aLong);
    }

    public Team(String team_name, String power_unit, int world_championships) {
        this.team_name = team_name;
        this.power_unit = power_unit;
        this.world_championships = world_championships;
    }

    public Team(String team_name, String power_unit, int world_championships, Set<Partner> partner) {
        this.team_name = team_name;
        this.power_unit = power_unit;
        this.world_championships = world_championships;
        this.partner = partner;
    }

    public Team() {

    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_id) {
        this.team_name = team_id;
    }

    public String getPower_unit() {
        return power_unit;
    }

    public void setPower_unit(String power_unit) {
        this.power_unit = power_unit;
    }

    public int getWorld_championships() {
        return world_championships;
    }

    public void setWorld_championships(int world_championships) {
        this.world_championships = world_championships;
    }

    public Set<Person> getPersonSet() {
        return personSet;
    }

    public void setPersonSet(Set<Person> personSet) {
        this.personSet = personSet;
    }

    public Set<Partner> getPartner() {
        return partner;
    }

    public void setPartner(Set<Partner> p) {
       this.partner = p;
    }

    public void addPartner(Partner p){
        partner.add(p);
        p.getTeam().add(this);
    }

    public void removePartner(Partner p){
        this.partner.remove(p);
        p.getTeam().remove(this);
    }

    public void removePerson(Person p){
        this.personSet.remove(p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        if (!super.equals(o)) return false;
        Team team = (Team) o;
        return world_championships == team.world_championships && team_name.equals(team.team_name) && power_unit.equals(team.power_unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), team_name, power_unit, world_championships);
    }
}
