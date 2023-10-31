package br.com.projetoA3.service;

import java.util.List;

import br.com.projetoA3.dto.CreateTransacaoResponse;
import br.com.projetoA3.dto.TransacaoRequest;
import br.com.projetoA3.dto.TransacaoResponse;
import br.com.projetoA3.model.Transacao;

public interface TransacaoService {
    CreateTransacaoResponse createTransacao(Long id, TransacaoRequest transacaoRequest);

    CreateTransacaoResponse createDeposito(Long id, TransacaoRequest transacaoRequest);

    List<TransacaoResponse> getAllByConta(Long id);

    List<Transacao> getAll();
}
