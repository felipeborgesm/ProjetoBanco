package br.com.projetoA3.model;

import br.com.projetoA3.dto.ContaRequest;
import br.com.projetoA3.dto.GetTransacaoContaResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "conta")
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", unique = true)
    private Integer numero;

    @Column(name = "agencia")
    private Integer agencia;

    @Column(name = "banco")
    private Integer banco;

    @Column(name = "tipoConta", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "dataCriacao")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Transacao> transacoes;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Emprestimo> emprestimos;

    public Conta(ContaRequest contaRequest) {
        this.agencia = 00100;
        this.banco = 1721;
        this.saldo = contaRequest.getSaldo();
    }

    public GetTransacaoContaResponse getContaReduced(Conta conta) {
        return new GetTransacaoContaResponse(conta.getNumero(), conta.getAgencia(),
                conta.getBanco(), conta.getUsuario().getUsuarioReduced(conta.getUsuario()));

    }
}
