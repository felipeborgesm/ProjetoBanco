package br.com.projetoA3.service;

import br.com.projetoA3.dto.*;
import br.com.projetoA3.model.TipoConta;

public interface ContaService {
  CreateContaResponse create(ContaRequest contaRequest, Long id, TipoConta tipoConta);

  ContaResponse getById(Long id);

}
