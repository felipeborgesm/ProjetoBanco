package br.com.projetoA3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.projetoA3.model.ResetSenhaToken;

@Repository
public interface ResetSenhaTokenRepository extends JpaRepository<ResetSenhaToken, Long> {

    ResetSenhaToken findByToken(String token);
}
