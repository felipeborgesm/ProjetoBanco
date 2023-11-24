package br.com.projetoA3.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projetoA3.dto.CreateEmprestimoResponse;
import br.com.projetoA3.dto.EmprestimoRequest;
import br.com.projetoA3.dto.EmprestimoResponse;
import br.com.projetoA3.model.Emprestimo;
import br.com.projetoA3.model.TipoMotivo;
import br.com.projetoA3.repository.ContaRepository;
import br.com.projetoA3.repository.EmprestimoRepository;
import br.com.projetoA3.repository.TransacaoRepository;
import br.com.projetoA3.service.EmprestimoService;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {

    private static final BigDecimal TAXA_JUROS_EMPRESTIMO = new BigDecimal(0.021);

    @Autowired
    EmprestimoRepository emprestimoRepository;
    @Autowired
    TransacaoRepository transacaoRepository;
    @Autowired
    ContaRepository contaRepository;

    @Override
    public CreateEmprestimoResponse createEmprestimo(Long id, TipoMotivo tipoMotivo,
            EmprestimoRequest emprestimoRequest) {
        var contaOrigem = contaRepository.findById(id).orElseThrow();

        var transacaoConta = transacaoRepository.getAllByContaId(id);
        if (transacaoConta == null || transacaoConta.isEmpty() || transacaoConta.size() < 2) {
            throw new RuntimeException("Faça mais movimentações na conta para o empréstimo ser aprovado.");
        }

        BigDecimal valorParcelas = emprestimoRequest.getValor().divide(new BigDecimal(emprestimoRequest.getParcelas()));
        if (contaOrigem.getSaldo().compareTo(valorParcelas) < 0) {
            throw new RuntimeException("Saldo insuficiente para aprovação do empréstimo.");
        }

        var emprestimoAtivo = temEmprestimoFinalizado(id);
        if (emprestimoAtivo) {
            throw new RuntimeException("Você não pode ter mais de um emprestimo ativo.");
        }

        BigDecimal parcelasComJuros = valorParcelas.add(valorParcelas.multiply(TAXA_JUROS_EMPRESTIMO));

        BigDecimal valorFinalEmprestimo = parcelasComJuros.multiply(new BigDecimal(emprestimoRequest.getParcelas()));

        Emprestimo emprestimo = new Emprestimo(emprestimoRequest);
        emprestimo.setTipoMotivo(tipoMotivo);
        emprestimo.setValorFinalEmprestimo(valorFinalEmprestimo);
        emprestimo.setValorParcelas(parcelasComJuros);
        emprestimo.setConta(contaOrigem);
        emprestimoRepository.save(emprestimo);

        return new CreateEmprestimoResponse(emprestimo);
    }

    @Override
    public List<EmprestimoResponse> getAllByConta(Long id) {
        return emprestimoRepository.getAllByContaId(id);
    }

    private boolean temEmprestimoFinalizado(long id) {
        var emprestimosConta = emprestimoRepository.getAllByContaId(id);

        for (EmprestimoResponse emprestimoResponse : emprestimosConta) {
            if (emprestimoResponse.getFinalizado() == true) {
                return true;
            }
        }
        return false;
    }
}
