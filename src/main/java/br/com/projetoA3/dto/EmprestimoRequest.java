package br.com.projetoA3.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class EmprestimoRequest {
    private BigDecimal valor;
    private Integer parcelas;
}
