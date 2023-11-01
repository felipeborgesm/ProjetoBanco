package br.com.projetoA3.service;

import br.com.projetoA3.dto.*;
import br.com.projetoA3.model.Conta;
import java.util.List;

public interface ContaService {
  CreateContaResponse create(ContaRequest contaRequest, Long id);

  ContaResponse getById(Long id);

  List<Conta> getAll(String senha);

  ContaResponse update(String senha, ContaRequest contaRequest, Long id);

}
