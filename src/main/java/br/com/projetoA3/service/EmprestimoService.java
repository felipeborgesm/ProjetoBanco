package br.com.projetoA3.service;

import java.util.List;
import br.com.projetoA3.dto.CreateEmprestimoResponse;
import br.com.projetoA3.dto.EmprestimoRequest;
import br.com.projetoA3.dto.EmprestimoResponse;
import br.com.projetoA3.model.TipoMotivo;

public interface EmprestimoService {
    CreateEmprestimoResponse createEmprestimo(Long id, TipoMotivo tipoMotivo, EmprestimoRequest emprestimoRequest);

    List<EmprestimoResponse> getAllByConta(Long id);
}
