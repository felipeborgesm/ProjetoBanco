package br.com.projetoA3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projetoA3.service.TransacaoService;
import br.com.projetoA3.repository.ContaRepository;
import br.com.projetoA3.repository.TransacaoRepository;
import br.com.projetoA3.dto.CreateTransacaoResponse;
import br.com.projetoA3.dto.TransacaoRequest;
import br.com.projetoA3.dto.TransacaoResponse;
import br.com.projetoA3.model.TipoTransacao;
import br.com.projetoA3.model.Transacao;
import java.util.List;
import java.util.Objects;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;
    @Autowired
    ContaRepository contaRepository;

    @Override
    public CreateTransacaoResponse createTransacao(Long id, TipoTransacao tipoTransacao, TransacaoRequest transacaoRequest) {
        var contaOrigem = contaRepository.findById(id).orElseThrow();

        if (contaOrigem.getSaldo().compareTo(transacaoRequest.getValor()) < 0) {
            throw new RuntimeException("Saldo insuficiente.");
        }

        var contaDestinada = contaRepository.findByNumeroAndAgencia(transacaoRequest.getNumero(),
                transacaoRequest.getAgencia());
        if (contaDestinada == null) {
            throw new RuntimeException("Conta não encontrada.");
        }
        if (Objects.equals(contaDestinada.getId(), contaOrigem.getId())) {
            throw new RuntimeException("Não é possível fazer transação para mesma conta dessa forma.");
        }

        var saldoParaTransferir = transacaoRequest.getValor();

        contaDestinada.setSaldo(contaDestinada.getSaldo().add(saldoParaTransferir));
        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(saldoParaTransferir));

        Transacao transacao = new Transacao(transacaoRequest);
        transacao.setConta(contaOrigem);
        transacao.setTipoTransacao(tipoTransacao);
        transacaoRepository.save(transacao);

        return new CreateTransacaoResponse(transacao);
    }

    @Override
    public CreateTransacaoResponse createDeposito(TransacaoRequest transacaoRequest, TipoTransacao tipoTransacao) {
        var contaOrigem = contaRepository.findByNumeroAndAgencia(transacaoRequest.getNumero(),
                transacaoRequest.getAgencia());
        if (contaOrigem == null) {
            throw new RuntimeException("Conta não encontrada.");
        }

        var saldoParaTransferir = transacaoRequest.getValor();

        contaOrigem.setSaldo(contaOrigem.getSaldo().add(saldoParaTransferir));

        Transacao transacao = new Transacao(transacaoRequest);
        transacao.setConta(contaOrigem);
        transacao.setTipoTransacao(tipoTransacao);
        transacaoRepository.save(transacao);

        return new CreateTransacaoResponse(transacao);
    }

    @Override
    public List<TransacaoResponse> getAllByConta(Long id) {
        return transacaoRepository.getAllByContaId(id);
    }

}