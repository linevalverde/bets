package br.com.zup.orange.bets.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;
import java.util.ArrayList;
import java.util.Random;

import br.com.zup.orange.bets.models.*;
import br.com.zup.orange.bets.models.pojo.BetterPojo;
import br.com.zup.orange.bets.repositories.BetterRepository;

@RestController
@RequestMapping(path = "/better")
public class BetterResource {
    
    private BetterRepository betterRepository;

    public BetterResource(BetterRepository betterRepository) {
        super();
        this.betterRepository = betterRepository;
    }

    @PostMapping(path = "/makeBet")
    public ResponseEntity<Bet> makeBet(@RequestBody BetterPojo better) {
        
        Better actualBetter = betterRepository.findByEmailIgnoreCase(better.getEmail());
        Better updatableBetter = null;
        if (actualBetter != null) {
            updatableBetter = actualBetter;
        } else {
            Better newBetter = new Better(better.getEmail());
            updatableBetter = newBetter;
        }
        Set<Bet> bets = updatableBetter.getBets();
        Bet newBet = new Bet(generateBetNumbers(), updatableBetter);
        bets.add(newBet);
        updatableBetter.setBets(bets);
        betterRepository.save(updatableBetter);

        return new ResponseEntity<>(newBet, HttpStatus.CREATED);
    }

    @GetMapping(path = "/allbets")
    public ResponseEntity<Better> allBets(@RequestParam(value = "email") String email) {
        Better actualBetter = betterRepository.findByEmailIgnoreCase(email);
        if (actualBetter != null) {
            return new ResponseEntity<>(actualBetter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(actualBetter, HttpStatus.NOT_FOUND);
        }
        
    }


    private String generateBetNumbers() {
        ArrayList<Integer> bets = new ArrayList<Integer>(); 
        Random randonNumber = new Random();
        while(bets.size() < 6) {
            int number = randonNumber.nextInt(60);
            if (!bets.contains(number)) {
                bets.add(number);
            }
        }
        String listString = bets.toString().replace("[", "").replace("]", "");

        return listString;

    }

}
