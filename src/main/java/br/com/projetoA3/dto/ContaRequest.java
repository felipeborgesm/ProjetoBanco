package br.com.projetoA3.dto;

import br.com.projetoA3.model.TipoConta;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ContaRequest {
    private TipoConta tipoConta;
    private BigDecimal saldo;
}
