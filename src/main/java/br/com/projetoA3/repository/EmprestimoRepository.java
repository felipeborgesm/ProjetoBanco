package br.com.projetoA3.repository;

import java.util.List;
import br.com.projetoA3.dto.EmprestimoResponse;
import br.com.projetoA3.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<EmprestimoResponse> getAllByContaId(Long Id);
}
