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
    private LocalDateTime dataAtualizacao;

    public UsuarioResponse(Usuario usuario) {
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.dataCriacao = formatarDataCriacao(LocalDateTime.now());
    }

    private String formatarDataCriacao(LocalDateTime dataCriacao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dataCriacao.format(formatter);
    }
}
