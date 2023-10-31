package br.com.projetoA3.model;

import br.com.projetoA3.dto.ContaRequest;
import br.com.projetoA3.dto.GetTransacaoContaResponse;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "agencia")
    private Integer agencia;

    @Column(name = "tipoConta")
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "dataCriacao")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @Column(name = "dataAtualizacao")
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.AUTO)
    private List<Transacao> transacao;

    public Conta(ContaRequest contaRequest) {
        this.numero = contaRequest.getNumero();
        this.agencia = contaRequest.getAgencia();
        this.tipoConta = contaRequest.getTipoConta();
        this.saldo = contaRequest.getSaldo();
    }

    public GetTransacaoContaResponse getContaReduced(Conta conta) {
        return new GetTransacaoContaResponse(conta.getNumero(), conta.getAgencia());
    }
}
