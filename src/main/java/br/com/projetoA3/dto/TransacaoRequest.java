package br.com.projetoA3.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoRequest {
    private Integer numero;
    private Integer agencia;
    private BigDecimal valor;
}
