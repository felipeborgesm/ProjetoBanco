package br.com.projetoA3.dto;

import br.com.projetoA3.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioResponse {
    private String nome;
    private String cpf;
    private String email;
    private String dataCriacao;
    private String dataAtualizacao;

    public UsuarioResponse(Usuario usuario) {
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.dataCriacao = formatarData(usuario.getDataCriacao());
        this.dataAtualizacao = formatarData(usuario.getDataAtualizacao());
    }

    private String formatarData(LocalDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return data.format(formatter);
    }
}
