package com.adcc.springboothibernate;

import javax.persistence.*;

/**
 * Created by Liu.DA on 2019/9/11
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity

public class Player {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "player_Sequence")
    @SequenceGenerator(name = "player_Sequence", sequenceName = "PLAYER_SEQ")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "num")
    private int num;

    @Column(name = "position")
    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)

    private Team team;
    public Player() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Long getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public Team getTeam() {
        return team;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    // getters/setters
}
