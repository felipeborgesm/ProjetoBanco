package br.com.projetoA3.controller;

import br.com.projetoA3.dto.UsuarioRequest;
import br.com.projetoA3.dto.CreateUsuarioResponse;
import br.com.projetoA3.dto.UsuarioResponse;
import br.com.projetoA3.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
  @Autowired UsuarioService usuarioService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CreateUsuarioResponse create(@RequestBody UsuarioRequest usuarioRequest) {
    return usuarioService.create(usuarioRequest);
  }

  @GetMapping("/{id}")
  public UsuarioResponse getById(@PathVariable Long id) {
    return usuarioService.getById(id);
  }
}
