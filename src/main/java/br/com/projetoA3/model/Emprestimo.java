package br.com.projetoA3.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import br.com.projetoA3.dto.EmprestimoRequest;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "emprestimo")
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "parcelas")
    private Integer parcelas;

    @Column(name = "valorParcelas")
    private BigDecimal valorParcelas;

    @Column(name = "valorFinalEmprestimo")
    private BigDecimal valorFinalEmprestimo;

    @Column(name = "tipoMotivo")
    @Enumerated(EnumType.STRING)
    private TipoMotivo tipoMotivo;

    @Column(name = "finalizado")
    private Boolean finalizado;

    @Column(name = "dataCriacao")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    private Conta conta;

    public Emprestimo(EmprestimoRequest emprestimoRequest) {
        this.valor = emprestimoRequest.getValor();
        this.parcelas = emprestimoRequest.getParcelas();
        this.finalizado = false;
        this.dataCriacao = LocalDateTime.now();
    }
}
