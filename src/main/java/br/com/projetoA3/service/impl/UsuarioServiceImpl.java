package br.com.projetoA3.service.impl;

import br.com.projetoA3.dto.UsuarioRequest;
import br.com.projetoA3.dto.CreateUsuarioResponse;
import br.com.projetoA3.dto.UsuarioResponse;
import br.com.projetoA3.model.Usuario;
import br.com.projetoA3.repository.UsuarioRepository;
import br.com.projetoA3.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired UsuarioRepository usuarioRepository;

  @Override
  public CreateUsuarioResponse create(UsuarioRequest usuarioRequest) {
    Usuario usuario = new Usuario(usuarioRequest);
    usuarioRepository.save(usuario);
    return new CreateUsuarioResponse(usuario);
  }

  @Override
  public UsuarioResponse getById(Long id) {
    var usuario = usuarioRepository.findById(id).orElseThrow();
    return new UsuarioResponse(usuario);
  }

  @Override
  public UsuarioResponse update(String senha, UsuarioRequest usuarioRequest, Long id) {
    if (!Objects.equals(senha, "admin123")){
      throw new RuntimeException("Senha errada");
    }

    var usuario = usuarioRepository.findById(id).orElseThrow();

    usuario.setNome(usuarioRequest.getNome());
    usuario.setCpf(usuarioRequest.getCpf());
    usuario.setSenha(usuarioRequest.getSenha());
    usuario.setDataAtualizacao(LocalDateTime.now());

    usuarioRepository.save(usuario);

    return new UsuarioResponse(usuario);
  }

  @Override
  public void delete(String senha, Long id) {
    if (!Objects.equals(senha, "admin123")){
      throw new RuntimeException("Senha errada");
    }

    var usuario = usuarioRepository.findById(id).orElseThrow();
    usuarioRepository.delete(usuario);
  }
}
