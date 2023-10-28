package br.com.projetoA3.service.impl;

import br.com.projetoA3.service.TransacaoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.projetoA3.repository.ContaRepository;
import br.com.projetoA3.repository.TransacaoRepository;
import br.com.projetoA3.dto.TransacaoRequest;
import br.com.projetoA3.dto.TransacaoResponse;
import br.com.projetoA3.model.Transacao;

public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;
    ContaRepository contaRepository;

    @Override
    public void create(Long id, TransacaoRequest transacaoRequest) {
        var contaOrigem = contaRepository.findById(id).orElseThrow();

        if (contaOrigem.getSaldo().compareTo(transacaoRequest.getValor()) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        var contaDestinada = contaRepository.findByNumeroAndAgencia(transacaoRequest.getNumero(),
                transacaoRequest.getAgencia());
        if (contaDestinada == null) {
            throw new RuntimeException("Conta não encontrada");
        }

        var saldoParaTransferir = transacaoRequest.getValor();

        contaDestinada.setSaldo(saldoParaTransferir);

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(saldoParaTransferir));

        Transacao transacao = new Transacao(transacaoRequest);
        transacaoRepository.save(transacao);
    }

    @Override
    public List<TransacaoResponse> getAllByConta(Long id) {
        return transacaoRepository.getAllByContaId(id);
    }

}