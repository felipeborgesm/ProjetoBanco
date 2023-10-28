package br.com.projetoA3.controller;

import br.com.projetoA3.dto.ContaRequest;
import br.com.projetoA3.dto.ContaResponse;
import br.com.projetoA3.dto.UsuarioRequest;
import br.com.projetoA3.dto.UsuarioResponse;
import br.com.projetoA3.model.Conta;
import br.com.projetoA3.service.ContaService;
import br.com.projetoA3.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/gerente")
public class GerenteController {
  @Autowired ContaService contaService;

  @Autowired UsuarioService usuarioService;

  @GetMapping
  public List<Conta> getAll(String senha) {
    return contaService.getAll(senha);
  }

  @DeleteMapping("/{id}")
  public void delete(String senha, @PathVariable Long id) {
    usuarioService.delete(senha, id);
  }

  @PutMapping("/update/user/{id}")
  public UsuarioResponse update(String senha, @PathVariable Long id, @RequestBody UsuarioRequest usuarioRequest) {
    return usuarioService.update(senha, usuarioRequest, id);
  }

  @PutMapping("/update/conta/{id}")
  public ContaResponse update(String senha, @PathVariable Long id, @RequestBody ContaRequest contaRequest) {
    return contaService.update(senha, contaRequest, id);
  }
}
