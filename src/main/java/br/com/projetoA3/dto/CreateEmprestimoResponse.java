package br.com.projetoA3.dto;

import br.com.projetoA3.model.Emprestimo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateEmprestimoResponse {
    private Long id;

    public CreateEmprestimoResponse(Emprestimo emprestimo) {
        this.id = emprestimo.getId();
    }
}
