package br.com.projetoA3.service;

import br.com.projetoA3.dto.*;

public interface ContaService {
  CreateContaResponse create(ContaRequest contaRequest, Long id);

  ContaResponse getById(Long id);

}
