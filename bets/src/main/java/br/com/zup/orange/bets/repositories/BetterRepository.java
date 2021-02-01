package br.com.zup.orange.bets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.orange.bets.models.Better;


@Repository
public interface BetterRepository extends JpaRepository<Better, Long> {
    Better findByEmailIgnoreCase(String email);
}
