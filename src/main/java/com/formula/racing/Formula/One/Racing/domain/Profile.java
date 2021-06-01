package com.formula.racing.Formula.One.Racing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;


@Entity
public class Profile extends AbstractEntity<Long>{
    public  static  final String TBL_NAME = "Profile";

    private int car_number;
    private String job_type;
    private  int points = 0;
    private int grand_prix_entered;
    private int podiums;
    private int world_championships;
    private String nationality;

    @OneToOne(mappedBy = "profile")
    private Person person;


    public Profile(Long aLong) {
        super(aLong);
    }

    public Profile() {
    }

    public Profile(int car_number, String job_type, int points, int grand_prix_entered, int podiums, int world_championships, String nationality) {
        super(null);
        this.car_number = car_number;
        this.job_type = job_type;
        this.points = points;
        this.grand_prix_entered = grand_prix_entered;
        this.podiums = podiums;
        this.world_championships = world_championships;
        this.nationality = nationality;
    }

    public int getCar_number() {
        return car_number;
    }

    public void setCar_number(int car_number) {
        this.car_number = car_number;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGrand_prix_entered() {
        return grand_prix_entered;
    }

    public void setGrand_prix_entered(int grand_prix_entered) {
        this.grand_prix_entered = grand_prix_entered;
    }

    public int getPodiums() {
        return podiums;
    }

    public void setPodiums(int podiums) {
        this.podiums = podiums;
    }

    public int getWorld_championships() {
        return world_championships;
    }

    public void setWorld_championships(int world_championships) {
        this.world_championships = world_championships;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;
        if (!super.equals(o)) return false;
        Profile profile = (Profile) o;
        return car_number == profile.car_number && points == profile.points && grand_prix_entered == profile.grand_prix_entered && podiums == profile.podiums && world_championships == profile.world_championships && job_type.equals(profile.job_type) && nationality.equals(profile.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), car_number, job_type, points, grand_prix_entered, podiums, world_championships, nationality);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "car_number=" + car_number +
                ", job_type='" + job_type + '\'' +
                ", points=" + points +
                ", grand_prix_entered=" + grand_prix_entered +
                ", podiums=" + podiums +
                ", world_championships=" + world_championships +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
