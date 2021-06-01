package com.formula.racing.Formula.One.Racing.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

//@Data
@Entity
public class Person extends AbstractEntity<Long>{

    public  static  final String TBL_NAME = "Person";
    @NotNull
    @Size(min = 2, max = 50)
    private String first_name;
    @NotNull
    @Size(min = 2, max = 50)
    private String last_name;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;


    public Person(Long aLong) {
        super(aLong);
    }

    public Person() {

    }

    public Person(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Person(String first_name, String last_name, Profile profile) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.profile = profile;
    }

    public Person(String first_name, String last_name, Profile profile, Team team) {
        super(null);
        this.first_name = first_name;
        this.last_name = last_name;
        this.profile = profile;
        this.team = team;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return first_name.equals(person.first_name) && last_name.equals(person.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), first_name, last_name);
    }

}
