package br.com.projetoA3.service;

import br.com.projetoA3.dto.CreateTransacaoResponse;
import br.com.projetoA3.dto.TransacaoRequest;
import br.com.projetoA3.dto.TransacaoResponse;
import java.util.List;

public interface TransacaoService {
    CreateTransacaoResponse createTransacao(Long id, TransacaoRequest transacaoRequest);

    CreateTransacaoResponse createDeposito(TransacaoRequest transacaoRequest);

    List<TransacaoResponse> getAllByConta(Long id);

}
