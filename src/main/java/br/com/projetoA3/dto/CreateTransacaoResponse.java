package br.com.projetoA3.dto;

import br.com.projetoA3.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateTransacaoResponse {
    private Long id;

    public CreateTransacaoResponse(Transacao transacao) {
        this.id = transacao.getId();
    }
}
