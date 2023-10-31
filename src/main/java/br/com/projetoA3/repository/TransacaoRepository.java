package br.com.projetoA3.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projetoA3.dto.TransacaoResponse;
import br.com.projetoA3.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<TransacaoResponse> getAllByContaId(@Param("id") Long Id);

}
