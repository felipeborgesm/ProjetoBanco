package br.com.projetoA3.service.impl;

import br.com.projetoA3.dto.ContaRequest;
import br.com.projetoA3.dto.ContaResponse;
import br.com.projetoA3.dto.CreateContaResponse;
import br.com.projetoA3.dto.TransacaoRequest;
import br.com.projetoA3.model.Conta;
import br.com.projetoA3.repository.ContaRepository;
import br.com.projetoA3.repository.UsuarioRepository;
import br.com.projetoA3.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ContaServiceImpl implements ContaService {
  @Autowired
  ContaRepository contaRepository;
  @Autowired
  UsuarioRepository usuarioRepository;

  @Override
  public CreateContaResponse create(ContaRequest contaRequest, Long id) {
    var usuario = usuarioRepository.getById(id);
    Conta conta = new Conta(contaRequest);
    conta.setUsuario(usuario);
    contaRepository.save(conta);
    return new CreateContaResponse(conta);
  }

  @Override
  public ContaResponse getById(Long id) {
    Conta conta = contaRepository.findById(id).orElseThrow();
    return new ContaResponse(conta);
  }

  @Override
  public List<Conta> getAll(String senha) {
    if (!Objects.equals(senha, "admin123")) {
      throw new RuntimeException("Senha errada");
    }
    return contaRepository.findAll();
  }

  @Override
  public ContaResponse update(String senha, ContaRequest contaRequest, Long id) {
    if (!Objects.equals(senha, "admin123")) {
      throw new RuntimeException("Senha errada");
    }

    var conta = contaRepository.findById(id).orElseThrow();

    conta.setNumero(contaRequest.getNumero());
    conta.setAgencia(conta.getAgencia());
    conta.setTipoConta(contaRequest.getTipoConta());
    conta.setDataAtualizacao(LocalDateTime.now());

    contaRepository.save(conta);

    return new ContaResponse(conta);
  }

  @Override
  public void createTransacao(Long id, TransacaoRequest transacaoRequest) {
    var contaOrigem = contaRepository.findById(id).orElseThrow();

    if (contaOrigem.getSaldo().compareTo(transacaoRequest.getValor()) < 0) {
      throw new RuntimeException("Saldo insuficiente");
    }
    
    var contaDestinada = contaRepository.findByNumeroAndAgencia(transacaoRequest.getNumero(), transacaoRequest.getAgencia());
    if (contaDestinada == null) { 
      throw new RuntimeException("Conta não encontrada");
    }

    var saldoParaTransferir = transacaoRequest.getValor();

    contaDestinada.setSaldo(saldoParaTransferir);

    contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(saldoParaTransferir));

  }
}
