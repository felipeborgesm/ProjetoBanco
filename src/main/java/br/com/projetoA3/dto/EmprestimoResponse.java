package br.com.projetoA3.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import br.com.projetoA3.model.Emprestimo;
import br.com.projetoA3.model.TipoMotivo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmprestimoResponse {
    private TipoMotivo tipoMotivo;
    private Integer parcelas;
    private BigDecimal valorParcelas;
    private BigDecimal valorFinalEmprestimo;
    private Boolean finalizado;
    private String dataCriacao;
    private GetTransacaoContaResponse infoConta;

    public EmprestimoResponse(Emprestimo emprestimo) {
        this.tipoMotivo = emprestimo.getTipoMotivo();
        this.parcelas = emprestimo.getParcelas();
        this.valorParcelas = emprestimo.getValorParcelas();
        this.valorFinalEmprestimo = emprestimo.getValorFinalEmprestimo();
        this.finalizado = emprestimo.getFinalizado();
        this.dataCriacao = formatarData(emprestimo.getDataCriacao());
        this.infoConta = emprestimo.getConta().getContaReduced(emprestimo.getConta());
    }

    private String formatarData(LocalDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return data.format(formatter);
    }
}
