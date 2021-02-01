package br.com.zup.orange.bets.models;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class Bet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "created")
    private Date created;

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "better_id", nullable = false)
    private Better better;

    public Bet() {
    }

    public Bet(String number, Better better) {
        java.util.Date utilDate = new java.util.Date();
        this.created    = new Date(utilDate.getTime());
        this.number     = number;
        this.better     = better;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String betNumber) {
        this.number = betNumber;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date betCreated) {
        this.created = betCreated;
    }      

}
