package br.com.zup.orange.bets.models;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Better {

    public Better(String email) {
        this.email = email;
        this.bets = new HashSet<Bet>();
    }

    public Better() {

    }    

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "better", cascade = CascadeType.ALL)
    private Set<Bet> bets;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Set<Bet> getBets() {
        return bets;
    }

    public void setBets(Set<Bet> bets) {
        this.bets = bets;
    }

}
