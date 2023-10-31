package br.com.projetoA3.controller;

import br.com.projetoA3.dto.ContaRequest;
import br.com.projetoA3.dto.ContaResponse;
import br.com.projetoA3.dto.CreateContaResponse;
import br.com.projetoA3.dto.CreateTransacaoResponse;
import br.com.projetoA3.dto.TransacaoRequest;
import br.com.projetoA3.service.ContaService;
import br.com.projetoA3.service.TransacaoService;
import br.com.projetoA3.dto.TransacaoResponse;
import br.com.projetoA3.model.Transacao;

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
  public CreateContaResponse create(@RequestBody ContaRequest contaRequest, @PathVariable Long id) {
    return contaService.create(contaRequest, id);
  }

  @GetMapping("/{id}")
  public ContaResponse getById(@PathVariable Long id) {
    return contaService.getById(id);
  }

  @PostMapping("/transacao/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public CreateTransacaoResponse createTransacao(@PathVariable Long id, @RequestBody TransacaoRequest transacaoRequest) {
    return transacaoService.createTransacao(id, transacaoRequest);
  }

    @PostMapping("/depositar/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public CreateTransacaoResponse createDeposito(@PathVariable Long id, @RequestBody TransacaoRequest transacaoRequest) {
    return transacaoService.createDeposito(id, transacaoRequest);
  }


  @GetMapping("/transacao/{id}")
  public List<TransacaoResponse> getAllTransacoesByConta(@PathVariable Long id) {
    return transacaoService.getAllByConta(id);
  }

  @GetMapping("/transacao")
  public List<Transacao> getAllTransacoes() {
    return transacaoService.getAll();
  }
}
