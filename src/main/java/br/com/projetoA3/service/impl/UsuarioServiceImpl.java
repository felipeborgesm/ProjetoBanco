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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  UsuarioRepository usuarioRepository;

  @Override
  public CreateUsuarioResponse create(UsuarioRequest usuarioRequest) {
    Usuario usuario = new Usuario(usuarioRequest);

    if (!validarEmail(usuario.getEmail())) {
      throw new RuntimeException("E-mail inválido");
    }

    usuarioRepository.save(usuario);
    return new CreateUsuarioResponse(usuario);
  }

  @Override
  public UsuarioResponse getById(Long id) {
    var usuario = usuarioRepository.findById(id).orElseThrow();
    return new UsuarioResponse(usuario);
  }

  @Override
  public UsuarioResponse update(UsuarioRequest usuarioRequest, Long id) {

    var usuario = usuarioRepository.findById(id).orElseThrow();

    if (!validarEmail(usuario.getEmail())) {
      throw new RuntimeException("E-mail inválido");
    }
    
    usuario.setNome(usuarioRequest.getNome());
    usuario.setCpf(usuarioRequest.getCpf());
    usuario.setEmail(usuarioRequest.getEmail());
    usuario.setDataAtualizacao(LocalDateTime.now());
    usuarioRepository.save(usuario);

    return new UsuarioResponse(usuario);
  }

  private boolean validarEmail(String email) {
    String regex = "^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-z]{2,4}$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);

    return matcher.matches();
  }
}
