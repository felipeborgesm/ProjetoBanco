package br.com.projetoA3.controller;

import br.com.projetoA3.dto.ContaRequest;
import br.com.projetoA3.dto.ContaResponse;
import br.com.projetoA3.dto.CreateContaResponse;
import br.com.projetoA3.dto.CreateEmprestimoResponse;
import br.com.projetoA3.dto.CreateTransacaoResponse;
import br.com.projetoA3.dto.EmprestimoRequest;
import br.com.projetoA3.dto.EmprestimoResponse;
import br.com.projetoA3.dto.TransacaoRequest;
import br.com.projetoA3.service.ContaService;
import br.com.projetoA3.service.EmprestimoService;
import br.com.projetoA3.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import br.com.projetoA3.dto.TransacaoResponse;
import br.com.projetoA3.model.TipoConta;
import br.com.projetoA3.model.TipoMotivo;
import br.com.projetoA3.model.TipoTransacao;
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

  @Autowired
  EmprestimoService emprestimoService;

  @PostMapping("/{id}/tipoConta/{tipoConta}")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Cria uma conta de acordo o Id do usuário", description = "Retorna o Id da conta criada")
  public CreateContaResponse create(@RequestBody ContaRequest contaRequest, @PathVariable Long id,
      @PathVariable TipoConta tipoConta) {
    return contaService.create(contaRequest, id, tipoConta);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Retorna os dados da conta de acordo o Id", description = "Retorna os dados da conta")
  public ContaResponse getById(@PathVariable Long id) {
    return contaService.getById(id);
  }

  @PostMapping("/transferir/{id}/tipoTransacao/{tipoTransacao}")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Cria uma transação entre duas contas", description = "Retorna o Id da transação criada")
  public CreateTransacaoResponse createTransacao(@PathVariable Long id, @PathVariable TipoTransacao tipoTransacao,
      @RequestBody TransacaoRequest transacaoRequest) {
    return transacaoService.createTransacao(id, tipoTransacao, transacaoRequest);
  }

  @PostMapping("/depositar/tipoTransacao/{tipoTransacao}")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Cria depósito para a conta", description = "Retorna o Id do depósito criado")
  public CreateTransacaoResponse createDeposito(@PathVariable TipoTransacao tipoTransacao,
      @RequestBody TransacaoRequest transacaoRequest) {
    return transacaoService.createDeposito(transacaoRequest, tipoTransacao);
  }

  @GetMapping("/transacao/{id}")
  @Operation(summary = "Retorna os dados de todas as transações/depósitos de acordo o Id da conta", description = "Retorna os dados de transações/depósitos")
  public List<TransacaoResponse> getAllTransacoesByConta(@PathVariable Long id) {
    return transacaoService.getAllByConta(id);
  }

  @PostMapping("/emprestimo/{id}/tipoMotivo/{tipoMotivo}")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Cria um emprestimo de acordo o Id da conta", description = "Retorna o Id da conta criada")
  public CreateEmprestimoResponse createEmprestimo(@PathVariable Long id, @PathVariable TipoMotivo tipoMotivo,
      @RequestBody EmprestimoRequest emprestimoRequest) {
    return emprestimoService.createEmprestimo(id, tipoMotivo, emprestimoRequest);
  }

  @GetMapping("/emprestimo/{id}")
  @Operation(summary = "Retorna os empréstimos de acordo o Id da conta", description = "Retorna os dados de empréstimo")
  public List<EmprestimoResponse> getAllEmprestimoByConta(@PathVariable Long id) {
    return emprestimoService.getAllByConta(id);
  }
}