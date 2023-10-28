package br.com.projetoA3.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.projetoA3.dto.TransacaoResponse;
import br.com.projetoA3.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    List<TransacaoResponse> getAllByContaId(Long Id);
}
