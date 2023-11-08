package br.com.projetoA3.controller;

import br.com.projetoA3.dto.UsuarioRequest;
import br.com.projetoA3.dto.TextoResponse;
import br.com.projetoA3.dto.UsuarioResetSenhaRequest;
import br.com.projetoA3.dto.CreateUsuarioResponse;
import br.com.projetoA3.dto.UsuarioResponse;
import br.com.projetoA3.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
  @Autowired
  UsuarioService usuarioService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Cria um usuário", description = "Retorna o Id do usuário criado")
  public CreateUsuarioResponse create(@RequestBody UsuarioRequest usuarioRequest) {
    return usuarioService.create(usuarioRequest);
  }

  @PutMapping("/atualizar/{id}")
  @Operation(summary = "Atualiza os dados do usuário de acordo o Id", description = "Retorna os dados atualizados")
  public UsuarioResponse update(@PathVariable Long id, @RequestBody UsuarioRequest usuarioRequest) {
    return usuarioService.update(usuarioRequest, id);
  }
  
  @GetMapping("/{id}")
  @Operation(summary = "Retorna os dados do usuário de acordo o Id", description = "Retorna os dados do usuário")
  public UsuarioResponse getById(@PathVariable Long id) {
    return usuarioService.getById(id);
  }

  @PutMapping("/token-senha/{email}")
  @Operation(summary = "Envia um e-mail com o token", description = "Retorna mensagem de sucesso")
  public TextoResponse findByEmail(@PathVariable String email) {
    return usuarioService.findByEmail(email);
  }

  @PutMapping("/trocar-senha")
  @Operation(summary = "Atualiza a senha de acordo com o token", description = "Retorna mensagem de sucesso")
  public TextoResponse updateSenha(@RequestBody UsuarioResetSenhaRequest usuarioResetSenhaRequest) {
    return usuarioService.updateSenha(usuarioResetSenhaRequest);
  }

}
