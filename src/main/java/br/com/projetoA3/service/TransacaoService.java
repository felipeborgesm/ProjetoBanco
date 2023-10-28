package br.com.projetoA3.service;

import java.util.List;
import br.com.projetoA3.dto.TransacaoRequest;
import br.com.projetoA3.dto.TransacaoResponse;

public interface TransacaoService {
    void create(Long id, TransacaoRequest transacaoRequest);

    List<TransacaoResponse> getAllByConta(Long id);
}
