package br.com.projetoA3.model;

import br.com.projetoA3.dto.UsuarioRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "usuario")
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "nome")
  private String nome;

  @Column(name = "cpf", nullable = false, unique = true)
  private String cpf;

  @Column(name = "senha")
  private String senha;

  @Column(name = "dataCriacao")
  @CreatedDate
  private LocalDateTime dataCriacao;

  @Column(name = "dataAtualizacao")
  @LastModifiedDate
  private LocalDateTime dataAtualizacao;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Conta> contas;

  public Usuario(UsuarioRequest usuarioRequest) {
    this.cpf = usuarioRequest.getCpf();
    this.nome = usuarioRequest.getNome();
    this.senha = usuarioRequest.getSenha();
  }
}
