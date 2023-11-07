package br.com.projetoA3.controller;

import br.com.projetoA3.dto.ContaRequest;
import br.com.projetoA3.dto.ContaResponse;
import br.com.projetoA3.dto.CreateContaResponse;
import br.com.projetoA3.dto.CreateTransacaoResponse;
import br.com.projetoA3.dto.TransacaoRequest;
import br.com.projetoA3.service.ContaService;
import br.com.projetoA3.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import br.com.projetoA3.dto.TransacaoResponse;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/conta")
public class ContaController {
  @Autowired
  ContaService contaService;

  @Autowired
  TransacaoService transacaoService;

  @PostMapping("/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Cria uma conta de acordo o Id do usuário", description = "Retorna o Id da conta criada")
  public CreateContaResponse create(@RequestBody ContaRequest contaRequest, @PathVariable Long id) {
    return contaService.create(contaRequest, id);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Retorna os dados da conta de acordo o Id", description = "Retorna os dados da conta")
  public ContaResponse getById(@PathVariable Long id) {
    return contaService.getById(id);
  }

  @PostMapping("/transacao/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Cria uma transação entre duas contas", description = "Retorna o Id da transação criada")
  public CreateTransacaoResponse createTransacao(@PathVariable Long id, @RequestBody TransacaoRequest transacaoRequest) {
    return transacaoService.createTransacao(id, transacaoRequest);
  }

  @PostMapping("/depositar")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Cria depósito para a conta", description = "Retorna o Id do depósito criado")
  public CreateTransacaoResponse createDeposito(@RequestBody TransacaoRequest transacaoRequest) {
    return transacaoService.createDeposito(transacaoRequest);
  }

  @GetMapping("/transacao/{id}")
  @Operation(summary = "Retorna os dados de todas as transações/depósitos de acordo o Id da conta", description = "Retorna os dados de transações/depósitos")
  public List<TransacaoResponse> getAllTransacoesByConta(@PathVariable Long id) {
    return transacaoService.getAllByConta(id);
  }

}