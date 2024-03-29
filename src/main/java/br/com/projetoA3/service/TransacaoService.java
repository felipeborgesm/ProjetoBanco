package br.com.projetoA3.service;

import br.com.projetoA3.dto.CreateTransacaoResponse;
import br.com.projetoA3.dto.TransacaoRequest;
import br.com.projetoA3.dto.TransacaoResponse;
import br.com.projetoA3.model.TipoTransacao;

import java.util.List;

public interface TransacaoService {
    CreateTransacaoResponse createTransacao(Long id, TipoTransacao tipoTransacao, TransacaoRequest transacaoRequest);

    CreateTransacaoResponse createDeposito(TransacaoRequest transacaoRequest, TipoTransacao tipoTransacao);

    List<TransacaoResponse> getAllByConta(Long id);

}
