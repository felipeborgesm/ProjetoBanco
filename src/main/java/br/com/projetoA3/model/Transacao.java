package br.com.projetoA3.model;

import br.com.projetoA3.dto.TransacaoRequest;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "transacao")
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "tipoTransacao")
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "agencia")
    private Integer agencia;

    @Column(name = "dataCriacao")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    private Conta conta;

    public Transacao(TransacaoRequest transacaoRequest) {
        this.valor = transacaoRequest.getValor();
        this.numero = transacaoRequest.getNumero();
        this.agencia = transacaoRequest.getAgencia();
    }
}
