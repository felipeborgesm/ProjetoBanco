package br.com.projetoA3.repository;

import br.com.projetoA3.dto.TransacaoResponse;
import br.com.projetoA3.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<TransacaoResponse> getAllByContaId(Long Id);

}
