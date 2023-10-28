package br.com.projetoA3.controller;

import br.com.projetoA3.dto.ContaRequest;
import br.com.projetoA3.dto.ContaResponse;
import br.com.projetoA3.dto.CreateContaResponse;
import br.com.projetoA3.dto.TransacaoRequest;
import br.com.projetoA3.service.ContaService;
import br.com.projetoA3.service.TransacaoService;
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
  public void createTransacao(@PathVariable Long id, @RequestBody TransacaoRequest transacaoRequest) {
    transacaoService.create(id, transacaoRequest);
  }

  @GetMapping("/transacao/{id}")
  public List<TransacaoResponse> getAllTransacoes(@PathVariable Long id) {
    return transacaoService.getAllByConta(id);
  }
}
