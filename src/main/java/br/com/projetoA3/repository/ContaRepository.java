package br.com.projetoA3.repository;

import br.com.projetoA3.dto.ContaResponse;
import br.com.projetoA3.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Conta findByNumeroAndAgencia(Integer numero, Integer agencia);
}
