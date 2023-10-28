package br.com.projetoA3.dto;

import br.com.projetoA3.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUsuarioResponse {
    private Long id;

    public CreateUsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
    }
}
